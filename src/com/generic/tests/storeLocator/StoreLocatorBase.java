package com.generic.tests.storeLocator;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;
import com.generic.page.SignIn;
import com.generic.page.StoreLocator;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.TestUtilities;
import com.generic.util.dataProviderUtils;

import sun.util.logging.resources.logging;

import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;
import com.generic.page.CheckOut;
import com.generic.page.PersonalDetails;
import com.generic.page.Registration;

public class StoreLocatorBase extends SelTestCase {

	// used sheet in test
	public static final String testDataSheet = SheetVariables.storeLocatorSheet;
	private static XmlTest testObject;

	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();
	private static LinkedHashMap<String, Object> users;

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger(""));
		testObject = test;
		users = Common.readUsers();
	}

	@DataProvider(name = "storeLocator", parallel = true)
	// concurrency maintenance on sheet reading
	public static Object[][] loadTestData() throws Exception {
		getBrowserWait(testObject.getParameter("browserName"));

		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@SuppressWarnings("unchecked") // avoid warning from linked hashmap
	@Test(dataProvider = "storeLocator")
	public void verifyStoreLocator(String caseId, String runTest, String desc, String proprties, String location) throws Exception {

		Testlogs.set(new SASLogger("StoreLocator_" + getBrowserName()));
		// Important to add this for logging/reporting
		setTestCaseReportName("StoreLocator Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc));
		
		//TODO: create a function to pull urls from property file 
		String url = "https://hybrisdemo.conexus.co.uk:9002/yacceleratorstorefront/en/store-finder";

		try {

			getDriver().get(url);
			
			StoreLocator.searchOnStore(location);

			//make sure results are loaded
			Thread.sleep(3000);
			
			if (proprties.contains("Negative"))
			{
				String errorMessage = StoreLocator.getErrorMessage();
				String expectedErrorMsg = proprties.split("Negative:")[1].split("\n")[0];
				sassert().assertTrue(errorMessage.contains(expectedErrorMsg),"error message: "+errorMessage+" is not as expected: "+ expectedErrorMsg);
			}else
			{
				String storeName = StoreLocator.getStroeName(getBrowserName());
				String StroeHours = StoreLocator.getStoreHours();
				if(!location.contains("NOT VALID"))
					sassert().assertTrue(storeName.contains(location),"Store name: " + storeName+" is not like expected:" + location);
				else
					sassert().assertFalse(storeName.contains(location),"Store name: " + storeName+" is not like expected:" + location);
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

}
