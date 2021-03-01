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
     *     capabilities.setCapability("bundleId", "com.volkswagen.oneapp.alpha"); UDID - connect the
     *     device and run xcrun xctrace or instruments -s device
     *     capabilities.setCapability("UDID","00008030-000C353A0C3A802E");
     */
    File appDirectory = new File("src/app");
    File app = new File(appDirectory, readValueFromMobileConfigFile(appName));

    String deviceName = readValueFromMobileConfigFile("ios_device");
    String platformVersion = readValueFromMobileConfigFile("ios_platform_version");

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(
        MobileCapabilityType.DEVICE_NAME, deviceName == null ? "iPhone 8" : deviceName);
    capabilities.setCapability(
        MobileCapabilityType.PLATFORM_VERSION, platformVersion == null ? "14.2" : platformVersion);
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
    capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
    capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
    capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 100);
    capabilities.setCapability("xcodeSigningId", "iPhone Developer");
    capabilities.setCapability("useNewWDA", false);
    capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
    appiumDriver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    return appiumDriver;
  }
}
