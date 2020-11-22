package rustykalny.eShop;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

public class AddToCart {

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
        /*      firefoxOptions.addArguments("--headless");
        firefoxOptions.addArguments("--no-sandbox");*/
        driver.get("http://rustykalnydev.pl/index.php?id_product=3044&rewrite=lampa-orientalna-maha-niebieska&controller=product");
        driver.findElement(By.className("add-to-cart")).click();
        driver.findElement(By.cssSelector("a.btn-primary")).click();
        driver.findElement(By.linkText("Lampa orientalna MAHA niebieska"));
    }
}
