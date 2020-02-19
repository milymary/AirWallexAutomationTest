AirWallex test automation project

	
	 The contained code includes an API service endpoint to collect customers' bank details, which is being tested against . The script covers the following:
	 •  Positive scenarios 
	 •  Negative scenarios around:
	 		•	Account number checks 
	 		•	Swift Code checks
	 		•	BSB number checks




Built With:

	•	Serenity BDD
	•	Maven
	•	Cucumber
	•	Selenium
	•	Page Object pattern
	•	Java
	•	Re-usable Request Specification methods
	•	Serenity reports
	•	Cucumber data tables and data driven testing using Examples
	•   Logging file


Requirements:

	In order to utilise this project you need to have the following installed locally:
	•	Maven (maven-3.6.1 is used )
	•	Chrome and Chromedriver (UI tests use Chrome by default, can be changed in serenity.properties file)
	•	Java 1.8(1.7 or above)


Set Up:

	- Clone this project:  
	  $git clone "https://github.com/milymary/AirWallexAutomationTest.git"
	- Make sure you have the above requirements set up on your machine.
	- Access the project folder from your console/terminal and give the following commands:
	  $mvn clean verify serenity:aggregate



	
Reporting:

	Reports for each module are written into their respective /target directories after a successful run.
	Serenity reports get generated once a successful build run has been completed.
	These can be accessed from the AirWallexAutomationTestt/target/site/serenity/index.html 
	Logging.txt will contain all the request payloads and the responses captured.

	
Authors:

	Mily Mary Chacko



License:

	This project is created under a public repository on my personal GIT account.





