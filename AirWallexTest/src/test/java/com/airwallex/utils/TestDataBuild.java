package com.airwallex.utils;

import com.airwallex.pojo.Bankdetails;

public class TestDataBuild {

	public Bankdetails AddBankDetailsPayload(String payment_method, String bank_country_code, String account_name, String account_number, String swift_code, String bsb, String aba)
	{
		Bankdetails bankdetailspojo= new Bankdetails();
		
		bankdetailspojo.setpayment_method(payment_method);
		bankdetailspojo.setbank_country_code(bank_country_code);
		bankdetailspojo.setaccount_name(account_name);
		bankdetailspojo.setaccount_number(account_number);
		bankdetailspojo.setswift_code(swift_code);
		bankdetailspojo.setbsb(bsb);
		bankdetailspojo.setaba(aba);
		
		bankdetailspojo.setpayment_method("SWIFT");
		bankdetailspojo.setbank_country_code("US");
		bankdetailspojo.setaccount_name("TestUser1");
		bankdetailspojo.setaccount_number("123");
		bankdetailspojo.setswift_code(swift_code);
		bankdetailspojo.setbsb("");
		bankdetailspojo.setaba("11122233A");
		
		
		
		return bankdetailspojo;
		
	}


}
