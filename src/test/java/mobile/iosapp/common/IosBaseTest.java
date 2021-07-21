package mobile.iosapp.common;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import mobile.common.AppiumListener;
import mobile.driverhandler.IosFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.util.concurrent.TimeUnit;

@Slf4j
@Listeners({AppiumListener.class})
public class IosBaseTest extends IosFactory {

  @SneakyThrows
  @BeforeClass(alwaysRun = true)
  protected void setUpIosApp(ITestContext result) {
    service = startServer();

    appiumDriver = iosCapabilities("ios_demo_app");
    appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @SneakyThrows
  @AfterClass(alwaysRun = true)
  protected void tearDown() {
    appiumDriver.closeApp();
    removeAppFromDevice();
  }

  private void removeAppFromDevice() {
    if (appiumDriver.isAppInstalled(readValueFromMobileConfigFile("ios_demo_app")))
      log.debug("Uninstalling the app as part of cleanup");
    appiumDriver.removeApp(readValueFromMobileConfigFile("ios_demo_app"));
  }
}
