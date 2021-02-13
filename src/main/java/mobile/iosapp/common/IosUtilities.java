package mobile.iosapp.common;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.ScreenOrientation;

import static mobile.androidapp.common.AndroidFactory.appiumDriver;
import static org.testng.Assert.assertSame;

public class IosUtilities {

  public static void switchScreenToLandscape(){
    appiumDriver.rotate(ScreenOrientation.LANDSCAPE);
    ScreenOrientation orientation = appiumDriver.getOrientation();
    assertSame(orientation, ScreenOrientation.LANDSCAPE);
  }

  public static void switchScreenToPortrait(){
    appiumDriver.rotate(ScreenOrientation.PORTRAIT);
    ScreenOrientation orientation = appiumDriver.getOrientation();
    assertSame(orientation, ScreenOrientation.PORTRAIT);
  }

  public static MobileElement getMobileElementWithXpath(String locator){
    return appiumDriver.findElementByXPath(locator);
  }

  public static MobileElement getMobileElementWithAccessibilityId(String locator){
    return appiumDriver.findElementByAccessibilityId(locator);
  }

  public static MobileElement getMobileElementWithId(String locator){
    return appiumDriver.findElementById(locator);
  }

  public static String getContext(){
    return appiumDriver.getContext();
  }

  public static void setContextToWebView(String packageName){
    appiumDriver.context("WEBVIEW_"+packageName);
  }

  public static void setContextToNativeApp(){
    appiumDriver.context("NATIVE_APP");
  }


}
