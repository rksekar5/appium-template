package mobile.utils;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;

import java.time.Duration;
import java.util.HashMap;

import static com.diconium.qa.testautomationframework.common.Logger.logError;
import static com.diconium.qa.testautomationframework.common.Logger.logInfo;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.lang.Thread.sleep;
import static java.time.Duration.ofSeconds;
import static mobile.driverhandler.AndroidFactory.appiumDriver;
import static org.testng.Assert.assertSame;

@Slf4j
public class AndroidUtils {

  public void scrollToText(String text) {
    appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector())" +
        ".scrollIntoView(new UiSelector().text(\"" + text + "\"));"));
  }



  public boolean isKeyboardDisplayed(){
    System.out.println(((AndroidDriver<MobileElement>)appiumDriver).isKeyboardShown());
    return ((AndroidDriver<MobileElement>)appiumDriver).isKeyboardShown();
  }

  public void hideAndroidKeyboard(){
    if(isKeyboardDisplayed()) {
      try {
        appiumDriver.hideKeyboard();
        sleep(1000);
        logInfo("Keyboard minimized on the screen");
      } catch (Exception e) {
        logInfo("Unable to minimize the keyboard");
        e.printStackTrace();
      }
    }
  }
}