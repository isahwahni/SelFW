package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.generic.selector.CheckOutSelectors;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

public class CheckOut extends SelTestCase {

	public static class keys {
		public static final String caseId = "caseId";
	}

	public static class guestCheckout {
		public static void typeGuestMail(String email) throws Exception {
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			getCurrentFunctionName(true);
			logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "guest mail", email));
			subStrArr.add(CheckOutSelectors.guestMail);
			valuesArr.add(email);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static void typeGuestConfMail(String email) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "guest confirmation mail", email));
			subStrArr.add(CheckOutSelectors.guestConfirmationMail);
			valuesArr.add(email);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static void clickCheckoutAsGuest() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.CLICK_ELEMENT_SEL, "checkout as guest button"));
			subStrArr.add(CheckOutSelectors.guestCheckoutButton);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static void fillAndClickGuestCheckout(String email) throws Exception {
			getCurrentFunctionName(true);
			typeGuestMail(email);
			typeGuestConfMail(email);
			clickCheckoutAsGuest();
			getCurrentFunctionName(false);
		}

		public static void fillPreRegFormAndClickRegBtn(String password, boolean newsLetterOptin) throws Exception {
			getCurrentFunctionName(true);
			typeGuestPassPreForm(password);
			typeGuestPassConfPreForm(password);
			checkNewsletterOptin(newsLetterOptin);
			clickCreatAnaccount();
			getCurrentFunctionName(false);

		}

		public static void clickCreatAnaccount() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.CLICK_ELEMENT_SEL, "guest creat an account"));
			subStrArr.add(CheckOutSelectors.guestCreateAccButton);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static void checkNewsletterOptin(boolean newsLetterOptin) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.CHECKBOX_SEL_VAL, "News Letter Optin", newsLetterOptin));
			subStrArr.add(CheckOutSelectors.guestCreateAccOtpin);
			valuesArr.add(String.valueOf(newsLetterOptin));
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static void typeGuestPassConfPreForm(String password) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "password confirmation ", password));
			subStrArr.add(CheckOutSelectors.guestCreateAccCPwd);
			valuesArr.add(password);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static void typeGuestPassPreForm(String password) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "password ", password));
			subStrArr.add(CheckOutSelectors.guestCreateAccPwd);
			valuesArr.add(password);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}
		
		public static void returningCustomerLogin(String username, String password) throws Exception
		{
			getCurrentFunctionName(true);
			typeRetuRningcustomerUserName(username);
			typeRetuRningcustomerPassword(password);
			clickRetuRningcustomerLogin();
			getCurrentFunctionName(false);
		}

		private static void clickRetuRningcustomerLogin() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, "Log In and Check Out"));
			subStrArr.add(CheckOutSelectors.returningcustomerloginBtn);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
			
		}

		private static void typeRetuRningcustomerPassword(String password) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "returning customer password ", password));
			subStrArr.add(CheckOutSelectors.returningcustomerPassword);
			valuesArr.add(password);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
			
		}

		private static void typeRetuRningcustomerUserName(String username) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "returning customer username ", username));
			subStrArr.add(CheckOutSelectors.returningcustomerUsername);
			valuesArr.add(username);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		}

	}

	public static class paymentType {
		
		public static void checkCardPayment() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(LoggingMsg.SAVING_ADDRESS);
			subStrArr.add(CheckOutSelectors.PaymentTypeSelection_CARD);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}
		
		public static void checwkAccountPayment() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(LoggingMsg.SAVING_ADDRESS);
			subStrArr.add(CheckOutSelectors.PaymentTypeSelection_ACCOUNT);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}
		public static void typePurchaseOrderNumber(String PurchaseOrderNumber) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "Purchase Order Number ", PurchaseOrderNumber));
			subStrArr.add(CheckOutSelectors.PurchaseOrderNumber);
			valuesArr.add(PurchaseOrderNumber);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}
		
		public static void selectCostCenter(String CostCenter) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.PaymentTypeCostCenterSelect);
			valuesArr.add(CostCenter);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}
		
		public static void clickNext() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(LoggingMsg.CLICKING_SHIPPING_NEXT_BTN);
			subStrArr.add(CheckOutSelectors.PaymentType_continue_button);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}
		
	}
	
	public static class shippingAddress {

		public static class keys {

			public static final String isSavedShipping = "saved-shipping";

			public static final String countery = "countery";
			public static final String title = "title";
			public static final String lastName = "lastName";
			public static final String firstName = "firstName";
			public static final String adddressLine = "adddressLine";
			public static final String city = "city";
			public static final String postal = "postal";
			public static final String phone = "phone";
		}

		public static void selectCountery(String countery) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.SELECTING_ELEMENT_VALUE, "Countery ", countery));
			subStrArr.add(CheckOutSelectors.countery);
			valuesArr.add(countery);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			Thread.sleep(1500);
			getCurrentFunctionName(false);

		}

		public static void selectTitle(String title) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.SELECTING_ELEMENT_VALUE, "title ", title));
			subStrArr.add(CheckOutSelectors.title);
			valuesArr.add(title);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		}

		public static void typeFirstName(String firstName) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "firstname ", firstName));
			subStrArr.add(CheckOutSelectors.firstName);
			valuesArr.add(firstName);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static void typeAddress(String address) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "address ", address));
			subStrArr.add(CheckOutSelectors.address);
			valuesArr.add(address);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static void typeLastName(String lastName) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "lastname ", lastName));
			subStrArr.add(CheckOutSelectors.lastName);
			valuesArr.add(lastName);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static void typeCity(String city) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "city ", city));
			subStrArr.add(CheckOutSelectors.city);
			valuesArr.add(city);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static void typePostalCode(String postal) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "postal", postal));
			subStrArr.add(CheckOutSelectors.postal);
			valuesArr.add(postal);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static void typePhone(String phone) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "phone", phone));
			subStrArr.add(CheckOutSelectors.phone);
			valuesArr.add(phone);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static void checkSaveAddress(boolean check) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(LoggingMsg.SAVING_ADDRESS);
			subStrArr.add(CheckOutSelectors.CheckSaveAddress);
			valuesArr.add(String.valueOf(check));
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static String getOrderTotal() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderTotalShippingAddress);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(SelectorUtil.textValue.get());
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String getOrdersubTotal() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderSubTotalShippingAddress);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(SelectorUtil.textValue.get());
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static void clickNext() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(LoggingMsg.CLICKING_SHIPPING_NEXT_BTN);
			subStrArr.add(CheckOutSelectors.submitShippingAddressBtn);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static void fillAndClickNext(String Countery, String title, String firstName, String lastName,
				String address, String city, String postal, String phone, boolean saveAdderss) throws Exception {
			getCurrentFunctionName(true);

			if (!"".equals(Countery))
				selectCountery(Countery);

			if (!"".equals(title))
				selectTitle(title);

			if (!"".equals(firstName))
				typeFirstName(firstName);

			if (!"".equals(lastName))
				typeLastName(lastName);

			if (!"".equals(address))
				typeAddress(address);

			if (!"".equals(city))
				typeCity(city);

			if (!"".equals(postal))
				typePostalCode(postal);

			if (!"".equals(phone))
				typePhone(phone);

			checkSaveAddress(saveAdderss);
			clickNext();

			getCurrentFunctionName(false);
		}

		public static void fillAndClickNext(String Countery, String title, String firstName, String lastName,
				String address, String city, String postal, String phone) throws Exception {
			getCurrentFunctionName(true);

			if (!"".equals(Countery))
				selectCountery(Countery);

			if (!"".equals(title))
				selectTitle(title);

			if (!"".equals(firstName))
				typeFirstName(firstName);

			if (!"".equals(lastName))
				typeLastName(lastName);

			if (!"".equals(address))
				typeAddress(address);

			if (!"".equals(city))
				typeCity(city);

			if (!"".equals(postal))
				typePostalCode(postal);

			if (!"".equals(phone))
				typePhone(phone);

			clickNext();

			getCurrentFunctionName(false);
		}

		// in case of using the address book
		public static void fillAndClickNext(boolean selectFromAddressBook) throws Exception {
			getCurrentFunctionName(true);
			clickOnAddressBook();
			selectFirstAddress();
			getCurrentFunctionName(false);
		}

		private static void selectFirstAddress() throws Exception {
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			getCurrentFunctionName(true);
			subStrArr.add(CheckOutSelectors.selectFirstAddress);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		private static void clickOnAddressBook() throws Exception {
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			getCurrentFunctionName(true);
			subStrArr.add(CheckOutSelectors.addressBookBtn);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}
		
		public static String getAlertInfo() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.alertInfo));
			subStrArr.add(CheckOutSelectors.alertInfo);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}
		
		public static String getCountryError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.countryError));
			subStrArr.add(CheckOutSelectors.countryError);
			valuesArr.add("index,1");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}
		
		public static String getTitelError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.titleError));
			subStrArr.add(CheckOutSelectors.titleError);
			valuesArr.add("index,1");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}
		
		public static String getFirstNameError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.fnameError));
			subStrArr.add(CheckOutSelectors.fnameError);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String getLastNameError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.lnameError));
			subStrArr.add(CheckOutSelectors.lnameError);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String getAddress1Error() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.address1Error));
			subStrArr.add(CheckOutSelectors.address1Error);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String getCityError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.cityError));
			subStrArr.add(CheckOutSelectors.cityError);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String getPostCodeEerror() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.postcodeEerror));
			subStrArr.add(CheckOutSelectors.postcodeEerror);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

	}// shipping address

	public static class shippingMethod {

		public static void fillAndclickNext(String shippingMethod) throws Exception {
			getCurrentFunctionName(true);
			selectShippingMethod(shippingMethod);
			clickNext();
			getCurrentFunctionName(false);

		}

		private static void selectShippingMethod(String shippingMethod) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.shippingMethod);
			valuesArr.add(shippingMethod);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		private static void clickNext() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.submitShippingMethodbtn);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		}

		public static String getOrderTotal() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderTotalShippingMethod);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(SelectorUtil.textValue.get());
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String getOrderSubTotal() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderSubTotalShippingMethod);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(SelectorUtil.textValue.get());
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

	}// shipping method

	public static class paymentInnformation {

		public static class keys {
			public static final String isSavedPayement = "saved-payment";

			public static final String name = "name";
			public static final String number = "number";
			public static final String expireYear = "expireYear";
			public static final String expireMonth = "expireMonth";
			public static final String CVCC = "CVCC";

		}

		public static void fillAndclickNext(String cardtype, String cardHolder, String cardNumber, String expireDay,
				String expireYear, String CVC, boolean savePayment, boolean billSameShip, String countery, String title,
				String firstName, String lastName, String address, String city, String postal, String phone)
				throws Exception {
			getCurrentFunctionName(true);

			if (!"".equals(cardtype))
				selectCardType(cardtype);
			if (!"".equals(cardHolder))
				typeCardholder(cardHolder);
			if (!"".equals(cardNumber))
				typeCardNumber(cardNumber);
			if (!"".equals(expireDay))
				selectExpireDay(expireDay);
			if (!"".equals(expireDay))
				typeExpireYear(expireYear);
			if (!"".equals(CVC))
				typeCVC(CVC);

			savePaymentMethod(savePayment);

			checkBillingAddressSameshipping(billSameShip);

			if (!billSameShip) {
				fillBillingAddress(countery, title, firstName, lastName, address, city, postal, phone);
			}

			clickNext();
			Thread.sleep(1000);
			getCurrentFunctionName(false);
		}

		public static void fillAndclickNext(String cardtype, String cardHolder, String cardNumber, String expireDay,
				String expireYear, String CVC, boolean billSameShip, String countery, String title, String firstName,
				String lastName, String address, String city, String postal, String phone) throws Exception {
			getCurrentFunctionName(true);

			if (!"".equals(cardtype))
				selectCardType(cardtype);
			if (!"".equals(cardHolder))
				typeCardholder(cardHolder);
			if (!"".equals(cardNumber))
				typeCardNumber(cardNumber);
			if (!"".equals(expireDay))
				selectExpireDay(expireDay);
			if (!"".equals(expireDay))
				typeExpireYear(expireYear);
			if (!"".equals(CVC))
				typeCVC(CVC);

			checkBillingAddressSameshipping(billSameShip);

			if (!billSameShip) {
				fillBillingAddress(countery, title, firstName, lastName, address, city, postal, phone);
			}

			clickNext();
			Thread.sleep(1000);
			getCurrentFunctionName(false);
		}

		// in case of using wallet
		public static void fillAndclickNext(boolean useAlreadySavedPayment) throws Exception {
			getCurrentFunctionName(true);
			clickOnUseedSavedCard();
			pickFirstpaymentsaved();
			getCurrentFunctionName(false);
		}

		public static void fillBillingAddress(String countery, String title, String firstName, String lastName,
				String address, String city, String postal, String phone) throws Exception {
			getCurrentFunctionName(true);

			if (!"".equals(countery))
				shippingAddress.selectCountery(countery);
			if (!"".equals(title))
				shippingAddress.selectTitle(title);
			if (!"".equals(firstName))
				shippingAddress.typeFirstName(firstName);
			if (!"".equals(lastName))
				shippingAddress.typeLastName(lastName);
			if (!"".equals(address))
				shippingAddress.typeAddress(address);
			if (!"".equals(city))
				shippingAddress.typeCity(city);
			if (!"".equals(postal))
				shippingAddress.typePostalCode(postal);
			if (!"".equals(phone))
				shippingAddress.typePhone(phone);

			getCurrentFunctionName(false);
		}

		private static void pickFirstpaymentsaved() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.selectFirstPayment);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		private static void clickOnUseedSavedCard() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.savedPaymentBtn);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		}

		public static void clickNext() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.submitPayment);
			valuesArr.add(String.valueOf(""));
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		}

		public static void checkBillingAddressSameshipping(boolean billSameShip) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.checkSame);
			valuesArr.add(String.valueOf(billSameShip));
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		private static void savePaymentMethod(boolean savePayment) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.checkSavePayment);
			valuesArr.add(String.valueOf(savePayment));
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		}

		public static void typeCVC(String CVC) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.CVC);
			valuesArr.add(CVC);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static void typeExpireYear(String expireYear) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.expireYear);
			valuesArr.add(expireYear);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static void selectExpireDay(String expireDay) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.expireDay);
			valuesArr.add(expireDay);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static void typeCardNumber(String cardNumber) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.cardNumber);
			valuesArr.add(cardNumber);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static void typeCardholder(String cardHolder) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.cardHolder);
			valuesArr.add(cardHolder);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static void selectCardType(String cardtype) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.cardtype);
			valuesArr.add(cardtype);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static String getOrderTotal() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderTotalPymentInfo);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(SelectorUtil.textValue.get());
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String getOrdershipping() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderShippingPymentInfo);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(SelectorUtil.textValue.get());
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String getOrderSubTotal() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderSubtotalPymentInfo);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(SelectorUtil.textValue.get());
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}
		
		public static String getAlertInfo() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.alertInfo));
			subStrArr.add(CheckOutSelectors.alertInfo);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}
		
		public static String getCardTypeError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.cardTypeError));
			subStrArr.add(CheckOutSelectors.cardTypeError);
			valuesArr.add("index,1");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}
		
		public static String getCardNumberError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.accountNumberError));
			subStrArr.add(CheckOutSelectors.accountNumberError);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}
		
		public static String getExpirationMonthError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.expirationMonthError));
			subStrArr.add(CheckOutSelectors.expirationMonthError);
			valuesArr.add("index,1");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}
		
		public static String getExpirationYearError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.expirationYearError));
			subStrArr.add(CheckOutSelectors.expirationYearError);
			valuesArr.add("index,1");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}
		
		public static String getCVNumberError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.cvNumberError));
			subStrArr.add(CheckOutSelectors.cvNumberError);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}
	}// payment info

	public static class reviewInformation {

		public static String getSubtotal() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.summaryTotal);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();

		}

		public static String shippingCost() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.shippingCost);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();

		}

		public static void acceptTerms(boolean acceptTerm) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.acceptTerm);
			valuesArr.add(String.valueOf(acceptTerm));
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static void placeOrder() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.placeOrderBtn);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static String getOrderTotal() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderTotalOrderSumary);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(SelectorUtil.textValue.get());
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String gettotal() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderTotalOrderSumary);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

	}// order review information

	public static class orderConfirmation {

		public static class keys {
			public static final String isSavedPayement = "saved-payment";

			public static final String orderId = "orderId";
			public static final String email = "email";
			public static final String orderTotal = "orderTotal";
			public static final String orderSubtotal = "orderSubtotal";
			public static final String orderTax = "orderTax";
			public static final String orderShipping = "orderShipping";

		}

		public static String getOrderId() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderId);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();

		}

		public static String getOrderTotal() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderConfirmationTotal);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();

		}

		public static String getSubTotal() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderConfirmationSubtotal);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();

		}

		public static String getShippingCost() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderConfirmationShippingCost);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();

		}

		public static String getBillingAddrerss() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderconfirmationBillingAddress);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();

		}

		public static String getShippingAddrerss() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderconfirmationshippingAddress);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String getBillToCountryError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.billToCountryError));
			subStrArr.add(CheckOutSelectors.billToCountryError);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

	}// order confirmation page
	
	// B2B order confirmation page
	public static class B2BOrderConfirmation {
	
		public static String getOrderTotal() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.B2BorderConfirmationTotal);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();

		}
		public static String getBillingAddrerss() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.B2BorderconfirmationBillingAddress);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();

		}

		public static String getShippingAddrerss() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.B2BorderconfirmationshippingAddress);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}
	}
}
