package Android.appium;

import static org.junit.Assert.assertTrue;

import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class uappTest extends chromebrowser {

	@BeforeTest
	public void LaunchApp() throws Throwable {
		File f = new File("src");
		File fs = new File(f, "Udemy.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_XL_API_29");
		capabilities.setCapability(CapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		capabilities.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
	    capabilities.setCapability("unicodeKeyboard",true);
	    capabilities.setCapability("resetKeyboard",true);
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.udemy.android");
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		// expwait.
		Thread.sleep(5000);

	}

	// @Test
	public void BrowseMenu(String action) throws InterruptedException {
		String act;
		driver.findElement(By.id("browse_button")).click();
		Thread.sleep(5000);
		List<AndroidElement> allele = (List<AndroidElement>) driver
				.findElements(By.xpath("//*[@class='android.widget.TextView']"));
		for (int i = 0; i < allele.size(); i++) {
			act = allele.get(i).getText();
			System.out.println(act);
			if (act.equalsIgnoreCase(action)) {
				System.out.println(act + " is displayed");
				allele.get(i).click();
				break;
			}
		}

	}

	@Test
	public void Search() throws InterruptedException {

		BrowseMenu("Search");
		MobileElement src = (MobileElement) driver.findElement(By.xpath("//*[contains(@resource-id,'search_bar_text')]"));
		chromebrowser.expwait(src);
		// act.sendKeys("Appium").build().perform();
		src.click();
		src.sendKeys("appium");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(@resource-id,'suggestions_list')]/descendant::android.widget.TextView[@text='appium']")).click();
		Thread.sleep(5000);
	}
	
	@Test
	public void SelectCourse() throws InterruptedException {
		String course;
		List<AndroidElement> courselist = (List<AndroidElement>) driver
				.findElements(By.xpath("//*[contains(@resource-id,'course_title')]"));
		for (int i = 0; i < courselist.size(); i++) {
			course = courselist.get(i).getText();
			System.out.println(course);
			if (course.contains("Scratch")) {
				System.out.println(course + " is displayed");
				courselist.get(i).click();
				Thread.sleep(5000);
				break;
			}
		}
	}
	
	public void scrollToAnElementByText(String text) {
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector())" +
                ".scrollIntoView(new UiSelector().text(\"" + text + "\"));"));
	}
	
	@AfterTest
	public void GetRating() {
		String rating;
		scrollToAnElementByText("average rating");
		rating = driver.findElement(By.xpath("//*[contains(@resource-id,'rating_summary')]//*[contains(@resource-id,'rating')]")).getText();
		System.out.println(rating);
	}
}
