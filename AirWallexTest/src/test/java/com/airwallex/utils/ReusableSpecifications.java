//this class has reusable contents that can be used to:
//1. returns - request specification obj(set of predefined conditions before sending request)- used with post(create) or put(update) specs
//2. returns - response specification obj(set of predefined conditions used to validate certain criteria in the response)
package com.airwallex.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.jruby.ir.instructions.GetGlobalVariableInstr;

public class ReusableSpecifications {

	// req builder and req specification type static variables
	public static RequestSpecBuilder rspecBuildr;
	public static RequestSpecification reqSpecification;

	// response builder and resp specification type static variables
	public static ResponseSpecBuilder respSpecBuilder;
	public static ResponseSpecification respSpecification;

	// method to return value of key passed into this method - value is being picked
	// from global.properties file
	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/java/com/airwallex/utils/global.properties");

		prop.load(fis);
		return prop.getProperty(key);
	}

	// method to return content specifications in the json body , validation purpose
	public String getJSONPath(Response response, String key) {
		String res = response.asString();
		JsonPath js = new JsonPath(res);
		return js.getString(key).toString();
	}

	// logging is handled here
	public RequestSpecification requestSpecification() throws Exception {

		if (reqSpecification == null) {

			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			reqSpecification = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();

		}
		return reqSpecification;

	}

	// this method returns - request specification object(set of predefined
	// conditions before sending request)
	public static RequestSpecification getGenericRequestSpec() {

		// creating RequestSpecBuilder object
		rspecBuildr = new RequestSpecBuilder();

		// creating the specifications of the req
		// we can even set cookies or parameters or multiple header values can should be
		// sent
		// content type
		rspecBuildr.setContentType(ContentType.JSON);

		// creating req specification object
		reqSpecification = rspecBuildr.build();

		// returning a request specification object
		return reqSpecification;
	}

	// this method returns - response specification obj(set of predefined conditions
	// used to validate certain criteria in the response)
	public static ResponseSpecification getGenericResponseSpec(String expectedCost) {
		// creating ResponseSpecBuilder object
		respSpecBuilder = new ResponseSpecBuilder();

		respSpecBuilder.expectBody("postage_result.costs.cost.cost", is(expectedCost));
		// creating the specifications of the response
		// we can even set values that have to be validated in the response
		// content -type
		respSpecBuilder.expectHeader("Content-Type", "application/json;charset=UTF-8");
		// encoding
		// response time matcher -hamcrest.matchers.* imported
		respSpecBuilder.expectResponseTime(lessThan(5L), TimeUnit.SECONDS);

		// creating req specification object
		respSpecification = respSpecBuilder.build();

		return respSpecification;
	}

}
