package mobile.utils;

import com.diconium.qa.testautomationframework.common.Logger;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Random;

import static com.diconium.qa.testautomationframework.common.Logger.logInfo;
import static mobile.utils.Waiters.driver;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Slf4j
public class MobileUtils {

  /**
   * Get attribute value from mobile element
   *
   * @param mobileElement
   * @return string
   */
  public static String getMobileAttributeValue(MobileElement mobileElement) {
    return mobileElement.getAttribute("value");
  }

  /**
   * Get mobile element checkbox status
   *
   * @param mobileElement
   * @return string
   */
  public static boolean getMobileElementCheckboxStatus(MobileElement mobileElement) {
    return Boolean.parseBoolean(mobileElement.getAttribute("checked"));
  }

  /**
   * Verify If the checkbox is checked
   *
   * @param mobileElement
   * @return string
   */
  public static boolean isCheckboxChecked(MobileElement mobileElement) {
    boolean checkboxStatus = getMobileElementCheckboxStatus(mobileElement);
    if (checkboxStatus) {
      logInfo("Check box: " + mobileElement + " is checked on");
      return true;
    } else {
      logInfo("Check box: " + mobileElement + " is checked off");
      return false;
    }
  }

  /**
   * check on the checkbox
   *
   * @param mobileElement
   * @return string
   */

  public static void checkOnCheckbox(MobileElement mobileElement) {
    if (! isCheckboxChecked(mobileElement)) {
      clickMobileElement(mobileElement);
      assertTrue(isCheckboxChecked(mobileElement));
      logInfo("Checked on the checkbox successfully");
    }
  }

  /**
   * Uncheck on the checkbox
   *
   * @param mobileElement
   * @return string
   */
  public static void uncheckOnCheckbox(MobileElement mobileElement) {
    if (isCheckboxChecked(mobileElement)) {
      clickMobileElement(mobileElement);
      assertFalse(isCheckboxChecked(mobileElement));
      logInfo("UnChecked the checkbox successfully");
    }
  }


  /**
   * Click on the mobile element
   *
   * @param mobileElement
   * @return string
   */
  public static void clickMobileElement(MobileElement mobileElement) {
    mobileElement.click();
    logInfo(String.format("%s clicked successfully", mobileElement));
  }

  /**
   * Type input keys to mobile element
   *
   * @param mobileElement
   * @return string
   */
  public static void sendKeysToMobileElement(MobileElement mobileElement, String input) {
    mobileElement.sendKeys(input);
    logInfo(String.format("%s is entered in %s", input, mobileElement));
  }

  /**
   * Get text from the specified mobile element
   *
   * @param mobileElement
   * @return string
   */
  public static String getTextFromMobileElement(MobileElement mobileElement) {
    return mobileElement.getText();
  }

  /**
   * Check if the mobile element is NOT displayed
   *
   * @param mobileElement
   * @return true or false
   */
  public boolean isMobileElementNotPresent(MobileElement mobileElement) {
    boolean isDisplayed = ! mobileElement.isDisplayed();
    if (isDisplayed) {
      logInfo("Element is displayed");
    } else {
      logInfo("Element is NOT displayed");
    }
    return isDisplayed;
  }

  /**
   * Check if the mobile element is displayed
   *
   * @param mobileElement
   * @return true or false
   */
  public boolean isMobileElementPresent(MobileElement mobileElement) {
    boolean isDisplayed = mobileElement.isDisplayed();
    if (isDisplayed) {
      logInfo("Element is displayed");
    } else {
      logInfo("Element is NOT displayed");
    }
    return isDisplayed;
  }

