package mobile.utils;

import static com.diconium.qa.testautomationframework.common.Logger.logInfo;
import static mobile.androidapp.common.AndroidFactory.androidDriver;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import com.diconium.qa.testautomationframework.web.Waiters;
import com.diconium.qa.testautomationframework.web.WebConstants;
import io.appium.java_client.AppiumFluentWait;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.time.Duration;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.allure.annotations.Step;

public class MobileUtils {

  private static final Logger log = LoggerFactory.getLogger(Waiters.class);

  static Wait<AndroidDriver> getFluentWait() {
    return getFluentWait(WebConstants.FLUENT_WAIT_TIMEOUT_SECONDS);
  }


  private static Wait<AndroidDriver> getFluentWait(long timeoutSeconds) {
    return new AppiumFluentWait(androidDriver)
        .withTimeout(Duration.ofSeconds(timeoutSeconds))
        .pollingEvery(Duration.ofSeconds(2))
        .ignoring(NoSuchElementException.class)
        .ignoring(TimeoutException.class);
  }

  @Step
  public static void waitUntilMobileElementVisible(MobileElement mobileElement) {
    log.trace("Waiting for {} to be visible", mobileElement);
    getFluentWait().until(ExpectedConditions.visibilityOf(mobileElement));
  }

  @Step
  public static void waitUntilMobileElementInvisible(String mobileElementXpath) {
    log.trace("Waiting for {} to be invisible", mobileElementXpath);
    getFluentWait().until(ExpectedConditions
        .invisibilityOfElementLocated(MobileBy.xpath(mobileElementXpath)));
  }

  @Step
  public static void waitUntilTextWillBePresent(MobileElement mobileElement, String text) {
    log.trace("Waiting for text \"{}\" to be present", mobileElement);
    getFluentWait().until(ExpectedConditions.textToBePresentInElement(mobileElement, text));
  }

  @Step
  public static void waitUntilElementClickable(MobileElement mobileElement) {
    log.trace("Waiting for element \"{}\" to be clickable", mobileElement);
    getFluentWait().until(ExpectedConditions.elementToBeClickable(mobileElement));
  }

  public static String getMobileAttributeValue(MobileElement mobileElement){
    return mobileElement.getAttribute("value");
  }

  public static boolean getMobileElementCheckboxStatus(MobileElement mobileElement){
    return Boolean.parseBoolean(mobileElement.getAttribute("checked"));
  }

  public static boolean isCheckboxChecked(MobileElement mobileElement){
    boolean checkboxStatus = getMobileElementCheckboxStatus(mobileElement);
    if(checkboxStatus){
      logInfo("Check box: "+mobileElement+" is checked on");
      return true;
    } else {
      logInfo("Check box: "+mobileElement+" is checked off");
      return false;
    }
  }

  public static void checkOnCheckbox(MobileElement mobileElement){
    if(!isCheckboxChecked(mobileElement)){
        clickMobileElement(mobileElement);
        assertTrue(isCheckboxChecked(mobileElement));
        logInfo("Checked on the checkbox successfully");
    }
  }

  public static void uncheckOnCheckbox(MobileElement mobileElement){
    if(isCheckboxChecked(mobileElement)){
      clickMobileElement(mobileElement);
      assertFalse(isCheckboxChecked(mobileElement));
      logInfo("UnChecked the checkbox successfully");
    }
  }

  public static void clickMobileElement(MobileElement mobileElement){
    mobileElement.click();
    logInfo(String.format("%s clicked successfully", mobileElement));
  }

  public static void sendKeysToMobileElement(MobileElement mobileElement, String input){
    mobileElement.sendKeys(input);
    logInfo(String.format("%s is entered in %s", input, mobileElement));
  }

  public static String getTextFromMobileElement(MobileElement mobileElement){
    return mobileElement.getText();
  }

  public static void switchScreenToLandscape(){
    androidDriver.rotate(ScreenOrientation.LANDSCAPE);
    ScreenOrientation orientation = androidDriver.getOrientation();
    assertSame(orientation, ScreenOrientation.LANDSCAPE);
  }

  public static void switchScreenToPortrait(){
    androidDriver.rotate(ScreenOrientation.PORTRAIT);
    ScreenOrientation orientation = androidDriver.getOrientation();
    assertSame(orientation, ScreenOrientation.PORTRAIT);
  }


}
