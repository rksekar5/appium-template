package mobile.androidapp.androidnativedemo;

import lombok.SneakyThrows;
import mobile.androidapp.common.AndroidBaseTest;
import mobile.androidapp.sauceLabs.pageobjects.*;
import mobile.utils.AndroidUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class OnboardingTask extends AndroidBaseTest {

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

    // loginPage.enterUserName("standard_user");
    loginPage.enterPassword("secret_sauce");
    loginPage.clickOnLoginButton();

    // test the sorting based on product name and scroll down
    productsPage.sortTheItemsOnProductListViewBasedOnProductName();
    AndroidUtils.scrollToText("Test.allTheThings() T-Shirt (Red)");

    // Add to cart and Remove item in shopping cart
    productsPage.clickOnAddToCartButton();
    productsPage.clickOnCartIcon();
    productsPage.clickOnRemoveButtonFromCartPage();
    productsPage.clickOnContinueShoppingButton();
    productsPage.clickOnAddToCartButton();
    productsPage.clickOnCartIcon();
    productsPage.clickOnCheckoutButton();
    productsPage.clickOnCheckoutContinueButton();

    // verify the error message for First name, last name and zipcode
    productsPage.verifyFirstNameErrorMessage();
    productsPage.enterFirstName("firstname");
    appiumDriver.hideKeyboard();
    productsPage.clickOnCheckoutContinueButton();

    productsPage.verifyLastNameErrorMessage();
    productsPage.enterLastName("lastname");
    appiumDriver.hideKeyboard();
    productsPage.clickOnCheckoutContinueButton();

    productsPage.verifyZipcodeErrorMessage();
    productsPage.enterZipcode("12345");
    appiumDriver.hideKeyboard();
    productsPage.clickOnCheckoutContinueButton();

    // test the web view inside the native app
    productsPage.clickOnLeftPanelList();
    listPage.clickOnListWebView();
    webViewPage.enterWebsiteURLOnTheTextBox("https://demoqa.com/automation-practice-form");
    webViewPage.launchTheWebsiteOnTheApp();
    productsPage.clickOnLeftPanelList();
    appiumDriver.hideKeyboard();

    // Geo location list and Accept button
    listPage.clickOnGeoLocationButton();
    listPage.clickOnGeoLocationAllowButton();

    productsPage.clickOnLeftPanelList();
    listPage.clickOnLogoutButton();
  }
}
