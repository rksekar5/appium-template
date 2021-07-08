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
    String device = readValueFromMobileConfigFile("android_device");
    if (isRemote()) {
      capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
      capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "RF8NA1KRMMW");
      capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
      capabilities.setCapability("avd", "RF8NA1KRMMW");
      capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
      // capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
      capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
      capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
      appiumDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
      capabilities.setCapability(
          MobileCapabilityType.APP, "/tmp/app/" + readValueFromMobileConfigFile(appName));
      appiumDriver = new AndroidDriver<>(new URL(HUB), capabilities);
    } else {
      if (!device.contains("emulator")) {
        final String platformVersion = readValueFromMobileConfigFile("android_platform_version");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
      }
      //            if(device.contains("emulator")){
      //              capabilities.setCapability("avd", "Pixel 4 XL API 28");
      //            }
      capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
      capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
      capabilities.setCapability("appPackage", "com.swaglabsmobileapp");
      capabilities.setCapability("appActivity", "com.swaglabsmobileapp.SplashActivity");
      capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
      capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
      appiumDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
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
