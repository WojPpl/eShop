package rustykalny.eShop;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

public class RemoveFromCart {

    ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter("testReports/removeFromCart.html");

    private WebDriver driver, driverChrome;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driverChrome = new ChromeDriver();
        driverChrome.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        extent.attachReporter(spark);
    }

    @After
    public void teardown() {
        if (driver != null && driverChrome != null) {
            driver.quit();
            driverChrome.quit();
        }
        extent.flush();
    }

    @Test
    public void firefoxTest() {
        try {
            testContent(driver);
            extent.createTest("Remove from cart - Firefox browser")
                    .log(Status.PASS, "click cart icon -> click remove icon -> check empty state -> Test passed!");
        } catch (Exception e) {
            extent.createTest("Remove from cart - Firefox browser")
                    .log(Status.FAIL, e.getMessage());
        }
    }

    @Test
    public void chromeTest() {
        try {
            testContent(driverChrome);
            extent.createTest("Remove from cart - Chrome browser")
                    .log(Status.PASS, "click cart icon -> click remove icon -> check empty state -> Test passed!");
        } catch (Exception e) {
            extent.createTest("Remove from cart - Chrome browser")
                    .log(Status.FAIL, e.getMessage());
        }
    }

    public void testContent(WebDriver webdriver) {
        webdriver.get("http://rustykalnydev.pl/index.php");
        webdriver.findElement(By.linkText("Koszyk(1)")).click();
        webdriver.findElement(By.xpath("//section[@id='main']/div/div[2]/div/div/ul/li/div/div/div/a/i")).click();
        webdriver.findElement(By.className("no-items"));
    }
}
