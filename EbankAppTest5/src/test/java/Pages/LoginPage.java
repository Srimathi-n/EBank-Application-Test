package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginPage {
    @FindBy(how = How.CLASS_NAME, using = "login-ebank-image")
    WebElement appImageLocator;

    @FindBy(how = How.CLASS_NAME, using = "input-label")
    List<WebElement> listofInputLables;

    @FindBy(how = How.CLASS_NAME, using = "login-heading")
    WebElement loginHeading;

    @FindBy(id = "userIdInput")
    @CacheLookup
    WebElement usernameInputLocator;

    @FindBy(how = How.ID, using = "pinInput")
    @CacheLookup
    WebElement pinInputLocator;

    @FindBy(how = How.CLASS_NAME, using = "login-button")
    @CacheLookup
    WebElement loginBtnLocator;

    @FindBy(how = How.CLASS_NAME, using = "error-message-text")
    @CacheLookup
    WebElement errorMsgText;

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }
    public WebElement findAppImage(){
        return appImageLocator;
    }
    public String getHeadingText(){
        return loginHeading.getText();
    }
    public String getTextContentAt(int index){
        return listofInputLables.get(index).getText();
    }
    public void enterUserId(String userId){
        usernameInputLocator.sendKeys(userId);
    }
    public void enterPin(String pin){
        pinInputLocator.sendKeys(pin);
    }
    public void clickLoginBtn(){
        loginBtnLocator.click();
    }
    public void loginToApp(String userId, String pin){
        enterUserId(userId);
        enterPin(pin);
        clickLoginBtn();
    }
    public String getErrorMsgText(){
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message-text")));
         return errorMsgText.getText();
    }

}
