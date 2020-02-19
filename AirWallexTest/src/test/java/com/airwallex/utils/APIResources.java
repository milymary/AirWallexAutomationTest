package com.airwallex.utils;

public enum APIResources {

	AddBankDetailsAPI("/bank");
	// add many more duch API enums here .. specify resource path
	// eg: , DeleteBankDetails("/abc/xyz")
	// eg: , UpdateBankDetails("/sdf/ghj")

	private String resource;

	APIResources(String resource) {
		this.resource = resource;
	}

	public String getResource() {
		return resource;

	}

}
