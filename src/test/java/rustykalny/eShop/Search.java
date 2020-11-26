package rustykalny.eShop;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

public class Search {

    private WebDriver driver, driverChrome, driverOpera;


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
        webdriver.findElement(By.name("s")).click();
        webdriver.findElement(By.name("s")).clear();
        webdriver.findElement(By.name("s")).sendKeys("lampa");
        webdriver.findElement(By.xpath("//div[@id='search_widget']/form/button/i")).click();
        webdriver.findElement(By.linkText("Marokańska lampa / gwiazda...")).click();
        webdriver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
    }
}
