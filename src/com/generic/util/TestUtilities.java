package com.generic.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import com.generic.setup.EnvironmentFiles;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;


public class TestUtilities extends SelTestCase {
	
	public static void prepareLogs() throws Exception {
		logs.debug(MessageFormat.format(LoggingMsg.CLEAR_LOGS_MSG,""));
		String logs_dir = EnvironmentFiles.getLogFilePath();
		String log_file = EnvironmentFiles.getLogFileName();
		String log_abs_path = logs_dir + "/" + log_file;
		
		PrintWriter writer = new PrintWriter(log_abs_path);
		writer.print("");
		writer.close();
		logs.debug(MessageFormat.format(LoggingMsg.CLEAR_LOGS_MSG, " done"));
	}

     public static void reportSetup() throws Exception {

            try {
                if(runReportSetup) {
                    logs.debug(LoggingMsg.EXECUTE_REPORT_SETUP);
                    
                    try {
                        mainReportDir = EnvironmentFiles.getReportsFolderPath();
                        
                        logs.debug(MessageFormat.format(LoggingMsg.REPORT_DIR, mainReportDir));
                        
                        File dir1 = new File(mainReportDir);
                        boolean exists = dir1.exists();
                        if (!exists) {
                            logs.debug(MessageFormat.format(LoggingMsg.MAIN_DIR_EXISTANCE_MSG, "does not", exists));
                            dir1.mkdir(); // creating main directory if it doesn't exist
                        } else {
                            logs.debug(MessageFormat.format(LoggingMsg.MAIN_DIR_EXISTANCE_MSG, "does", exists));
                        }
                        createRunReportDirectory();
                    } catch (Throwable t) {
                        t.printStackTrace();
                        logs.debug(LoggingMsg.FAILED_REPORT_FOLDERS_CREATION_MSG);
                    }

                    ReportUtil.startTesting(reportDirectory + "//index.html",
                            ReportUtil.now(SelTestCase.time_date_format),
                            SelTestCase.getCONFIG().getProperty("testEnvironment"));

                    runReportSetup = false;
                }

            } catch (Throwable t) {
                t.printStackTrace();
                throw new Exception(t);
            }

        }

     public static void createRunReportDirectory() {
         Date dNow = new Date( );
         SimpleDateFormat ft = new SimpleDateFormat (SelTestCase.reportFolderDateStampFormat);
         Calendar cal = Calendar.getInstance();
         SimpleDateFormat sdf = new SimpleDateFormat(SelTestCase.reportFolderTimeStampFormat);
    	 reportDirectory = mainReportDir + "/" +"AutoRep_" +ft.format(dNow) + sdf.format(cal.getTime());
         new File(reportDirectory).mkdir();

     }

     
    public static void configInitialization() throws Exception{
    	getCurrentFunctionName(true);
    	
		logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, "Execute initialize function"));
		// config property file
		setCONFIG(new Properties());
		FileInputStream fn =new FileInputStream(EnvironmentFiles.getConfigFilePath());
		getCONFIG().load(fn);
		logs.debug(MessageFormat.format(LoggingMsg.ADDED_ENVIRONMENT_NAME, getCONFIG().getProperty("testEnvironment")));
		//getCONFIG().setProperty("testSiteName", "https://"+getCONFIG().getProperty("testEnvironment")+"/"+getCONFIG().getProperty("testSiteName"));
		//getCONFIG().setProperty("logout", "https://"+getCONFIG().getProperty("testEnvironment")+"."+getCONFIG().getProperty("logout"));
		
		logs.debug(MessageFormat.format(LoggingMsg.REPORT_TCID_MSG, testCaseRepotId));
		
		setDatatable(new Xls_Reader(EnvironmentFiles.getDataSheetPath()));
		
		//set the max wait time
		setWaitTime(Integer.parseInt(getCONFIG().getProperty("waitTime")));
		
        getCurrentFunctionName(false);
    }

}
