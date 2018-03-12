/*
 * As log as we don't have quickView in our base environment
 * this test was completed on other sites and will not work on the current site. 
 */

package com.generic.tests.AdditionalSFC;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.generic.page.MailSignup;
import com.generic.page.PDP;
import com.generic.page.PLP_QuickView;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;
import com.generic.util.dataProviderUtils;

public class quickViewBase extends SelTestCase {
	private static  LinkedHashMap<String, Object> invintory = null ;
	// used sheet in test
	public static final String testDataSheet = SheetVariables.QuickViewRegressionSheet;

	private static XmlTest testObject;

	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		testCaseRepotId = SheetVariables.QuickViewTestCaseId;
		Testlogs.set(new SASLogger(test.getName() + test.getIndex()));
		testObject = test;
		invintory = Common.readLocalInventory();
	}

	@DataProvider(name = "MailSignup", parallel = true)
	// concurrency maintenance on sheet reading
	public static Object[][] loadTestData() throws Exception {
		getBrowserWait(testObject.getParameter("browserName"));
		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@SuppressWarnings("unchecked")
	@Test(dataProvider = "MailSignup")
	public void verifyQuickView(String caseId, String runTest, String desc, String proprties, String product, String email, String globalAlert )
			throws Exception {
		Testlogs.set(new SASLogger("MailSignUp_" + getBrowserName()));
		// Important to add this for logging/reporting
		setTestCaseReportName("mailSignup Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc));

		try {

			LinkedHashMap<String, Object> productDetails = (LinkedHashMap<String, Object>) invintory.get(product);
			PLP_QuickView.addProductsToCart((String) productDetails.get(PDP.keys.url),
					(String) productDetails.get(PDP.keys.color), (String) productDetails.get(PDP.keys.size),
					(String) productDetails.get(PDP.keys.qty));

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

}
