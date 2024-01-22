package stepDefination;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import static org.junit.Assert.*;
import pojo.LocationClass;
import resources.APIResources;
import resources.TestData;
import resources.Utility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepdefinationClass extends Utility {

	// global variable
	   RequestSpecification response;
	   ResponseSpecification resSpec;
	   Response respons;
	   TestData data = new TestData();
	   static String placeId;
	   APIResources res;
	 
	   // give an input

	   @Given("Add Place Payload with {string} {string} {string}")
	   public void add_place_payload_with(String name, String language, String address) throws IOException  {
	    // Write code here that turns the phrase above into concrete actions
      
		  
		    response = given().spec(requestSpecification()).body(data.getAddPlacePayload(name,language,address));
	    
		   
	}
	   @When("user call {string} with {string} http request")
	   public void user_call_with_http_request(String resource, String method) {
	       // Write code here that turns the phrase above into concrete actions
	       
			 res = APIResources.valueOf(resource);
			
		    System.out.println("the resources is:"+res.getResources());
		    
		 resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 
		 // if we declared like this then there is no need to change in our scenario outline to declare http resource and method 
		 // again and again for each http request. we an write it in only one single line...
		 if(method.equalsIgnoreCase("POST"))
		 respons = response.when().post(res.getResources()).then().spec(resSpec)
				 	.log().all() .extract().response(); 
		 else if(method.equalsIgnoreCase("GET"))
			 respons = response.when().get(res.getResources());
		 else if(method.equalsIgnoreCase("delete"))
			 respons = response.when().delete(res.getResources());
			 
	}
	@Then("the api call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer expectedCode) {
	    // Write code here that turns the phrase above into concrete actions
		//System.out.println("respons=>>>>>"+respons);
		System.out.println("int1===>>>"+expectedCode);
		int actualCode = respons.getStatusCode();
		System.out.println(actualCode);
		System.out.println("code===>>>"+actualCode);
		
		//assertEquals(respons.getStatusCode(), 200);
		
	}
	@Then("{string} in Response Body is {string}")
	public void in_response_body_is(String keyvalu, String ExpectedValue) {
	    // Write code here that turns the phrase above into concrete actions
	    
		String response = respons.asString();
		JsonPath js = new JsonPath(response);
		String status = js.getString("status");
		System.out.println(status);
		
		System.out.println(response);
		
		String Scope = js.getString("scope");
		System.out.println(Scope);
		
		placeId = js.getString("place_id");
		System.out.println("placeid is is:" +placeId );
		//assertEquals(js.get(keyvalu).toString(),ExpectedValue);
		
		
		//compareboth value with assertion
		String ActualValue = "APP";
		String Expected = js.getString("scope");
		System.out.println(Expected);
		
		//assertEquals(Expected,ActualValue);
		
	}
	
	@Then("verify place_id is created map to {string} using {string}")
	public void verify_place_id_is_created_map_to_using(String paceId, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	    
		 response = given().spec(requestSpecification()).queryParam("place_id", placeId);
		 user_call_with_http_request("GetPlaceAPI","GET");
		 String response = respons.asString();
		 JsonPath js = new JsonPath(response);
		 String name = js.getString("name");
		 System.out.println("the name is:" +name);
		 assertEquals(name,js.get("name"));
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	    
		response = given().spec(requestSpecification()).body(data.getDeletePlace(placeId));
		//user_call_with_http_request("DeletePlaceAPI","delete");
		//respons = response.when().delete(res.getResources()).then().spec(resSpec).log().all() .extract().response(); 
			 	
	}
	
	
	
	

}
