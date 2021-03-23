package mobile.iosapp.common;

import com.diconium.qa.testautomationframework.common.RetryListener;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import mobile.driverhandler.IosFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.util.concurrent.TimeUnit;

@Slf4j
@Listeners({RetryListener.class})
public class IosBaseTest extends IosFactory {

  @SneakyThrows
  @BeforeClass(alwaysRun = true)
  protected void setUpIosApp(ITestContext result) {
    service = startServer();

    appiumDriver = iosCapabilities("SL_ios_demo_app");
    appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

    @SneakyThrows
    @AfterClass(alwaysRun = true)
    protected void tearDown() {
        appiumDriver.closeApp();
        removeAppFromDevice();
        service.stop();
    }

  private void removeAppFromDevice() {
    if (appiumDriver.isAppInstalled(readValueFromMobileConfigFile("SL_ios_demo_app")))
      log.debug("Uninstalling the app as part of cleanup");
    appiumDriver.removeApp(readValueFromMobileConfigFile("SL_ios_demo_app"));
  }
}