  /**
   * Move file to the path
   *
   * @param scrFile
   * @param fileNamePath
   */
  public static void moveFileToPath(File scrFile, File fileNamePath) throws IOException {
    if (Files.exists(Paths.get(String.valueOf(fileNamePath)))) {
      System.out.println("File exists already");
    } else {
      Path temp =
              Files.move(Paths.get(String.valueOf(scrFile)), Paths.get(String.valueOf(fileNamePath)));
      if (temp != null) {
        System.out.println("File renamed and moved successfully");
      } else {
        System.out.println("Failed to move the file");
      }
    }
  }


  @Step()
  /**
   * Take a screenshot of the current viewport/window/page
   *
   * @param filename
   */
  public static void takeScreenshot(String filename) throws IOException {

    File scrFile = driver.getScreenshotAs(OutputType.FILE);

    Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
    Path filePath = Paths.get(root.toString(), "src", "main", "resources", "screenshots");
    File fileNamePath = new File(filePath.toString() + "/" + filename + ".png");

    if (driver.getCapabilities().getCapability("platformName").equals("iOS")) {
      moveFileToPath(scrFile, fileNamePath);
    } else if (driver.getCapabilities().getCapability("platformName").equals("Android")) {
      moveFileToPath(scrFile, fileNamePath);
    }
  }

  /**
   * Assert if mobile element is displayed
   *
   * @param mobileElement
   */
  public void assertMobileElementPresent(MobileElement mobileElement) {
    assertTrue(isMobileElementPresent(mobileElement));
  }

  /**
   * Assert if mobile element is NOT displayed
   *
   * @param mobileElement
   */
  public void assertMobileElementNotPresent(MobileElement mobileElement) {
    assertFalse(isMobileElementPresent(mobileElement));
  }

  /**
   * Clear the input field of the mobile element
   *
   * @param mobileElement
   */
  @Step
  public static void clearInputField(MobileElement mobileElement) {
    try {
      mobileElement.clear();
      logInfo(String.format("%s cleared successfully", mobileElement));
    } catch (TimeoutException e) {
      e.getMessage();
    }
  }

  /**
   * Get random numbers
   */
  @Step
  public static int getRandomNumber(int min, int max) {
    Random random = new Random();
    return random.nextInt(max - min + 1) + min;
  }

  /**
   * Check If the expected text is present
   *
   * @param mobileElement
   * @param text
   */
  @Step
  public static boolean isTextPresent(MobileElement mobileElement, String text) {
    try {
      return mobileElement.getText().contains(text);
    } catch (TimeoutException var3) {
      Logger.logError(String.format("The Text %s is not present", text));
      var3.getCause();
      return false;
    }
  }

  /**
   * Check If the expected text is NOT present
   *
   * @param mobileElement
   * @param text
   */
  @Step
  public static boolean isTextNotPresent(MobileElement mobileElement, String text) {
    return ! isTextPresent(mobileElement, text);
  }

  /**
   * Verify If the text is present
   *
   * @param mobileElement
   * @param text
   */
  @Step
  public static void verifyTextPresent(MobileElement mobileElement, String text) {
    boolean textPresent = isTextPresent(mobileElement, text);
    if (textPresent) {
      Logger.logInfo(String.format("Text: '%s' is displayed as expected", text));
    } else {
      Logger.logError(String.format("Text '%s' is not displayed", text));
    }
  }

  /**
   * Verify If the text is NOT present
   *
   * @param mobileElement
   * @param text
   */
  @Step
  public static void verifyTextNotPresent(MobileElement mobileElement, String text) {
    boolean textNotPresent = isTextNotPresent(mobileElement, text);
    if (! textNotPresent) {
      Logger.logInfo(String.format("Text: '%s' is still displayed", text));
    } else {
      Logger.logError(String.format("Text '%s' is not displayed as expected ", text));
    }
  }

  /**
   * Assert If the text is present
   *
   * @param mobileElement
   * @param text
   */
  @Step
  public static void assertTextPresent(MobileElement mobileElement, String text) {
    Assert.assertTrue(mobileElement.getText().contains(text));
    Logger.logInfo(String.format("Text: '%s' is displayed as expected", text));
  }

