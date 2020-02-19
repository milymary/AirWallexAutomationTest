package com.airwallex.cucumber.serenity;

import com.airwallex.utils.ReusableSpecifications;
import com.airwallex.utils.TestDataBuild;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import net.thucydides.core.annotations.Step;

public class AccNumberSteps extends ReusableSpecifications {

	public int expectedMinLength;
	public int expectedMaxLength;
	public int length_AccNumber;

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

	
	@Step
	public void verifyContent(String account_number) {
		length_AccNumber = account_number.length();
		
		if(length_AccNumber == 0) {
			System.out.println("account_number is null");
		}
		
	}

}
