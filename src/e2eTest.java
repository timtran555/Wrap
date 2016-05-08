import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class e2eTest {
	private WebDriver driver;
	
	//Arguments
	private String homeUrl = "https://www.wrap.co/index/";
	private String eMailAdd = "test12@yahoo.com";
	private String userName = "test12";
	private String password = "password0000";
	private String firstName ="Firstname";
	private String lastName = "Lastname";
	private String companyName = "My Company";
	private String phoneNumber = "4085558888";
	private String aTemplate ="'App Download'";
	
	//Selectors
	private String freeTrial = "//span[contains(text(),'Free trial')]";
	private String signUp = "//a[text()='Sign Up']";
	private String signUpTitle = "//h3[text()='Sign Up']";
	private String email = ".o-auth-input";
	private String userN = "input[placeholder='Create a username']";
	private String pword = "input[placeholder='Password']";
	private String submitButton = ".o-auth-button[type='submit']";
	private String fNam = "input[placeholder='First Name *']";
	private String lNam = "input[placeholder='Last Name *']";
	private String company = "input[placeholder='Company *']";
	private String phoneN = "input[placeholder='Phone Number']";
	private String closeButton = ".help-tour_nav--close";
	private String createNewWrap = ".wraps_create-btn";
	private String publish = "//button[text()='Publish']";
	private String successMessage = "h4.modal-title";


	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void endtoendTest() throws Exception {
		navigatetoHomePage();
		maximizeWindow();
		clickFreeTrial();
		clickSignUp();		
		verifySignUpHeader();
		inputEmail(eMailAdd);
		clickSubmit();
		createUserName(userName);
		createPassword(password);
		Thread.sleep(2000);
		clickSubmit();
		inputFirstName(firstName);
		inputLastName(lastName);
		inputCompany(companyName);
		inputPhoneNumber(phoneNumber);
		clickSubmit();
		clickCreateNewWrap();
		pickATemplate(aTemplate);
		closeHelpTour();
		Thread.sleep(2000);
		clickPublish();		
		closeHelpTour();
		verifyPublishSuccessful();
		System.out.println("Pass");
	}
	

	@After
	public void tearDown() throws Exception {
		//Take screenshot
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("screenshot.png"));
		//quit
        driver.quit();
	}
	

	//Methods
	private void navigatetoHomePage() {
		driver.get(homeUrl);
	}
	private void maximizeWindow() {
		driver.manage().window().maximize();
	}
	private void clickFreeTrial() {
		driver.findElement(By.xpath(freeTrial)).click();
	}
	private void clickSignUp() {
		driver.findElement(By.xpath(signUp)).click();
	}
	private void verifySignUpHeader() {
		String signUpHeader = driver.findElement(By.xpath(signUpTitle)).getText();
		Assert.assertEquals("SIGN UP",signUpHeader);
	}
	private void inputEmail(String eMail) {
		driver.findElement(By.cssSelector(email)).sendKeys(eMail);
	}
	private void createUserName(String urName) {
		driver.findElement(By.cssSelector(userN)).sendKeys(urName);
	}
	private void createPassword(String pw) {
		driver.findElement(By.cssSelector(pword)).sendKeys(pw);
	}
	private void clickSubmit() {
		driver.findElement(By.cssSelector(submitButton)).click();
	}
	private void inputFirstName(String fName) {
		driver.findElement(By.cssSelector(fNam)).sendKeys(fName);
	}
	private void inputLastName(String lName) {
		driver.findElement(By.cssSelector(lNam)).sendKeys(lName);
	}
	private void inputCompany(String comName) {
		driver.findElement(By.cssSelector(company)).sendKeys(comName);
	}
	private void inputPhoneNumber(String pNumber) {
		driver.findElement(By.cssSelector(phoneN)).sendKeys(pNumber);
	}
	private void clickCreateNewWrap() {
		driver.findElement(By.cssSelector(createNewWrap)).click();
	}
	private void pickATemplate(String template) {
		driver.findElement(By.xpath("//label[@title="+template+"]/../div/button[contains(text(),'Use')]")).click();
	}
	private void closeHelpTour() {
		driver.findElement(By.cssSelector(closeButton)).click();
	}
	private void clickPublish() {
		driver.findElement(By.xpath(publish)).click();
	}
	private void verifyPublishSuccessful() {
		String publishSuccessfulMessage = driver.findElement(By.cssSelector(successMessage)).getText();
		Assert.assertEquals("Publish Successful", publishSuccessfulMessage);	
	}
}
