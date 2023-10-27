package unitTest;

import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import dto.Data;
import dto.Support;
import dto.User;
import dto.Users;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAPITest {

	// for re-usability we can initialize a variable!!
	String baseUri = "https://reqres.in";
	String getUserEndpoint = "api/users";

	@Test
	@Disabled
	public void simpleGetCall() {
		RestAssured.get("https://reqres.in/api/users/3").andReturn().prettyPrint();// in builtname and get the url from
																					// postman

	}

	@Test
	@Disabled
	public void simpleGetCallWithLog() {
		// RestAssuredis the class!!
		RestAssured.given().log().all()// to get request method
				.get("https://reqres.in/api/users/3")// from console after calling GET
				.andReturn().prettyPrint();
	}

	@Test
	@Disabled
	public void simpleGetCallWithLogUsingBDDFormat() {
		RestAssured.given().log().all()// to get request method headers output
				.when()// action for get url
				.get("https://reqres.in/api/users/3")// gives the body only without headers .
				.then()// validation for statusCode
				.statusCode(200).log().all();// for response headers output
	}

	@Test
	@Disabled
	public void getCallWithPathParam() {
		RestAssured.given().baseUri(baseUri)// from String variable
				.basePath("api/users/{id}").header("Accept", "*/*").pathParam("id", 5).log().all().when()// action
				.get().then()// validation
				.log().all();
	}

	@Test
	@Disabled
	public void getCallWithQueryParam() {
		RestAssured.given().baseUri(baseUri)// from String variable
				.basePath("api/users")
				// .header("Accept", "*/*")
				.queryParam("page", 1).log().all().when()// action
				.get().then()// validation
				.log().all();
	}

	// ----------till now we just create request but not validated
	// respond---------------------
	@Test
	@Disabled
	public void getCallValidatingResponse() {
		RestAssured.given().baseUri(baseUri)// from String variable
				.basePath("api/users")
				// .header("Accept", "*/*")
				.queryParam("page", 1).log().all().when()// action
				.get().then().statusCode(200).header("Content-type", containsString("application/json"))
				.body("per_page", equalTo(6));

	}

	@Test
	@Disabled
	public void getCallValidatinglListSize() {
		Response response = RestAssured.given()// to save till get request response we created an object
				.baseUri(baseUri)// from String variable
				.basePath("api/users")
				// .header("Accept", "*/*")
				.queryParam("page", 1).log().all().when()// action
				.get();
		response.prettyPrint();

		JsonPath jsonPath = JsonPath.from(response.asString());// (from)asks where to get the jasonPath from---ans is
																// from reponse obj(who saves request).
		int perPageValue = jsonPath.getInt("per_page");// 6 is an int so i called variable for int
		List<String> list = jsonPath.getList("data");// we need to read the list so called from where the list starts
														// (which here is from data)
		System.out.println(list);
		Assertions.assertEquals(perPageValue, list.size());
		System.out.println(jsonPath.getString("data.email[1]"));
		System.out.println(jsonPath.getString("data.avatar[3]"));
	}

	@Test
	@Disabled
	public void convertToPojoGetUser() {
		Response response = RestAssured.given()// to save till get request response we created an object
				.baseUri(baseUri)// from String variable
				.basePath(getUserEndpoint + "/{id}")
				// .header("Accept", "*/*")
				.pathParam("id", 5).log().all().when()// action
				.get();
		response.prettyPrint();
//-------------------jSonPath---------------	         
//       JsonPath jsonpath =JsonPath.from(response.asString());
//      System.out.println(jsonpath.getString("data.first_name"));//method 1        
//      Data data= jsonpath.getObject("data",Data.class);
//	     Support support = jsonpath.getObject("support", Support.class);	        
//	     System.out.println("First name from data Object:"+ data.getFirst_name());
//	      System.out.println("Support Text from support Object:"+ support.getTextl());
//--------------------response conversion------------	         
		User user = response.as(User.class);
		Data data1 = user.getData();
		Support support1 = user.getSupport();
		System.out.println("The first id" + data1.getId());
		System.out.println("The url for support" + support1.getUrl());

	}

	@Test
	// @Disabled
	public void convertToPojoGetUsers() {
		Response response = RestAssured.given()// to save till get request response we created an object
				.baseUri(baseUri)// from String variable
				.basePath(getUserEndpoint)
				// .header("Accept", "*/*")
				.queryParam("page", 1).log().all().when()// action
				.get();
		response.prettyPrint();
//ObjectMapper mapper=new ObjectMapper();//STH WRONG
//Users users= mapper.convertValue(response,Users.class);
//System.out.println(users.getPage());
		Users users = response.as(Users.class);
		System.out.println("The value for the page object" + users.getPage());
		System.out.println("The second email of the data array" + users.getData().get(2).getEmail());

	}

	@Test
	@Disabled
	public void breakdown_rqst_response() {
		Response response;// this is in built for response
		RequestSpecification requestspecification;// this is for in built request
		requestspecification = RestAssured.given();// initialized
		requestspecification.baseUri(baseUri);// reqres.com
		requestspecification.basePath(getUserEndpoint);/// api/users/
		requestspecification.queryParam("page", 1);// ?page=1
		requestspecification.header("Accept", "*/*");

		response = requestspecification.get();// reqre.com/api/user?page=1
		response.prettyPrint();

		int status = response.getStatusCode();// code is in integer so need to use a variable int to get the value
		System.out.println("Status Code:" + status);

		int total = response.path("total");
		System.out.println("Total:" + total);
	}
}