package com.airwallex.utils;

import com.airwallex.pojo.Bankdetails;

public class TestDataBuild {
	public int expectedMinLength;
	public int expectedMaxLength;
	public int length_AccNumber;

	public Bankdetails AddBankDetailsPayload(String payment_method, String bank_country_code, String account_name,
			String account_number, String swift_code, String bsb, String aba) {

		// tets data verification for validation of details
		validate_payment_methodContent(payment_method);
		validate_bankCodeContent(bank_country_code);
		verifyLength(account_number, bank_country_code);
		verifyContent(account_number);
		verifyCountryCodeInSwift_code(bank_country_code, swift_code);
		verifyBSBEmpty(bsb);

		Bankdetails bankdetailspojo = new Bankdetails();

		bankdetailspojo.setpayment_method(payment_method);
		bankdetailspojo.setbank_country_code(bank_country_code);
		bankdetailspojo.setaccount_name(account_name);
		bankdetailspojo.setaccount_number(account_number);
		bankdetailspojo.setswift_code(swift_code);
		bankdetailspojo.setbsb(bsb);
		bankdetailspojo.setaba(aba);

		return bankdetailspojo;

	}

	// validating incoming test data for correctness -payment_method
	public boolean validate_payment_methodContent(String payment_method) {

		boolean payment_methodValue = true;
		if ((payment_method.equalsIgnoreCase("LOCAL")) || (payment_method.equalsIgnoreCase("SWIFT"))) {
			payment_methodValue = true;
		} else {
			payment_methodValue = false;
		}

		return payment_methodValue;

	}

	// validating incoming test data for correctness - bank country code
	public boolean validate_bankCodeContent(String bank_country_code) {

		boolean bank_country_codeValue = true;
		if ((bank_country_code.equalsIgnoreCase("US")) || (bank_country_code.equalsIgnoreCase("AU"))
				|| (bank_country_code.equalsIgnoreCase("CN"))) {
			bank_country_codeValue = true;
		} else {
			bank_country_codeValue = false;
		}

		return bank_country_codeValue;

	}

	public boolean verifyLength(String account_number, String bank_country_code) {
		boolean AccNumLengthCheck = true;
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
					System.out.println("expected Acc Number length for US");
				} else {
					System.out.println("Length of account_number should be between 1 and 17 when bank_country_code US");
					AccNumLengthCheck = false;
				}
			}
			if (bank_country_code.equalsIgnoreCase("AU")) {
				if ((length_AccNumber >= expectedMinLength) && (length_AccNumber <= expectedMaxLength)) {
					System.out.println("expected Acc Number length for AU");
				} else {
					System.out.println("Length of account_number should be between 6 and 9 when bank_country_code AU");
					AccNumLengthCheck = false;
				}
			}
			if (bank_country_code.equalsIgnoreCase("CN")) {
				if (length_AccNumber > expectedMinLength && length_AccNumber < expectedMaxLength) {
					System.out.println("expected Acc Number length for CN");
				} else {
					System.out.println("Length of account_number should be between 8 and 20 when bank_country_code CN");
					AccNumLengthCheck = false;
				}
			}

		} else { // if 0
			System.out.println("account_number length condition not satisfied");
			AccNumLengthCheck = false;
		}

		return AccNumLengthCheck;

	}

	public boolean verifyContent(String account_number) {
		boolean checkAccNumberLength = true;
		length_AccNumber = account_number.length();

		if (length_AccNumber == 0) {
			System.out.println("account_number is null");
			checkAccNumberLength = false;
		}

		return checkAccNumberLength;
	}

	private boolean verifyCountryCodeInSwift_code(String bank_country_code, String swift_code) {
		boolean countryCodePresent = true;
		// can even check for indexes 5 and 6 for country code.
		if (swift_code.contains(bank_country_code)) {
			System.out.println("country code present within swift code");

		} else {
			System.out.println("country code not present within swift code");
			countryCodePresent = false;
		}
		return countryCodePresent;

	}

	public boolean verifyBSBEmpty(String bsb) {
		Boolean bsbPrsesnce = true;
		if (bsb.isEmpty()) {
			System.out.println("bsb content  is empty");
			bsbPrsesnce = false;
		} else {
			System.out.println("bsb content present");
		}
		return bsbPrsesnce;
	}

}
