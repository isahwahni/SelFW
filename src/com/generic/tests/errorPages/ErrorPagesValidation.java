package com.generic.tests.errorPages;

import java.text.MessageFormat;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import java.util.Arrays;
import com.generic.page.errorPages;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.PagesURLs;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.TestUtilities;
import com.generic.util.dataProviderUtils;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;

public class ErrorPagesValidation extends SelTestCase {

	// used sheet in test
	public static final String testDataSheet = SheetVariables.ErrorPagesRegressionSheet;
	String url =null;

	private static XmlTest testObject;

	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger(""));
		testObject = test;
	}

	@DataProvider(name = "ErrorPages", parallel = true)
	// concurrency maintenance on sheet reading
	public static Object[][] loadTestData() throws Exception {
		getBrowserWait(testObject.getParameter("browserName"));

		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@Test(dataProvider = "ErrorPages")
	public void verifyEmailAddress(String caseId, String runTest, String desc, String proprties, String globalAlerts) throws Exception {

		Testlogs.set(new SASLogger("ErrorPages_" + getBrowserName()));
		// Important to add this for logging/reporting
		setTestCaseReportName("ErrorPages Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc));
		 
		try {
			
			if (proprties.contains("404")) {
				String url = PagesURLs.getErrorPage();
				getDriver().get(url);
				String currentMsg = errorPages.getGlobalAlertsMsg();
				logs.debug(currentMsg);
				String glopalEerrorrMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR, currentMsg,
						globalAlerts);
				sassert().assertTrue(currentMsg.contains(globalAlerts), glopalEerrorrMsg);
			} else {
				url = "";
				getDriver().get(url);
				String currentMsg = errorPages.getGlobalAlertsMsg();
				logs.debug(currentMsg);
				String glopalEerrorrMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR, currentMsg,
						globalAlerts);
				sassert().assertTrue(currentMsg.contains(globalAlerts), glopalEerrorrMsg);
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