  /**
   * Assert If the value is present
   *
   * @param mobileElement
   * @param value
   */
  @Step
  public static void assertValuePresent(MobileElement mobileElement, String value) {
    Assert.assertTrue(mobileElement.getAttribute("value").contains(value));
  }

  /**
   * Assert If the text is NOT present
   *
   * @param mobileElement
   * @param text
   */
  @Step
  public static void assertTextNotPresent(MobileElement mobileElement, String text) {
    Assert.assertFalse(mobileElement.getText().contains(text));
    Logger.logInfo(String.format("Text: '%s' is not displayed as expected", text));
  }

  /**
   * Verify If the element is selected
   *
   * @param mobileElement
   */
  @Step
  public static boolean isElementSelected(MobileElement mobileElement) {
    return mobileElement.isSelected();
  }

  /**
   * Get the size of the mobile element
   *
   * @param mobileElement
   */
  @Step
  public static Dimension getElementSize(MobileElement mobileElement) {
    return mobileElement.getSize();
  }

//  @Step
//  public static void selectFromListByIndex(String list, int option) {
//    Waiters.getFluentWait()
//            .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(list), 1));
//    (new Select(tryFindElementByXpath(list))).selectByIndex(option);
//  }
//
//  @Step
//  public static void selectFromListByValue(String list, String value) {
//    Logger.logInfo(String.format("Selecting from list %s by value ", list));
//    Waiters.getFluentWait()
//            .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(list), 1));
//    (new Select(tryFindElementByXpath(list))).selectByValue(value);
//    Waiters.getFluentWait().until(ExpectedConditions.attributeToBe(By.xpath(list), "value", value));
//  }
//
//  @Step
//  public static void selectFromListByVisibleText(MobileElement mobileElement, String list, String text) {
//    (new Select(mobileElement(list))).selectByVisibleText(text);
//  }
//
//  @Step
//  public static void hoverOverElement(MobileElement mobileElement) {
//    Actions builder = new Actions(AppiumDriver.getDriver());
//    builder.moveToElement(mobileElement.perform();
//  }


  @Step
  /**
   * Get the active mobile element
   */
  public static void getActiveElement() {
    driver.switchTo().activeElement();
  }


  @Step()
  /**
   * Start an Android Activity
   *
   * @param activityName
   */

  public static void startAnAndroidActivity(Activity activityName) {
    try {
      ((AndroidDriver) driver).startActivity(activityName);
    } catch (Exception e) {
      e.printStackTrace();
      e.getMessage();
      e.getCause();
    }
  }


  @Step()
  /**
   * Get current Android Activity
   */
  public static String getCurrentAnAndroidActivity() {
    String activity = ((AndroidDriver) driver).currentActivity();
    return activity;
  }


  @Step()
  /**
   * Get current Android Package
   */
  public static String getCurrentAnAndroidPackage() {
    String packageName = ((AndroidDriver) driver).getCurrentPackage();
    return packageName;
  }

  @Step()
  /**
   * Get Device Time on the device
   */
  public static String getDeviceTime() {
    return driver.getDeviceTime();
  }

  @Step()
  /**
   * Open the Keyboard
   */
  public static void getKeyboard() {
    driver.getKeyboard();
  }

