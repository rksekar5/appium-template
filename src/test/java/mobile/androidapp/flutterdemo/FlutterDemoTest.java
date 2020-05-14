package mobile.androidapp.flutterdemo;

import static com.diconium.qa.testautomationframework.common.Logger.logInfo;
import static java.lang.Thread.sleep;

import lombok.SneakyThrows;
import mobile.androidapp.common.FlutterBaseTest;
import mobile.androidapp.flutterapp.pageobjects.LandingPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlutterDemoTest extends FlutterBaseTest {

  private LandingPage landingPage;

  @BeforeMethod
  public void setup(){
    landingPage = new LandingPage();
  }

  @SneakyThrows
  @Test
  public void flutterDemoTest() {

    landingPage.clickOnStumilateHostCard();
    sleep(5000);

    logInfo("Current context of the app is "+androidDriver.getContext());

  }
}
