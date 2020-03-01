package mobile.androidapp.flutterdemo;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import mobile.androidapp.common.BaseTest;
import org.testng.annotations.Test;

public class FlutterDemoTest extends BaseTest {

  @Test
  public void flutterDemoTest() throws IOException, InterruptedException {
    AndroidDriver<AndroidElement> driver = capabilities("flutter_demo_app");
    Thread.sleep(10000);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

  }
}
