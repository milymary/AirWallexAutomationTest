Feature: Automation Test - MLCInsurance

	Scenario Outline: Test1- Check form validation for the Request a LifeView demo for MLCInsurance Lifeview 
	#Given All Mandatory fields <incomeYear>, <taxableIncome>, <residencyStatus>, <monthsOfResidency> are filled in the form and submitted
	Given Add Bank Details Payload with payment method"<payment_method>" country code"<bank_country_code>" acc name"<account_name>" acc number"<account_number>" swift code"<swift_code>" bsb"<bsb>" aba"<aba>"
	When User calls "AddBankDetailsAPI" with "POST" http request
	Then API call is a success with status code "200"
	And "success" status in response body says "Bank details saved" 
	
	Examples:
	
	|payment_method	|bank_country_code|account_name |account_number |swift_code |bsb 		|aba 	   |
	|SWIFT			|US			    |TestUser1	 |123			|ICBCUSBJ  |NA  		|11122233A |
	

	