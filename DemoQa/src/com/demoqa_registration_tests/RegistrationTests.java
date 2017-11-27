package com.demoqa_registration_tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests {
	WebDriver driver;
	String url = "http://demoqa.com/registration/";

	//
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/sidkr/Documents/Libraries/drivers/chromedriver.exe");
		this.driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(url);
	}

	@Test
	public void weakPasswordTest() {
		fillRegForm();
		enterPasswordAndConfirmPassword("DemoQa", "DemoQa");
		submit();
		WebElement message = driver.findElement(By.id("piereg_passwordStrength"));
		assertTrue(message.isDisplayed());
		assertEquals(message.getText(), "Very weak");
	}

	@Test
	public void invalidPasswordTest() {
		fillRegForm();
		enterPasswordAndConfirmPassword("", "");
		submit();
		WebElement message = driver.findElement(By.cssSelector(".legend.error"));
		assertTrue(message.isDisplayed());
		assertEquals(message.getText(), "* This field is required");
	}

	@Test
	public void strongPassword() {
		fillRegForm();
		enterPasswordAndConfirmPassword("DemoQaPookieTimkaPichu", "DemoQaPookieTimkaPichu");
		WebElement message = driver.findElement(By.id("piereg_passwordStrength"));
		assertTrue(message.isDisplayed());
		assertEquals(message.getText(), "Strong");
		submit();
		message = driver.findElement(By.cssSelector(".piereg_login_error"));
		assertTrue(message.isDisplayed());
		assertEquals(message.getText(), "Error: Username already exists");

	}

	private void fillRegForm() {

		// First Name
		this.driver.findElement(By.id("name_3_firstname")).sendKeys("Sidney");

		// Last Name
		driver.findElement(By.id("name_3_lastname")).sendKeys("Rosato");

		// Select material status Married
		driver.findElement(
				By.xpath("html/body/div[1]/div/div[1]/main/article/div/div/div[3]/form/ul/li[2]/div/div/input[2]"))
				.click();

		// Select Hobby Cricket
		WebElement cricketRadioBtn = driver.findElement(
				By.xpath("html/body/div[1]/div/div[1]/main/article/div/div/div[3]/form/ul/li[3]/div/div[1]/input[3]"));
		cricketRadioBtn.click();

		// Select Country
		WebElement month_dropdown = driver.findElement(By.id("dropdown_7"));
		Select month_dd = new Select(month_dropdown);
		month_dd.selectByValue("Australia");

		// Select Month 6
		WebElement month_dropdown1 = driver.findElement(By.id("mm_date_8"));
		Select month_dd1 = new Select(month_dropdown1);
		month_dd1.selectByValue("6");

		// Select Day 6
		WebElement month_dropdown2 = driver.findElement(By.name("date_8[date][dd]"));
		Select month_dd2 = new Select(month_dropdown2);
		month_dd2.selectByValue("6");

		// Select Year 1998
		WebElement month_dropdown3 = driver.findElement(By.id("yy_date_8"));
		Select month_dd3 = new Select(month_dropdown3);
		month_dd3.selectByValue("1998");

		// Phone Number
		driver.findElement(By.id("phone_9")).sendKeys("3012259874");

		// UserName
		driver.findElement(By.id("username")).sendKeys("Timka");

		// Email
		driver.findElement(By.id("email_1")).sendKeys("Timka@gmail.com");

		// Profile Picture
		WebElement chooseFile = driver.findElement(By.id("profile_pic_10"));
		String filePath = "C:/Users/sidkr/OneDrive/Pictures/Pug_portrait.jpg";
		chooseFile.sendKeys(filePath);

		// About Yourself
		driver.findElement(By.id("description")).sendKeys("I am an Automation Engineer");

	}

	private void enterPasswordAndConfirmPassword(String password, String passwordConf) {
		driver.findElement(By.id("password_2")).sendKeys(password);
		driver.findElement(By.id("confirm_password_password_2")).sendKeys(passwordConf);
	}

	private void submit() {
		driver.findElement(By.name("pie_submit")).click();
	}

	@AfterMethod
	public void closeWindow() {
		driver.quit();
	}

}