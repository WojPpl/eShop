package rustykalny.eShop;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class SortingList {


    ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter("testReports/Sort.html");

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
            extent.createTest("Search products - Firefox browser")
                    .log(Status.PASS, "click sort select -> choose from cheapest -> get and check price -> Test passed!");
        } catch (Exception e) {
            extent.createTest("Search products - Firefox browser")
                    .log(Status.FAIL, e.getMessage());
        }
    }

    @Test
    public void chromeTest() {
        try {
            testContent(driverChrome);
            extent.createTest("Search products - Chrome browser")
                    .log(Status.PASS, "click sort select -> choose from cheapest -> get and check price -> Test passed!");
        } catch (Exception e) {
            extent.createTest("Search products - Chrome browser")
                    .log(Status.FAIL, e.getMessage());
        }
    }

    public void testContent(WebDriver webdriver) throws InterruptedException {
        webdriver.get("http://rustykalnydev.pl/index.php?id_category=266&controller=category");
        webdriver.findElement(By.xpath("//div[@id='js-product-list-top']/div[2]/div/div/button")).click();
        webdriver.findElement(By.linkText("Cena, malejąco")).click();
        Thread.sleep(2000);
        String price = webdriver.findElement(By.xpath("//div[@id='js-product-list']/div/article/div/div/div/span[2]")).getText();
        assertTrue(price.equals("187,20 zł"));
    }
}
