package com.avivatest.demo.models;

import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;

public class WeatherInformation extends WCMUsePojo{
	
	private static final Logger LOG = LoggerFactory.getLogger(WeatherInformation.class);
	
	private String buttonLabel;
	private String inputLabel;
	private String resultLabel;
	private String heading;
	
	private static final String BUTTON_LABEL = "buttonLabel";
	private static final String INPUT_LABEL = "inputLabel";
	private static final String RESULT_LABEL = "resultLabel";
	private static final String HEADING = "heading";
	


	@Override
	public void activate() throws Exception {
		ValueMap props = getProperties();
		
		if(props.get(BUTTON_LABEL) != null){
		setButtonLabel(props.get(BUTTON_LABEL).toString());
		}
		if(props.get(HEADING) != null){
		setHeading(props.get(HEADING).toString());
		}
		if(props.get(INPUT_LABEL) != null){
		setInputLabel(props.get(INPUT_LABEL).toString());
		}
		if(props.get(RESULT_LABEL) != null){
		setResultLabel(props.get(RESULT_LABEL).toString());
		}
		
		LOG.debug("The values coming from the dialog are: Button Label:  %s", buttonLabel );
	}
	
	public String getButtonLabel() {
		return buttonLabel;
	}

	public void setButtonLabel(String buttonLabel) {
		this.buttonLabel = buttonLabel;
	}

	public String getInputLabel() {
		return inputLabel;
	}

	public void setInputLabel(String inputLabel) {
		this.inputLabel = inputLabel;
	}

	public String getResultLabel() {
		return resultLabel;
	}

	public void setResultLabel(String resultLabel) {
		this.resultLabel = resultLabel;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

}
