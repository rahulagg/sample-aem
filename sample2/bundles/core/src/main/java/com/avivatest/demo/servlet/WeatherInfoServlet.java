package com.avivatest.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import com.avivatest.demo.service.GetWeatherInfoService;

	@SuppressWarnings("serial")
	@SlingServlet(paths="/bin/get/weatherinfo", methods="GET")
	public class WeatherInfoServlet extends SlingSafeMethodsServlet {
	    @Reference
	    private GetWeatherInfoService weatherService;
	    @Override
	    protected void doGet(final SlingHttpServletRequest req,
	            final SlingHttpServletResponse resp) throws ServletException, IOException {
	    	String requestedCity = req.getParameter("city");
	    	resp.setContentType("application/json");
	        PrintWriter out = resp.getWriter();
	        String weatherData = weatherService.getWeatherInfo(requestedCity);
	        
	        try {
	        JSONObject jsonObject = new JSONObject();
	       
				jsonObject.put("weatherData", weatherData);
			
	        out.println(jsonObject);
	        } catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	


