package mobile.iosapp.test_app.page_objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import mobile.common.Logger;
import mobile.utils.MobileUtils;
import mobile.utils.Waiters;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;

import static mobile.driverhandler.AppFactory.appiumDriver;

public class ShoppingCartPage {
  public ShoppingCartPage() {
    PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
  }

  @iOSXCUITFindBy(accessibility = "test-CONTINUE SHOPPING")
  public MobileElement CONTINUE_SHOPPING_BUTTON;

  @iOSXCUITFindBy(accessibility = "test-CHECKOUT")
  public MobileElement CHECKOUT_BUTTON;

  @iOSXCUITFindBy(accessibility = "test-First Name")
  public MobileElement FIRST_NAME_TEXT_BOX;

  @iOSXCUITFindBy(accessibility = "test-Last Name")
  public MobileElement LAST_NAME_TEXT_BOX;

  @iOSXCUITFindBy(accessibility = "test-Zip/Postal Code")
  public MobileElement POSTAL_CODE;

  @iOSXCUITFindBy(accessibility = "test-CONTINUE")
  public MobileElement CART_CONTINUE_BUTTON;

  public void clickOnTheCheckoutButton() {
    Waiters.waitUntilMobileElementVisible(CONTINUE_SHOPPING_BUTTON);
    MobileUtils.clickMobileElement(CHECKOUT_BUTTON);
    Logger.logInfo(
        "Clicked on the checkout button and moving to the next screen to enter user details");
  }

  public void enterUserDetails(String firstName, String lastName, String postalCode) {
    Waiters.waitUntilMobileElementVisible(FIRST_NAME_TEXT_BOX);
    FIRST_NAME_TEXT_BOX.sendKeys(firstName);
    LAST_NAME_TEXT_BOX.sendKeys(lastName);
    POSTAL_CODE.sendKeys(postalCode);
    Logger.logInfo("User detail has been entered");
  }

  /*getKeyboard().pressKey(Keys.ENTER) method has been used instead of hideKeyboard as this doesn't work now*/
  public void continueToTheCheckoutPage() {
    appiumDriver.getKeyboard().pressKey(Keys.ENTER);
    // appiumDriver.hideKeyboard();
    CART_CONTINUE_BUTTON.click();
    Logger.logInfo("Proceeding to the checkout page");
  }
}
