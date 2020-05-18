package mobile.iosapp.common;

import static mobile.androidapp.common.AndroidFactory.androidDriver;
import static mobile.iosapp.common.IosFactory.iosDriver;
import static org.testng.Assert.assertSame;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.ScreenOrientation;

public class IosUtilities {

  public static void switchScreenToLandscape(){
    iosDriver.rotate(ScreenOrientation.LANDSCAPE);
    ScreenOrientation orientation = iosDriver.getOrientation();
    assertSame(orientation, ScreenOrientation.LANDSCAPE);
  }

  public static void switchScreenToPortrait(){
    iosDriver.rotate(ScreenOrientation.PORTRAIT);
    ScreenOrientation orientation = iosDriver.getOrientation();
    assertSame(orientation, ScreenOrientation.PORTRAIT);
  }

  public static MobileElement getMobileElementWithXpath(String locator){
    return iosDriver.findElementByXPath(locator);
  }

  public static MobileElement getMobileElementWithAccessibilityId(String locator){
    return iosDriver.findElementByAccessibilityId(locator);
  }

  public static MobileElement getMobileElementWithId(String locator){
    return iosDriver.findElementById(locator);
  }

  public static String getContext(){
    return androidDriver.getContext();
  }

  public static void setContextToWebView(String packageName){
    iosDriver.context("WEBVIEW_"+packageName);
  }

  public static void setContextToNativeApp(){
    iosDriver.context("NATIVE_APP");
  }


}
