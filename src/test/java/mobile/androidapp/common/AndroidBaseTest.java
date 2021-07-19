package mobile.androidapp.common;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import mobile.common.RetryListener;
import mobile.driverhandler.AndroidFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.util.concurrent.TimeUnit;

@Slf4j
@Listeners({RetryListener.class})
public class AndroidBaseTest extends AndroidFactory {

  @SneakyThrows
  @BeforeClass(alwaysRun = true)
  protected void setUpIosApp(ITestContext result) {
    service = startServer();

    appiumDriver = androidCapabilities("SL_android_package_name");
    appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @SneakyThrows
  @AfterClass(alwaysRun = true)
  protected void tearDown() {
    appiumDriver.closeApp();
    removeAppFromDevice();
    service.stop();
  }

  @SneakyThrows
  private void removeAppFromDevice() {
    if (appiumDriver.isAppInstalled(readValueFromMobileConfigFile("SL_android_package_name")))
      log.debug("Uninstalling the app as part of cleanup");
    appiumDriver.removeApp(readValueFromMobileConfigFile("SL_android_package_name"));
  }
}
