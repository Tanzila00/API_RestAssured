package api.test.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import dto.PostUser;

import endpoint.GetUsersAPI;
import endpoint.PostUserAPI;

public class PostUserAPITest {
	static PostUserAPI postUser;

	@BeforeAll
	public static void init() {
		postUser = new PostUserAPI();

	}

	@BeforeEach
	public void beforeTest() {
		postUser.build();
	}

	@Test
	@DisplayName("Post User Validation")
	public void postUserValidation() {
		PostUser user = new PostUser();
		user.setName("Tanzila");
		user.setJob("QA");
		postUser.postUserRequest(user);
		PostUser responseUser = postUser.getResponse().as(PostUser.class);
		Assertions.assertEquals(user.getName(), responseUser.getName());
		Assertions.assertEquals(201, postUser.getStatusCode());

		System.out.println(user.hashCode());
		System.out.println(responseUser.hashCode());
	}

	@AfterEach
	public void resetTest() {// need to retest after each test.
		postUser.reset();

	}
}
