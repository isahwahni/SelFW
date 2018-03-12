package com.generic.page;


import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import com.generic.selector.CLPSelectors;
import com.generic.setup.EnvironmentFiles;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
/**
 * The Class HomePage.
 */
public class CLP extends SelTestCase {
	
	public static void prepareBaselineforLogs(String baseline) throws Exception {
		String VTAs =EnvironmentFiles.getVisualTestingAssetsPath();
		String baselineAbsPath = VTAs + "/" + baseline+ ".png";
		String logs_dir = EnvironmentFiles.getLogFilePath();	
    	File baseLineFile = new File(baselineAbsPath);
		FileUtils.copyFileToDirectory(baseLineFile, Paths.get(logs_dir).toFile());
		String baselinePathInLogs =  logs_dir + "/" + baseline + ".png";
		logs.debug("IMAGE:<br><a target=\"_blank\" href="+ baseline + ".png"+"><img src=" + baseline + ".png"+" alt=" + baseline + ".png"+" style=\"width:150px\"></a>");
	}
	
	public static void updateHeaderBaseline(String baseline) throws Exception {
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "guest mail", baseline));
		subStrArr.add(CLPSelectors.header);
		valuesArr.add("VisualTesting");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		String imagePath = EnvironmentFiles.getVisualTestingAssetsPath() + "/" + baseline+".png";
		ImageIO.write(SelectorUtil.screenShot.get().getImage(),"PNG",new File(imagePath));
		logs.debug("IMAGE:<br><a target=\"_blank\" href="+ baseline+".png"+"><img src=" + baseline+".png"+" alt=" + baseline+".png"+" style=\"width:150px\"></a>");
		getCurrentFunctionName(false);
	}

	public static boolean verifyHeader(String baseline) throws Exception {
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "guest mail", baseline));
		subStrArr.add(CLPSelectors.header);
		valuesArr.add("VisualTesting");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		String imagePath = EnvironmentFiles.getLogFilePath() + "/" + baseline+"_actual.png";
		BufferedImage actualImage = SelectorUtil.screenShot.get().getImage();
		
		ImageIO.write(SelectorUtil.screenShot.get().getImage(),"PNG",new File(imagePath));
		logs.debug("IMAGE:<br><a target=\"_blank\" href="+ baseline+"_actual.png"+"><img src=" + baseline+"_actual.png"+" alt=" + baseline+"_actual.png"+" style=\"width:150px\"></a>");
		
		String BaseImagePath = EnvironmentFiles.getVisualTestingAssetsPath() + "/" + baseline+".png";
		
		BufferedImage expectedImage = ImageIO.read(new File(BaseImagePath));
        
		ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
		
		getCurrentFunctionName(false);
		return !diff.hasDiff();
	}
	
}
