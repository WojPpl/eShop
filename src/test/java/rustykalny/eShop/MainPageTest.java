package rustykalny.eShop;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class MainPageTest {



//    @Before
//    public void setupTest() {
//        driver = new ChromeDriver();
//    }
    @Test
    public void openPage() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.wp.pl");
    }
}