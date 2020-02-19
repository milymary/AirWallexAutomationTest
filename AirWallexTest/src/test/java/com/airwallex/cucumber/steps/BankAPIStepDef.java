package com.airwallex.cucumber.steps;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;


import com.airwallex.cucumber.serenity.BankAPISteps;
import com.airwallex.pojo.Bankdetails;
import com.airwallex.utils.ReusableSpecifications;
import com.airwallex.utils.TestDataBuild;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import net.bytebuddy.utility.privilege.SetAccessibleAction;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;

//@RunWith(SerenityParameterizedRunner.class)
//@UseTestDataFrom("testdata/ATOTaxCalTestData.csv")

public class BankAPIStepDef {

	Response res;

	TestDataBuild data = new TestDataBuild();

	// @Managed(driver="chrome", uniqueSession = true)
	// WebDriver driver;

	@Steps
	BankAPISteps bankdetails;

	//@Steps
	//AccNumberSteps accNumber;

	@Given("^Add Bank Details Payload with payment method\"([^\"]*)\" country code\"([^\"]*)\" acc name\"([^\"]*)\" acc number\"([^\"]*)\" swift code\"([^\"]*)\" bsb\"([^\"]*)\" aba\"([^\"]*)\"$")
	public void add_Bank_Details_Payload(String payment_method, String bank_country_code, String account_name,
			String account_number, String swift_code, String bsb, String aba) throws Exception {

		bankdetails.postPayload(payment_method, bank_country_code, account_name, account_number, swift_code, bsb, aba);

	}

	@When("^User calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
	public void user_calls_with_http_request(String resource, String httpMethod) {

		bankdetails.callHTTPRequest(resource, httpMethod);
	}

	@Then("^API call is a success with status code \"([^\"]*)\"$")
	public void api_call_is_a_success_with_status_code(String statusCode) {

		bankdetails.verifyResponseCode(statusCode);

	}

	@Then("^\"([^\"]*)\" status in response body says \"([^\"]*)\"$")
	public void status_in_response_body_says(String responseKey, String responseValue) {

		bankdetails.verifyResponseValueForKey(responseKey, responseValue);

	}

}
