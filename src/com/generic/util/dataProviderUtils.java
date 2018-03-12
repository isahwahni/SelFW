package com.generic.util;

import java.text.MessageFormat;
import com.generic.setup.EnvironmentFiles;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;

public class dataProviderUtils {

	private static dataProviderUtils myObj;
	SASLogger logs = new SASLogger("DataProviderUtils");

	private dataProviderUtils() {
		// TODO Auto-generated constructor stub
	}

	public static dataProviderUtils getInstance() {
		if (myObj == null) {
			myObj = new dataProviderUtils();
		}
		return myObj;
	}

	// get the data from xls file
	public Object[][] getData(String testName) {
		return getData(testName, 2);
	}

	public Object[][] getData(String testName, int startingRow) {
		/*
		 * if the sheet is regression then the sheet name should contains the
		 * "regression" word and in col 2 should have the property runTest (empty not->
		 * run , full-> run) so this function can ignore the test cases before it's been
		 * send to parameterized class
		 */
		// starting row 1 to rows sheets
		if (SelTestCase.getDatatable() == null) {
			SelTestCase.setDatatable(new Xls_Reader(EnvironmentFiles.getDataSheetPath()));
		}
		int rows = SelTestCase.getDatatable().getRowCount(testName) - 1;
		// if empty sheet return empty data
		if (rows <= 0) {
			Object[][] testData = new Object[1][0];
			return testData;
		}

		rows = SelTestCase.getDatatable().getRowCount(testName);
		int cols = SelTestCase.getDatatable().getColumnCount(testName);

		logs.debug(MessageFormat.format(LoggingMsg.TEST_NAME, testName) + "this sheet contains rows:"+rows+" and cols:"+cols);
		Object data[][] = new Object[rows - (startingRow - 1)][cols];// rows -1 since we dont have to include header

		int ignoredCases = 0;

		for (int rowNum = startingRow; rowNum <= rows; rowNum++) {
			if (testName.contains("Regression")) {
				if (SelTestCase.getDatatable().getCellData(testName, 1, 1).contains("runTest")
						&& !SelTestCase.getDatatable().getCellData(testName, 1, rowNum).equals("")) {
					for (int colNum = 0; colNum < cols; colNum++) {
						data[rowNum - startingRow][colNum] = SelTestCase.getDatatable().getCellData(testName, colNum,
								rowNum);
					}
				} else {
					for (int colNum = 0; colNum < cols; colNum++) {
						data[rowNum - startingRow][colNum] = "";
					}
					ignoredCases++;
				}
			} else {
				for (int colNum = 0; colNum < cols; colNum++) {
					data[rowNum - startingRow][colNum] = SelTestCase.getDatatable().getCellData(testName, colNum,
							rowNum);
				}
			}
		}
		logs.debug(MessageFormat.format(LoggingMsg.IGNORE_CASES, ignoredCases));

		// remove ignored cases
		Object dataFinal[][] = new Object[rows - (startingRow - 1) - ignoredCases][cols];
		int realRow = 0;
		for (int row = 0; row < data.length; row++) {
			if (!data[row][1].toString().equals("")) {
				for (int colNum = 0; colNum < cols; colNum++) {
					dataFinal[realRow][colNum] = data[row][colNum];
				}
				realRow++;
			}
		}

		return dataFinal;
	}

}
