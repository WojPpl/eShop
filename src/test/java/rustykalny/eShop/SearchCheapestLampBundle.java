package rustykalny.eShop;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class SearchCheapestLampBundle {

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
    public void firefoxTest() throws InterruptedException {
        testContent(driver);
    }

    @Test
    public void chromeTest() throws InterruptedException {
        testContent(driverChrome);
    }

    public void testContent(WebDriver webdriver) throws InterruptedException {
        webdriver.get("http://rustykalnydev.pl/index.php");
        webdriver.findElement(By.name("s")).click();
        webdriver.findElement(By.name("s")).clear();
        webdriver.findElement(By.name("s")).sendKeys("lampa");
        webdriver.findElement(By.xpath("//div[@id='search_widget']/form/button/i")).click();
        webdriver.findElement(By.xpath("//div[@id='js-product-list-top']/div[2]/div/div/button")).click();
        webdriver.findElement(By.linkText("Cena, rosnąco")).click();
        Thread.sleep(2000);
        String price = webdriver.findElement(By.xpath("//div[@id='js-product-list']/div/article/div/div/div/span[2]")).getText();
        assertTrue(price.equals("119,00 zł"));
    }

}
