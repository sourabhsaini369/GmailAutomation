package TestScript;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Policy.Generic.BaseLib;
import com.Policy.PageObject.gmailHomePage;
import com.Policy.PageObject.loginPage;

public class gmailTest extends BaseLib {

	@Test
	public void verifyGmailContnt() {
		loginPage lp = new loginPage(driver);
		gmailHomePage gh = new gmailHomePage(driver);

		if (lp.gmailLogIn(prop.get("email").toString(), prop.get("password").toString())) {
			List<String> negativeResult = gh.printEmailContent("Dummy");
			if (!negativeResult.isEmpty()) {

				System.out.println("Failed due to: " + negativeResult);
				Assert.assertTrue(false, "Failed due to: " + negativeResult);
			}
		}
		else
		{
			System.out.println("Failed due to: " + "Not Able to Login");
			Assert.assertTrue(false, "Failed due to: " + "Not Able to Login");
			
		}
	}

}
