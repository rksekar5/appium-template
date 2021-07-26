package mobile.common;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestResult;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.testng.AllureTestListener;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static mobile.driverhandler.AppFactory.appiumDriver;

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

  @Override
  public void onTestSkipped(ITestResult tr) {
    Logger.logInfo(tr.getName() + " --- SKIPPED ---\n");
    Throwable ex = tr.getThrowable();
    if (ex != null) {
      String cause = ex.toString();
      Logger.logError(cause);
    }
  }

  // This method will execute only on the event of fail test.
  public void onTestFailure(ITestResult tr) {
    captureScreenShot(tr, "fail");
  }

  // This method will execute before starting of Test suite.
  private static boolean deleteDir(File dir) {
    if (dir.isDirectory()) {
      String[] children = dir.list();
      assert children != null;
      for (String aChildren : children) {
        boolean success = deleteDir(new File(dir, aChildren));
        if (!success) {
          return false;
        }
      }
    }
    return dir.delete();
  }

  public void onTestFailedButWithinSuccessPercentage(ITestResult tr) {}
  // Function to capture screenshot.
  @SneakyThrows
  @Attachment(value = "page screenshot", type = "image/png")
  private static byte[] captureScreenShot(ITestResult result, String status) {
    String destDir = "";
    String passfailMethod =
        result.getMethod().getRealClass().getSimpleName()
            + "."
            + result.getMethod().getMethodName();
    // To capture screenshot.
    File scrFile = appiumDriver.getScreenshotAs(OutputType.FILE);
    DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
    // If status = fail then set folder name "screenshots/Failures"
    if (status.equalsIgnoreCase("fail")) {
      destDir = "screenshots/Failures";
    }
    // If status = pass then set folder name "screenshots/Success"
    else if (status.equalsIgnoreCase("pass")) {
      destDir = "screenshots/Success";
    }
    // To create folder to store screenshots
    new File(destDir).mkdirs();
    // Set file name with combination of test class name + date time.
    String destFile = passfailMethod + " - " + dateFormat.format(new Date()) + ".png";
    try {
      // Store file at destination folder location
      FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return appiumDriver.getScreenshotAs(OutputType.BYTES);
  }
}
