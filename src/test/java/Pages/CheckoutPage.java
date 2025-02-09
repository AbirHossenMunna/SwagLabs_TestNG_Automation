package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    WebElement productOne;
    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    WebElement productTwo;
    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    public WebElement logoAddToCart;
    @FindBy(id = "checkout")
    public WebElement btnCheckout;
    @FindBy(id = "first-name")
    public WebElement txtFirstName;
    @FindBy(id = "last-name")
    public WebElement txtLastName;
    @FindBy(id = "postal-code")
    public WebElement txtPostalCode;
    @FindBy(id = "continue")
    public WebElement btnContinue;
    @FindBy(id = "cancel")
    public WebElement btnCancel;
    @FindBy(id = "finish")
    public WebElement btnFinish;

    public CheckoutPage (WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void doCheckout() throws InterruptedException {
        productOne.click();
        Thread.sleep(2000);
        productTwo.click();
        logoAddToCart.click();
        btnCheckout.click();
    }
}
