# SwagLabs_TestNG_Automation

### This is a complete project where a DailyFinance site is automated by writing test suites using selenium-webdriver and TestNg as testing framework.
The following key modules/pages are automated:

* Login Functionality Test 
* Add to Cart Functionality
* Checkout Process Validation 
* Sorting Products Validation
* Logout Functionality Test
* Locked-Out User Validation
* Cart Badge Count Validation

For failed test cases it will take a screenshot aswell at the point of failure.

### Technology:

* Tool: Selenium Webdriver
* IDE: Intellij
* Build tool: Gradle
* Language: Java
* Test_Runner: TestNG

### Prerequisite:
* Need to install jdk 11, gradle and allure
* Configure Environment variable for jdk 11, gradle and allure
* Clone this project and unzip it
* Open the project folder
* Double click on "build.gradle" and open it through IntellIJ IDEA
* Let the project build successfully
* Click on "Terminal" and run the automation scripts

### Run the Automation Script by the following command:
* Selenium will open the browser and start automating for smokeSuites.
  
```bash
  gradle clean test 
```
### Run the Allure Report Script by the following command:  
```bash
  allure generate allure-results --clean -o allure-report
  allure serve allure-results
```
#### Here is the Summary report:
![Screenshot 2025-02-10 050718](https://github.com/user-attachments/assets/16907336-c7db-4a34-bbf9-caf6fbe00e06)

#### Here is the Allure report overview:
![Screenshot 2025-02-10 051102](https://github.com/user-attachments/assets/b1bbb0f4-5d54-43c8-a35f-cfb546be48fb)

#### Here are the allure suites of this project:
![Screenshot 2025-02-10 051131](https://github.com/user-attachments/assets/6fbcc939-0219-428e-8156-9b4b51496b6d)

#### Here are the Allure Behaviors of this project:
![Screenshot 2025-02-10 051141](https://github.com/user-attachments/assets/b28b495b-f045-4967-a18c-9b777b85cd8c)

