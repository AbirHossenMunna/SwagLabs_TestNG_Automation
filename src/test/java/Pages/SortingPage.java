package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SortingPage {
    @FindBy(xpath = "//select[@class='product_sort_container']")
    WebElement dropdown;
    @FindBy(xpath = "//option[contains(text(),'Price (low to high)')]")
    public WebElement priceLowToHigh;
    @FindBy(xpath = "//option[contains(text(),'Price (high to low)')]")
    public WebElement priceHighToLow;
    @FindBy(xpath = "//option[contains(text(),'Name (A to Z)')]")
    public WebElement nameA_Z;
    @FindBy(xpath = "//option[contains(text(),'Name (Z to A)')]")
    public WebElement nameZ_A;

    public SortingPage (WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void doSorting(){
        dropdown.click();
    }
}
