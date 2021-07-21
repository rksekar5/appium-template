package mobile.utils;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;
import mobile.common.Logger;
import org.openqa.selenium.TimeoutException;

import static mobile.common.Logger.logInfo;
import static mobile.driverhandler.AppFactory.appiumDriver;

@Slf4j
public class AndroidUtils {

  /**
   * Scroll to the specified text
   *
   * @param text
   */
  public static void scrollToText(String text) {
    /*appiumDriver.findElement(
    MobileBy.AndroidUIAutomator(
        "new UiScrollable(new UiSelector())"
            + ".scrollIntoView(new UiSelector().text(\""
            + text
            + "\"));"));*/

    MobileElement element =
        (MobileElement)
            appiumDriver.findElement(
                MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector()).scrollIntoView("
                        + "new UiSelector().text(\""
                        + text
                        + "\"));"));
  }

  /** Scroll to the specified text */
  public boolean isKeyboardDisplayed() {
    Logger.logInfo(String.valueOf(((AndroidDriver<MobileElement>) appiumDriver).isKeyboardShown()));
    return ((AndroidDriver<MobileElement>) appiumDriver).isKeyboardShown();
  }

  /** Hide the Android keyboard */
  public void hideAndroidKeyboard() {
    if (isKeyboardDisplayed()) {
      try {
        appiumDriver.hideKeyboard();
        logInfo("Keyboard minimized on the screen");
      } catch (Exception e) {
        logInfo("Unable to minimize the keyboard");
        e.printStackTrace();
      }
    }
  }

  /**
   * Remove an app from Android device
   *
   * @param app
   * @param appPackage bundleId == "com.example.AppName"
   */
  public static void removeApkFromAndroidDevice(String app, String appPackage) {
    try {
      if (checkIfApkInstalled(appPackage)) {
        appiumDriver.removeApp(appPackage);
        Logger.logInfo(
            String.format(
                "App '%s' with AppPackage '%s' is removed from Android device", app, appPackage));
      } else {
        Logger.logInfo(
            String.format(
                "App '%s' with AppPackage '%s' is not installed/ not removed from Android device",
                app, appPackage));
      }
    } catch (TimeoutException var2) {
      var2.getMessage();
    }
  }

  /**
   * Terminate the given app onto Android device
   *
   * @param appPackage bundleId == "com.example.AppName"
   */
  public static void terminateAppOnAndroidDevice(String appPackage) {
    try {
      if (appiumDriver.isAppInstalled(appPackage)) {
        appiumDriver.terminateApp(appPackage);
        Logger.logInfo(String.format("The App is terminated on the Android device"));
      } else {
        Logger.logInfo(String.format("The App is not terminated on the Android device"));
      }
    } catch (TimeoutException var2) {
      var2.getMessage();
    }
  }

  /**
   * Check whether the specified app is installed on the Android device
   *
   * @param appPackage appPackage == "com.google.android.AppName"
   */
  public static boolean checkIfApkInstalled(String appPackage) {
    try {
      if (appiumDriver.isAppInstalled(appPackage)) {
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

  /**
   * Install the given Android app in the device
   *
   * @param app
   * @param appPackage
   */
  public static void installAndroidApp(String app, String appPackage) {
    try {
      if (checkIfApkInstalled(appPackage)) {
        Logger.logInfo(String.format("App with AppPackage '%s' is already present", appPackage));
      } else {
        appiumDriver.installApp(app);
        Logger.logInfo(
            String.format("App with AppPackage '%s' is now installed into Device ", appPackage));
      }

    } catch (TimeoutException var2) {
      var2.getMessage();
    }
  }

  /**
   * Get the state of the app on the Android device
   *
   * @param appPackage
   */
  public static void getAndroidAppStatus(String appPackage) {
    // bundleId == "com.example.AppName"
    try {
      if (appiumDriver.isAppInstalled(appPackage)) {
        appiumDriver.queryAppState(appPackage);
        Logger.logInfo(
            String.format(
                "The given app status on Android device is: %s ",
                appiumDriver.queryAppState(appPackage)));
      }
    } catch (TimeoutException var2) {
      var2.getMessage();
    }
  }
}
