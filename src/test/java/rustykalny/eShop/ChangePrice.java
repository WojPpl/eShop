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


public class ChangePrice {
    private WebDriver driver, driverChrome;
    private String className = this.getClass().getSimpleName();
    private String testDesciption = "test text";
    //private String baseUrl;

    ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter("testReports/" + className + ".html");


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
            extent.createTest(splitCamelCase(className) + " - Firefox browser")
                    .log(Status.PASS, testDesciption);
        } catch (Exception e) {
            extent.createTest(splitCamelCase(className) + " - Firefox browser")
                    .log(Status.FAIL, e.getMessage());
        }
    }

    @Test
    public void chromeTest() {
        try {
            testContent(driverChrome);
            extent.createTest(splitCamelCase(className) + " - Chrome browser")
                    .log(Status.PASS, testDesciption);
        } catch (Exception e) {
            extent.createTest(splitCamelCase(className) + " - Chrome browser")
                    .log(Status.FAIL, e.getMessage());
        }
    }
    
    static String splitCamelCase(String s) {
        return s.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );
    }

    public void testContent(WebDriver driver) {
        driver.get("http://rustykalnydev.pl/index.php?id_product=3045&rewrite=mauretanska-lampa-moulawlidat&controller=product");
        driver.findElement(By.id("total-price")).click();
        driver.findElement(By.xpath("//button[@type='button']")).click();
        driver.findElement(By.xpath("//button[@type='button']")).click();
        driver.findElement(By.xpath("//button[@type='button']")).click();
        driver.findElement(By.id("total-price")).click();
    }

}
