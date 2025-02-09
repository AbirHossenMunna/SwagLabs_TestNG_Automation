package TestRunner;

import Base.Setup;
import Pages.LoginPage;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class LoginTestRunners extends Setup {
    LoginPage loginPage;
    Utils utils;

    @Test(priority = 0, description = "Users tries to login with incorrect username but correct password")
    public void doLoginWithInvalidUserNameAndValidPassword () throws IOException, ParseException, InterruptedException {
        loginPage= new LoginPage(driver);
        utils = new Utils();
        utils.getUserCreds(0);
        loginPage.doLogin(utils.getUserName(), utils.getPassword());

        //Assertion
        doAssertion();

        Allure.description("User tries to login with Invalid username and correct password" +
                "User will not be allowed to login and 'Username and password do not match any user in this service' will be prompted");
        loginPage.clearCredential();
    }
    @Test(priority = 1, description = "Users tries to login with correct username but incorrect password")
    public void doLoginWithValidUserNameAndInvalidPassword () throws IOException, ParseException, InterruptedException {
        loginPage = new LoginPage(driver);
        utils = new Utils();
        utils.getUserCreds(1);
        loginPage.doLogin(utils.getUserName(), utils.getPassword());

        //Assertion
        doAssertion();

        Allure.description("User tries to login with Invalid username and correct password" +
                "User will not be allowed to login and 'Username and password do not match any user in this service' will be prompted");
        Thread.sleep(3000);
        loginPage.clearCredential();
    }
    @Test(priority = 2, description = "Users gives valid credentials and login is successful")
    public void doLoginWithValidCredential () throws IOException, ParseException, InterruptedException {
        Thread.sleep(3000);
        loginPage = new LoginPage(driver);
        utils = new Utils();

        utils.getUserCreds(2);
        loginPage.doLogin(utils.getUserName(),utils.getPassword());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        String successfullyMsgActual =wait.until(ExpectedConditions.visibilityOf(loginPage.lblValidationMessage)).getText();
        String expectedTxt = "Swag Labs";
        Assert.assertTrue(successfullyMsgActual.contains(expectedTxt));
        String actual = driver.findElement(By.xpath("//span[contains(text(),'Products')]")).getText();
        String expected = "Products";
        Assert.assertTrue(actual.contains(expected));
        Allure.description("This test verifies that a user can log in with valid credentials and" +
                " is successfully redirected to the dashboard.");
    }
    public void doAssertion() throws InterruptedException {
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        String errorMsgActual =wait.until(ExpectedConditions.visibilityOf(loginPage.lblErrorMessage)).getText();
        String expectedTxt = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertTrue(errorMsgActual.contains(expectedTxt));
    }

    @Test(priority = 3, description = "Verify Users tries logout to the system or not")
    public void doLogout() throws IOException, ParseException, InterruptedException{
        loginPage = new LoginPage(driver);
        loginPage.menuBar.click();
        loginPage.linkLogout.click();

        String validation = String.valueOf(loginPage.btnLogin.isDisplayed());
        Assert.assertTrue(Boolean.parseBoolean(validation));

        Allure.description("User can logout to the system");
    }
}
