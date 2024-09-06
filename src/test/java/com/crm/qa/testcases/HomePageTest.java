package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LogInPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase {
	LogInPage logInPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	public HomePageTest() {
		super();
	}	
	//test cases should be separated---independent with each other
	//before each test case-- launch the browser and log in
	//@test -- execute test case
	//after each test case -- close the browser
	
	@BeforeMethod
	public void setUp() {
		initialization();
		testUtil=new TestUtil();
		contactsPage=new ContactsPage();
		logInPage = new LogInPage();
		homePage=logInPage.login(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		String homePageTitle=homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "CRMPRO","Home Page Title not matched");
			
	}
	
	@Test(priority=2)
	public void verifyUserName() {
		testUtil.switchToFrame();
		Boolean cUserName=homePage.verifyCorrectUserName();
		Assert.assertTrue(cUserName);
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest() {
		testUtil.switchToFrame();
		contactsPage=homePage.clickOnContactsLink();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
