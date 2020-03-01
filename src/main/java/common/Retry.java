package common;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/** Created by Parthiban. */
public class Retry implements IRetryAnalyzer {

  private int retryCount = 0;

  private final int maxRetryCount = 1;

  /** Create a new instance with a default number of retries. */
  public Retry() {
    super();
  }

  public boolean retry(final ITestResult result) {
    if (retryCount < maxRetryCount) {
      retryCount++;
      return true;
    }
    return false;
  }
}
