package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LogInPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	LogInPage logInPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName="contacts";
	
	public ContactsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		testUtil=new TestUtil();
		contactsPage=new ContactsPage();
		logInPage = new LogInPage();
		homePage=logInPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		//contactsPage=homePage.clickOnContactsLink();
	}
	
	@Test(priority=1)
	public void verifyContactsPageLabel() {
		Boolean cPageLabel=contactsPage.verifyContactsLabel();
		Assert.assertTrue(cPageLabel,"contacts label is missing on the page");
	}
	
	@Test(priority=2)
	public void selectContactsTest() {
		contactsPage.selectContactsByName("Alex Berth");
	}
	
	//call two times
	@Test(priority=3)
	public void selectMultiContactsTest() {
		contactsPage.selectContactsByName("Alex Berth");
		contactsPage.selectContactsByName("Akshay Singh");
		
	}
	@DataProvider
	public Object[][] getCrmTestData() {
		Object data [][]=TestUtil.getTestData(sheetName);
		return data;
		
	}
	
	@Test(priority=4, dataProvider="getCrmTestData")
	public void validateCreateNewContact(String title,String firstName, String lastName, String company) {
		homePage.clickOnNewContactLink();
		//contactsPage.createNewContact("Mr.", "Tom", "Peter", "Google");
		contactsPage.createNewContact(title, firstName, lastName, company);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
