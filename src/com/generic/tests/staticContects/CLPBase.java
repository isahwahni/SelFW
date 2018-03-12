package com.generic.tests.staticContects;

import java.text.MessageFormat;
import java.util.Arrays;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.generic.page.CLP;
import com.generic.page.HomePage;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.PagesURLs;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;
import com.generic.util.dataProviderUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import javax.imageio.ImageIO;
import java.io.File;

public class CLPBase extends SelTestCase {

	// possible scenarios
	public static final String update = "update";
	public static final String verify = "verify";
	public static final String header = "header";

	// used sheet in test
	public static final String testDataSheet = SheetVariables.VisualTestingCLPRegressionsheet;

	private static XmlTest testObject;
	
	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>() ; 
	
	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger(test.getName() + test.getIndex()));
		testObject = test;
	}

	@DataProvider(name = "CLP_SC", parallel = true)
	//concurrency maintenance on sheet reading 
	public static Object[][] loadTestData() throws Exception {
		getBrowserWait(testObject.getParameter("browserName"));
		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}


	@Test(dataProvider = "CLP_SC")
	public void CLPBaseTest(String caseId, String runTest, String desc, String proprties, String baseline) throws Exception {
		Testlogs.set(new SASLogger("CLP_SC "+getBrowserName()));
		//Important to add this for logging/reporting 
		setTestCaseReportName(SheetVariables.VisualTestingHPTestCaseId);
		//Testlogs.get().debug("Case Browser: "  + testObject.getParameter("browserName") );
		logCaseDetailds(MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc));
		
		try {
			String url = PagesURLs.getCLP();
			getDriver().get(url);
			
			String baseline_browser = baseline+"_"+getBrowserName();
			
			if (proprties.contains(this.update))
			{
				if (proprties.contains(this.header))
					CLP.updateHeaderBaseline(baseline_browser);
				
				CLP.prepareBaselineforLogs(baseline_browser);
			}
			else if (proprties.contains(this.verify))
			{
				if (proprties.contains(this.header))
					sassert().assertTrue(CLP.verifyHeader(baseline_browser),"headerbase line is not same site header");
				CLP.prepareBaselineforLogs(baseline_browser);
			}
			else {
				Testlogs.get().debug("please check proprties provided in excel sheet");
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
