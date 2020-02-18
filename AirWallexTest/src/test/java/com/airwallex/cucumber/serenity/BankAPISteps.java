package com.airwallex.cucumber.serenity;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;

import com.airwallex.utils.ReusableSpecifications;
import com.airwallex.utils.TestDataBuild;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.junit.annotations.TestData;

public class BankAPISteps extends ReusableSpecifications {

	TestDataBuild data = new TestDataBuild();

	
	
	@Step
	public void postPayload(String payment_method, String bank_country_code, String account_name, String account_number,
			String swift_code, String bsb, String aba) throws Exception {

		RestAssured.baseURI = "http://preview.airwallex.com:30001";

		Response res = SerenityRest.given().headers("Content-Type", "application/json")
				.baseUri("http://preview.airwallex.com:30001/bank").body(data.AddBankDetailsPayload(payment_method,
						bank_country_code, account_name, account_number, swift_code, bsb, aba))
				.when().post();

		res.then().log().all().assertThat().statusCode(200);

	}

}
