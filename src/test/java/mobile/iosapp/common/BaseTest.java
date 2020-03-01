package mobile.iosapp.common;

import common.RetryListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({RetryListener.class})
public class BaseTest extends IosFactory {

  @BeforeMethod(alwaysRun = true)
  protected void setUpIosApp(ITestContext result) {
    service = startServer();
  }

  @AfterMethod(alwaysRun = true)
  protected void tearDown(ITestResult testResult) {
    driver.closeApp();
    //    driver.removeApp(readMobileConfigFromConfigFile("apiDemo"));
    service.stop();
  }
}
