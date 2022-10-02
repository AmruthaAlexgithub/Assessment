package com.assessment;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumTest {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public WebDriver launchDriver() {
		ChromeOptions chromeOptions = new ChromeOptions();
		WebDriverManager.chromedriver().driverVersion("105.0.5195.127").setup();
		driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		System.out.println("launching chrome browser");
		driver.get("https://demoqa.com");
		String expectedTitle = "ToolsQA";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		return driver;
	}

	public void login() throws InterruptedException {
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		WebElement bookStore = driver.findElement(By.xpath("//h5[text()='Book Store Application']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bookStore);
		Thread.sleep(500);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5[text()='Book Store Application']")))
				.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='login']"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='userName']")))
				.sendKeys("Demobook");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='password']")))
				.sendKeys("Demo@123");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='login']"))).click();
	}

	@Test
	public void addBooks() throws InterruptedException {
		login();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Git Pocket Guide']"))).click();
		WebElement addCollection = driver.findElement(By.xpath("//button[text()='Add To Your Collection']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addCollection);
		Thread.sleep(500);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Add To Your Collection']")))
				.click();
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void deleteBooks() throws InterruptedException {
		login();
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//a[text()='Learning JavaScript Design Patterns']"))).click();
		WebElement addCollection = driver.findElement(By.xpath("//button[text()='Add To Your Collection']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addCollection);
		Thread.sleep(500);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Add To Your Collection']")))
				.click();
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread.sleep(5000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Profile']"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"(//a[text()='Learning JavaScript Design Patterns']/parent::*/parent::*/parent::*/following-sibling::*)[3]//span")))
				.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='closeSmallModal-ok']"))).click();
		Thread.sleep(1000);
		try {
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			alert.accept();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void closeBowser() {
		driver.quit();
	}
}
