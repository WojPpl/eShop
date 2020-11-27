package rustykalny.eShop;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Logout {
    ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter("testReports/" + this.getClass().getSimpleName() + ".html");

    private WebDriver driver, driverChrome;
    private String baseUrl;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driverChrome = new ChromeDriver();
        driverChrome.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        extent.attachReporter(spark);
        // baseUrl = "https://www.google.com/";
    }

    @After
    public void tearDown() throws Exception {
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
            extent.createTest("Change price - Firefox browser")
                    .log(Status.PASS, "text......");
        } catch (Exception e) {
            extent.createTest("Change price - Firefox browser")
                    .log(Status.FAIL, e.getMessage());
        }
    }

    @Test
    public void chromeTest() {
        try {
            testContent(driverChrome);
            extent.createTest("Add to cart - Chrome browser")
                    .log(Status.PASS, "text......");
        } catch (Exception e) {
            extent.createTest("Add to cart - Chrome browser")
                    .log(Status.FAIL, e.getMessage());
        }
    }

    public void testContent(WebDriver driver) {
        driver.get("http://rustykalnydev.pl/index.php?controller=identity");
        driver.findElement(By.cssSelector("path.icofill.icostr2")).click();
    }

}
