package mobile.iosapp.common;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import mobile.utils.AppFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

public class IosFactory extends AppFactory {

  public static IOSDriver<MobileElement> iosDriver;

  public static IOSDriver<MobileElement> capabilities(String appName) throws IOException {

    File appDirectory = new File("src/app");
    File app = new File(appDirectory, readValueFromMobileConfigFile(appName));

    String deviceName = readValueFromMobileConfigFile("ios_device");
    String platformVersion = readValueFromMobileConfigFile("ios_platform_version");

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName == null ? "iPhone 11" : deviceName);
    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion == null ? "13.3" : platformVersion);
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
    capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
    capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 100);
    capabilities.setCapability("xcodeSigningId", "iPhone Developer");
    capabilities.setCapability("useNewWDA", true);
    capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

    iosDriver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    return iosDriver;
  }
}
