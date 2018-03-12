package com.generic.tests.account;

import java.text.MessageFormat;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import java.util.Arrays;
import java.util.LinkedHashMap;

import com.generic.page.Registration;
import com.generic.page.CheckOut;
import com.generic.page.MyAccount_Password;
import com.generic.page.SignIn;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.PagesURLs;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.TestUtilities;
import com.generic.util.dataProviderUtils;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;

public class MyAccount_PasswordValidation extends SelTestCase {
	// used sheet in test
	public static final String testDataSheet = SheetVariables.PasswordRegressionSheet;
	private int caseIndexInDatasheet;


	private static XmlTest testObject;

	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();
	private static LinkedHashMap<String, Object> users;

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger(test.getName() + test.getIndex()));
		testObject = test;
		users = Common.readUsers();
	}

	@DataProvider(name = "Password", parallel = true)
	// concurrency maintenance on sheet reading
	public static Object[][] loadTestData() throws Exception {
		getBrowserWait(testObject.getParameter("browserName"));

		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@SuppressWarnings("unchecked") // avoid warning from linked hashmap
	@Test(dataProvider = "Password")
	public void verifyPasswordUpdates(String caseId, String runTest, String desc, String proprties, String email,
			String currentPassword, String newPassword,String confirmNewPassword, String globalAlerts, String currentPasswordErrors,
			String newPasswordEerrors,String confirmNewPasswordErrors) throws Exception {

		boolean doClickCancelBtn = proprties.contains("click cancel");
		boolean doClickUpdateBtn = proprties.contains("click update");
		boolean revertChanges = proprties.contains("revert changes");
		
		LinkedHashMap<String, Object> userDetails = (LinkedHashMap<String, Object>) users.get(email);

		Testlogs.set(new SASLogger("Password" + getBrowserName()));
		// Important to add this for logging/reporting
		setTestCaseReportName("Password Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc));

		String emailSubmail = getSubMailAccount(email);
		String url = PagesURLs.getPasswordPage();
		
		caseIndexInDatasheet = getDatatable().getCellRowNum(testDataSheet, MyAccount_Password.keys.caseId, caseId);

		try {

			SignIn.logIn(emailSubmail, (String)userDetails.get(Registration.keys.password));
			getDriver().get(url);
//			if(password.equals("1234567")){
//				password=(String)userDetails.get(Registration.keys.password);
//			}

				MyAccount_Password.fillInNewValuesAndClickUpdateOrCancel(currentPassword, newPassword
						, confirmNewPassword, doClickUpdateBtn,
						doClickCancelBtn);
				Thread.sleep(3000);
				if (doClickUpdateBtn) {
					String globalAlertMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
							MyAccount_Password.getGlobalAlertsMsg(), globalAlerts);
					Assert.assertTrue(globalAlertMsg.contains(globalAlerts), globalAlertMsg);
					if (!currentPasswordErrors.equals("")) {
						String currentMsg = MyAccount_Password.getCurrentPasswordErrorrMsg();
						logs.debug(currentMsg);
						String currentPasswordErrorMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,currentMsg, currentPasswordErrors);
						sassert().assertTrue(currentMsg.contains(currentPasswordErrors), currentPasswordErrorMsg );
					}
					if (!newPasswordEerrors.equals("")) {
						String currentMsg = MyAccount_Password.getNewPasswordErrorMsg();
						logs.debug(currentMsg);
						String newPasswordEerrorsMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
								currentMsg, newPasswordEerrors);
						sassert().assertTrue(currentMsg.contains(newPasswordEerrors) , newPasswordEerrorsMsg);
					}
					if (!confirmNewPasswordErrors.equals("")) {
						String currentMsg = MyAccount_Password.getConfirmNewPasswordErrorMsg();
						logs.debug(currentMsg);
						String confirmNewPasswordErrorsMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
								currentMsg, confirmNewPasswordErrors);
						sassert().assertTrue(currentMsg.contains(confirmNewPasswordErrors) , confirmNewPasswordErrorsMsg);
					}
					
				}
			
			
			if(revertChanges)
			{
				getDriver().get(url);
				MyAccount_Password.fillInNewValuesAndClickUpdateOrCancel(newPassword,(String)userDetails.get(Registration.keys.password),(String)userDetails.get(Registration.keys.password), doClickUpdateBtn, doClickCancelBtn);
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