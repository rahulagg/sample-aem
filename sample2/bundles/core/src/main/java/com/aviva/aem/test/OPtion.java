package com.aviva.aem.test;

/* Class Name is not correct. First letter should be uppercase and other small */
public class OPtion {
	
	/* Class Variable should be private as not used by other classed */
    protected String name;
    private String value;

    /* Constructor should be private as not used by other classed */
    public OPtion(){

    }

    public OPtion(String value, String name) {
    	/* Both the assignments are done wrongly */
        name = this.name;
        value = this.value;
    }

}