package mobile.androidapp.common;

import static com.diconium.qa.testautomationframework.common.ConfigReader.getValueFromJsonConfigFile;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import mobile.utils.AppFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

@Slf4j
public class AndroidFactory extends AppFactory {

  public static AndroidDriver<MobileElement> androidDriver;

  public static AndroidDriver<MobileElement> capabilities(String appName) throws IOException {

    File appDirectory = new File("src/app");
    File app = new File(appDirectory, readValueFromMobileConfigFile(appName));

    DesiredCapabilities capabilities = new DesiredCapabilities();
    String device = readValueFromMobileConfigFile("android_device");

    if (!device.contains("emulator")) {
      String platformVersion = readValueFromMobileConfigFile("android_platform_version");
      capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
      capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
    }
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
    capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 100);
    capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
    androidDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    return androidDriver;
  }

  public static void getScreenshot(String s) throws IOException {
    File scrfile = ((TakesScreenshot) androidDriver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrfile, new File(System.getProperty("user.dir") + "\\" + s + ".png"));
  }

  private static String readHubDetailsFromConfigFile(@NotNull String propName) {
    return getValueFromJsonConfigFile("selenium_hub_config.json", propName);
  }
}
