package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    @FindBy(how = How.CLASS_NAME, using = "heading")
    WebElement headingEl;

    @FindBy(how = How.CLASS_NAME, using = "ebank-logo")
    WebElement appLogoImageEl;

    @FindBy(how = How.CLASS_NAME, using = "digital-card-image")
    WebElement digitalCardImageEl;

    @FindBy(how = How.CLASS_NAME, using = "logout-button")
    WebElement logoutBtnEl;

    WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getHeadingText(){
        return headingEl.getText();
    }
    public WebElement findAppLogoImage(){
        return appLogoImageEl;
    }
    public WebElement digitalCardImage(){
        return digitalCardImageEl;
    }
    public void clickLogoutBtn(){
        logoutBtnEl.click();
    }
}
