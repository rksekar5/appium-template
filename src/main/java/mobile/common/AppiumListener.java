package mobile.common;

import lombok.SneakyThrows;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.testng.AllureTestListener;

import static mobile.browser.common.WebDriverHolder.getDriver;

public class AppiumListener extends AllureTestListener {

  @Override
  public void onStart(ITestContext arg0) {
    Logger.logInfo("Test suite: " + arg0.getName());
  }

  @Override
  public void onTestStart(ITestResult arg0) {
    Logger.logInfo("Starting test: " + arg0.getMethod());
  }

  @Override
  public void onTestSuccess(ITestResult tr) {
    Logger.logInfo(tr.getName() + " \n--- SUCCESS ---\n");
  }

  @Step("Step on Failure")
  @Override
  public void onTestFailure(ITestResult tr) {
    Logger.logError(tr.getName() + " --- FAILED --- ");
    // saveTextLog(tr.getName());
    Logger.logInfo(String.format("%s failed and screenshot taken", tr.getName()));
    saveScreenshotPNG();
    Throwable ex = tr.getThrowable();
    if (ex != null) {
      String cause = ex.toString();
      Logger.saveTextLog(cause);
      Logger.logError(cause + "\n");
    }
  }

  @Override
  public void onTestSkipped(ITestResult tr) {
    Logger.logInfo(tr.getName() + " --- SKIPPED ---\n");
    Throwable ex = tr.getThrowable();
    if (ex != null) {
      String cause = ex.toString();
      Logger.logError(cause);
    }
  }

  /**
   * Take and save a screen shot as an attachment to the allure report.
   *
   * @return the screenshot, which will be handled by the annotation
   */
  @SneakyThrows
  @Attachment(value = "page screenshot", type = "image/png")
  private static byte[] saveScreenshotPNG() {
    Logger.logDebug("Taking screenshot");
    /* Only used when we integrate Report Portal
           Logger.logInfo(
                   new ObjectMessage(
                           new ReportPortalMessage(
                                   ((TakesScreenshot)
    getDriver()).getScreenshotAs(OutputType.FILE),
                                   "Appending Screenshot")));*/
    return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
  }
}
