package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.generic.selector.ErrorPagesSelectors;
import com.generic.setup.SelTestCase;
import com.generic.setup.LoggingMsg;
import com.generic.util.SelectorUtil;

public class errorPages extends SelTestCase {

	public static class keys {
		public static final String caseId = "caseId";
	}

	public static String getGlobalAlertsMsg() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(
				MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, ErrorPagesSelectors.globalAlerts));
		subStrArr.add(ErrorPagesSelectors.globalAlerts);
		valuesArr.add("");
		String globalAlertMsg = "";
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		globalAlertMsg = SelectorUtil.textValue.get();
		getCurrentFunctionName(false);
		return globalAlertMsg;
	}
}
