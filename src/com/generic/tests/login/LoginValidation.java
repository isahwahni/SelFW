package com.generic.tests.login;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;


import com.generic.page.Registration;
import com.generic.page.SignIn;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;

import com.generic.util.dataProviderUtils;


public class LoginValidation extends SelTestCase {

	private static  LinkedHashMap<String, Object> users =null ;
	private static ThreadLocal<String> email = new ThreadLocal<String>();
	private static int testCaseID;
	// used sheet in test
	public static final String testDataSheet = SheetVariables.loginSheet;
	private static XmlTest testObject;
	
	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>() ; 
	
	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger(test.getName() + test.getIndex()));
		testObject = test;
		users = Common.readUsers();
	}

	@DataProvider(name = "Login", parallel = true)
	//concurrency maintenance on sheet reading 
	public static Object[][] loadTestData() throws Exception {
		getBrowserWait(testObject.getParameter("browserName"));
		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@SuppressWarnings("unchecked") // avoid warning from linked hashmap
	@Test(dataProvider = "Login")
	public void LoginValidationTest(String caseId,String runTest, String desc, String proprties,String email, String fieldsValidation) {

		Testlogs.set(new SASLogger("Login "+getBrowserName()));
		//Important to add this for logging/reporting 
		setTestCaseReportName("Login Case");
		//Testlogs.get().debug("Case Browser: "  + testObject.getParameter("browserName") );
		logCaseDetailds(MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc, proprties.replace("\n", "<br>- ")));
		this.email.set(getSubMailAccount(email));
		LinkedHashMap<String, Object> userdetails = (LinkedHashMap<String, Object>) users.get(email);


		try {

			if (proprties.equals("Success login")) {
				Testlogs.get().debug(this.email.get());
				Testlogs.get().debug((String) userdetails.get(Registration.keys.password) );
				SignIn.logIn(this.email.get(), (String) userdetails.get(Registration.keys.password));
				sassert().assertTrue(SignIn.checkUserAccount(),LoggingMsg.USER_IS_NOT_LOGGED_IN_SUCCESSFULLY );
			}
			if (proprties.equals("invalidUserEmail")) {
				SignIn.fillLoginFormAndClickSubmit(this.email.get(), "1234567");
					String alertMessage = SignIn.getErrorMsg();
					String failureMessage = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
							alertMessage, fieldsValidation);
					sassert().assertTrue(alertMessage.contains(fieldsValidation), failureMessage);
			}
			if (proprties.equals("invalidUserPassword")) {
				Testlogs.get().debug(this.email.get());
				SignIn.fillLoginFormAndClickSubmit(this.email.get(),"1234");
					String alertMessage = SignIn.getErrorMsg();
					String failureMessage = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
							alertMessage, fieldsValidation);
					sassert().assertTrue(alertMessage.contains(fieldsValidation), failureMessage);
			}
			if (proprties.equals("emptyData")) {
				SignIn.clickLogin();
					String alertMessage = SignIn.getErrorMsg();
					String failureMessage = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
							alertMessage, fieldsValidation);
					sassert().assertTrue(alertMessage.contains(fieldsValidation), failureMessage);
			}
			
			if (proprties.equals("Forgot password -Valid Email")) {
				SignIn.clickForgotPasswordBtn();
				SignIn.typeForgottenPwdEmail(this.email.get());
				SignIn.clickForgotPasswordSubmitBtn();
				Thread.sleep(1500);
					String alertMessage = SignIn.getAlertPositiveForgottenPasswordd();
					String failureMessage = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
							alertMessage, fieldsValidation);
					sassert().assertTrue(alertMessage.contains(fieldsValidation), failureMessage);
			}
			if (proprties.equals("Forgot password -Invalid Email")) {
				SignIn.clickForgotPasswordBtn();
				SignIn.typeForgottenPwdEmail(this.email.get());
				SignIn.clickForgotPasswordSubmitBtn();
				Thread.sleep(1500);
					String alertMessage = SignIn.getForgottenPwdEmailError();
					String failureMessage = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
							alertMessage, fieldsValidation);
					sassert().assertTrue(alertMessage.contains(fieldsValidation), failureMessage);
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
		} // catch
	}// test
}
