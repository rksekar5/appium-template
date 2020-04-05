package mobile.androidapp.common;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AndroidUtilities {
  AndroidDriver<MobileElement> driver;

  public AndroidUtilities(AndroidDriver<MobileElement> driver) {
    this.driver = driver;
  }

  public void scrollToText(String text) {
    driver.findElementByAndroidUIAutomator(
        "new UiScrollable(new UiSelector()).scrollIntoView(text('" + text + "'));");
  }
}
