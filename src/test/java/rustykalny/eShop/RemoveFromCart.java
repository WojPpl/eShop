package rustykalny.eShop;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

public class RemoveFromCart {

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
    }

    @After
    public void teardown() {
        if (driver != null && driverChrome != null) {
            driver.quit();
            driverChrome.quit();
        }
    }

    @Test
    public void firefoxTest() {
        testContent(driver);
    }

    @Test
    public void chromeTest() {
        testContent(driverChrome);
    }

    public void testContent(WebDriver webdriver) {
        webdriver.get("http://rustykalnydev.pl/index.php");
        webdriver.findElement(By.linkText("Koszyk(1)")).click();
        webdriver.findElement(By.className("no-items"));
    }
}
