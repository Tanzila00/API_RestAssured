package configuration;

import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public abstract class BaseAPI {
	Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	RequestSpecification requestSpecification;
	Response response;

	public RequestSpecification build() {// Step 01----we created a value to build it!
		logger.log(Level.INFO, "Building Request");
		return requestSpecification = RestAssured.given();
	}

	public RequestSpecification reset() {// reset
		logger.log(Level.INFO, "Resetting");
		return requestSpecification = null;
	}

	public RequestSpecification setBaseUrl(String baseUri) {// Step 02----we created method for baseUrl
		logger.log(Level.INFO, "Setting up baseUri:" + baseUri);
		return requestSpecification.baseUri(baseUri);// baseUri is from restassured
	}

	public RequestSpecification setEndPoint(String basePath) {// Step 03----we created a value for basePath!
		logger.log(Level.INFO, "Setting up EndPoint:" + basePath);
		return requestSpecification.basePath(basePath);
	}

	public RequestSpecification setQueryParam(String paramName, String paramValue) {// Step 04---QueryParam!
		return requestSpecification.queryParam(paramName, paramValue);
	}

	public RequestSpecification setPathParam(String paramName, String paramValue) {// Step 05--PathParam!
		return requestSpecification.pathParam(paramName, paramValue);
	}

	public RequestSpecification setHeader(String headerName, String headerValue) {
		return requestSpecification.header(headerName, headerValue);
	}

	public RequestSpecification setContentType(ContentType json) {
		return requestSpecification.contentType(json);
	}

	public RequestSpecification getAllRequestLog() {
		return requestSpecification.log().all();
	}

	public ValidatableResponse getAllResponseLog() {
		return response.then().log().all();
	}

	public RequestSpecification setPayLoad(String payLoad) {
		return requestSpecification.body(payLoad);
	}

	public RequestSpecification setPayLoad(Map<String, ?> payLoad) {
		return requestSpecification.body(payLoad);
	}

	public RequestSpecification setPayLoad(Object payLoad) {
		return requestSpecification.body(payLoad);
	}

	public RequestSpecification setPayLoad(File payLoad) {
		return requestSpecification.body(payLoad);
	}

	public RequestSpecification setPayLoad(InputStream payLoad) {
		return requestSpecification.body(payLoad);
	}

	public RequestSpecification setPayLoad(Byte[] payLoad) {
		return requestSpecification.body(payLoad);
	}

	public Response get() {
		// in java we can initialized and also get return
		return response = requestSpecification.get();// get response from requestS
	}

	public Response post() {
		return response = requestSpecification.post();
	}

	public Response put() {
		return response = requestSpecification.put();
	}

	public Response delete() {
		return response = requestSpecification.delete();
	}

	public Response getResponse() {
		logger.log(Level.INFO, "GET API");
		return response;
	}

	public String getResponseString() {
		return response.asString();
	}

	public int getStatusCode() {
		return response.getStatusCode();
	}

	public Response get(String url) {
		// in java we can initialized and also get return
		return response = requestSpecification.get(url);// get response from requestS
	}

	public Response post(String url) {
		return response = requestSpecification.post(url);
	}

	public Response put(String url) {
		return response = requestSpecification.put(url);
	}

	public Response delete(String url) {
		return response = requestSpecification.delete(url);
	}

	public void bodyValidationUsingJsonEqualsTo(String path, Object expected) {
		getResponse().then().body(path, Matchers.equalTo(expected));
	}

	public void bodyValidationUsingJsonStringContains(String path, String expected) {
		getResponse().then().body(path, Matchers.containsString(expected));
	}
}
