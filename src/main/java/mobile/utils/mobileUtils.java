package mobile.utils;

import static common.Logger.logInfo;

import io.appium.java_client.MobileElement;

public class mobileUtils {

  public static String getMobileAttributeValue(MobileElement mobileElement){
    return mobileElement.getAttribute("value");
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

}
