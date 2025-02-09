package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LockedOutPage {
    @FindBy(id = "user-name")
    WebElement txtUserName;
    @FindBy(id = "password")
    WebElement txtPassword;
    @FindBy(id = "login-button")
    public WebElement btnLogin;

    public LockedOutPage (WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void doLogin(String userName, String password) throws InterruptedException {
        txtUserName.sendKeys(userName);
        Thread.sleep(1000);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }
}
