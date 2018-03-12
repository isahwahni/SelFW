package com.generic.tests.ordersHistory;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;
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
import com.generic.page.OrderDetails;
import com.generic.page.OrderHistory;
import com.generic.page.PaymentDetails;
import com.generic.page.Registration;

public class OrderHistoryValidation extends SelTestCase {

	// used sheet in test
	public static final String testDataSheet = SheetVariables.orderHistorySheet;
	private static  LinkedHashMap<String, Object> users =null ;
	private int caseIndexInDatasheet;

	private String caseId;
	private String runTest;
	private String desc;
	private String email;
	private String url;
	private String sortByText;
	private String ordersNthChildren;
	private String orderNumToBeClicked;

	private static XmlTest testObject;

	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		testCaseRepotId = SheetVariables.orderHistoryTestCaseId;
		Testlogs.set(new SASLogger(test.getName() + test.getIndex()));
		testObject = test;
		users = Common.readUsers();
	}

	@DataProvider(name = "ordersHistory", parallel = true)
	// concurrency maintenance on sheet reading
	public static Object[][] loadTestData() throws Exception {
		getBrowserWait(testObject.getParameter("browserName"));
		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@SuppressWarnings("unchecked") // avoid warning from linked hashmap
	@Test(dataProvider = "ordersHistory")
	public void verifyOrderHistory(String caseId, String runTest, String desc, String email, String sortByText, String ordersNthChildren, String orderNumToBeClicked) throws Exception {

		Testlogs.set(new SASLogger("ordersHistory" + getBrowserName()));
		// Important to add this for logging/reporting
		setTestCaseReportName("ordersHistory Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc));

		this.email = getSubMailAccount(email);
		caseIndexInDatasheet = getDatatable().getCellRowNum(testDataSheet, OrderDetails.keys.caseId, caseId);
		String url = PagesURLs.getOrderHistoryPage();


		try {
			LinkedHashMap<String, Object> userdetails = (LinkedHashMap<String, Object>) users.get(email);
			Testlogs.get().debug(this.email);
			Testlogs.get().debug((String) userdetails.get(Registration.keys.password) );
			SignIn.logIn(this.email, (String) userdetails.get(Registration.keys.password));
			
			Thread.sleep(2000);
			getDriver().get(url);
			Thread.sleep(3000);
			if (!sortByText.isEmpty()) {
				OrderHistory.selectSortOptions1ByValue(sortByText);
				String sortOptionsEqualityWithFirstMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
						OrderHistory.getSortOptions2SelectedValue(), OrderHistory.getSortOptions1SelectedValue());
				Assert.assertEquals(OrderHistory.getSortOptions1SelectedValue(),
						OrderHistory.getSortOptions2SelectedValue(),sortOptionsEqualityWithFirstMsg);

				OrderHistory.selectSortOptions2ByValue(sortByText);
				String sortOptionsEqualityWithSecondMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
						OrderHistory.getSortOptions1SelectedValue(), OrderHistory.getSortOptions2SelectedValue());
				Assert.assertEquals(OrderHistory.getSortOptions2SelectedValue(),
						OrderHistory.getSortOptions1SelectedValue(),sortOptionsEqualityWithSecondMsg);
			

			boolean isOrderNumEqShownOnes = OrderHistory.doesDisplayedOrdersNumTextMatchesOrdersDisplayed();
			sassert().assertEquals(isOrderNumEqShownOnes, "orders shown don't match the number displayed");
			int totalNumOfOrders = Integer.parseInt(OrderHistory.getNumberOfOrders());
			// Values to compare with
			// nthChildren
			String[] ordersNthChildrenArr = ordersNthChildren.split(";");
		
			for (int i = 0; i < totalNumOfOrders; i++) {
				// TODO: assertion to verify values plus verify when no orders
				// exist
				for (int j = 0; j < ordersNthChildrenArr.length; j++) {
					OrderHistory.getNthResponsiveTableItemColumn(ordersNthChildrenArr[j], i);
				}
			}
			OrderHistory.clickNthResponsiveTableItemTableCellAnchor(ordersNthChildrenArr[0],
					Integer.parseInt(orderNumToBeClicked));
			}
			else{
				OrderHistory.getNoOrdersMessage();
				sassert().assertTrue(OrderHistory.getNoOrdersMessage().contains(LoggingMsg.ORDER_HISTORY_NO_ORDERS),
						"<font color=#f442cb>No Orders Found as expected</font>");
			}
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
