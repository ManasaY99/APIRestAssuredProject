package com.apiframework.apiframework.tests;

import static org.testng.Assert.assertNotNull;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.apiframework.apiframework.helpers.PersonServiceHelper;

public class testPATCHPerson {
	
	private PersonServiceHelper personservicehelper;
	@BeforeClass
	public void init()
	{
		personservicehelper=new PersonServiceHelper();
	}
	@Test
	public void testPatchUpdatePerson() {
		String firstname=personservicehelper.updatePerson(3).jsonPath().getString("first name");
		System.out.println(firstname);
		assertNotNull(firstname,"updated");
		
		
	}

}
