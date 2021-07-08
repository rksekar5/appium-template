package mobile.androidapp.common;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import mobile.common.RetryListener;
import mobile.driverhandler.AndroidFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Slf4j
@Listeners({RetryListener.class})
public class AndroidBaseTest extends AndroidFactory {

  public void stopServer() {
    Runtime runtime = Runtime.getRuntime();
    try {
      runtime.exec("taskkill /F /IM node.exe");
      runtime.exec("taskkill /F /IM cmd.exe");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @SneakyThrows
  @BeforeMethod(alwaysRun = true)
  protected void setUpAndroidApp(ITestContext result) {

    String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);

    if (OS.contains("windows")) {
      stopServer();
      service = startServer();
      appiumDriver = androidCapabilities("SL_android_demo_app");
      appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    } else if (OS.contains("MAC")) {
      service.stop();
      service = startServer();
      appiumDriver = androidCapabilities("SL_android_demo_app");
      appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
  }

  @AfterMethod(alwaysRun = true)
  protected void tearDown(ITestResult testResult) {
    String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
    System.out.println(OS);
    if (OS.contains("windows")) {
      appiumDriver.closeApp();
      removeAppFromDevice();
      stopServer();

    } else if (OS.contains("MAC")) {
      appiumDriver.closeApp();
      removeAppFromDevice();
      service.stop();
    }
  }

  @SneakyThrows
  private void removeAppFromDevice() {
    if (appiumDriver.isAppInstalled(readValueFromMobileConfigFile("SL_android_package_name")))
      log.debug("Uninstalling the app as part of cleanup");
    appiumDriver.removeApp(readValueFromMobileConfigFile("SL_android_package_name"));
  }
}
