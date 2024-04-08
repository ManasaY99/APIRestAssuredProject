package com.apiframework.apiframework.tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.apiframework.apiframework.helpers.PersonServiceHelper;
import com.apiframework.apiframework.models.PersonPogo;

public class testGetPerson {
	
	private PersonServiceHelper personServiceHelper;
	
	@BeforeClass
	public void init() {
		personServiceHelper = new PersonServiceHelper();
	}
	
	@Test
	public void testGetAllPersons() {
		List<PersonPogo> personlist=personServiceHelper.getAllPersons();
		assertNotNull(personlist,"person list is not null");
		assertFalse(personlist.isEmpty(),"person list is not empty-true");
		
	}
	
	

}
