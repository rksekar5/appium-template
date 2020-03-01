package mobile.androidapp.common;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class AndroidUtilities {
  AndroidDriver<AndroidElement> driver;

  public AndroidUtilities(AndroidDriver<AndroidElement> driver) {
    this.driver = driver;
  }

  public void scrollToText(String text) {
    driver.findElementByAndroidUIAutomator(
        "new UiScrollable(new UiSelector()).scrollIntoView(text('" + text + "'));");
  }
}
