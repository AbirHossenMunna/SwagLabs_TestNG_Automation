package TestRunner;

import Base.Setup;
import Pages.AddToCartPage;
import Pages.LoginPage;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTestRunner extends Setup {
    AddToCartPage addToCartPage;
    LoginPage loginPage;
    Utils utils;

    @BeforeTest
    public void doLogin() throws IOException, ParseException, InterruptedException {
        loginPage = new LoginPage(driver);
        utils = new Utils();
        utils.getUserCreds(2);
        loginPage.doLogin(utils.getUserName(), utils.getPassword());
    }

    @Test(priority = 0, description = "Verify cart badge count updates correctly.")
    public void doAddToCartBadgeCount() throws InterruptedException {
        addToCartPage= new AddToCartPage(driver);

        addToCartPage.doAddToCart();
        // Verify cart count
        WebElement cartBadge = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        String cartCount = cartBadge.getText();
        System.out.println("Cart Count: " + cartCount);

        // Assertion to verify the cart count is 2
        Assert.assertEquals(cartCount, "2", "Cart count mismatch!");

        Allure.description("Verify cart badge count updates correctly.");

    }

    @Test(priority = 1, description = "Verify user can multiple items should remove")
    public void doAddToCartWithRemoveItems() throws InterruptedException {
        addToCartPage= new AddToCartPage(driver);
        WebElement cartBadge = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        cartBadge.click();

        Thread.sleep(2000);
        addToCartPage.btnRemoveItemOne.click();
        addToCartPage.btnRemoveItemTwo.click();

        // Assertion: Cart badge should disappear (element should not be found)
        boolean isCartBadgePresent = utils.isElementPresent(driver, By.xpath("//a[@class='shopping_cart_link']"));
        if (isCartBadgePresent) {
            WebElement cartBadge1 = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
            String cartCount = cartBadge1.getText();
            System.out.println("Cart Count: " + cartCount);
            Assert.assertEquals(cartCount, "", "Cart count mismatch!");
        }

        Allure.description("Verify user can multiple items should remove");
    }
    @Test(priority = 2, description = "Verify user can multiple item should be added or not")
    public void doAddToCartAddWithMultipleItems() throws InterruptedException {
        addToCartPage= new AddToCartPage(driver);
        addToCartPage.btnContinueShopping.click();
        addToCartPage.doAddToCart();
        WebElement cartBadge3 = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        cartBadge3.click();

        // Assertion to verify multiple items add

        String successfullyMsgActual = addToCartPage.txtValidationOne.getText();
        String expectedTxt = "Sauce Labs Backpack";
        Assert.assertTrue(successfullyMsgActual.contains(expectedTxt));

        String actualTxt = addToCartPage.txtValidationTwo.getText();
        String expected = "Sauce Labs Bike Light";
        Assert.assertTrue(actualTxt.contains(expected));

        Allure.description("User can multiple item should be added");

    }
}
