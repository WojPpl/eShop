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

public class Search {

    ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter("testReports/Search.html");

    private WebDriver driver, driverChrome;


    @BeforeClass
    public static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
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
                    .log(Status.PASS, "click search input -> clear search input -> enter phrase -> click search button -> find product containing phrase -> Test passed!");
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
                    .log(Status.PASS, "click search input -> clear search input -> enter phrase -> click search button -> find product containing phrase -> Test passed!");
        } catch (Exception e) {
            extent.createTest("Search products - Chrome browser")
                    .log(Status.FAIL, e.getMessage());
        }
    }

    public void testContent(WebDriver webdriver) {
        webdriver.get("http://rustykalnydev.pl/index.php");
        webdriver.findElement(By.name("s")).click();
        webdriver.findElement(By.name("s")).clear();
        webdriver.findElement(By.name("s")).sendKeys("lampa");
        webdriver.findElement(By.xpath("//div[@id='search_widget']/form/button/i")).click();
        webdriver.findElement(By.linkText("Marokańska lampa / gwiazda...")).click();
        webdriver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
    }
}
