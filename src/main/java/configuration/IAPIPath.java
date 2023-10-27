package configuration;

public interface IAPIPath {
	String BASE_URI = "https://reqres.in";
	String USERS_ENDPOINT = "/api/users";
	String USER_ENDPOINT = "/api/users{userId}";

	String USER_QUERY_PARAM = "page";
}
