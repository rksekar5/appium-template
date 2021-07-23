package mobile.driverhandler;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static mobile.common.ConfigReader.getValueFromJsonConfigFile;

@Slf4j
public class AndroidFactory extends AppFactory {
  private static String HUB;

  static {
    HUB = readHubDetailsFromConfigFile("hub");
  }

  public static AppiumDriver<MobileElement> androidCapabilities(String appName) throws IOException {
    /**
     * @param deviceName
     * @param platformName
     * @param platformVersion
     * @param automationName
     * @return
     */
    File appDirectory = new File("src/app");
    File app = new File(appDirectory, readValueFromMobileConfigFile(appName));
    DesiredCapabilities capabilities = new DesiredCapabilities();

    String realDevice = readValueFromMobileConfigFile("android_real_device");
    String androidEmulator = readValueFromMobileConfigFile("android_emulator");
    String udid = readValueFromMobileConfigFile("android_udid");
    String appPackage = readValueFromMobileConfigFile("android_package_name");
    String appActivity = readValueFromMobileConfigFile("android_app_activity");
    String modelName = readValueFromMobileConfigFile("android_model_name");
    final String platformVersion = readValueFromMobileConfigFile("android_platform_version");

    if (udid.equals("")) {
      capabilities.setCapability(MobileCapabilityType.FULL_RESET, "TRUE");
      capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, androidEmulator);
      capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
      capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
      capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
      capabilities.setCapability("appPackage", appPackage);
      capabilities.setCapability("appActivity", appActivity);
      capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
      // capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
      capabilities.setCapability(
          "app",
          "https://github.com/saucelabs/sample-app-mobile/releases/download/2.7.1/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
      appiumDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    } else {
      capabilities.setCapability(MobileCapabilityType.FULL_RESET, "TRUE");
      capabilities.setCapability(MobileCapabilityType.UDID, udid);
      capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
      capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
      capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, realDevice);
      // Check If model_name is needed
      capabilities.setCapability("modelName", modelName);
      capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
      capabilities.setCapability("appPackage", appPackage);
      capabilities.setCapability("appActivity", appActivity);
      capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
      capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
      appiumDriver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
    }

    appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    return appiumDriver;
  }

  private static boolean isRemote() {
    return Boolean.TRUE.equals(Boolean.valueOf(System.getProperty("remoteExecution")));
  }

  public static void getScreenshot(String s) throws IOException {
    File scrfile = ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrfile, new File(System.getProperty("user.dir") + "\\" + s + ".png"));
  }

  @SneakyThrows
  private static String readHubDetailsFromConfigFile(@NotNull String propName) {
    return getValueFromJsonConfigFile("selenium_config.json", propName);
  }
}
