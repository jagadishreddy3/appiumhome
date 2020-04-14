package homeJini;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class BaseClass {
	public static AndroidDriver<AndroidElement> driver;
	public static WebDriverWait expwait;
	public static HashMap<String, String> hm;
	public static AppiumDriverLocalService appiumService;

	@BeforeSuite
	public void LaunchApp() throws Throwable {
		appiumstart();
		//Process p=Runtime.getRuntime().exec("adb -s emulator-5554 shell strace -p 871");
		File f = new File("src");
		File fs = new File(f, configdata("appname"));
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, configdata("devicename"));
		capabilities.setCapability(CapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		capabilities.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		// capabilities.setCapability("unicodeKeyboard",true);
		// capabilities.setCapability("resetKeyboard",true);
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, configdata("apppackage"));
		driver = new AndroidDriver<>( appiumService.getUrl(), capabilities);
		// expwait.new URL(configdata("url"))
		Thread.sleep(5000);

	}

	public static void expwait(By locator) throws NumberFormatException, IOException {
		expwait = new WebDriverWait(driver, Integer.parseInt(configdata("wait")));
		expwait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static void scrollToAnElementByText(String text) {
		driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector())" + ".scrollIntoView(new UiSelector().text(\"" + text + "\"));"));
	}

	public static void swipeVertical(double startPercentage, double finalPercentage, double anchorPercentage,
			int duration) throws Exception {
		Dimension size = driver.manage().window().getSize();
		int anchor = (int) (size.width * anchorPercentage);
		int startPoint = (int) (size.height * startPercentage);
		int endPoint = (int) (size.height * finalPercentage);
		(new TouchAction(driver)).press(PointOption.point(anchor, startPoint))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
				.moveTo(PointOption.point(anchor, endPoint)).release().perform();
	}

	public static void scrollbydimen() {
		Dimension size = driver.manage().window().getSize();
		int percentage = size.width / 2;
		int startingPoint = (int) ((double) size.height * 0.8D);
		int endingPoint = (int) ((double) size.height * 0.2D);
		(new TouchAction(driver)).press(PointOption.point(percentage, startingPoint))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2L)))
				.moveTo(PointOption.point(percentage, endingPoint)).release().perform();
	}

	public static String configdata(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\jagadishwarg\\eclipse-workspace\\\\appium\\config.properties");
		prop.load(fis);
		String value = prop.getProperty(key);
		return value;
	}

	public static void readexcel(String TCname) throws Throwable {
		hm = new HashMap<String, String>();
		File f = new File(configdata("exclpath"));
		if (f.exists()) {
			System.out.println("Exists");
		} else {
			// createexcel();
		}
		FileInputStream fis = new FileInputStream(f);
		try {
			XSSFWorkbook new_workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = new_workbook.getSheet("TestCase_Netterm");
			int rowcount = sheet.getLastRowNum();
			int columncount = sheet.getRow(0).getLastCellNum();
			for (int i = 8; i <= 20; i++) {
				Row firstrow = sheet.getRow(7);
				Row row = sheet.getRow(i); // returns the logical row
				Cell cell = row.getCell(0); // getting the cell representing the given column
				String value = cell.getStringCellValue();
				if (value.equalsIgnoreCase(TCname)) {
					for (int j = 1; j <= 8; j++) {
						hm.put(firstrow.getCell(j).getStringCellValue(), row.getCell(j).getStringCellValue());
					}
					// sheet.getRow(i).getCell(12).setCellValue("Pass");
					break;
				}
			}
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterSuite
	public void tearDown() {
		driver.quit();
		appiumService.stop();
	}
	
	public static void appiumstart() throws IOException {
		CommandLine command = new CommandLine("/bin/sh");
		command.addArgument("--log-level" ,false);
		appiumService = AppiumDriverLocalService
		.buildService(new AppiumServiceBuilder()
				.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
				.withAppiumJS(new File("C:\\Users\\jagadishwarg\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress(configdata("ip")).usingPort(Integer.parseInt(configdata("port"))));
		appiumService.start();
	}
	
	public static void takeScreenshot(String name) throws IOException {
		Calendar cale = Calendar.getInstance();
		int day = cale.get(Calendar.DAY_OF_MONTH);
		int month = cale.get(Calendar.MONTH);
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(configdata("capturepath")+name+day+month+".jpg"));			
	}
	
}
