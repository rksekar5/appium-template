package mobile.androidapp.flutterdemo;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import mobile.androidapp.common.FlutterBaseTest;
import mobile.androidapp.flutter_app.page_objects.HomePage;
import org.testng.annotations.Test;

public class FlutterDemoTest extends FlutterBaseTest {

  @Test
  public void flutterDemoTest() throws IOException, InterruptedException {
    AndroidDriver<MobileElement> driver = capabilities("flutter_demo_app");
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    HomePage homePage = new HomePage(driver);

//    Assert.assertTrue(homePage.PageHeader.isDisplayed());
//    System.out.println("Page header is displayed in the home page as expected");
//    int initialCount = Integer.parseInt(homePage.ToolTipCounter.getText());
//    System.out.println("Initial counter value is: "+initialCount);
//    homePage.Increment.click();
//    int postIncrementValue = Integer.parseInt(homePage.ToolTipCounter.getText());
//    System.out.println("Post incremental value is: "+postIncrementValue);

    homePage.StimulateHostCardButton.click();
    Thread.sleep(10000);

  }
}
