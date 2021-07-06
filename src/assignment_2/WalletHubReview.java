package assignment_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WalletHubReview {

	public static void main(String[] args) {

		String URL = "http://wallethub.com/profile/test_insurance_company/";

		String emailAddress = "your email address";
		String password = "your password";

		String reviewDescription = "A health insurance policy is a contract between an insurance provider.(e.g. an insurance company or a government)"
				+ " and an individual or his/her sponsor (that may be employer or a community organization).";

		// Configuring Property - provide the path to chormedirver.exe in the value
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

		// Disabling Notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-notifications");

		// Launching Chrome Browser
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		// Navigate to wallethub - insurance
		driver.get(URL);

		// Wait
		WebDriverWait wait = new WebDriverWait(driver, 30);

		// Login
		WebElement Login = driver.findElement(By.xpath("//span[text()='Login']"));
		Login.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		driver.findElement(By.id("email")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath("//span[text()='Login']")).click();
		waitTime(5000);

		// Navigate to Reviews Section and select 4th star
		WebElement reviewSection = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='nav-txt'][text()='Reviews']")));
		reviewSection.click();

		Actions mouseHoverAction = new Actions(driver);
		WebElement reviewStar4 = driver
				.findElement(By.cssSelector("review-star[class='rvs-svg']>div>svg:nth-of-type(4)"));
		mouseHoverAction.moveToElement(reviewStar4).build().perform();
		waitTime(5000);
		reviewStar4.click();

		// select Health Insurance from dropdown
		WebElement dropdownSelect = driver.findElement(By.xpath("//span[contains(text(),'Select...')]"));
		dropdownSelect.click();
		waitTime(5000);

		WebElement dropdownvalue = driver.findElement(By.xpath(
				"//ul[@class='dropdown-list ng-enter-element']//li[@class='dropdown-item' and text()='Health Insurance']"));
		dropdownvalue.click();

		// Review Description
		WebElement review = driver.findElement(By.cssSelector(".textarea.wrev-user-input.validate"));
		review.sendKeys(reviewDescription);
		waitTime(2000);

		// Submit Review
		WebElement submitReview = driver.findElement(By.xpath("//div[text()='Submit']"));
		mouseHoverAction.moveToElement(submitReview).build().perform();
		waitTime(2000);
		submitReview.click();
		waitTime(6000);

		// Verify review
		driver.get(URL);
		waitTime(5000);
		WebElement reviewVerify = driver.findElement(By.xpath("//span[text()=' Your Review']"));
		if (reviewVerify.isDisplayed()) {
			System.out.println("Successully Reviewed");
		} else {
			System.out.println("Review Not Successfull");

		}
		driver.quit();
	}

	static void waitTime(long waitTime) {
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
