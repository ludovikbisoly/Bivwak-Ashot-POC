import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

/*
 * A) Cas de test numéro 1
 * Prérequis : Dossier Screenshot ( racine du projet ) -> Dossier dans lequel seront rangé les screenshots
 * 			 : Dossier Baseline-> Bibliothèque d'image
 * B) Workflow : 
 * 	  a) take screenshot ( by default in Screenshot forlder)
 * 	  b) baseline the screenshot (copy it tinto Baseline folder)
 * 	  c) Comparation between a) et b)
 * 	  d) Test OK
*/

//call test case to connect to PDI
WebUI.callTestCase(findTestCase('WEB/Deskotp/connexion_passant'), [('userName') : GlobalVariable.user, ('password') : GlobalVariable.password],
	FailureHandling.STOP_ON_FAILURE)

//Variable
WebDriver driver

String baseline_folder     = 'Screenshots/Baseline/'
driver = DriverFactory.getWebDriver()
driver.manage().window().fullscreen()

'Take screenshot of full page with : takeEntirePageScreenshot function '
 CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ScreenCapture.takeEntirePageScreenshot'( 'fullscreen_PDI_takeEntirePageScreenshot.png', 
 FailureHandling.OPTIONAL)
 
 'Test Screenshot 2'
 CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ScreenCapture.takeScreenshot'( 'fullscreen_PDI_takeScreenshot.png',
	 FailureHandling.OPTIONAL)

'Baseline the image => take the screenchot and put into /Baseline folder'
 CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ScreenCapture.baselineImage'("fullscreen_PDI_takeScreenshot.png", 
    baseline_folder, FailureHandling.OPTIONAL)
 
 'Open LequipeTV.fr'
 WebUI.openBrowser("www.lequipetv.fr")
 
 'Maximize LequipeTV.fr '
 WebUI.maximizeWindow()
 
 'Take screenshot of the full page of lequipetv'
 //!\\ You have to maximize the page to have the whole screenshot.
 CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ScreenCapture.takeEntirePageScreenshot'( 'fullscreen_Lequipe_max.png',
 FailureHandling.OPTIONAL)
 
 'Close Browser'
 WebUI.closeBrowser()
 
 'Open LequipeTV.fr'
 WebUI.openBrowser("www.lequipetv.fr")
 
'Take screenshot of the full page of lequipetv'
//!\\ You have to maximize the page to have the whole screenshot.
 CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ScreenCapture.takeEntirePageScreenshot'( 'fullscreen_Lequipe_no_max.png',
FailureHandling.OPTIONAL)
 
 'Close browser'
 WebUI.closeBrowser()
 
'Compare if element in root patch is matched with the baselined image'
 CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ImageComparison.verifyMatchBaseline'("fullscreen_PDI_takeScreenshot.png",baseline_folder, FailureHandling.OPTIONAL)
 
