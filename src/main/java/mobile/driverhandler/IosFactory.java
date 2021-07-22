package mobile.driverhandler;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class IosFactory extends AppFactory {

  public static AppiumDriver<MobileElement> iosCapabilities(String appName) throws IOException {

    /**
     * @param deviceName
     * @param platformName
     * @param platformVersion
     * @param automationName For full reset of the app -
     *     capabilities.setCapability(MobileCapabilityType.FULL_RESET,true); The below two
     *     capabilities are meant to be used for real iOS devices bundleId - Change the .ipa file
     *     into .zip format and load into Intellij. Open info.plit and search CFBundleIdentifier
     *     capabilities.setCapability("bundleId", "com.volkswagen.oneapp.alpha") "UIDeviceFamily";
     *     UDID - connect the device and run xcrun xctrace or instruments -s device
     *     capabilities.setCapability("UDID","00008030-000C353A0C3A802E"); Provide the gitlab url
     *     where the app is present and it will pick automatically
     *     capabilities.setCapability("app","https://github.com/appium/appium/blob/master/sample-code/apps/TestApp.app.zip?raw=true");
     */
    File appDirectory = new File("src/app");
    File app = new File(appDirectory, readValueFromMobileConfigFile(appName));

    String deviceName = readValueFromMobileConfigFile("ios_device");
    String platformVersion = readValueFromMobileConfigFile("ios_platform_version");
    String udid = readValueFromMobileConfigFile("ios_udid");
    String bundleId = readValueFromMobileConfigFile("ios_bundle_id");

    DesiredCapabilities capabilities = new DesiredCapabilities();

    if (udid.equals("")) {
      //capabilities.setCapability(MobileCapabilityType.FULL_RESET, "TRUE");
      capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
      capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
      capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
      capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
      capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
      capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 100);
      capabilities.setCapability("xcodeSigningId", "iPhone Developer");
      //capabilities.setCapability("useNewWDA", true);
      // capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
      capabilities.setCapability(
          "app",
          "https://github.com/saucelabs/sample-app-mobile/releases/download/2.7.1/iOS.Simulator.SauceLabs.Mobile.Sample.app.2.7.1.zip");
    } else {
      //capabilities.setCapability(MobileCapabilityType.FULL_RESET, "TRUE");
      capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
      capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
      capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
      capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
      capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
      capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 100);
      capabilities.setCapability("xcodeSigningId", "iPhone Developer");
      capabilities.setCapability("useNewWDA", true);
      capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
      capabilities.setCapability(MobileCapabilityType.UDID, udid);
      capabilities.setCapability("bundleId", bundleId);
    }
    appiumDriver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    return appiumDriver;
  }
}
