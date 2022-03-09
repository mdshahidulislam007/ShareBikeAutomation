package appTestCases;

import static io.appium.java_client.touch.offset.PointOption.point;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofSeconds;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

public class BaseClass {

	public static AppiumDriver<MobileElement> driver;

	@BeforeTest
	public void setup() throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "Galaxy Tab A7 Lite");
		caps.setCapability("udid", "R8KR50035QP");
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "11");
		caps.setCapability("appPackage", "com.sharebike.android.bigissue");
		caps.setCapability("appActivity", "com.sharebike.android.MainActivity");
		caps.setCapability("autoGrantPermissions",true);


		/* { "deviceName": "Galaxy Tab A7 Lite", "udid": "R8KR50035QP",
		  "platformName": "Android", "platformVersion": "11", "appPackage":
		  "com.sharebike.android.bigissue", "appActivity":
		  "com.sharebike.android.MainActivity" }
		 */

		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AppiumDriver<MobileElement>(url, caps);
	}

	public static void clickElement (By element) throws  Exception{

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait webDriverWait = new WebDriverWait(driver,60);
		webDriverWait.until(ExpectedConditions.elementToBeClickable (element));


		driver.findElement(element).click();
		Thread.sleep(1000);

	}

	public static void verifyElementAndDataEntry(AppiumDriver<MobileElement> driver2, By element,String value) throws Exception {

		boolean elementResult=driver2.findElement(element).isDisplayed();
		clickElement(element);
		driver2.findElement(element).clear();

		if(elementResult==true){

			System.out.println("The ID: " +element+ "element is visible");
		}
		else{

			System.out.println("The ID: " +element+ "element is not visible");
		}

		driver2.findElement(element).sendKeys(new String[] { value });


	}


	public static void  tabByCordinate(AppiumDriver<MobileElement> driver,int x,int y,long seconds) throws Exception {
		
		TouchAction action = new TouchAction(driver);
		action.press(point(x,y))
        .waitAction(waitOptions(ofSeconds(seconds)))
        .release()
        .perform();	
	}

	public static void  SendValueNpressEnter(AppiumDriver<MobileElement> driver, By element,String value) throws Exception {
		//clickElement(element);

		driver.findElement(element).sendKeys(value); 
		Thread.sleep(1000);
		driver.findElement(element).sendKeys(Keys.ENTER);
	}

	public boolean isPresent(AppiumDriver<MobileElement> driver,By element) throws  Exception{


		List<MobileElement> elements=driver.findElements(element);
		if(elements.size()>0) {

			return true;

		}else{

			return false;
		}


	}

	/*
	 * @AfterTest public void closeTheApp( ) { driver.closeApp(); }
	 */
}

