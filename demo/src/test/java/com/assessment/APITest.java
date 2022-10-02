package com.assessment;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class APITest {
	
	String isbn ="9781491904244";
		
	  @Test
	  public void updateBook() {
	      //RestAssured.baseURI = baseUrl;
	      RestAssured.given().auth().preemptive().basic("Demobook", "Demo@123")
	    		  .body("{\"userId\":\"172081b4-33fc-4ee4-a416-8cea8a0c0869\",\"collectionOfIsbns\":[{\"isbn\":\""+isbn+"\"}]}")
	    		  .contentType(ContentType.JSON).when().post("https://demoqa.com/BookStore/v1/Books")
	    		  .then().statusCode(201);
	    
	  }
}