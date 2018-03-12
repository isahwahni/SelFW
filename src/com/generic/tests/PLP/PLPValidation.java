package com.generic.tests.PLP;

import java.text.MessageFormat;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.generic.page.PLP;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.PagesURLs;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;
import com.generic.util.dataProviderUtils;

public class PLPValidation extends SelTestCase {

	// used sheet in test
	public static final String testDataSheet = SheetVariables.plpSheet;

	private static XmlTest testObject;

	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();
	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger(test.getName() + test.getIndex()));
		testObject = test;
	}
	
	@DataProvider(name = "PLP", parallel = true)
	// concurrency maintenance on sheet reading
	public static Object[][] loadTestData() throws Exception {
		getBrowserWait(testObject.getParameter("browserName"));

		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@Test(dataProvider = "PLP")
	public void verifyPLP(String caseId, String runTest, String desc, String sortOptions1,
			String sortOptions2, String userLocationStore, String addToCartProduct, String pickUpInStoreProduct, String pickupNthIconIndex, String pickupInStoreQty,String plpFilter, String nthProductItem,
			String nthAppliedFacet, String SdoClickAddToCart, String SdoClickPickupInStore, String SdoClickNthProductItem, String SdoClickCheckoutBtn, String SdoClickCloseBtn) throws Exception {
		
		boolean doClickAddToCart = Boolean.valueOf(SdoClickAddToCart);
		boolean doClickPickupInStore = Boolean.valueOf(SdoClickPickupInStore);
		boolean doClickNthProductItem = Boolean.valueOf(SdoClickNthProductItem);
		boolean doClickCheckoutBtn = Boolean.valueOf(SdoClickCheckoutBtn);
		boolean doClickCloseBtn = Boolean.valueOf(SdoClickCloseBtn);
		
		Testlogs.set(new SASLogger("PLP " + getBrowserName()));
		// Important to add this for logging/reporting
		setTestCaseReportName("PLP Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc));

		try {
			String url = PagesURLs.getPLP();
			getDriver().get(url);
			Thread.sleep(5000);
			
			PLP.selectSortOptions1ByValue(sortOptions1);
			Thread.sleep(3000);
			
			PLP.selectSortOptions2ByValue(sortOptions2);
			Thread.sleep(3000);
			
//			String productsNum = PLP.getNumberOfproducts();
//			logs.debug(MessageFormat.format(LoggingMsg.NUMBER_OF_PRODUCTS, productsNum));
			
			sassert().assertTrue(PLP.doesDisplayedProductsNumTextMatchesProductsDisplayed());
		
			PLP.typeUserLocationStore(userLocationStore);
			Thread.sleep(4000);
			
//			PLP.clickFindStores();
//			Thread.sleep(3000);
			
			PLP.clickMoreStores();
			Thread.sleep(3000);
			
		
			
		     // PLP.clickleftNavCheckBoxCheckBox("London Hospital");
			//logs.debug(MessageFormat.format(LoggingMsg.PLP_SELECTED_FILTER_COUNT, "Store", PLP.getFacetNavTitleStoresCount()));
			//logs.debug(MessageFormat.format(LoggingMsg.PLP_SELECTED_FILTER_COUNT, "price", PLP.getFacetNavTitlePriceCount()));
			//logs.debug(MessageFormat.format(LoggingMsg.PLP_SELECTED_FILTER_COUNT, "colour", PLP.getFacetNavTitleColourCount()));
			//logs.debug(MessageFormat.format(LoggingMsg.PLP_SELECTED_FILTER_COUNT, "Size", PLP.getFacetNavTitleSizeCount()));
			sassert().assertTrue(PLP.compareAppliedFilterWithDisplayedProductNumber(plpFilter));
			
			PLP.removeNthAppliedFacet(nthAppliedFacet);
			
			PLP.clickChangeLocationLink();
			Thread.sleep(3000);
			
		    PLP.verifyChangeLocationLink();
		    
		    if (doClickAddToCart) {
		    	PLP.clickAddToCart(addToCartProduct);
				Thread.sleep(3000);
				String productPriceAddedToCart = PLP.getPLPProductPriceFromCartBag();
				logs.debug(MessageFormat.format(LoggingMsg.PLP_PRODUCT_PRICE, productPriceAddedToCart));
				String productPrice = PLP.getPLPProductPrice(addToCartProduct);
				logs.debug(MessageFormat.format(LoggingMsg.PLP_PRODUCT_PRICE, productPrice));
				Thread.sleep(2000);
				if (doClickCloseBtn) {
					PLP.clickCboxCloseBtn();
				} else if (doClickCheckoutBtn) {
					PLP.clickCheckoutBtn();
				} else {
					PLP.clickContinueShoppingBtn();
				}
			}
		    
		    if (doClickPickupInStore) {
		    	PLP.clickProductPickupInStoreButton(pickUpInStoreProduct);
		    	PLP.typePickUpInStoreLocationForSearch(userLocationStore);
		    	PLP.clickPickupNthAccessibleTabIcon(pickupNthIconIndex);
		    	PLP.clickPickUpInStoreDecreaseQtyBtn(pickUpInStoreProduct);
		    	PLP.clickPickUpInStoreIncreaseQtyBtn(pickUpInStoreProduct);
		    	PLP.typePickUpInStoreQty(pickUpInStoreProduct, pickupInStoreQty);
		    	PLP.clickPickUpInStoreAddToBagBtn(pickUpInStoreProduct);
		    	PLP.clickCboxCloseBtn();
		    	Thread.sleep(2000);
		    }
		    
		    if (doClickNthProductItem) {
		    	PLP.clickNthProductItem(nthProductItem);
		    }
		    
			Thread.sleep(4000);
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
