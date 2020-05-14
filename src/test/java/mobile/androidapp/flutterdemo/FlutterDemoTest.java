package mobile.androidapp.flutterdemo;

import static java.lang.Thread.sleep;
import mobile.androidapp.flutterapp.pageobjects.LandingPage;

import lombok.SneakyThrows;
import mobile.androidapp.common.FlutterBaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlutterDemoTest extends FlutterBaseTest {

  LandingPage landingPage;

  @BeforeMethod
  public void setup(){
    landingPage = new LandingPage();
  }

  @SneakyThrows
  @Test
  public void flutterDemoTest() {

    landingPage.clickOnStumilateHostCard();
    sleep(5000);

  }
}
