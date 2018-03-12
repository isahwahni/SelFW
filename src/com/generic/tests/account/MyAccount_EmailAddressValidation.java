package com.generic.tests.account;

import java.text.MessageFormat;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.xml.XmlTest;

import java.util.Arrays;
import java.util.LinkedHashMap;

import com.generic.page.Registration;
import com.generic.page.MyAccount_EmailAddress;
import com.generic.page.SignIn;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.PagesURLs;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.dataProviderUtils;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;

public class MyAccount_EmailAddressValidation extends SelTestCase {

	// used sheet in test
	private static LinkedHashMap<String, Object> users;
	public static final String testDataSheet = SheetVariables.EmailAddressRegressionSheet;
	
	private static ThreadLocal<String> email = new ThreadLocal<String>();
	
	private static ThreadLocal<String> currentEmail = new ThreadLocal<String>();
	private static ThreadLocal<String> confirmNewEmail = new ThreadLocal<String>();
	private int caseIndexInDatasheet;

	private static XmlTest testObject;

	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger(""));
		testObject = test;
		users = Common.readUsers();
	}

	@DataProvider(name = "EmailAddress", parallel = true)
	// concurrency maintenance on sheet reading
	public static Object[][] loadTestData() throws Exception {
		getBrowserWait(testObject.getParameter("browserName"));

		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@SuppressWarnings("unchecked") // avoid warning from linked hashmap
	@Test(dataProvider = "EmailAddress")
	public void verifyEmailAddress(String caseId, String runTest, String desc, String proprties, String email,
			String currentEmail, String confirmNewEmail, String password, String globalAlerts, String emailErrors,
			String confirmEmailEerrors, String passwordEerrors) throws Exception {


		String url = PagesURLs.getEmailAddressPage();

		LinkedHashMap<String, Object> userDetails = (LinkedHashMap<String, Object>) users.get(email);

		Testlogs.set(new SASLogger("EmailAddress" + getBrowserName()));
		// Important to add this for logging/reporting
		setTestCaseReportName("EmailAddress Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc));

		this.email.set(getSubMailAccount(email));
		this.currentEmail.set(getSubMailAccount(currentEmail));
		this.confirmNewEmail.set(getSubMailAccount(confirmNewEmail));
		//this.caseIndexInDatasheet = getDatatable().getCellRowNum(testDataSheet, MyAccount_EmailAddress.keys.caseId, caseId);

		try {

			SignIn.logIn(this.email.get(), (String) userDetails.get(Registration.keys.password));
			getDriver().get(url);
			Thread.sleep(1000);
			if (proprties.contains("Verify current user")) {
				String ProfileMail = MyAccount_EmailAddress.getEmailValue();
				sassert().assertTrue(this.email.get().contains(ProfileMail), "Email not as expected: " + this.email + "found:" +ProfileMail );
			} else {
				MyAccount_EmailAddress.fillInNewValuesAndClickUpdateOrCancel(this.currentEmail.get(), this.confirmNewEmail.get(),
						password, proprties.contains("click update"), proprties.contains("click cancel"));
				Thread.sleep(3000);
				if (proprties.contains("click update")) {
					String alertMessage = MyAccount_EmailAddress.getGlobalAlertsMsg();
					String failureMessage = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
							MyAccount_EmailAddress.getGlobalAlertsMsg(), globalAlerts);
					sassert().assertTrue(alertMessage.contains(globalAlerts), failureMessage);
					if (!emailErrors.equals("")) {
						String currentMsg = MyAccount_EmailAddress.getEmailErrorMsg();
						String currentEmailErrorMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR, currentMsg,
								emailErrors);
						sassert().assertTrue(currentMsg.contains(emailErrors), currentEmailErrorMsg);
					}
					if (!confirmEmailEerrors.equals("")) {
						String currentMsg = MyAccount_EmailAddress.getConfirmEmailErrorMsg();
						logs.debug(currentMsg);
						String confirmEmailEerrorsMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
								currentMsg, confirmEmailEerrors);
						sassert().assertTrue(currentMsg.contains(confirmEmailEerrors), confirmEmailEerrorsMsg);
					}
					if (!passwordEerrors.equals("")) {
						String currentMsg = MyAccount_EmailAddress.getPasswordErrorMsg();
						logs.debug(currentMsg);
						String passwordEerrorrMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR, currentMsg,
								passwordEerrors);
						sassert().assertTrue(currentMsg.contains(passwordEerrors), passwordEerrorrMsg);
					}
				}
			}

			if (proprties.contains("revert changes")) {
				getDriver().get(url);
				MyAccount_EmailAddress.fillInNewValuesAndClickUpdateOrCancel(this.email.get(), this.email.get(),
						(String) userDetails.get(Registration.keys.password), true, false);
				Thread.sleep(2000);
			}

			sassert().assertAll();
			Common.testPass();
		} catch (Throwable t) {
			setTestCaseDescription(getTestCaseDescription());
			Testlogs.get().debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t.getMessage()));
			t.printStackTrace();
			String temp = getTestCaseReportName();
			Common.testFail(t, temp);
			ReportUtil.takeScreenShot(getDriver());
			Assert.assertTrue(false, t.getMessage());
		}

	}
}// class
