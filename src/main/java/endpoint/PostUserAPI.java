package endpoint;

import configuration.BaseAPI;
import configuration.IAPIPath;
import io.restassured.http.ContentType;
import dto.PostUser;

public class PostUserAPI extends BaseAPI implements IAPIPath {
	public void postUserRequest(PostUser user) {

		setBaseUrl(BASE_URI);
		setEndPoint(USERS_ENDPOINT);
		setContentType(ContentType.JSON);

		setPayLoad(user);
		getAllRequestLog();
		post();
		getAllResponseLog();

	}

}
