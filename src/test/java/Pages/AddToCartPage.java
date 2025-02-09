package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddToCartPage {
    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    WebElement productOne;
    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    WebElement productTwo;
    @FindBy(id = "remove-sauce-labs-backpack")
    public WebElement btnRemoveItemOne;
    @FindBy(id = "remove-sauce-labs-bike-light")
    public WebElement btnRemoveItemTwo;
    @FindBy(xpath = "//button[@id='continue-shopping']")
    public WebElement btnContinueShopping;
    @FindBy(xpath = "//div[@class='inventory_item_name' and text()='Sauce Labs Backpack']")
    public WebElement txtValidationOne;
    @FindBy(xpath = "//div[@class='inventory_item_name' and text()='Sauce Labs Bike Light']")
    public WebElement txtValidationTwo;

    public AddToCartPage (WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void doAddToCart() throws InterruptedException {
        productOne.click();
        Thread.sleep(2000);
        productTwo.click();
    }
}
