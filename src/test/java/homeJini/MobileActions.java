package homeJini;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;

public class MobileActions extends PageObjects {
	public static Boolean click(By locator) throws NumberFormatException, IOException {
		expwait(locator);
		Boolean flag = false;
		try {
			driver.findElement(locator).click();
			flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (!flag) {
				System.out.println("not clicked on locator");
			} else {
				System.out.println("clicked on locator");
			}
		}

		return flag;
	}

	public static Boolean isElementPresent(By locator) {
		Boolean flag = false;
		try {
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			MobileElement we = (MobileElement) driver.findElement(locator);
			flag = we.isDisplayed();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} finally {
			if (!flag) {
				System.out.println("Element is not present");
			} else {
				System.out.println("Element is present");
			}
		}

		return flag;
	}

	public static Boolean provideInput(By locator, String data) throws Throwable {
		expwait(locator);
		Boolean flag = false;
		try {
			MobileElement we = (MobileElement) driver.findElement(locator);
			we.sendKeys(data);
			flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (!flag) {
				System.out.println("unable to enter testdata");
			} else {
				System.out.println("able to enter testdata");
			}
		}

		return flag;

	}

	public static Boolean provideInputList(By locator, String data) {
		Boolean flag = false;
		char val;
		try {
			List<AndroidElement> elelist = (List<AndroidElement>) driver.findElements(locator);
			char[] ch = data.toCharArray();
			for (int i = 0; i < elelist.size(); i++) {
				val = ch[i];
				elelist.get(i).click();
				elelist.get(i).sendKeys(Character.toString(val));
				// elelist.get(i).sendKeys(data.charAt(i));
			}
			flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (!flag) {
				System.out.println("unable to enter data in list");
			} else {
				System.out.println("able to enter data in list");
			}
		}

		return flag;
	}

	public static String getText(By locator) {
		String text = "";
		Boolean flag = false;
		try {
			MobileElement we = (MobileElement) driver.findElement(locator);
			text = we.getText();
			flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (!flag) {
				System.out.println("unable to enter testdata");
			} else {
				System.out.println("able to enter testdata");
			}
		}
		return text;
	}

	public static Boolean isElementEnabled(By locator) {
		Boolean flag = false;
		try {
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			MobileElement we = (MobileElement) driver.findElement(locator);
			flag = we.isEnabled();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (!flag) {
				System.out.println("Element is not present " + driver.findElement(locator).getText());
			} else {
				System.out.println("Element is present " + driver.findElement(locator).getText());
			}
		}

		return flag;
	}

	public static Boolean ischeckboxChecked(By locator) {
		Boolean flag = false;
		try {
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			MobileElement we = (MobileElement) driver.findElement(locator);
			String stat = we.getAttribute("checked");
			if (stat.equalsIgnoreCase("true")) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (!flag) {
				System.out.println("Element is not checked");
			} else {
				System.out.println("Element is checked");
			}
		}

		return flag;
	}
}
