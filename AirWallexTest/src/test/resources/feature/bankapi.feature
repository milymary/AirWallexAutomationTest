Feature: Automation Tests -  Air Wallex Bank API scenarios

	#--------------------------- Positive Scenarios ------------------------------------------------------#
	@positivescenarios_AddBankDetailsAPI
	Scenario Outline: Test1- Positive scenarios validation  
	Given Add Bank Details Payload with payment method"<payment_method>" country code"<bank_country_code>" acc name"<account_name>" acc number"<account_number>" swift code"<swift_code>" bsb"<bsb>" aba"<aba>"
	When User calls "AddBankDetailsAPI" with "POST" http request
	Then API call is a success with status code "200"
	And "success" status in response body says "Bank details saved" 
	
	Examples:
	
	|payment_method	|bank_country_code|account_name  |account_number |swift_code |bsb 		|aba 	   |
	|SWIFT			|US			      |TestUser1	 |123			 |ICBCUSBJ 	 |  		|11122233A |
	|LOCAL			|AU			      |TestUser2	 |123987		 |ICBKAUBJ	 |876098  	|		   |
	|LOCAL			|CN			      |TestUser3	 |123987234		 |ICBLCNBJ	 |			|		   |
	

	#--------------------------- Negative Scenarios ------------------------------------------------------#
	#--------------------------- AccNumber Scenarios -----#

	@negativescenarios_AddBankDetailsAPI
	@negative_accountNumberLength
	@negative_AccNumberScenarios
	Scenario Outline: Test2- Negative checks validation for Account Field Length 
	Given Add Bank Details Payload with payment method"<payment_method>" country code"<bank_country_code>" acc name"<account_name>" acc number"<account_number>" swift code"<swift_code>" bsb"<bsb>" aba"<aba>"
	When User calls "AddBankDetailsAPI" with "POST" http request
	#And Verify "<account_number>" length for the country "<bank_country_code>"
	Then API call is a success with status code "400"
	And "error" status in response body says "Length of account_number should be between 7 and 11 when bank_country_code is 'US'" 
	
	Examples:
	
	|payment_method	|bank_country_code|account_name  |account_number 									|swift_code |bsb 	|aba 	   |
	|SWIFT			|AU			      |TestUser4	 |345			 									|ICBKAUBJ 	|876098 |11122233A |
	|LOCAL			|CN			      |TestUser5	 |876			 									|ICBKCNBJ 	|  		|11122233A |
	|LOCAL			|US			      |TestUser6	 |985756453435746743426424267584656575657			|ICBKUSBJ	|		|11122233A|
	
	
	
	@negativescenarios_AddBankDetailsAPI
	@negative_accountNumberError
	@negative_AccNumberScenarios
	Scenario Outline: Test3- Negative checks validation for Account Number Error
	Given Add Bank Details Payload with payment method"<payment_method>" country code"<bank_country_code>" acc name"<account_name>" acc number"<account_number>" swift code"<swift_code>" bsb"<bsb>" aba"<aba>"
	When User calls "AddBankDetailsAPI" with "POST" http request
	#And Verify "<account_number>" content 
	Then API call is a success with status code "400"
	And "error" status in response body says "'account_number' is required" 
	
	Examples:
	
	|payment_method	|bank_country_code|account_name  |account_number |swift_code |bsb 		|aba 	   |
	|SWIFT			|AU			      |TestUser7	 |				 |ICBCAUBJ 	 |756476  	|		   |
	

	
	
	
	#--------------------------- SwiftCode Scenarios -----#
	
	
	
	@negativescenarios_AddBankDetailsAPI
	@negative_swiftCodeLength
	@negative_swiftCodeScenarios
	Scenario Outline: Test4- Negative checks validationon for Swift Code Length
	Given Add Bank Details Payload with payment method"<payment_method>" country code"<bank_country_code>" acc name"<account_name>" acc number"<account_number>" swift code"<swift_code>" bsb"<bsb>" aba"<aba>"
	When User calls "AddBankDetailsAPI" with "POST" http request
	Then API call is a success with status code "400"
	And "error" status in response body says "Length of 'swift_code' should be either 8 or 11"
	
	Examples:
	
	|payment_method	|bank_country_code|account_name  |account_number |swift_code |bsb 		|aba 	   |
	|SWIFT			|US			      |TestUser8	 |123			 |ICBC 	 	 |  		|11122233A |
	
	
	@negativescenarios_AddBankDetailsAPI
	@negative_swiftCodeError
	@negative_swiftCodeScenarios
	Scenario Outline: Test5- Negative checks validationon for Swift Code Error 
	Given Add Bank Details Payload with payment method"<payment_method>" country code"<bank_country_code>" acc name"<account_name>" acc number"<account_number>" swift code"<swift_code>" bsb"<bsb>" aba"<aba>"
	When User calls "AddBankDetailsAPI" with "POST" http request
	Then API call is a success with status code "400"
	And "error" status in response body says "The swift code is not valid for the given bank country code: US"
	
	Examples:
	
	|payment_method	|bank_country_code|account_name  |account_number |swift_code |bsb 		|aba 	   |
	|SWIFT			|US			      |TestUser9	 |985756453		 |ICBKAUBJ 	 |  		|11122233A |
	

	@negativescenarios_AddBankDetailsAPI
	@negative_swiftCodecContent
	@negative_swiftCodeScenarios
	Scenario Outline: Test6- Negative checks validationon for Swift Code Content 
	Given Add Bank Details Payload with payment method"<payment_method>" country code"<bank_country_code>" acc name"<account_name>" acc number"<account_number>" swift code"<swift_code>" bsb"<bsb>" aba"<aba>"
	When User calls "AddBankDetailsAPI" with "POST" http request
	Then API call is a success with status code "400"
	And "error" status in response body says "'swift_code' is required when payment method is 'SWIFT'"
	
	Examples:
	
	|payment_method	|bank_country_code|account_name  |account_number |swift_code |bsb 		|aba 	   |
	|SWIFT			|US			      |TestUser9	 |985756453		 |	 	 	 |  		|11122233A |
	
	
	#--------------------------- BSB Scenarios -----#
	
	@negativescenarios_AddBankDetailsAPI
	@negative_bsbContent
	@negative_bsbScenarios
	Scenario Outline: Test7- Negative checks validationon for BSB Content
	Given Add Bank Details Payload with payment method"<payment_method>" country code"<bank_country_code>" acc name"<account_name>" acc number"<account_number>" swift code"<swift_code>" bsb"<bsb>" aba"<aba>"
	When User calls "AddBankDetailsAPI" with "POST" http request
	Then API call is a success with status code "400"
	And "error" status in response body says "'bsb' is required when bank country code is 'AU'"
	
	Examples:
	
	|payment_method	|bank_country_code|account_name  |account_number |swift_code |bsb 		|aba 	   |
	|SWIFT			|AU			      |TestUser10	 |123222245		 |ICBKAUBJ	 |  		|11122233A |
	
	
	
