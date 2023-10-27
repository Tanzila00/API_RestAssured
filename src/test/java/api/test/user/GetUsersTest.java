package api.test.user;

import org.junit.jupiter.api.AfterEach;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dto.Data;
import dto.Users;
import endpoint.GetUsersAPI;

public class GetUsersTest {
	static GetUsersAPI getUsers;

	@BeforeAll
	public static void init() {
		getUsers = new GetUsersAPI();

	}

	@BeforeEach
	public void beforeTest() {
		getUsers.build();
	}

	@Test
	@DisplayName("Happy Query Param Validation")
	public void getUsersValidationWithQueryParam() {
		getUsers.getUsersCall();
		Users users = getUsers.getResponse().as(Users.class);
		List<Data> dataList = users.getData();
		Assertions.assertEquals(users.getPage(), 2);
		Assertions.assertTrue(dataList.get(2).getEmail().contains("@reqres"));
		Assertions.assertEquals(9, users.getData().get(2).getId());
	}

	@Test
	@DisplayName("No Query Param Validation")
	public void getUsersValidationforNoQueryParam() {
		getUsers.getUsersNoQueryParam();
		Assertions.assertEquals(200, getUsers.getStatusCode());// uses static getUsers obj to getStatusCode.
		// getStatusCode is in built io.rest.assured
		getUsers.bodyValidationUsingJsonEqualsTo("page", 1);
		getUsers.bodyValidationUsingJsonStringContains("data.email[3]", "@reqres");

	}

	@AfterEach
	public void resetTest() {// need to retest after each test.
		getUsers.reset();

	}

}
