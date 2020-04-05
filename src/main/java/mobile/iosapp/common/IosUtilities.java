package mobile.iosapp.common;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class IosUtilities {
  IOSDriver<IOSElement> driver;

  public IosUtilities(IOSDriver<IOSElement> driver) {
    this.driver = driver;
  }

}
