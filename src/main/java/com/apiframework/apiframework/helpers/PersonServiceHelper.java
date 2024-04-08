package com.apiframework.apiframework.helpers;

import com.apiframework.apiframework.constants.endPoints;
import com.apiframework.apiframework.models.PersonPogo;
import com.apiframework.apiframework.utils.ConfigManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.lang.reflect.Type;
import java.util.*;

import org.apache.http.HttpStatus;

public class PersonServiceHelper {
	
	/*we need to read the config values
	 * we need to assign those value through RestAssured
	 * We need to make Get request and get the list of persons and send that data to TestGETPerson*/
	
	// here we are reading the configfields
	private static final String BASEURL= ConfigManager.getInstance().getString("base_url");
	private static final String PORT= ConfigManager.getInstance().getString("port");
	private static final ObjectMapper MAPPER=new ObjectMapper();
	
	//create public constructor to assign the values to RestAssured
	
	public PersonServiceHelper()
	{
		RestAssured.baseURI=BASEURL;
		RestAssured.port=Integer.parseInt(PORT);
		RestAssured.useRelaxedHTTPSValidation();
	}
	/*this method helps to extract get all the persons list using personpogo class, 
	and convert the reponse to Type using TypeReference*/
	
	public List<PersonPogo> getAllPersons()
	{
		Response response= RestAssured.given().log().all()
				.contentType(ContentType.JSON)
				.get(endPoints.GET_ALL_PERSONS).andReturn();	
		
		Type type= new TypeReference<List<PersonPogo>>(){}.getType();
		assertEquals(response.getStatusCode(),HttpStatus.SC_OK,"ok");
		List<PersonPogo>PersonList=response.as(type);
		
		return PersonList;
		}
	
	/*Post method to create the new user*/
	
	public Response createPerson() throws JsonProcessingException
	{
		PersonPogo pp=new PersonPogo();
		pp.setFirstName("Amit");
		pp.setLastName("mishra");
		pp.setId("3");
		pp.setAge(30);
		pp.setPhoneNumbers("123344444");
		//String pp_json=MAPPER.writeValueAsString(pp);
		
		//need to make new request to create new student
		Response response= RestAssured.given().contentType(ContentType.JSON)
							.when().body(pp).post(endPoints.CREATE_PERSONS).andReturn();
		
		
		assertEquals(response.getStatusCode(),HttpStatus.SC_ACCEPTED,"created");				
		
		return response;
		
	}
	
	public Response updatePerson(Integer id)

	{

		PersonPogo pp=new PersonPogo();
		pp.setFirstName("Amit");
		pp.setLastName("mishra");
		pp.setId("12");
		pp.setAge(30);
		pp.setPhoneNumbers("123-344-444");
		
		Response response= RestAssured.given().log().all()
							.contentType(ContentType.JSON).pathParam("id", id).
							when().body(pp).patch(endPoints.UPDATE_PERSONS).andReturn();
		assertTrue(response.statusCode()==HttpStatus.SC_OK);
							
		
		return response;
		
	}
	



	public Response deletePerson(Integer id)
	{
		Response response=RestAssured.given().contentType(ContentType.JSON).pathParam("id", id).log().all().when()
				.delete(endPoints.DELETE_PERSON).andReturn();
		assertTrue(response.statusCode()==HttpStatus.SC_OK);
		return response;
		}
}
