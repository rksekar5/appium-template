package mobile.androidapp.common;

import static com.diconium.qa.testautomationframework.common.Logger.logError;
import static com.diconium.qa.testautomationframework.common.Logger.logInfo;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.lang.Thread.sleep;
import static java.time.Duration.ofSeconds;
import static mobile.androidapp.common.AndroidFactory.androidDriver;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import java.time.Duration;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;

@Slf4j
public class AndroidUtilities {

  public void scrollToText(String text) {
    androidDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector())" +
        ".scrollIntoView(new UiSelector().text(\"" + text + "\"));"));
  }

  public void dragAndDrop(MobileElement fromElement, MobileElement toElement) {
    logInfo(String.format("Drag element from %s to %s", fromElement, toElement));
    new TouchAction<>(androidDriver)
        .longPress(longPressOptions()
            .withElement(element(fromElement))
            .withDuration(ofSeconds(2)))
        .moveTo(element(toElement))
        .release().perform();
  }

  public void longPressAndRelease(MobileElement mobileElement) {
    logInfo("Long press and release the element");
    new TouchAction<>(androidDriver)
        .longPress(longPressOptions()
            .withElement(element(mobileElement))
            .withDuration(ofSeconds(2)))
        .release().perform();
  }

  public void clickAndHold(MobileElement mobileElement){
    logInfo("Click and hold on the element");
    new Actions(androidDriver).clickAndHold(mobileElement).release().perform();
  }

  public void tapOnMobileElement(MobileElement mobileElement){
    logInfo("Tap on the element");
    new TouchActions(androidDriver).singleTap(mobileElement).perform();
  }

  public void doubleTapOnMobileElement(MobileElement mobileElement){
    logInfo("Double tap on the element");
    new TouchActions(androidDriver).doubleTap(mobileElement).perform();
  }

  public boolean isKeyboardDisplayed(){
    return androidDriver.isKeyboardShown();
  }

  public void hideAndroidKeyboard(){
    if(isKeyboardDisplayed()) {
      try {
        androidDriver.hideKeyboard();
        sleep(1000);
        logInfo("Keyboard minimized on the screen");
      } catch (Exception e) {
        logInfo("Unable to minimize the keyboard");
        e.printStackTrace();
      }
    }
  }

  public void swipeFromUpToBottomWithJavascriptExecutor()
  {
    try {
      JavascriptExecutor js = androidDriver;
      HashMap<String, String> scrollObject = new HashMap<>();
      scrollObject.put("direction", "up");
      js.executeScript("mobile: swipe", scrollObject);
      log.debug("Swipe up was Successfully done");
    }
    catch (Exception e)
    {
      logError("swipe up was not successfull: "+e);
    }
  }

  public void swipeFromBottomToUpWithJavascriptExecutor()
  {
    try  {
      JavascriptExecutor js = androidDriver;
      HashMap<String, String> scrollObject = new HashMap<>();
      scrollObject.put("direction", "down");
      js.executeScript("mobile: swipe", scrollObject);
      log.debug("Swipe down was Successfully done");
    }
    catch (Exception e)
    {
      logError("swipe up was not successfull: "+e);
    }
  }

  public void scrollFromUpToBottomWithJavascriptExecutor()
  {
    try {
      JavascriptExecutor js = androidDriver;
      HashMap<String, String> scrollObject = new HashMap<>();
      scrollObject.put("direction", "up");
      js.executeScript("mobile: scroll", scrollObject);
      log.debug("Swipe up was Successfully done");
    }
    catch (Exception e)
    {
      logError("swipe up was not successful: "+e);
    }
  }

  public void scrollFromBottomToUpWithJavascriptExecutor()
  {
    try  {
      JavascriptExecutor js = androidDriver;
      HashMap<String, String> scrollObject = new HashMap<>();
      scrollObject.put("direction", "down");
      js.executeScript("mobile: scroll", scrollObject);
      log.debug("Swipe down was Successfully done");
    }
    catch (Exception e)
    {
      logError("swipe up was not successful: "+e);
    }
  }

  public void swipeRight(){
    swipe("right");
  }

  public void swipeLeft(){
    swipe("right");
  }

  public void swipeUp(){
    swipe("up");
  }

  public void swipeDown(){
    swipe("down");
  }

  private void swipe(String direction) {
    Dimension size = androidDriver.manage().window().getSize();

    int startX;
    int endX;
    int startY;
    int endY;

    switch (direction) {
      case "right":
        startY = size.height / 2;
        startX = (int) (size.width * 0.90);
        endX = (int) (size.width * 0.05);
        new TouchAction<>(androidDriver)
            .press(point(startX, startY))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
            .moveTo(point(endX, startY))
            .release()
            .perform();
        break;

      case "left":
        startY = size.height / 2;
        startX = (int) (size.width * 0.05);
        endX = (int) (size.width * 0.90);
        new TouchAction<>(androidDriver)
            .press(point(startX, startY))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
            .moveTo(point(endX, startY))
            .release()
            .perform();
        break;

      case "up":
        endY = (int) (size.height * 0.70);
        startY = (int) (size.height * 0.30);
        startX = (size.width / 2);
        new TouchAction<>(androidDriver)
            .press(point(startX, startY))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
            .moveTo(point(startX, endY))
            .release()
            .perform();
        break;

      case "down":
        startY = (int) (size.height * 0.70);
        endY = (int) (size.height * 0.30);
        startX = (size.width / 2);
        new TouchAction<>(androidDriver)
            .press(point(startX, startY))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
            .moveTo(point(startX, endY))
            .release()
            .perform();
        break;
    }
  }

  /**
   * Retrieves the device location
   */
  public void getDeviceLocation(){
    final Location location = androidDriver.location();
    logInfo(String.format("Device location is: %s", location.toString()));
  }

  /**
   * Assign the device location
   *
   * @param latitude
   * @param longitude
   * @param altitude
   */
  public void setDeviceLocation(long latitude, long longitude, long altitude){
    androidDriver.setLocation(new Location(latitude,longitude,altitude));
    logInfo(String
        .format("Location set for devices successfully to %s %s %s", latitude, longitude, altitude));
  }

  /**
   * Scroll to the mobile element
   *
   * @param mobileElement
   */
  public void scrollToElement(MobileElement mobileElement){
    new TouchActions(androidDriver).scroll(mobileElement, 10, 100).perform();
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

  public static MobileElement getMobileElementWithXpath(String locator){
    return androidDriver.findElementByXPath(locator);
  }

  public static MobileElement getMobileElementWithAccessibilityId(String locator){
    return androidDriver.findElementByAccessibilityId(locator);
  }

  public static MobileElement getMobileElementWithId(String locator){
    return androidDriver.findElementById(locator);
  }

  public static String getContext(){
    return androidDriver.getContext();
  }

  public static void setContextToWebView(String packageName){
    androidDriver.context("WEBVIEW_"+packageName);
  }

  public static void setContextToNativeApp(){
    androidDriver.context("NATIVE_APP");
  }

}
