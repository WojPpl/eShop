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

import java.util.concurrent.TimeUnit;

public class LoginAndLogoutBundle {
    private WebDriver driver, driverChrome, driverOpera;
    private String className = this.getClass().getSimpleName();
    private String testDesciption = "Open product page -> click on login " +
            "-> click on field email -> write email ->go to password " +
            "-> write password -> click on submit -> click on information " +
            "click on logout ->Test passed.";

    ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter("testReports/" + className + ".html");


    @BeforeClass
    public static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.chromedriver().setup();
        WebDriverManager.operadriver().setup();
    }

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driverChrome = new ChromeDriver();
        driverChrome.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driverOpera = new OperaDriver();
        driverOpera.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        extent.attachReporter(spark);

    }

    @After
    public void tearDown() throws Exception {
        if (driver != null && driverChrome != null && driverOpera != null) {
            driver.quit();
            driverChrome.quit();
            driverOpera.quit();
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

    @Test
    public void operaTest() {
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

    public void testContent(WebDriver driver) {
        driver.get("http://rustykalnydev.pl/index.php");
        driver.findElement(By.cssSelector("path.icofill.icostr2")).click();
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("test@test.pl");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("test1");
        driver.findElement(By.id("submit-login")).click();
        driver.findElement(By.xpath("//a[@id='identity-link']/span")).click();
        driver.get("http://rustykalnydev.pl/index.php?controller=identity");
        driver.findElement(By.cssSelector("path.icofill.icostr2")).click();
    }


}
