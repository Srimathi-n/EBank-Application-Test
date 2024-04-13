import Pages.HomePage;
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

public class HomePageTest {
    public WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\vijay\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qaebank.ccbp.tech/ebank/login");
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);

        loginPage.loginToApp("142420","231225");
        String expectedUrl = "https://qaebank.ccbp.tech/";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test
    public void testHomePageUI(){
        Assert.assertTrue(homePage.findAppLogoImage().isDisplayed(),"App logo is not displayed");
        Assert.assertEquals(homePage.getHeadingText(),"Your Flexibility, Our Excellence", "Heading text does not match");
        Assert.assertTrue(homePage.digitalCardImage().isDisplayed(), "Digital card image is not displayed");
    }
    @Test(priority = 1)
    public void testLogoutFunction(){
        homePage.clickLogoutBtn();
        String expectedUrl = "https://qaebank.ccbp.tech/ebank/login";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "URLs do not match");
    }

}
