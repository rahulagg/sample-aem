package com.avivatest.demo.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.commons.osgi.PropertiesUtil;

import com.avivatest.demo.service.GetWeatherInfoService;

@Component(immediate = true,metatype=true,label = "Weather Info API Details", description = "Get Configurations"
)
@Service(GetWeatherInfoService.class)
@Properties({
	@Property(name = "apiUrl", label = "Root Path",value = "http://api.apixu.com/v1/current.json"),
	@Property(name = "apikey", label = "Root Path",value = "e8cb3de6f5a24429916155207170506"),
	@Property(name = "username", label = "Root Path",value = "admin"),
	@Property(name = "password", label = "Root Path",value = "admin")
	})
public class GetWeatherInfoServiceImpl implements GetWeatherInfoService {
	
	private String apiUrl; 
	private String apiKey;
	private String username;
	private String password;
	private static final String APPLICATION_JSON ="application/json";
	
	@Activate
    public void activate(Map<String, Object> properties) {
		this.apiUrl = PropertiesUtil.toString(properties.get("rootPath"), "http://api.apixu.com/v1/current.json");
		this.apiKey = PropertiesUtil.toString(properties.get("apiKey"), "e8cb3de6f5a24429916155207170506");
		this.username = PropertiesUtil.toString(properties.get("username"), "");
		this.password = PropertiesUtil.toString(properties.get("passwrod"), "");
    }

	@Override
	public String getWeatherInfo(String city) {
		try
        {
			HttpClient client = HttpClientBuilder.create().build();
              
            HttpGet getRequest = new HttpGet(apiUrl + "?key=" + apiKey + "&q=" + city);
            getRequest.addHeader("accept", APPLICATION_JSON);
 
            HttpResponse response = client.execute(getRequest);
 
            if (response.getStatusLine().getStatusCode() != 200) {
                            throw new RuntimeException("Failed : HTTP error code : "
                                    + response.getStatusLine().getStatusCode());
                        }
            
          
            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
            
            String output;
             String outputJson="" ;
                while ((output = br.readLine()) != null) {
                    //System.out.println(output);
                	outputJson = outputJson + output;
                }
 
            
            return outputJson ;
        }
         
        catch (Exception e)
        {
            e.printStackTrace() ; 
        }
		return null;
	}

}
