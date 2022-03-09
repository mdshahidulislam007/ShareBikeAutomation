package appTestCases;

import org.testng.annotations.Test;

import appPages.LoginPage;

public class TC_01_loginTest extends BaseClass {
	
	LoginPage lp = new LoginPage();
	@Test
	public void logintest() throws Exception
	{
		System.out.println("Test started");
		lp.login();
	}
}
