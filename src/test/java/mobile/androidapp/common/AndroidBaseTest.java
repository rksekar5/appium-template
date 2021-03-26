package mobile.androidapp.common;

import com.diconium.qa.testautomationframework.common.RetryListener;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import mobile.driverhandler.AndroidFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.util.concurrent.TimeUnit;

@Slf4j
@Listeners({RetryListener.class})
public class AndroidBaseTest extends AndroidFactory {

  @SneakyThrows
  @BeforeMethod(alwaysRun = true)
  protected void setUpAndroidApp(ITestContext result) {
    service = startServer();

    appiumDriver = androidCapabilities("SL_android_demo_app");
    appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @AfterMethod(alwaysRun = true)
  protected void tearDown(ITestResult testResult) {
    appiumDriver.closeApp();
    removeAppFromDevice();
    service.stop();
  }

  private void removeAppFromDevice() {
    if (appiumDriver.isAppInstalled(readValueFromMobileConfigFile("SL_android_package_name")))
      log.debug("Uninstalling the app as part of cleanup");
    appiumDriver.removeApp(readValueFromMobileConfigFile("SL_android_package_name"));
  }
}