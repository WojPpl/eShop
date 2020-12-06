package rustykalny.eShop;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class ChangePrice {
    private WebDriver driver, driverChrome, driverOpera;
    private String className = this.getClass().getSimpleName();
    private String testDesciption = "Open page with product Mauretańska lampa -> " +
            "get actual total price of product -> click 3 times on plus button -> " +
            "get total price product after changes -> check is it the same price-> Test passed!";

    ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter("testReports/" + className + ".html");


    @BeforeClass
    public static void setupClass() throws IOException {
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.chromedriver().setup();
        WebDriverManager.operadriver().setup();
    }

    @Before
    public void setUp() throws IOException {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driverChrome = new ChromeDriver();
        driverChrome.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driverOpera = new OperaDriver();
        driverOpera.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        extent.attachReporter(spark);

    }

    @After
    public void tearDown() throws IOException {
        if (driver != null && driverChrome != null && driverOpera != null) {
            driver.quit();
            driverChrome.quit();
            driverOpera.quit();
        }
        extent.flush();
    }

    @Test
    public void firefoxTest() throws IOException {
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
    public void chromeTest() throws IOException {
        try {
            testContent(driverChrome);
            extent.createTest(splitCamelCase(className) + " - Chrome browser")
                    .log(Status.PASS, testDesciption);
        } catch (Exception e) {
            extent.createTest(splitCamelCase(className) + " - Chrome browser")
                    .log(Status.FAIL, e.getMessage());
        }
    }

    @Test
    public void operaTest() throws IOException {
        try {
            testContent(driverOpera);
            extent.createTest(splitCamelCase(className) + " - Opera browser")
                    .log(Status.PASS, testDesciption);
        } catch (Exception e) {
            extent.createTest(splitCamelCase(className) + " - Opera browser")
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

    public void testContent(WebDriver driver) throws Exception {
        driver.get("http://rustykalnydev.pl/index.php?id_product=3045&rewrite=mauretanska-lampa-moulawlidat&controller=product");
        String actualPrice = driver.findElement(By.id("total-price")).getText();
        driver.findElement(By.xpath("//button[@type='button']")).click();
        driver.findElement(By.xpath("//button[@type='button']")).click();
        driver.findElement(By.xpath("//button[@type='button']")).click();
        String afterPrice = driver.findElement(By.id("total-price")).getText();
        if (actualPrice.contains(afterPrice)) throw new Exception();
    }

}
