package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.generic.selector.RegistrationSelectors;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

public class Registration extends SelTestCase {
	public static class keys {
		public static final String caseId = "caseId";
		public static final String title = "title";
		public static final String name = "name";
		public static final String userName = "userName";
		public static final String firstName = "firstName";
		public static final String lastName = "lastName";
		public static final String password = "password";
		public static final String email = "mail";

	}

	public static void selectTitle(String title) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.SELECTING_ELEMENT_VALUE, "title ", title));
		subStrArr.add(RegistrationSelectors.title);
		valuesArr.add(title);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	public static void typeFirstName(String firstName) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "firstname ", firstName));
		subStrArr.add(RegistrationSelectors.firstName);
		valuesArr.add(firstName);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);

	}

	public static void typeLastName(String lastName) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "lastname ", lastName));
		subStrArr.add(RegistrationSelectors.lastName);
		valuesArr.add(lastName);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);

	}

	public static void typeEmailAddress(String address) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "emailAddress ", address));
		subStrArr.add(RegistrationSelectors.emailAddress);
		valuesArr.add(address);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);

	}

	public static void typePassword(String password) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "password ", password));
		subStrArr.add(RegistrationSelectors.password);
		valuesArr.add(password);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	public static void typeConfirmPassword(String confPassword) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "confirmPassword", confPassword));
		subStrArr.add(RegistrationSelectors.confirmPassword);
		valuesArr.add(confPassword);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	public static void checkConsentGiven1(boolean isConsentGiven1Checked) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CHECKBOX_SEL_VAL, "isConsentGiven1Checked", isConsentGiven1Checked));
		subStrArr.add(RegistrationSelectors.consentGiven);
		valuesArr.add(String.valueOf(isConsentGiven1Checked));
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);

	}

	public static void clickRegistration() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, "Register btn"));
		subStrArr.add(RegistrationSelectors.register);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);

	}

	public static void fillAndClickRegister(String title, String fName, String lName, String email, String pass,
			String confPass, boolean isConsentGiven1Checked) throws Exception {
		getCurrentFunctionName(true);
		if (!"".equals(title))
			selectTitle(title);

		if (!"".equals(fName))
			typeFirstName(fName);

		if (!"".equals(lName))
			typeLastName(lName);

		if (!"".equals(email))
			typeEmailAddress(email);

		if (!"".equals(pass))
			typePassword(pass);

		if (!"".equals(confPass))
			typeConfirmPassword(confPass);

		checkConsentGiven1(isConsentGiven1Checked);

		clickRegistration();
		getCurrentFunctionName(false);

	}

	public static String getFirstNameError() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "First Name Error"));
		subStrArr.add(RegistrationSelectors.firstNameError);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();

	}

	public static String getLastNameError() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Last Name Error"));
		subStrArr.add(RegistrationSelectors.lastNameError);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();

	}

	public static String getTitleError() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Title Error"));
		subStrArr.add(RegistrationSelectors.titleError);
		valuesArr.add("index,1");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	public static String getEmailAddressError() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Email Address Error"));
		subStrArr.add(RegistrationSelectors.emailAddressError);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	public static String getPasswordError() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Password Error"));
		subStrArr.add(RegistrationSelectors.passwordError);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	public static String getConfirmPasswordError() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Confirm Password Error"));
		subStrArr.add(RegistrationSelectors.confirmPasswordError);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	public static String getPasswordRulesError() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Password Rules Error"));
		subStrArr.add(RegistrationSelectors.passwordRulesError);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	public static String getPasswordMatchError() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Password Mismatch Error"));
		subStrArr.add(RegistrationSelectors.passwordMatchError);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	public static String getRandomTitle() {
		String[] titles = { "Mr.", "Mrs.", "Miss", "Ms.", "Dr.", "Rev." };
		Random random = new Random();
		return titles[random.nextInt(titles.length)];
	}

	public static void verifyRegistrationFormErrors() throws Exception {
		getCurrentFunctionName(true);
		getTitleError();
		getFirstNameError();
		getLastNameError();
		getEmailAddressError();
		getPasswordError();
		getConfirmPasswordError();
		getPasswordRulesError();
		getCurrentFunctionName(false);
	}

	public static String getRegistrationSuccessMessage() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Account created success message"));
		subStrArr.add(RegistrationSelectors.registrationSuccess);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();

	}
}
