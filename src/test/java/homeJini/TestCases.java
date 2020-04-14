package homeJini;

import org.testng.annotations.Test;

public class TestCases extends Reusemethods {
	@Test
	public void TC_1() throws Throwable {
		MobileActions.isElementPresent(signin);
		readexcel("TC_007");
		System.out.println(hm.get("Expected Result"));
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
		verifyotp();
		MobileActions.isElementPresent(home);
		if (MobileActions.ischeckboxChecked(services)) {
			System.out.println("services is opened by default");
		} else {
			System.out.println("services is not opened by default");
		}
		MobileActions.provideInput(searchfield, "salon");
		MobileActions.click(search);
		MobileActions.isElementPresent(searchresults);
		MobileActions.click(Haircut);
		Thread.sleep(3000);
		System.out.println(getTotalAmount());
		takeScreenshot("TC_1_finalresult");
		hm.clear();
	}

	@Test
	public void TC_2() throws Throwable {
		MobileActions.isElementPresent(signin);
		readexcel("TC_006");
		System.out.println(hm.get("Test Case Heading"));
		String exp = MobileActions.getText(weltext);
		if (exp.equalsIgnoreCase("Welcomes you to the magical world of products and services")) {
			System.out.println("Welcome screen is Present");
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
		verifyotp();
		MobileActions.isElementPresent(home);
		if (MobileActions.ischeckboxChecked(services)) {
			System.out.println("services is opened by default");
		} else {
			System.out.println("services is not opened by default");
		}
		Thread.sleep(3000);
		swipeVertical(0.8, 0.2, 0.5, 2000);
		MobileActions.click(Houseclean);
		MobileActions.isElementPresent(category);
		MobileActions.click(pestcontrol);
		MobileActions.isElementPresent(service);
		MobileActions.click(onebhk);
		Thread.sleep(3000);
		System.out.println(getTotalAmount());
		takeScreenshot("TC_2_finalresult");
		hm.clear();
	}

}
