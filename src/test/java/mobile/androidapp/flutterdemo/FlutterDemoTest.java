package mobile.androidapp.flutterdemo;

import lombok.SneakyThrows;
import mobile.androidapp.common.FlutterBaseTest;
import mobile.androidapp.flutterapp.pageobjects.LandingPage;
import mobile.common.RetryListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;
import static mobile.common.Logger.logInfo;

@Listeners({RetryListener.class})
public class FlutterDemoTest extends FlutterBaseTest {

  private LandingPage landingPage;

  @BeforeMethod
  public void setup() {
    landingPage = new LandingPage();
  }

  @SneakyThrows
  @Test
  public void flutterDemoTest() {

    landingPage.clickOnStimulateHostCard();
    sleep(2000);

    logInfo("Current context of the app is " + appiumDriver.getContext());
  }
}
