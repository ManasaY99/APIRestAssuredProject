package com.apiframework.apiframework.tests;

import static org.testng.Assert.assertNotNull;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.apiframework.apiframework.helpers.PersonServiceHelper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class testPOSTPerson {
	
	//Init-to initiate the helper class for URL and other config details
	//Assert- to verify that the persons are created or not.
	 
	private PersonServiceHelper personservicehelper;
	@BeforeClass
	public void init()
	{
		personservicehelper=new PersonServiceHelper();
	}
	@Test
	public void testPostCreatePerson() throws JsonProcessingException
	{
		String id=personservicehelper.createPerson().jsonPath().getString("id");
		assertNotNull(id,"person id is created");
	}
	

}
