package unitTest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.io.File;
import dto.PostUser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostUserTest {

	String baseUri = "https://reqres.in";
	String userEndpoint = "api/users";

	RequestSpecification requestSpecification;
	Response response;

	@Test
	@Disabled
	public void postUserStringBody() {
		requestSpecification = RestAssured.given();// initialized
		requestSpecification.baseUri(baseUri);
		requestSpecification.basePath(userEndpoint);
		requestSpecification.contentType(ContentType.JSON);// this is additional for post so add this when making
															// request
		requestSpecification.log().all();
		String body = "{\r\n" + "    \"name\": \"morpheus\",\r\n" + "    \"job\": \"leader\"\r\n" + "}";// copied the
																										// body from
																										// reqres
		requestSpecification.body(body);// this is the body used in line 23
		response = requestSpecification.post();
		response.prettyPrint();

		PostUser postUser = response.as(PostUser.class);// convert the response to PostUser class
		System.out.println("The name of the Job: " + postUser.getJob());// get the elemts from PostUser
	}

	@Test
	public void postUserFileBody() {
		requestSpecification = RestAssured.given();// initialized
		requestSpecification.baseUri(baseUri);
		requestSpecification.basePath(userEndpoint);
		requestSpecification.contentType(ContentType.JSON);// this is additional for post so add this when making
															// request
		requestSpecification.log().all();
		File file = new File("src/test/resources/postUser.json");
		requestSpecification.body(file);// this is the body used in line 23
		response = requestSpecification.post();
		response.prettyPrint();

	}
}
