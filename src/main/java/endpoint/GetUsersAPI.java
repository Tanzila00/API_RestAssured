package endpoint;

import java.util.logging.Logger;

import configuration.BaseAPI;
import configuration.IAPIPath;

public class GetUsersAPI  extends BaseAPI implements IAPIPath{
	Logger logger= Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);//java log format
	
	public void getUsersCall() {
	setBaseUrl(BASE_URI);//method from BaseAPI class method and param from IAPI
	setEndPoint(USERS_ENDPOINT);
	setQueryParam(USER_QUERY_PARAM,"2" );
	getAllRequestLog();//request Log
	get();//make the get call.
	getAllResponseLog();//response log
	System.out.println(getResponseString());
	}
	public void getUsersNoQueryParam() {
		setBaseUrl(BASE_URI);
		setEndPoint(USERS_ENDPOINT);
		getAllRequestLog();
		get();
		getAllResponseLog();
		System.out.println(getResponseString());
	}

}
