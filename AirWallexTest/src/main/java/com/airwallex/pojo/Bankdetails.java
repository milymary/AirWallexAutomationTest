package com.airwallex.pojo;

public class Bankdetails {

	private String payment_method;
	private String bank_country_code;
	private String account_name;
	private String account_number;
	private String swift_code;
	private String bsb;
	private String aba;

	public String getpayment_method() {
		return payment_method;
	}

	public void setpayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getbank_country_code() {
		return bank_country_code;
	}

	public void setbank_country_code(String bank_country_code) {
		this.bank_country_code = bank_country_code;
	}

	public String getaccount_name() {
		return account_name;
	}

	public void setaccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getaccount_number() {
		return account_number;
	}

	public void setaccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getswift_code() {
		return swift_code;
	}

	public void setswift_code(String swift_code) {
		this.swift_code = swift_code;
	}

	public String getbsb() {
		return bsb;
	}

	public void setbsb(String bsb) {
		this.bsb = bsb;
	}

	public String getaba() {
		return aba;
	}

	public void setaba(String aba) {
		this.aba = aba;
	}

}
