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

/*
 * A) Cas de test numéro 4
 * Prérequis : Dossier Screenshot ( racine du projet ) -> Dossier dans lequel seront rangé les screenshots
 * 			 : Dossier Baseline-> Bibliothèque d'image
 * B) Workflow :
 * 	  a) Compare if Screenshot from a) matched with Screenshot a) from previous Test case
 *    b) Get the difference ration between both screenshot
*/

        //\\
	   //!!\\
      // !! \\
     //  !!  \\
    //   !!   \\ ========> DO YOUR MANUAL SCREENSHOT(S) AND PUT IT INTO SCREENSHOT FOLDER !
   //          \\
  //     !!     \\=======> REPLACE THE NAME OF THE SCREENSHOT(S) IN MYSCREENSHOT VARIABLE !
 //==============\\

String baseline_folder       = 'Screenshots/Baseline/'
String screenshot_folder     = 'Screenshots/'
String MyScreenhot           = 'screenshot_ashot_insta.png'


'Open katalon.com'
WebUI.openBrowser("https://www.instagram.com/")

WebUI.maximizeWindow()

'Take screenshot of the full page of https://www.katalon.com/'
//!\\ You have to maximize the page to have the whole screenshot.
CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ScreenCapture.takeScreenshot'( 'screenshot_ashot_insta.png',
FailureHandling.OPTIONAL)

'Baseline the image => take the screenchot and put into /Baseline folder'
CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ScreenCapture.baselineImage'("insta.png",
   baseline_folder, FailureHandling.OPTIONAL)


'Compare logo is matched the another image'
CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ImageComparison.areMatched'(baseline_folder  + 'insta.png', screenshot_folder + MyScreenhot, FailureHandling.OPTIONAL)

'Get difference ratio of two images'
def ratio = CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ImageComparison.getDifferenceRatio'(baseline_folder  + 'insta.png', screenshot_folder + MyScreenhot, FailureHandling.OPTIONAL)

System.out.println("the difference ratio between both Screenshots is " + ratio + "%")

