package eShop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ChangePrice {
	WebDriver driver;

	public void launchBrowser() {
		System.setProperty("webdriver.gecko.driver", "seleniumWebDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://rustykalnydev.pl/index.php?id_product=2858&rewrite=plytki-cementowe-403&controller=product");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ChangePrice  obj = new ChangePrice();
		obj.launchBrowser();

	}

}
