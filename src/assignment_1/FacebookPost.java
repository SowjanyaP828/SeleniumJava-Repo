package assignment_1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FacebookPost {

	public static void main(String args[]) {

		WebDriver driver;
		String URL = "https://www.facebook.com/";

		// username and password changeable
		String username = "your username";
		String password = "your password";

		// Configuring Property - provide the path to chormedirver.exe in the value
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

		// Disabling notifications of browser
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-notifications");

		// Launching browser
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		driver.get(URL);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		try {
			driver.findElement(By.cssSelector("button[title='Accept all']")).click();
		} catch (NoSuchElementException e) {
			System.out.println("No cookies popup");
		}

		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));

		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys(username);
		WebElement pwd = driver.findElement(By.id("pass"));
		pwd.sendKeys(password);

		// Login
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));
		WebElement login = driver.findElement(By.name("login"));
		login.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector(".m9osqain.a5q79mjw.gy2v8mqq.jm1wdb64.k4urcfbm.qv66sw1b,span#What"))).click();

		// Post Hello World
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div._1mj._1mj")))
				.sendKeys("Hello World");

		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Post'][role='button']")))
				.click();

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		// Verify
		String actualPost = driver.findElement(By.cssSelector("div.kvgmc6g5.cxmmr5t8.oygrvhab.hcukyx3x.c1et5uql"))
				.getText();

		// Verification
		if (actualPost.equals("Hello World")) {
			System.out.println("Successfully Posted Hello World.");
		} else {
			System.out.println("Incorrect Post or not Sucessfully posted");
		}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.quit();
	}

}
