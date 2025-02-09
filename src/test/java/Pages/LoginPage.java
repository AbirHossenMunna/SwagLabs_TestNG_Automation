package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(id = "user-name")
    WebElement txtUserName;
    @FindBy(id = "password")
    WebElement txtPassword;
    @FindBy(id = "login-button")
    public WebElement btnLogin;
    @FindBy(xpath = "//h3[text()='Epic sadface: Username and password do not match any user in this service']")
    public WebElement lblErrorMessage;
    @FindBy(xpath = "//div[@class='app_logo']")
    public WebElement lblValidationMessage;
    @FindBy(id = "react-burger-menu-btn")
    public WebElement menuBar;
    @FindBy(id = "logout_sidebar_link")
    public WebElement linkLogout;

    public LoginPage (WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void doLogin(String userName, String password) throws InterruptedException {
        txtUserName.sendKeys(userName);
        Thread.sleep(1000);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }
    public void clearCredential() {
        txtUserName.sendKeys(Keys.CONTROL, "a");
        txtUserName.sendKeys(Keys.BACK_SPACE);
        txtPassword.sendKeys(Keys.CONTROL, "a");
        txtPassword.sendKeys(Keys.BACK_SPACE);
    }

}