  @Step()
  /**
   * Long Tap on the mobile element
   *
   *  @param mobileElement
   */
  public static void longTapOnTheElement(MobileElement mobileElement) throws InterruptedException {

    TouchAction action = new TouchAction(driver);

    MobileElement element = mobileElement;

    action.longPress(PointOption.point(element.getLocation().getX(), element.getLocation().getY()));
    action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(10000)));
    action.release().perform();
  }


  @Step()
  /**
   * Tap on the mobile element by coordinates
   */
  public static void tapByCoordinates(int xOffset, int yOffset) {
    TouchAction action = new TouchAction(driver);
    action.tap(PointOption.point(xOffset, yOffset))
            .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3))).perform();
  }

  @Step()
  /**
   * Double tap on the mobile element by coordinates
   */
  public static void doubleTapByCoordinates(int x, int y) {
    TouchAction action = new TouchAction(driver);
    Point point = new Point(x, y);
    int xcord = point.getX();
    int ycord = point.getY();

    action.tap(PointOption.point(xcord, ycord))
            .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)));
    action.perform();

    action.tap(PointOption.point(xcord, ycord))
            .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)));
    action.perform();
  }

  @Step()
  /**
   * Perform touch
   */
  public static void performTouch(int xOffset, int y1Offset, int y2xOffset) {
    TouchAction action = new TouchAction(driver);
    action.press(PointOption.point(xOffset, y1Offset));
    action.moveTo(PointOption.point(xOffset, y2xOffset));
    action.release();
    action.perform();
  }

  @Step()
  /**
   * Double tap on the touch screen using finger motion events
   *
   *  @param mobileElement
   */
  public static void doubleTapAction(MobileElement mobileElement) {
    TouchAction action = new TouchAction(driver);
    MobileElement element = mobileElement;
    assert element != null;
    Point point = element.getLocation();
    int xcord = point.getX();
    int ycord = point.getY();

    action.press(PointOption.point(xcord, ycord))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)));
    action.release().perform();

    action.press(PointOption.point(xcord, ycord))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(50)));
    action.release().perform();
  }


  @Step()
  /**
   * Finger move on the screen
   */
  public static void moveToScreen(int startx, int starty, int endx, int endy) {
    TouchAction action = new TouchAction(driver);

    action.press(PointOption.point(startx, starty))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
            .moveTo(PointOption.point(endx, endy));
    action.release().perform();

  }

  @Step()
  /**
   * Perform multi touch on the screen
   */
  public static void multiTouchPerform(Point point1, Point point2) {
    TouchAction actionOne = new TouchAction(driver);
    actionOne.press(PointOption.point(point1.getX(), point1.getY()));
    actionOne.moveTo(PointOption.point(point1.getX(), point1.getY()));
    actionOne.release();

    TouchAction actionTwo = new TouchAction(driver);
    actionTwo.press(PointOption.point(point2.getX(), point2.getY()));
    actionTwo.moveTo(PointOption.point(point2.getX(), point2.getY()));
    actionTwo.release();

    MultiTouchAction multiAction = new MultiTouchAction(driver);
    multiAction.add(actionOne);
    multiAction.add(actionTwo);
    multiAction.perform();
  }

  @Step()
  /**
   * Multi touch on the mobile element
   *
   * @param mobileElement
   */
  public static void multiTouchByElement(MobileElement mobileElement) {
    TouchAction action = new TouchAction(driver);

    MobileElement element = mobileElement;
    action.press(PointOption.point(element.getLocation().getX(), element.getLocation().getY()))
            .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).release();
    MultiTouchAction multiAction = new MultiTouchAction(driver);
    multiAction.add(action).perform();
  }


  @Step()
  /**
   * Vertical scroll down on the screen
   */
  public static void verticalScrollDown() {
    TouchAction action = new TouchAction(driver);
    Dimension size = driver.manage().window().getSize();
    // Down area points
    int start_x_down = (int) (size.width * 0.5);
    int end_x_down = (int) (size.height * 0.95);

    // Up area points
    int start_x_up = start_x_down;
    int end_x_up = (int) (size.height * 0.35);

    action.press(PointOption.point(start_x_down, end_x_down));
    action.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)));
    action.moveTo(PointOption.point(start_x_up, end_x_up));
    action.release().perform();
  }

  @Step()
  /**
   * Vertical scroll up on the screen
   */
  public static void verticalScrollUp() {
    TouchAction action = new TouchAction(driver);

    Dimension size = driver.manage().window().getSize();

    // Area points
    int start_x = (int) (size.width / 2);
    int start_y = (int) (size.height * 0.8);

    int end_y = (int) (size.height * 0.2);

    action.press(PointOption.point(start_x, end_y));
    action.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)));
    action.moveTo(PointOption.point(start_x, start_y));
    action.release().perform();

  }

  @Step()
  /**
   * Horizontal swipe on Seekbar (SeekBar is an extension of ProgressBar that adds a draggable thumb.
   * The user can touch the thumb and drag left or right to set the current progress level or use the arrow keys)
   * @param mobileElement
   */
  public static void swipeOnSeekbar(MobileElement mobileElement, int percentage) {
    MobileElement element = mobileElement;
    String newValue = String.valueOf(percentage / 100);
    element.setValue(newValue);
    Logger.logInfo(String.format("Element %s is moved to %s value", element,
            element.getAttribute("value").toString()));
  }


  @Step()
  /**
   * Single tap on the touch enabled device
   *
   * @param mobileElement
   */
  public static void singleTapAction(MobileElement mobileElement) {
    TouchAction action = new TouchAction(driver);
    MobileElement element = mobileElement;

    action.tap(PointOption.point(element.getLocation().getX(), element.getLocation().getY()));
    action.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)));
    action.perform();
  }


  @Step()
  /**
   * Check whether the specified app is installed on the Android device
   *
   * @param appPackage
   */
  public static boolean checkIfApkInstalled(String appPackage) {
    // appPackage == "com.google.android.AppName"
    try {
      if (driver.isAppInstalled(appPackage)) {
        return true;
      } else {
        Logger.logInfo(
                String.format("App with AppPackage '%s' is not installed into Device ", appPackage));
        return false;
      }
    } catch (TimeoutException var2) {
      var2.getMessage();
      return false;
    }
  }

  @Step()
  /**
   * Install the given Android app in the device
   *
   *  @param app
   * @param appPackage
   */
  public static void installAndroidApp(String app, String appPackage) {
    try {
      if (checkIfApkInstalled(appPackage)) {
        Logger.logInfo(String.format("App with AppPackage '%s' is already present", appPackage));
      } else {
        driver.installApp(app);
        Logger.logInfo(
                String.format("App with AppPackage '%s' is now installed into Device ", appPackage));
      }

    } catch (TimeoutException var2) {
      var2.getMessage();
    }
  }

  @Step()
  /**
   * Check whether the specified app is installed on the iOS device
   *
   * @param app
   * @param bundleId
   */
  public static boolean checkIfIOSAppInstalled(String app, String bundleId) {
    try {
      if (driver.isAppInstalled(bundleId)) {
        return true;
      } else {
        Logger.logInfo(String.format("App '%s' with AppPackage '%s' is not installed into Device ",
                app, bundleId));
        return false;
      }
    } catch (TimeoutException var2) {
      var2.getMessage();
      return false;
    }
  }

  @Step()
  /**
   * Install the given app on the iOS device
   *
   * @param app
   * @param bundleId
   */
  public static void installIOSApp(String app, String bundleId) {
    try {
      if (checkIfIOSAppInstalled(app, bundleId)) {
        Logger.logInfo(
                String.format("App '%s' with AppPackage '%s' is already present", app, bundleId));
      } else {
        driver.installApp(app);
        Logger.logInfo(String.format("App '%s' with AppPackage '%s' is now installed into Device ",
                app, bundleId));
      }

    } catch (TimeoutException var2) {
      var2.getMessage();
    }
  }

  @Step()
  /**
   * Launch the app under test on the device
   */
  public static void launchApp() {
    try {
      driver.launchApp();
      Logger.logInfo(String.format("The App is launched on the device"));
    } catch (TimeoutException var2) {
      var2.getMessage();
    }
  }

  @Step()
  /**
   * Send the currently running app for this session to the background
   */
  public static void runAppInBackground(long time) {
    try {
      Duration duration = Duration.ofSeconds(time);
      // driver.runAppInBackground(Duration.ofSeconds(time));
      driver.runAppInBackground(duration);
      Logger.logInfo(String.format("The App was running on Background of the device"));
    } catch (TimeoutException var2) {
      var2.getMessage();
    }
  }

  @Step()
  /**
   * Close the app on device
   */
  public static void closeAnApp() {
    try {
      driver.closeApp();
      Logger.logInfo(String.format("The App is closed on the device"));
    } catch (TimeoutException var2) {
      var2.getMessage();
    }
  }

  @Step()
  /**
   * Reset the currently running app for this session
   */
  public static void resetApp() {
    try {
      driver.resetApp();
      Logger.logInfo(String.format("The App is reset for this session"));
    } catch (TimeoutException var2) {
      var2.getMessage();
    }
  }

  @Step()
  /**
   * Remove an app from Android device
   *
   * @param app
   * @param appPackage
   */
  public static void removeApkFromAndroidDevice(String app, String appPackage) {
    // bundleId == "com.example.AppName"
    try {
      if (checkIfApkInstalled(appPackage)) {
        driver.removeApp(appPackage);
        Logger.logInfo(String.format("App '%s' with AppPackage '%s' is removed from Android device",
                app, appPackage));
      } else {
        Logger.logInfo(String.format(
                "App '%s' with AppPackage '%s' is not installed/ not removed from Android device", app,
                appPackage));
      }
    } catch (TimeoutException var2) {
      var2.getMessage();
    }
  }

  @Step()
  /**
   * Remove an app from iOS device
   *
   * @param app
   * @param appPackage
   */
  public static void removeAppFromIOSDevice(String app, String bundleId) {
    // bundleId == "com.example.AppName"
    try {
      if (checkIfIOSAppInstalled(app, bundleId)) {
        driver.removeApp(bundleId);
        Logger.logInfo(String.format("App '%s' with AppPackage '%s' is removed from iOS device",
                app, bundleId));
      } else {
        Logger.logInfo(String.format(
                "App '%s' with AppPackage '%s' is not installed/removed on/from iOS device", app,
                bundleId));
      }
    } catch (TimeoutException var2) {
      var2.getMessage();
    }
  }


  @Step("")
  /**
   * Terminate the given app onto Android device
   *
   * @param appPackage
   */
  public static void terminateAppOntoAndroidDevice(String appPackage) {
    // bundleId == "com.example.AppName"
    try {
      if (driver.isAppInstalled(appPackage)) {
        driver.terminateApp(appPackage);
        Logger.logInfo(String.format("The App is terminated on the Android device"));
      } else {
        Logger.logInfo(String.format("The App is not terminated on the Android device"));
      }
    } catch (TimeoutException var2) {
      var2.getMessage();
    }
  }

  @Step()
  /**
   * Terminate the given app onto iOS device
   *
   * @param bundleId
   */
  public static void terminateAppOntoiOSDevice(String bundleId) {
    // bundleId == "com.example.AppName"
    try {
      if (driver.isAppInstalled(bundleId)) {
        driver.terminateApp(bundleId);
        Logger.logInfo(String.format("The App is terminated onto iOS device"));
      } else {
        Logger.logInfo(String.format("The App is not terminated onto iOS device"));
      }
    } catch (TimeoutException var2) {
      var2.getMessage();
    }
  }

  @Step()
  /**
   * Get the state of the app on the Android device
   *
   * @param appPackage
   */
  public static void getAndroidAppStatus(String appPackage) {
    // bundleId == "com.example.AppName"
    try {
      if (driver.isAppInstalled(appPackage)) {
        driver.queryAppState(appPackage);
        Logger.logInfo(String.format("The given app status on Android device is: %s ",
                driver.queryAppState(appPackage)));
      }
    } catch (TimeoutException var2) {
      var2.getMessage();
    }
  }

  @Step()
  /**
   * Get the state of the app on the iOS device
   *
   * @param appPackage
   */
  public static void getiOSAppStatus(String bundleId) {
    // bundleId == "com.example.AppName"
    try {
      if (driver.isAppInstalled(bundleId)) {
        driver.queryAppState(bundleId);
        Logger.logInfo(String.format("The given app status on iOS device is: %s ",
                driver.queryAppState(bundleId)));
      }
    } catch (TimeoutException var2) {
      var2.getMessage();
    }
  }

  @Step()
  /**
   * Perform a shake action on iOS device
   */
  public static void shakeAction() {
    try {
      if (driver.getPlatformName().equals("ios")) {
        ((IOSDriver<MobileElement>) driver).shake();
        Logger.logInfo(String.format("The iOS device has been shaken"));
      }
    } catch (TimeoutException var2) {
      var2.getMessage();
    }
  }

  @Step()
  /**
   * Lock the Android/iOS device
   */
  public static void lockDevice() {
    try {
      if (driver.getPlatformName().equals("ios")) {
        ((IOSDriver<MobileElement>) driver).lockDevice();
        Logger.logInfo(String.format("The iOS device is locked"));
      } else if (driver.getPlatformName().equals("android")) {
        ((AndroidDriver<MobileElement>) driver).lockDevice();
        Logger.logInfo(String.format("The Android device is locked"));
      }
    } catch (TimeoutException var2) {
      var2.getMessage();
    }
  }

  @Step()
  /**
   * Check whether the device is locked or not
   */
  public static boolean isDeviceLocked() {
    boolean isLocked;
    try {
      if (driver.getPlatformName().equals("ios")) {
        isLocked = ((IOSDriver<MobileElement>) driver).isDeviceLocked();
        return isLocked;
      } else if (driver.getPlatformName().equals("android")) {
        isLocked = ((AndroidDriver<MobileElement>) driver).isDeviceLocked();
        return isLocked;
      }
      Logger.logInfo(String.format("The device is unlocked"));
    } catch (TimeoutException var2) {
      var2.getMessage();
      return false;
    }
    return false;
  }

  @Step()
  /**
   * Unlock the iOS device
   */
  public static void unlockiOSDevice() {
    try {
      if (driver.getPlatformName().equals("ios")) {
        if (isDeviceLocked()) {
          ((IOSDriver<MobileElement>) driver).unlockDevice();
          Logger.logInfo(String.format("The iOS device is unlocked"));
        } else {
          ((IOSDriver<MobileElement>) driver).lockDevice();
          ((IOSDriver<MobileElement>) driver).unlockDevice();
          Logger.logInfo(String.format("The iOS device is unlocked"));
        }
      }
    } catch (TimeoutException var2) {
      var2.getMessage();
    }
  }

  @Step()
  /**
   * Unlock the Android device
   */
  public static void unlockAndroidDevice() {
    try {
      if (driver.getPlatformName().equals("android")) {
        if (isDeviceLocked()) {
          ((AndroidDriver<MobileElement>) driver).unlockDevice();
          Logger.logInfo(String.format("The Android device is unlocked"));
        } else {
          ((AndroidDriver<MobileElement>) driver).lockDevice();
          ((AndroidDriver<MobileElement>) driver).unlockDevice();
          Logger.logInfo(String.format("The Android device is unlocked"));
        }
      }
    } catch (TimeoutException var2) {
      var2.getMessage();
    }
  }


  @Step()
  /**
   * Rotate the device in three dimensions only
   */
  public static void rotateDevice() {
    try {
      driver.rotate(new DeviceRotation(10, 10, 10));
      Logger.logInfo(String.format("The Device is rotated in three dimensions"));
    } catch (TimeoutException var2) {
      var2.getMessage();
    }
  }


  @Step()
  /**
   * Toggle airplane mode on Android device
   */
  public static void toggleAirplaneMode() {
    if (driver.getPlatformName().equals("android")) {
      ((AndroidDriver<MobileElement>) driver).toggleAirplaneMode();
      Logger.logInfo(String.format("The Device is in Airplane mode"));
    } else {
      Logger.logInfo(String.format("The Device is not in Airplane mode"));
    }
  }


  @Step()
  /**
   * Switch the state of the wifi service
   */
  public static void toogleWifi() {
    if (driver.getPlatformName().equals("android")) {
      ((AndroidDriver<MobileElement>) driver).toggleWifi();
      Logger.logInfo(String.format("Toggle Wifi"));
    }
  }

  @Step()
  /**
   * Switch the state of the location service
   */
  public static void toggleLocationServices() {
    if (driver.getPlatformName().equals("android")) {
      ((AndroidDriver<MobileElement>) driver).toggleLocationServices();
      Logger.logInfo(String.format("Toggle Location service"));
    }
  }

  @Step()
  /**
   * Simulate an SMS message (Emulator only)
   */
  public static void sendSMS(String phoneNumber, String message) {
    try {
      if (driver.getPlatformName().equals("android")) {
        ((AndroidDriver<MobileElement>) driver).sendSMS(phoneNumber, message);
        Logger.logInfo(String.format("A SMS Message is sent"));
      } else {
        Logger.logInfo(String.format("Not possible to send a SMS Message"));
      }
    } catch (TimeoutException var2) {
      var2.getMessage();
    }
  }

  @Step()
  /**
   * Toggle the simulator being enrolled to accept touchId (iOS Simulator only)
   */
  public static void toggleTouchIDEnrollment(boolean enrolled) {
    try {
      if (driver.getPlatformName().equals("ios")) {
        ((IOSDriver<MobileElement>) driver).toggleTouchIDEnrollment(enrolled);
        Logger.logInfo(String.format("Toggle Touch ID has been enrolled"));
      } else {
        Logger.logInfo(String.format("Toggle Touch ID has not been enrolled"));
      }

    } catch (TimeoutException var2) {
      var2.getMessage();
    }
  }

  @Step()
  /**
   * Perform Touch ID (iOS Simulator only)
   *
   * @param enrolled
   */
  public static void performTouchID(boolean enrolled) {
    try {
      if (driver.getPlatformName().equals("ios")) {
        if (enrolled == true) {
          ((IOSDriver<MobileElement>) driver).performTouchID(true);
          Logger.logInfo(String.format("Simulates a passing touch"));
        } else {
          ((IOSDriver<MobileElement>) driver).performTouchID(false);
          Logger.logInfo(String.format("Simulates a failed touch"));
        }
      }
    } catch (TimeoutException var2) {
      var2.getMessage();
      Logger.logInfo(String.format("Touch ID has been not performed"));
    }
  }

  @Step()
  /**
   * Open Android notification Bar (Emulator only)
   */
  public static void openNotifications() {
    if (driver.getPlatformName().equals("android")) {
      ((AndroidDriver<MobileElement>) driver).openNotifications();
    }
  }

  @Step()
  /**
   * Open a url on the browser
   */
  public static void openBrowserUrl(String url) {
    driver.get(url);
  }

  @Step()
  /**
   * Switch to Alert
   */
  public static void switchToAlert() {
    try {
      driver.switchTo().alert();
    } catch (Exception e) {
      e.getCause();
      e.getMessage();
    }
  }

  @Step()
  /**
   * Switch to Frame
   */
  public static void switchToFrame(MobileElement mobileElement) {
    MobileElement element = mobileElement;
    driver.switchTo().frame(element);
  }

  @Step()
  /**
   * Reload the current page.
   */
  public static void reload() {
    driver.navigate().refresh();
  }

  @Step()
  /**
   * Delete all cookies
   */
  public static void deleteAllCookies() {
    driver.manage().deleteAllCookies();
  }

}
