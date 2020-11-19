package eShop.Wojp;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxDriver;



public class rustykalnyTest {

    @Test
    public void addToCart()  {
        System.setProperty("webdriver.gecko.driver", "/home/wojp/eShop/src/drivers/geckodriver");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
/*      firefoxOptions.addArguments("--headless");
        firefoxOptions.addArguments("--no-sandbox");*/
        WebDriver driver = new FirefoxDriver(firefoxOptions);
        driver.get("http://rustykalnydev.pl/index.php?id_product=3044&rewrite=lampa-orientalna-maha-niebieska&controller=product");
        driver.findElement(By.className("add-to-cart")).click();
        driver.findElement(By.cssSelector("a.btn-primary")).click();
    }


    public static void main(String[] args) {

    }
}