import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginPageTest {
    public WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\vijay\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qaebank.ccbp.tech/ebank/login");
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test
    public void LoginPageUITest(){
        Assert.assertTrue(loginPage.findAppImage().isDisplayed(),"Login image is not displayed");
        Assert.assertEquals(loginPage.getTextContentAt(0),"User ID","User id label text does not match");
        Assert.assertEquals(loginPage.getTextContentAt(1),"PIN","Pin label text does not match");
        Assert.assertEquals(loginPage.getHeadingText(),"Welcome Back!","Heading text does not match");
    }
    @Test(priority = 1)
    public void testWithEmptyInputFields(){
        loginPage.clickLoginBtn();
        Assert.assertEquals(loginPage.getErrorMsgText(),"Invalid user ID","Error text with empty input fields does not match");
    }
    @Test(priority = 2)
    public void testLoginWithEmptyUserId(){
        loginPage.enterPin("231225");
        loginPage.clickLoginBtn();
        Assert.assertEquals(loginPage.getErrorMsgText(),"Invalid user ID","Error text with empty input field do not match");
    }
    @Test(priority = 3)
    public void testLoginwithEmptyPin(){
        loginPage.enterUserId("142420");
        loginPage.clickLoginBtn();
        Assert.assertEquals(loginPage.getErrorMsgText(),"Invalid PIN","Error text with empty input field do not match");
    }
    @Test(priority = 4)
    public void testLoginWithInvalidPin(){
        loginPage.enterUserId("142420");
        loginPage.enterPin("123456");
        loginPage.clickLoginBtn();
        Assert.assertEquals(loginPage.getErrorMsgText(),"User ID and PIN didn't match","Error text with invalid PIN do not match");
    }
    @Test(priority = 5)
    public void testLoginWithValidCreds(){
        loginPage.enterUserId("142420");
        loginPage.enterPin("231225");
        loginPage.clickLoginBtn();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("https://qaebank.ccbp.tech/"));
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, "https://qaebank.ccbp.tech/","URLs do not match");
    }

}
