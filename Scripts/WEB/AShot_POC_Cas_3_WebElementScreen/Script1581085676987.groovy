import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import javax.imageio.ImageIO as ImageIO
import org.apache.commons.io.FileUtils as FileUtils
import org.openqa.selenium.OutputType as OutputType
import org.openqa.selenium.TakesScreenshot as TakesScreenshot
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import org.openqa.selenium.By as By;
import org.openqa.selenium.By.ByClassName
import org.openqa.selenium.By.ById
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver as WebDriver ;
import org.openqa.selenium.WebElement as WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.pagefactory.AjaxElementLocator.NoSuchElementError
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import common.utilities
import com.diginuance.ConfirmEmail as CEmail
String myState = "OK";
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * A) Cas de test numéro 3
 * Prérequis : Dossier Screenshot ( racine du projet ) -> Dossier dans lequel seront rangé les screenshots
 * 			 : Dossier Baseline-> Bibliothèque d'image
 * B) Workflow :
 *    a) Switch to fr profile
 * 	  b) take screenshot ( by default in Screenshot forlder) of PDI from uk account
 * 	  c) baseline the screenshot (copy it tinto Baseline folder)
 * 	  d) Comparation between a) et a) from previous test case
 * 	  e) Test KO
*/

//call test case to connect to PDI

WebUI.callTestCase(findTestCase('WEB/Deskotp/connexion_passant'), [('userName') : GlobalVariable.user, ('password') : GlobalVariable.password],
	FailureHandling.STOP_ON_FAILURE)

// Variable
WebDriver driver
driver = DriverFactory.getWebDriver()

//Test Folder
String baseline_folder   = 'Screenshots/Baseline/'
String screenshot_folder = 'Screenshots/'

//Wait for 
WebDriverWait await = new WebDriverWait(driver, 50);

my_use= new utilities()
my_use_email = new CEmail()
driver.manage().window().fullscreen() 
WebUI.waitForPageLoad(10)


//Click overview section
WebElement overview = driver.findElement(By.xpath("//*[@src = '../../../assets/images/dashboard/Overview.svg']")).click()
WebElement icon = driver.findElement(By.id("tarteaucitronPersonalize"))
//wait for cookie pop up
await.until(ExpectedConditions.presenceOfElementLocated(By.id("tarteaucitronPersonalize")))
	try {
			//If disconnect button is visible and enable Hightlight it
			if(icon.enabled && icon.displayed == true){
				//my_use.runhightlight("profile-icon", webdriver, myState)
				my_use.runhightlightById("tarteaucitronPersonalize", driver, myState)
				icon.click()
		}
	}
	catch (NoSuchElementError e) {
		mystate="KO"
		System.out.println("Element Not Found : " + e.message);
		}
// wait for 5s seconds
WebUI.delay(5)

'Empty Screenshot folder'
for(File file: new java.io.File(screenshot_folder).listFiles())
if (!file.isDirectory())
	file.delete();
	
'Take screenshot element'
CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ScreenCapture.takeWebElementScreenshot'(
ObjectRepository.findTestObject('WEB/sample_WEB/Dashboard_fr/Page_Property Data Insight/Page_Property Data Insight/div_Stacking planAQUAREL (1)'), "img_div_aquarel.png",5, FailureHandling.OPTIONAL)

'Take screenshot element'
CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ScreenCapture.takeWebElementScreenshot'(
	ObjectRepository.findTestObject('Object Repository/WEB/sample_WEB/Dashboard_fr/Page_Property Data Insight/Page_Property Data Insight/div_0'), "img_cp.png", 5, FailureHandling.OPTIONAL)

'take screenshot element'
CustomKeywords.'kms.turing.katalon.plugins.visualtesting.ScreenCapture.takeWebElementScreenshot'(
	ObjectRepository.findTestObject('Object Repository/WEB/sample_WEB/Dashboard_fr/Page_Property Data Insight/Page_Property Data Insight/div_ExportStacking planTenancy scheduleOccupier contacts'), "img_cp_block_tenancy.png", 5, FailureHandling.OPTIONAL)

WebUI.closeBrowser()