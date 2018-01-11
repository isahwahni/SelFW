/**
 * this generic test for PDP regression that will pull tests from PDPRegression tab from
 * datasheet.xlsx. 
*/
package com.generic.tests.PDP;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.generic.page.Cart;
import com.generic.page.CheckOut;
import com.generic.page.PDP;
import com.generic.page.Registration;
import com.generic.page.SignIn;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;
import com.generic.util.TestUtilities;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class Base_PDP extends SelTestCase {

	private static LinkedHashMap<String, Object> invintory;
	private static LinkedHashMap<String, Object> users;

	// user types
	public static final String guestUser = "guest";
	public static final String loggedInUser = "loggedin";

	// used sheet in test
	public static final String testDataSheet = SheetVariables.PDPSheet;

	private static XmlTest testObject;
	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();
	private int caseIndexInDatasheet;
	private String email;
	LinkedHashMap<String, Object> productDetails;

	@BeforeClass
	public static void initialSetUp(XmlTest test) throws Exception {
		testCaseRepotId = SheetVariables.PDPCaseId;
		Testlogs.set(new SASLogger(test.getName() + test.getIndex()));
		testObject = test;
		invintory = Common.readLocalInventory();
		users = Common.readUsers();
	}

	@DataProvider(name = "PDPs", parallel = true)
	public static Object[][] loadTestData() throws Exception {
		// concurrency maintenance on sheet reading
		getBrowserWait(testObject.getParameter("browserName"));
		Object[][] data = TestUtilities.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@Test(dataProvider = "PDPs")
	public void checkOutBaseTest(String caseId, String runTest, String desc, String proprties, String product,
			String email, String ValidationMsg) throws Exception {
		// Important to add this for logging/reporting
		Testlogs.set(new SASLogger("PDP_" + getBrowserName()));
		setTestCaseReportName("PDP Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.CARTDESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc, proprties.replace("\n", "<br>- ")));
		this.email = getSubMailAccount(email);
		this.caseIndexInDatasheet = getDatatable().getCellRowNum(testDataSheet, CheckOut.keys.caseId, caseId);
		try {

			if (proprties.contains("Loggedin")) {
				LinkedHashMap<String, Object> userdetails = (LinkedHashMap<String, Object>) users.get(email);
				Testlogs.get().debug("Used mail to login: "+this.email);
				Testlogs.get().debug("Used Password to login: "+(String) userdetails.get(Registration.keys.password) );
				SignIn.logIn(this.email, (String) userdetails.get(Registration.keys.password));
			}
			LinkedHashMap<String, Object> productDetails = (LinkedHashMap<String, Object>) invintory.get(product);
			Testlogs.get().debug("productDetails" + Arrays.asList(productDetails));
			Testlogs.get().debug("url key " + PDP.keys.url);
			Testlogs.get().debug("url key value " + (String) productDetails.get(PDP.keys.url));
			getDriver().get((String) productDetails.get(PDP.keys.url));

			if (proprties.contains("id") ){
				logs.debug("checking PDP ID");
				String Id = PDP.getId();
				sassert().assertTrue(Id.contains((String) productDetails.get(PDP.keys.id)), "<font color=#f442cb>product id is not expected</font>");
				ReportUtil.takeScreenShot(getDriver());
			}//id check
			

			if (proprties.contains("name") ){
				logs.debug("checking PDP title");
				String title = PDP.getTitle();
				sassert().assertTrue(title.contains((String) productDetails.get(PDP.keys.title)), "<font color=#f442cb>product title is not expected</font>");
				ReportUtil.takeScreenShot(getDriver());
			}//title check
			
			if (proprties.contains("price") ){
				logs.debug("checking PDP price");
				String price = PDP.getPrice();
				sassert().assertTrue(price.contains((String) productDetails.get(PDP.keys.price)), "<font color=#f442cb>product price is not expected</font>");
				ReportUtil.takeScreenShot(getDriver());
			}//price check
			
			if (proprties.contains("summary") ){
				logs.debug("checking PDP summary");
				String summary = PDP.getSummary();
				sassert().assertTrue(summary.contains((String) productDetails.get(PDP.keys.summary)), "<font color=#f442cb>product summary is not expected</font>");
				ReportUtil.takeScreenShot(getDriver());
			}//summary check
			
			if (proprties.contains("desc") ){
				logs.debug("checking PDP desc");
				String Pdesc = PDP.getDesc();
				sassert().assertTrue(Pdesc.contains((String) productDetails.get(PDP.keys.desc)), "<font color=#f442cb>product desc is not expected</font>");
				ReportUtil.takeScreenShot(getDriver());
			}//desc check
			
			if (proprties.contains("stock level indicator") ){
				logs.debug("checking PDP stock level indicator");
				String SL = PDP.getStockLevel();
				Testlogs.get().debug(SL);
				ReportUtil.takeScreenShot(getDriver());
			}//stock level indicator check
			
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

	public void prepareCartNotLoggedInUser(String product) throws Exception {
		logs.debug(MessageFormat.format(LoggingMsg.ADDING_PRODUCT, product));
		productDetails = (LinkedHashMap<String, Object>) invintory.get(product);
		PDP.addProductsToCart((String) productDetails.get(PDP.keys.url), (String) productDetails.get(PDP.keys.color),
				(String) productDetails.get(PDP.keys.size), (String) productDetails.get(PDP.keys.qty));
	}

	public void prepareCartLoggedInUser(String user, String product) throws Exception {
		logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, user));
		LinkedHashMap<String, Object> userDetails = (LinkedHashMap<String, Object>) users.get(user);
		logs.debug((String) userDetails.get("password"));
		logs.debug((String) userDetails.get("mail"));
		SignIn.logIn(getSubMailAccount((String) userDetails.get("mail")), (String) userDetails.get("password"));

		logs.debug(MessageFormat.format(LoggingMsg.ADDING_PRODUCT, product));
		productDetails = (LinkedHashMap<String, Object>) invintory.get(product);
		PDP.addProductsToCart((String) productDetails.get(PDP.keys.url), (String) productDetails.get(PDP.keys.color),
				(String) productDetails.get(PDP.keys.size), (String) productDetails.get(PDP.keys.qty));

	}

}
