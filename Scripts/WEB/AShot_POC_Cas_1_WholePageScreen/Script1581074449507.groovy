import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * A) Cas de test numéro 1
 * Prérequis : Dossier Screenshot ( racine du projet ) -> Dossier dans lequel seront rangé les screenshots
 * 			 : Dossier Baseline-> Bibliothèque d'image
 * 
 * B) Workflow: 
 *    a) Switch for staging-uk profile
 * 	  b) take screenshot of Dashboard pdi fr with takescreenshot() function
 * 	  c) baseline the screenshot (copy it tinto Baseline folder)
 * 	  d) Open https://www.katalon.com/
 * 	  e) Take screenshot of the full page of https://www.katalon.com/ takescreenshot()
 *    f) Take screenshot of the full page of https://www.katalon.com/ takeEntirescreenshot()
 *    g) Compare if element in root patch from a) is matched with the baselined image b)
*/

//call test case to connect to PDI
WebUI.callTestCase(findTestCase('WEB/Deskotp/connexion_passant'), [('userName') : GlobalVariable.user, ('password') : GlobalVariable.password],
	FailureHandling.STOP_ON_FAILURE)

//Variable
WebDriver driver

String baseline_folder     = 'Screenshots/Baseline/'
String screenshot_folder     = 'Screenshots/'
driver = DriverFactory.getWebDriver()
driver.manage().window().fullscreen()

for(File file: new java.io.File(screenshot_folder).listFiles())
if (!file.isDirectory())
	file.delete();


 'Test Screenshot'
 CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ScreenCapture.takeScreenshot'( 'fullscreen_PDI_FR.png',
	 FailureHandling.OPTIONAL)

'Baseline the image => take the screenchot and put into /Baseline folder'
 CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ScreenCapture.baselineImage'("fullscreen_PDI_FR.png", 
    baseline_folder, FailureHandling.OPTIONAL)
 
 'Open katalon.com'
 WebUI.openBrowser("https://www.katalon.com/")
 
 'Maximize katalon.com '
WebUI.maximizeWindow(FailureHandling.CONTINUE_ON_FAILURE)


'Take screenshot of the full page of https://www.katalon.com/'
//!\\ You have to maximize the page to have the whole screenshot.
 CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ScreenCapture.takeEntirePageScreenshot'( 'fullscreen_Katalon_Scale.png',
FailureHandling.OPTIONAL)
 
 'Take screenshot of the full page of https://www.katalon.com/'
 //!\\ You have to maximize the page to have the whole screenshot.
 CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ScreenCapture.takeScreenshot'( 'fullscreen_Katalon_NoScale.png',
 FailureHandling.OPTIONAL)
 
 'Compare if element in root patch is matched with the baselined image'
 CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ImageComparison.verifyMatchBaseline'("fullscreen_PDI_FR.png",baseline_folder, FailureHandling.OPTIONAL)
 
 
 'Close browser'
 WebUI.closeBrowser()
