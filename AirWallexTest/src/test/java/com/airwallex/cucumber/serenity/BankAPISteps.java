package com.airwallex.cucumber.serenity;

import static org.junit.Assert.assertEquals;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;

import com.airwallex.utils.APIResources;
import com.airwallex.utils.ReusableSpecifications;
import com.airwallex.utils.TestDataBuild;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.junit.annotations.TestData;

public class BankAPISteps extends ReusableSpecifications {

	TestDataBuild data = new TestDataBuild();
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	Response response;
	public int expectedMinLength;
	public int expectedMaxLength;
	public int length_AccNumber;

	@Step
	public void postPayload(String payment_method, String bank_country_code, String account_name, String account_number,
			String swift_code, String bsb, String aba) throws Exception {

		// using spec - to add request data from a pre-defined specificatio
		// data calls the payload creation testdata file, which generates the payload
		// for the request
		reqSpec = SerenityRest.given().spec(requestSpecification()).body(data.AddBankDetailsPayload(payment_method,
				bank_country_code, account_name, account_number, swift_code, bsb, aba));

	}

	@Step
	public void callHTTPRequest(String resource, String httpMethod) {

		// calling enum for the specific resource path for the payload to be sent
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println("resource path applied here:" + resourceAPI.getResource());

		// getting response into a variable depending ont he http method present
		// reusable if else conditions
		if (httpMethod.equalsIgnoreCase("POST")) {
			response = reqSpec.when().post(resourceAPI.getResource());
		} else if (httpMethod.equalsIgnoreCase("GET")) {
			response = reqSpec.when().get(resourceAPI.getResource());
		} else if (httpMethod.equalsIgnoreCase("PUT")) {
			response = reqSpec.when().put(resourceAPI.getResource());
		} else if (httpMethod.equalsIgnoreCase("DELETE")) {
			response = reqSpec.when().delete(resourceAPI.getResource());
		}
	}

	@Step
	public void verifyResponseCode(String statusCode) {

		// verifying the response status code
		assertEquals(response.getStatusCode(), Integer.parseInt(statusCode));

	}

	@Step
	public void verifyResponseValueForKey(String responseKey, String responseValue) {
		// verify particular key value content in json response
		assertEquals(getJSONPath(response, responseKey), responseValue);

	}

	@Step
	public void verifyLength(String account_number, String bank_country_code) {

		length_AccNumber = account_number.length();

		if (bank_country_code.equalsIgnoreCase("US")) {
			expectedMinLength = 1;
			expectedMaxLength = 17;
		}
		if (bank_country_code.equalsIgnoreCase("AU")) {
			expectedMinLength = 6;
			expectedMaxLength = 9;
		}
		if (bank_country_code.equalsIgnoreCase("CN")) {
			expectedMinLength = 8;
			expectedMaxLength = 20;
		}

		if (length_AccNumber != 0) {
			if (bank_country_code.equalsIgnoreCase("US")) {
				if (length_AccNumber > expectedMinLength & length_AccNumber < expectedMaxLength) {
					System.out.println("expected length for US");
				} else {
					System.out.println("Length of account_number should be between 1 and 17 when bank_country_code US");
				}
			}
			if (bank_country_code.equalsIgnoreCase("AU")) {
				if ((length_AccNumber >= expectedMinLength) && (length_AccNumber <= expectedMaxLength)) {
					System.out.println("expected length for AU");
				} else {
					System.out.println("Length of account_number should be between 6 and 9 when bank_country_code AU");
				}
			}
			if (bank_country_code.equalsIgnoreCase("CN")) {
				if (length_AccNumber > expectedMinLength && length_AccNumber < expectedMaxLength) {
					System.out.println("expected length for CN");
				} else {
					System.out.println("Length of account_number should be between 8 and 20 when bank_country_code CN");
				}
			}

		} else { // if 0
			System.out.println("account_number is required");
		}

	}

}
