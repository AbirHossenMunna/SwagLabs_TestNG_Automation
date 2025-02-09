package TestRunner;

import Base.Setup;
import Pages.AddToCartPage;
import Pages.LoginPage;
import Pages.SortingPage;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortingTestRunner extends Setup {
    SortingPage sortingPage;
    LoginPage loginPage;
    Utils utils;
    @BeforeTest
    public void doLogin() throws IOException, ParseException, InterruptedException {
        loginPage = new LoginPage(driver);
        utils = new Utils();
        utils.getUserCreds(2);
        loginPage.doLogin(utils.getUserName(), utils.getPassword());
    }
    @Test(priority = 0, description = "Verify user can filtering for sorting Low to High")
    public void doSortingWithPriceLowToHigh() throws  InterruptedException {
        sortingPage= new SortingPage(driver);
        sortingPage.doSorting();
        Thread.sleep(2000);
        sortingPage.priceLowToHigh.click();

        //Assertion for Price Low to High

        //Get all product prices
        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));

        //  Convert prices to a numeric list
        List<Double> actualPrices = new ArrayList<>();
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replace("$", ""); // Remove "$" sign
            actualPrices.add(Double.parseDouble(priceText));
        }

        // Create a sorted copy of the price list
        List<Double> sortedPrices = new ArrayList<>(actualPrices);
        Collections.sort(sortedPrices); // Sort in ascending order

        // Assertion to check if actual list is sorted correctly
        Assert.assertEquals(actualPrices, sortedPrices, "Prices are not sorted in ascending order!");

        System.out.println("Prices are sorted correctly from low to high.");
    }
    @Test(priority = 1, description = "Verify user can filtering for sorting High to Low")
    public void doSortingWithPriceHighToLow() throws  InterruptedException {
        sortingPage= new SortingPage(driver);
        sortingPage.doSorting();
        Thread.sleep(2000);
        sortingPage.priceHighToLow.click();

        //Assertion for Price High to Low

        //Get all product prices
        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));

        //  Convert prices to a numeric list
        List<Double> actualPrices = new ArrayList<>();
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replace("$", ""); // Remove "$" sign
            actualPrices.add(Double.parseDouble(priceText));
        }

        // Create a sorted copy of the price list
        List<Double> sortedPrices = new ArrayList<>(actualPrices);
        sortedPrices.sort(Collections.reverseOrder()); // Sort in descending  order

        // Assertion to check if actual list is sorted correctly
        Assert.assertEquals(actualPrices, sortedPrices, "Prices are not sorted in descending order!");

        System.out.println("Prices are sorted correctly from high to low.");
    }
    @Test(priority = 2, description = "Verify user can filtering for sorting Name Z to A")
    public void doSortingWithNameZToA() throws  InterruptedException {
        sortingPage= new SortingPage(driver);
        sortingPage.doSorting();
        Thread.sleep(2000);
        sortingPage.nameZ_A.click();

        //Get all product prices
        List<WebElement> nameElements = driver.findElements(By.className("inventory_item_name"));

        //Store product names in a list
        List<String> actualNames = new ArrayList<>();
        for (WebElement nameElement : nameElements) {
            actualNames.add(nameElement.getText().trim());
        }

        // Create a sorted copy in reverse alphabetical order
        List<String> sortedNames = new ArrayList<>(actualNames);
        sortedNames.sort(Collections.reverseOrder()); // Sort in ascending order (Z to A)

        // Assertion to check if actual list is sorted correctly
        Assert.assertEquals(actualNames, sortedNames, "Product names are not sorted in ascending (Z to A) order!");

        System.out.println("Product names are sorted correctly from Z to A.");
    }
    @Test(priority = 3, description = "Verify user can filtering for sorting Name A to Z")
    public void doSortingWithNameAToZ() throws  InterruptedException {
        sortingPage= new SortingPage(driver);
        sortingPage.doSorting();
        Thread.sleep(2000);
        sortingPage.nameA_Z.click();

        //Get all product prices
        List<WebElement> nameElements = driver.findElements(By.className("inventory_item_name"));

        //Store product names in a list
        List<String> actualNames = new ArrayList<>();
        for (WebElement nameElement : nameElements) {
            actualNames.add(nameElement.getText().trim());
        }

        // Create a sorted copy in alphabetical order
        List<String> sortedNames = new ArrayList<>(actualNames);
        sortedNames.sort(String::compareTo); // Sort in ac order (A to Z)

        // Assertion to check if actual list is sorted correctly
        Assert.assertEquals(actualNames, sortedNames, "Product names are not sorted in ascending (A to Z) order!");

        System.out.println("Product names are sorted correctly from A to Z.");
    }
}
