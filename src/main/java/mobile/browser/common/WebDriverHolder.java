package mobile.browser.common;

import static com.diconium.qa.testautomationframework.common.Logger.logInfo;
import static mobile.browser.common.BrowserFactory.getBrowser;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public class WebDriverHolder {

  private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

  public static void setDriver(String browserName, String testName) {
    try {
      driver.set(getBrowser(browserName, testName));
      logInfo(String.format("Starting browser : %s", browserName));
    } catch (MalformedURLException e) {
      log.error("Unable to open {}", browserName, e);
    }
    getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
  }

  public static WebDriver getDriver() {
    return driver.get();
  }

  public static void tearDownBrowser() {
    logInfo("Closing Browser");
    if (getDriver() != null) {
      getDriver().quit();
    }
  }
}
