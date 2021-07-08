package mobile.androidapp.sauceLabs.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobile.common.Logger;
import mobile.utils.AndroidUtils;
import mobile.utils.Waiters;
import org.openqa.selenium.support.PageFactory;

import static mobile.driverhandler.AppFactory.getAppiumDriver;
import static mobile.utils.MobileUtils.assertMobileElementPresent;
import static mobile.utils.MobileUtils.clickMobileElement;

public class ProductsPage {

  public ProductsPage() {
    PageFactory.initElements(new AppiumFieldDecorator(getAppiumDriver()), this);
  }

  private final AndroidUtils androidUtils = new AndroidUtils();

  @AndroidFindBy(
      xpath = "//android.view.ViewGroup[@content-desc=\"test-Toggle\"]/android.widget.ImageView")
  public MobileElement FIRST_PRODUCT_ON_THE_LIST;

  @AndroidFindBy(accessibility = "test-Modal Selector Button")
  public MobileElement PRODUCTS_FILTER;

  @AndroidFindBy(
      xpath =
          "//android.widget.ScrollView[@content-desc=\"Selector container\"]/android.view.ViewGroup/android.view.ViewGroup[4]/android.view.ViewGroup")
  public MobileElement PRODUCTS_FILTER_PRICE_LOW_TO_HIGH;

  @AndroidFindBy(accessibility = "test-Menu")
  public MobileElement LEFT_PANEL_LIST;

  @AndroidFindBy(
      xpath =
          "//android.widget.ScrollView[@content-desc=\"Selector container\"]/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup")
  public MobileElement PRODUCTS_FILTER_NAME_A_TO_Z;

  @AndroidFindBy(
      xpath =
          "//android.widget.ScrollView[@content-desc=\"Selector container\"]/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup/android.widget.TextView")
  public MobileElement PRODUCTS_FILTER_NAME_Z_TO_A;

  @AndroidFindBy(
      xpath =
          "//android.view.ViewGroup[@content-desc=\"test-Item\"]/android.view.ViewGroup/android.widget.ImageView")
  public MobileElement FIRST_PRODUCT_NAME_ON_THE_LIST;

  @AndroidFindBy(
      xpath =
          "(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[2]/android.widget.TextView")
  public MobileElement ADD_TO_CART_BUTTON;

  @AndroidFindBy(
      xpath =
          "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.TextView")
  public MobileElement CART_ICON;

  @AndroidFindBy(
      xpath = "//android.view.ViewGroup[@content-desc=\"test-REMOVE\"]/android.widget.TextView")
  public MobileElement REMOVE_BUTTON;

