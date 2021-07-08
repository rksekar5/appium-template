package mobile.androidapp.androidnativedemo;

import lombok.SneakyThrows;
import mobile.androidapp.common.AndroidBaseTest;
import mobile.androidapp.sauceLabs.pageobjects.ListPage;
import mobile.androidapp.sauceLabs.pageobjects.LoginPage;
import mobile.androidapp.sauceLabs.pageobjects.ProductsPage;
import mobile.androidapp.sauceLabs.pageobjects.WebViewPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class AndroidDemoTest extends AndroidBaseTest {

  private LoginPage loginPage;
  private ProductsPage productsPage;
  private ListPage listPage;
  private WebViewPage webViewPage;

  @SneakyThrows
  @BeforeMethod
  public void setup() {

    loginPage = new LoginPage();
    productsPage = new ProductsPage();
    listPage = new ListPage();
    webViewPage = new WebViewPage();
  }

  @Test(priority = 1)
  @SneakyThrows
  @Severity(SeverityLevel.BLOCKER)
  public void testAppFeaturesOnTheAndroidApp() {

    loginPage.enterUserName("standard_user");
    loginPage.enterPassword("secret_sauce");
    loginPage.clickOnLoginButton();

    // test the sorting based on price
    productsPage.clickOnProductListView();
    productsPage.sortTheItemsOnProductListViewBasedOnPrice();

    // test the web view inside the native app
    productsPage.clickOnLeftPanelList();
    listPage.clickOnListWebView();
    webViewPage.enterWebsiteURLOnTheTextBox("https://demoqa.com/automation-practice-form");
    webViewPage.launchTheWebsiteOnTheApp();
    productsPage.clickOnLeftPanelList();
    appiumDriver.hideKeyboard();
    listPage.clickOnLogoutButton();
  }
}
