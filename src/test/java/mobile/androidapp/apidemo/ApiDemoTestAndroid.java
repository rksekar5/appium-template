package mobile.androidapp.apidemo;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import mobile.androidapp.api_demos_app.page_objects.DependenciesPage;
import mobile.androidapp.api_demos_app.page_objects.HomePage;
import mobile.androidapp.api_demos_app.page_objects.PreferencesPage;
import mobile.androidapp.common.AndroidBaseTest;
import mobile.androidapp.common.TestData;
import org.testng.annotations.Test;

public class ApiDemoTestAndroid extends AndroidBaseTest {

  @Test(dataProvider = "InputData", dataProviderClass = TestData.class)
  public void apiDemoTest(String input) throws IOException {
    AndroidDriver<MobileElement> driver = capabilities("android_demo_app");
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    HomePage homePage = new HomePage(driver);
    PreferencesPage preferencesPage = new PreferencesPage(driver);
    DependenciesPage dependenciesPage = new DependenciesPage(driver);

    homePage.clickOnPreference();

    preferencesPage.clickOnDependencies();

    dependenciesPage.clickWifiCheckbox();
    dependenciesPage.clickWifiSettingsOption();
    dependenciesPage.enterInputInWifiSettings(input);

    dependenciesPage.clickOnOkButtonOnWifiSettings();
  }

  // Create tests for the following
  // Views -> Drag and Drop
  // Views -> Date widget -> Dialog (Date picker)
  // Views -> Date widget -> Inline (long press and move)
  // Views -> controls -> Light theme (Checkbox, radio button and dropdown)
  // Content -> Storage -> External Storage (tap and check enable or disable)
  // app -> Alert dialogs (Alert handling)
  // app -> Menu -> Inflate (Inflate and deflate context menu)
  // app -> Alarm -> Alarm controller (Capture Toast message)
  // SMS testing scenario
  // Push notification scenario



}
