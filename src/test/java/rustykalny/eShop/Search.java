package rustykalny.eShop;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class Search {

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
    public void firefoxTest() {
        String h1;
        driver.get("http://rustykalnydev.pl/index.php");
        driver.findElement(By.name("s")).click();
        driver.findElement(By.name("s")).clear();
        driver.findElement(By.name("s")).sendKeys("lampa");
        driver.findElement(By.linkText("Mosiężna lampa HIND")).click();
        h1 = driver.findElement(By.cssSelector("h1.product-name")).getText();
       // assertTrue(h1 == "Mosiężna lampa HIND");
    }
}