  @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-CONTINUE SHOPPING\"]")
  public MobileElement CONTINUE_SHOPPING_BUTTON;

  @AndroidFindBy(accessibility = "test-CHECKOUT")
  public MobileElement CHECKOUT_BUTTON;

  @AndroidFindBy(
      xpath = "//android.view.ViewGroup[@content-desc=\"test-CONTINUE\"]/android.widget.TextView")
  public MobileElement CHECKOUT_CONTINUE_BUTTON;

  @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"test-First Name\"]")
  public MobileElement CHECKOUT_FIRST_NAME_REQUIRED_ERROR_MESSAGE;

  @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"test-Last Name\"]")
  public MobileElement CHECKOUT_LAST_NAME_REQUIRED_ERROR_MESSAGE;

  @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"test-Zip/Postal Code\"]")
  public MobileElement CHECKOUT_ZIPCODE_REQUIRED_ERROR_MESSAGE;

  @AndroidFindBy(accessibility = "test-First Name")
  public MobileElement FIRSTNAME_TEXTBOX;

  @AndroidFindBy(accessibility = "test-Last Name")
  public MobileElement LASTNAME_TEXTBOX;

  @AndroidFindBy(accessibility = "test-Zip/Postal Code")
  public MobileElement ZIPCODE_TEXTBOX;

  public void clickOnProductListView() {
    Waiters.waitUntilMobileElementVisible(FIRST_PRODUCT_ON_THE_LIST);
    clickMobileElement(FIRST_PRODUCT_ON_THE_LIST);
    Logger.logInfo("Product list view has been clicked");
  }

  public void clickOnProductsFilter() {
    Waiters.waitUntilMobileElementVisible(PRODUCTS_FILTER);
    clickMobileElement(PRODUCTS_FILTER);
    Logger.logInfo("Product filter has been clicked");
  }

  public void sortTheItemsOnProductListViewBasedOnPrice() {
    clickOnProductsFilter();
    Waiters.waitUntilMobileElementVisible(PRODUCTS_FILTER_PRICE_LOW_TO_HIGH);
    clickMobileElement(PRODUCTS_FILTER_PRICE_LOW_TO_HIGH);
    Logger.logInfo("The products have been sorted based on the price");
  }

  public void clickOnLeftPanelList() {
    Waiters.waitUntilMobileElementVisible(LEFT_PANEL_LIST);
    clickMobileElement(LEFT_PANEL_LIST);
    Logger.logInfo("Clicked on the Left panel list");
  }

  public void clickOnProductName() {
    Waiters.waitUntilMobileElementVisible(FIRST_PRODUCT_NAME_ON_THE_LIST);
    clickMobileElement(FIRST_PRODUCT_NAME_ON_THE_LIST);
    Logger.logInfo("Product Name has been clicked");
  }

  public void clickOnAddToCartButton() {
    Waiters.waitUntilMobileElementVisible(ADD_TO_CART_BUTTON);
    clickMobileElement(ADD_TO_CART_BUTTON);
    Logger.logInfo("Add to cart button has been clicked");
  }

  public void sortTheItemsOnProductListViewBasedOnProductName() {
    clickOnProductsFilter();
    Waiters.waitUntilMobileElementVisible(PRODUCTS_FILTER_NAME_A_TO_Z);
    clickMobileElement(PRODUCTS_FILTER_NAME_A_TO_Z);
    Logger.logInfo("The products have been sorted based on the product name");
  }

  public void clickOnCartIcon() {
    Waiters.waitUntilMobileElementVisible(CART_ICON);
    clickMobileElement(CART_ICON);
    Logger.logInfo("Cart icon has been clicked");
  }

  public void clickOnRemoveButtonFromCartPage() {
    Waiters.waitUntilMobileElementVisible(REMOVE_BUTTON);
    clickMobileElement(REMOVE_BUTTON);
    Logger.logInfo("Clicked on Remove button");
  }

  public void clickOnContinueShoppingButton() {
    Waiters.waitUntilMobileElementVisible(CONTINUE_SHOPPING_BUTTON);
    clickMobileElement(CONTINUE_SHOPPING_BUTTON);
    Logger.logInfo("Clicked on Continue shopping button");
  }

  public void clickOnCheckoutButton() {
    Waiters.waitUntilMobileElementVisible(CHECKOUT_BUTTON);
    clickMobileElement(CHECKOUT_BUTTON);
    Logger.logInfo("Clicked on Checkout button");
  }

  public void clickOnCheckoutContinueButton() {
    Waiters.waitUntilMobileElementVisible(CHECKOUT_CONTINUE_BUTTON);
    clickMobileElement(CHECKOUT_CONTINUE_BUTTON);
    Logger.logInfo("Clicked on Checkout button");
  }

  public void verifyFirstNameErrorMessage() {
    Waiters.waitUntilMobileElementVisible(CHECKOUT_FIRST_NAME_REQUIRED_ERROR_MESSAGE);
    assertMobileElementPresent(CHECKOUT_FIRST_NAME_REQUIRED_ERROR_MESSAGE);
  }

  public void verifyLastNameErrorMessage() {
    Waiters.waitUntilMobileElementVisible(CHECKOUT_LAST_NAME_REQUIRED_ERROR_MESSAGE);
    assertMobileElementPresent(CHECKOUT_LAST_NAME_REQUIRED_ERROR_MESSAGE);
  }

  public void verifyZipcodeErrorMessage() {
    Waiters.waitUntilMobileElementVisible(CHECKOUT_ZIPCODE_REQUIRED_ERROR_MESSAGE);
    assertMobileElementPresent(CHECKOUT_ZIPCODE_REQUIRED_ERROR_MESSAGE);
  }

  public void enterFirstName(String firstName) {
    Waiters.waitUntilMobileElementVisible(FIRSTNAME_TEXTBOX);
    clickMobileElement(FIRSTNAME_TEXTBOX);
    FIRSTNAME_TEXTBOX.sendKeys(firstName);
    Logger.logInfo("First name has been entered");
  }

  public void enterLastName(String lastName) {
    Waiters.waitUntilMobileElementVisible(LASTNAME_TEXTBOX);
    clickMobileElement(LASTNAME_TEXTBOX);
    LASTNAME_TEXTBOX.sendKeys(lastName);
    Logger.logInfo("Last name has been entered");
  }

  public void enterZipcode(String zipcode) {
    Waiters.waitUntilMobileElementVisible(ZIPCODE_TEXTBOX);
    clickMobileElement(ZIPCODE_TEXTBOX);
    ZIPCODE_TEXTBOX.sendKeys(zipcode);
    Logger.logInfo("Last name has been entered");
  }
}
