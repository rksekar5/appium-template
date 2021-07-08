package mobile.browser.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.validation.constraints.NotNull;
import java.net.MalformedURLException;
import java.net.URL;

import static mobile.browser.common.WebDriverHolder.getDriver;
import static mobile.common.ConfigReader.getValueFromJsonConfigFile;

public class BrowserFactory {

  private static String HUB;
  private static String HUB_IE;

  static {
    HUB = readHubDetailsFromConfigFile("hub");
    HUB_IE = readHubDetailsFromConfigFile("hub_ie");
  }

  /** This method is to setup webDriverManager for different browser */
  private static void setupWebdriverManager() {
    WebDriverManager.chromedriver().setup();
    WebDriverManager.firefoxdriver().setup();
    WebDriverManager.iedriver().setup();
  }

  /**
   * Set Chrome browser capabilities.
   *
   * @param testName Used to identify the current test being run
   * @return A not null chrome driver
   * @throws MalformedURLException if the constant HUB is not a valid URL.
   */
  private static WebDriver getChromeBrowser(String testName) throws MalformedURLException {
    final ChromeOptions options = new ChromeOptions();
    options.setCapability("elementScrollBehavior", true);
    options.setCapability("name", testName);
    options.addArguments("--disable-dev-shm-usage");

    if (isRemote()) {
      setupWebdriverManager();
      return new RemoteWebDriver(new URL(HUB), options);
    } else {
      setupWebdriverManager();
      return new ChromeDriver();
    }
  }

  /**
   * Set Firefox browser capabilities.
   *
   * @param testName Used to identify the test being run
   * @return A valid instance
   * @throws MalformedURLException if the constant HUB is not a valid URL.
   */
  private static WebDriver getFirefoxBrowser(String testName) throws MalformedURLException {
    FirefoxOptions options = new FirefoxOptions();
    options.setCapability("name", testName);
    options.setCapability("moz:firefoxOptions", options);
    System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
    System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

    if (isRemote()) {
      setupWebdriverManager();
      return new RemoteWebDriver(new URL(HUB), options);
    } else {
      setupWebdriverManager();
      return new FirefoxDriver();
    }
  }

  /**
   * Check a system property to decide if we should use a remote web driver.
   *
   * @return True only if the property is correctly set.
   */
  private static boolean isRemote() {
    return Boolean.TRUE.equals(Boolean.valueOf(System.getProperty("remoteExecution")));
  }

  /**
   * Set IE browser capabilities.
   *
   * @param testName Used to identify the current test being run
   * @return A not null ie webdriver.
   * @throws MalformedURLException if the constant HUB_IE is not a valid URL.
   */
  private static WebDriver getInternetExplorerBrowser(String testName)
      throws MalformedURLException {
    final DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
    caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    caps.setCapability(
        InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
    caps.setCapability("name", testName);
    caps.setPlatform(Platform.WINDOWS);

    if (isRemote()) {
      setupWebdriverManager();
      ((RemoteWebDriver) getDriver()).setFileDetector(new LocalFileDetector());
      return new RemoteWebDriver(new URL(HUB_IE), caps);
    } else {
      setupWebdriverManager();
      return new InternetExplorerDriver();
    }
  }

  /**
   * Set Edge browser capabilities.
   *
   * @param testName Used to identify the current test being run
   * @return A usable, not null edge driver.
   * @throws MalformedURLException if the constant HUB_IE is not a valid URL.
   */
  private static WebDriver getEdgeBrowser(String testName) throws MalformedURLException {
    final DesiredCapabilities caps = DesiredCapabilities.edge();
    caps.setCapability("name", testName);
    if (isRemote()) {
      setupWebdriverManager();
      return new RemoteWebDriver(new URL(HUB_IE), caps);
    } else {
      setupWebdriverManager();
      return new EdgeDriver();
    }
  }

  /**
   * This method returns the browser driver based on the browser name specified in the test suite
   * xml.
   *
   * @param browserName One of firefox, edge, iexplore or chrome.
   * @param testName Used to identify the current test being run
   * @return default is chrome
   * @throws MalformedURLException if the constant for the hub (HUB or HUB_IE) is not a valid URL.
   */
  static WebDriver getBrowser(String browserName, String testName) throws MalformedURLException {
    switch (browserName) {
      case "firefox":
        return getFirefoxBrowser(testName);
      case "edge":
        return getEdgeBrowser(testName);
      case "iexplore":
        return getInternetExplorerBrowser(testName);
      case "chrome":
      default:
        return getChromeBrowser(testName);
    }
  }

  @SneakyThrows
  private static String readHubDetailsFromConfigFile(@NotNull String propName) {
    return getValueFromJsonConfigFile("selenium_config.json", propName);
  }
}
