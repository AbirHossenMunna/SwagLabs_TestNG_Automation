package TestRunner;

import Base.Setup;
import Pages.AddToCartPage;
import Pages.CheckoutPage;
import Pages.LoginPage;
import Utils.Utils;
import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckoutTestRunner extends Setup {
    CheckoutPage checkoutPage;
    LoginPage loginPage;
    Utils utils;

    @BeforeTest
    public void doLogin() throws IOException, ParseException, InterruptedException {
        loginPage = new LoginPage(driver);
        utils = new Utils();
        utils.getUserCreds(2);
        loginPage.doLogin(utils.getUserName(), utils.getPassword());
    }

    @Test(priority = 0, description = "Verify that user can blank field with checkout or not")
    public void doCheckoutWithBlankInformation() throws InterruptedException {
        checkoutPage= new CheckoutPage(driver);
        checkoutPage.doCheckout();
        checkoutPage.btnContinue.click();

        String actual = driver.findElement(By.xpath("//h3[contains(text(), 'First Name is required')]")).getText();
        String expected = "Error: First Name is required";
        Assert.assertTrue(actual.contains(expected));
        Allure.description("Users trying to checkout with blank info");
    }
    @Test(priority = 1, description = "Verify that user can cancel his/her checkout process or not")
    public void doCheckoutWithCancel() throws InterruptedException {
        checkoutPage= new CheckoutPage(driver);

        Faker faker = new Faker();
        String firstName= faker.name().firstName();
        String lastName = faker.name().lastName();
        String zipCode = String.valueOf(utils.generateRandomNumber(1000,9999));

        checkoutPage.txtFirstName.sendKeys(firstName);
        checkoutPage.txtLastName.sendKeys(lastName);
        checkoutPage.txtPostalCode.sendKeys(zipCode);

        checkoutPage.btnContinue.click();
        checkoutPage.btnCancel.click();

        String actual = driver.findElement(By.xpath("//span[contains(text(),'Products')]")).getText();
        String expected = "Products";
        Assert.assertTrue(actual.contains(expected));
        Allure.description("Users can cancel his/her checkout process");
    }
    @Test(priority = 2, description = "Verify that user can checkout with valid information")
    public void doCheckout() throws InterruptedException {
        checkoutPage= new CheckoutPage(driver);
        checkoutPage.logoAddToCart.click();
        checkoutPage.btnCheckout.click();

        Faker faker = new Faker();
        String firstName= faker.name().firstName();
        String lastName = faker.name().lastName();
        String zipCode = String.valueOf(utils.generateRandomNumber(1000,9999));

        checkoutPage.txtFirstName.sendKeys(firstName);
        checkoutPage.txtLastName.sendKeys(lastName);
        checkoutPage.txtPostalCode.sendKeys(zipCode);

        checkoutPage.btnContinue.click();
        checkoutPage.btnFinish.click();

        String actual = driver.findElement(By.xpath("//h2[contains(text(), 'Thank you for your order!')]")).getText();
        String expected = "Thank you for your order!";
        Assert.assertTrue(actual.contains(expected));
        Allure.description("Users can checkout with valid information");
    }
}
