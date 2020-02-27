import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver as WebDriver ;
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

//call test case to connect to PDI
WebUI.callTestCase(findTestCase('WEB/Deskotp/connexion_passant'), ["userName":GlobalVariable.user,"password":GlobalVariable.password], FailureHandling.STOP_ON_FAILURE)

//WebUI.openBrowser("www.google.com")
WebDriver driver ;
driver = DriverFactory.getWebDriver()
driver.manage().window().fullscreen()


'Take screenshot of the page within screen size' 
//CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ScreenCapture.takeScreenshot'('fullscreen.png', FailureHandling.OPTIONAL)

'Take screenshot of the page within screen size and cut header and footer' 
//CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ScreenCapture.takeCuttingScreenshot'('cutting_screen.png',100, 50, FailureHandling.OPTIONAL)

'Take screenshot of the page within screen size and scale image according to dpr' 
//CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ScreenCapture.takeScalingScreenshot'('scaling_screen.png',2 , FailureHandling.OPTIONAL)

'Take screenshot of the full page' 
//CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ScreenCapture.takeEntirePageScreenshot'('fullpage_screen.png', FailureHandling.OPTIONAL)

'Take screenshot of the logo' 
//CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ScreenCapture.takeWebElementScreenshot'(ObjectRepository.findTestObject('Object Repository/Cambridge/img_Logo'), filename, 10, FailureHandling.OPTIONAL) 'Baseline the image' CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ScreenCapture.baselineImage'(filename, baselineDir, FailureHandling.OPTIONAL)

'Compare logo is matched the baselined image' 
//CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ImageComparison.verifyMatchBaseline'(filename, baselineDir, FailureHandling.CONTINUEONFAILURE)

'Compare logo is matched the another image' 
//CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ImageComparison.areMatched'(baselineDir + "\" + filename, screenshotDir + "\" + filename, FailureHandling.CONTINUEONFAILURE)

'Get difference ratio of two images' 
//def ratio = CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ImageComparison.getDifferenceRatio'(baselineDir + "\" + filename, screenshotDir + "\" + filename, FailureHandling.CONTINUEONFAILURE) println ratio


