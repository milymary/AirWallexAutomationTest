 package com.airwallex.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
  
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		features="src/test/resources/feature/",
		tags= {"~@_AddBankDetailsAPI"}
		
		) 
public class testrunner {

}
