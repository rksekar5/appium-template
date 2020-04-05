package mobile.androidapp.apidemo;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import mobile.androidapp.common.BaseTest;
import mobile.androidapp.common.TestData;
import mobile.androidapp.pageObjects.HomePage;
import mobile.androidapp.pageObjects.Preferences;
import org.testng.annotations.Test;

public class ApiDemoTest extends BaseTest {

  @Test(dataProvider = "InputData", dataProviderClass = TestData.class)
  public void apiDemoTest(String input) throws IOException {
    AndroidDriver<AndroidElement> driver = capabilities("android_demo_app");
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    HomePage homePage = new HomePage(driver);
    Preferences preferences = new Preferences(driver);

    homePage.Preferences.click();

    preferences.dependencies.click();
    driver.findElementById("android:id/checkbox").click();
    driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
    driver.findElementByClassName("android.widget.EditText").sendKeys(input);
    preferences.buttons.get(1).click();
  }
}
