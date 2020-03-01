package mobile.androidapp.common;

import common.RetryListener;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Slf4j
@Listeners({RetryListener.class})
public class BaseTest extends AndroidFactory {

  @BeforeMethod(alwaysRun = true)
  protected void setUpAndroidApp(ITestContext result) {
    service = startServer();
  }

  @AfterMethod(alwaysRun = true)
  protected void tearDown(ITestResult testResult) {
    driver.closeApp();
    removeAppFromDevice();
    service.stop();
  }

  private void removeAppFromDevice() {
    if (driver.isAppInstalled(readValueFromMobileConfigFile("android_package_name")))
      log.debug("Uninstalling the app as part of cleanup");
    driver.removeApp(readValueFromMobileConfigFile("android_package_name"));
  }
}
