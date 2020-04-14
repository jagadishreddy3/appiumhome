package homeJini;

import org.openqa.selenium.By;

public class PageObjects extends BaseClass {

	//Signin page
	public static By phnno = By.xpath("//*[contains(@text,'phone number')]");
	public static By tnc = By.xpath("//*[contains(@resource-id,'cbAgreeTerms')]");
	public static By signin = By.xpath("//*[contains(@text,'Sign in')]");
	public static By weltext = By.xpath("//*[@class='android.widget.TextView']");
	
	//OTP screen
	public static By otp = By.xpath("//*[contains(@resource-id,'etDigits')]");
	public static By resendotp = By.xpath("//*[contains(@resource-id,'tvResendOTP')]");
	
	//home page
	public static By home = By.xpath("//*[contains(@resource-id,'action_home')]");
	public static By services = By.xpath("//*[contains(@resource-id,'rdServices')]");
	public static By searchfield = By.xpath("//*[contains(@resource-id,'etSearch')]");
	public static By search = By.xpath("//*[contains(@resource-id,'ivSearch')]");
	public static By searchresults = By.xpath("//*[contains(@text,'Search')]");
	public static By Haircut = By.xpath("//*[contains(@text,'Men Hair Cut')]");
	public static By service = By.xpath("//*[contains(@text,'Service')]");
	public static By amount = By.xpath("//*[contains(@resource-id,'tvGstPrice')]/..//following-sibling::android.widget.LinearLayout//*[contains(@resource-id,'tvTotal')]");
	public static By Houseclean = By.xpath("//*[contains(@text,'Home Cleaning')]");
	public static By category = By.xpath("//*[contains(@text,'Category')]");
	public static By pestcontrol = By.xpath("//*[contains(@text,'Pest Control')]");
	public static By onebhk = By.xpath("//*[contains(@text,'1 BHK')]");
	public static By back = By.xpath("//*[contains(@resource-id,'llLeftMenu')]");
	
	//Profile page
	public static By profile = By.xpath("//*[contains(@resource-id,'ivProfile')]");
	public static By name = By.xpath("//*[contains(@text,'Name')]");
	public static By logout = By.xpath("//*[contains(@text,'Logout')]");
	public static By profileok = By.xpath("//*[contains(@text,'OK')]");
	public static By logouttitle = By.xpath("//*[contains(@text,'Home Jiny')]");
	
}
