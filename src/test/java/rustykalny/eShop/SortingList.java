package rustykalny.eShop;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class SortingList {

    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
    }

    @Before
    public void setupTest() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void firefoxTest() throws InterruptedException {
        driver.get("http://rustykalnydev.pl/index.php?id_category=266&controller=category");
        driver.findElement(By.xpath("//div[@id='js-product-list-top']/div[2]/div/div/button")).click();
        driver.findElement(By.linkText("Cena, malejąco")).click();
        Thread.sleep(2000);
        String price = driver.findElement(By.xpath("//div[@id='js-product-list']/div/article/div/div/div/span[2]")).getText();
        assertTrue(price.equals("187,20 zł"));
    }
}
