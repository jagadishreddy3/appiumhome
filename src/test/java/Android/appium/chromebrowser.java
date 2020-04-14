package Android.appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class chromebrowser {
	public static AndroidDriver<AndroidElement> driver;
	public static WebDriverWait expwait;
	//driver.manage().timeouts().implicitlyWait(20L,TimeUnit.SECONDS);
	//WebDriverWait expwait = new WebDriverWait(driver,20);
	public  void chromebrow() throws MalformedURLException{
		File f = new File("src");
		File fs = new File(f,"Udemy.apk");
	    DesiredCapabilities  capabilities = new DesiredCapabilities();
	    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel_XL_API_29");
	    capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
	    capabilities.setCapability(CapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
	    capabilities.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE,"C://Users//jagadishwarg//Downloads//chromedriver_win32//chromedriver.exe");
	    //capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".activity.PinterestActivity ");
	    //capabilities.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
	    //capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.pintrest");    
	    AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	    driver.get("http://www.yahoo.com");
	    
	}
	
	public static void expwait(MobileElement loc) {
		expwait = new WebDriverWait(driver,20);
		expwait.until(ExpectedConditions.visibilityOf(loc));
	}
}
