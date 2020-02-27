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
 * A) Cas de test numéro 2 a)
 * Prérequis : Dossier Screenshot ( racine du projet ) -> Dossier dans lequel seront rangé les screenshots
 * 			 : Dossier Baseline-> Bibliothèque d'image
 * B) Workflow : 
 *    a) Switch for staging-uk profile
 * 	  b) Take screenshot of Dashboard pdi uk with takescreenshot() function
 * 	  c) Baseline the screenshot (copy it into Baseline folder)
 * 	  d) Check if element in root patch a) matched with the Screenshot in the baseline folder
 * 	  e) Compare if Screenshot from a) matched with Screenshot a) from previous Test case
 *    f) Get the difference ration between both screenshot
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

 'Test Screenshot'
 CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ScreenCapture.takeScreenshot'( 'fullscreen_PDI_UK.png',
	 FailureHandling.OPTIONAL)

'Baseline the image => take the screenchot and put into /Baseline folder'
 CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ScreenCapture.baselineImage'("fullscreen_PDI_UK.png", 
    baseline_folder, FailureHandling.OPTIONAL)
 
'Compare if element in root patch is matched with the baselined image'
 CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ImageComparison.verifyMatchBaseline'("fullscreen_PDI_UK.png",baseline_folder, FailureHandling.OPTIONAL)
 
 'Compare Screenshot' 
 CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ImageComparison.areMatched'(baseline_folder  + 'fullscreen_PDI_FR.png', screenshot_folder + 'fullscreen_PDI_UK.png', FailureHandling.OPTIONAL)
 
 'Get difference ratio of two images' 
 def ratio = CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ImageComparison.getDifferenceRatio'(baseline_folder  + 'fullscreen_PDI_FR.png', screenshot_folder + 'fullscreen_PDI_UK.png', FailureHandling.OPTIONAL)
 
System.out.println("the difference ratio between both Screenshots is " + ratio + "%")
 
 WebUI.closeBrowser()
