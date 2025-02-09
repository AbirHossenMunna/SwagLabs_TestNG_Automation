package TestRunner;

import Base.Setup;
import Pages.LockedOutPage;
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

public class LockedOutTestRunner extends Setup {
    LockedOutPage lockedOutPage;
    String username;
    String password;

    public void basicInfo(){
        username ="locked_out_user";
        password ="secret_sauce";
    }

    @Test(priority = 0, description = "Verify that a locked-out user cannot log in")
    public void doLoginWithLockedOut () throws IOException, ParseException, InterruptedException {
        Thread.sleep(3000);
        lockedOutPage = new LockedOutPage(driver);

        basicInfo();
        lockedOutPage.doLogin(username,password);

        String actual = driver.findElement(By.xpath("//h3[@data-test='error' and contains(text(), " +
                "'Sorry, this user has been locked out.')]")).getText();
        String expected = "Sorry, this user has been locked out.";
        Assert.assertTrue(actual.contains(expected));
        Allure.description("Locked out users try to login to the system");
    }
}
