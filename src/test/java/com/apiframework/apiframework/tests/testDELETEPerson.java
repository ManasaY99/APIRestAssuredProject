package com.apiframework.apiframework.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.apiframework.apiframework.helpers.PersonServiceHelper;

public class testDELETEPerson {
	private PersonServiceHelper personservicehelper;
	@BeforeClass
	public void init()
	{
		personservicehelper=new PersonServiceHelper();
	}
	
	@Test
	public void deletePerson()
	{
		personservicehelper.deletePerson(3);
	}

}
