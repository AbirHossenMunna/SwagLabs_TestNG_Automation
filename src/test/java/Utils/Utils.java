package Utils;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public void takeScreenshot(WebDriver driver) throws IOException {
        File screenshotFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String time=new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-aa").format(new Date());
        String fileWithPath="./src/test/resources/screenshots/"+time+".png";
        File DestFile= new File(fileWithPath);
        FileUtils.copyFile(screenshotFile,DestFile);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String userName;
    private String password;

    //Read for Admin Credential. Read Json
    public void getUserCreds(int pos) throws IOException, ParseException {
        String fileName="./src/test/resources/loginUsers.json";
        JSONParser jsonParser = new JSONParser();
        Object obj =jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray=(JSONArray) obj;
        JSONObject jsonObject=(JSONObject) jsonArray.get(pos);
        setUserName((String)jsonObject.get("userName"));
        setPassword((String)jsonObject.get("password"));
    }

    public static boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;  // Element exists
        } catch (NoSuchElementException e) {
            return false; // Element does not exist
        }
    }
    public int generateRandomNumber(int min,int max){
        int randomId= (int)(Math.random() * ((max - min) + 1)) + min;
        return randomId;
    }
}
