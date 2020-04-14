package homeJini;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Reusemethods extends MobileActions {

	public void Login() throws Throwable {
		MobileActions.isElementPresent(signin);
		String exp = MobileActions.getText(weltext);
		if (exp.equalsIgnoreCase("Welcomes you to the magical world of products and services")) {
			System.out.println("Present");
		} else {
			System.out.println("Not Present");
		}
		MobileActions.isElementEnabled(phnno);
		MobileActions.provideInput(phnno, "1234567890");
		if (MobileActions.ischeckboxChecked(tnc)) {

		} else {
			MobileActions.click(tnc);
		}
		MobileActions.click(signin);
	}

	public void verifyotp() throws Throwable {

		MobileActions.isElementPresent(resendotp);
		// launchKeyboard();
		MobileActions.provideInput(otp, "1234");
		// HideKeyboard();

	}

	public static String getTotalAmount() throws Exception {
		MobileActions.isElementPresent(service);
		// scrollToAnElementByText("Total Amount");
		swipeVertical(0.8, 0.1, 0.5, 2000);
		return MobileActions.getText(amount);
	}

	public void launchKeyboard() {
		driver.getKeyboard();
	}

	public void HideKeyboard() {
		driver.hideKeyboard();
		;
	}

	@AfterMethod
	public static void Home() throws Throwable {
		if (MobileActions.isElementPresent(home)) {
			System.out.println("In Home page");
		} else {
			while (MobileActions.isElementPresent(back)) {
				MobileActions.click(back);
				Thread.sleep(1500);
			}
		}
	}

	@BeforeMethod
	public static void NavigateToLoginPage() throws Throwable {
		if (MobileActions.isElementPresent(signin)) {
			System.out.println("In Login page");
		} else if (MobileActions.isElementPresent(home)) {
			logout();
		} else {
			Home();
			logout();
		}
	}

	public static void logout() throws Throwable {
		MobileActions.click(profile);
		MobileActions.isElementPresent(name);
		Thread.sleep(1500);
		swipeVertical(0.8, 0.1, 0.5, 2000);
		MobileActions.click(logout);
		MobileActions.isElementPresent(logouttitle);
		MobileActions.click(profileok);
		MobileActions.click(profileok);
		if (MobileActions.isElementPresent(signin)) {
			System.out.println("Logged out from application");
		}
	}

}
