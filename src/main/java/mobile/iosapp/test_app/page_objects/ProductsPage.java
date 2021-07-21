package mobile.iosapp.test_app.page_objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import mobile.common.Logger;
import mobile.utils.MobileUtils;
import mobile.utils.Waiters;
import org.openqa.selenium.support.PageFactory;

import static mobile.driverhandler.AppFactory.appiumDriver;

public class ProductsPage {

  public ProductsPage() {
    PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
  }

  @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"test-Item title\"])[1]")
  public MobileElement FIRST_AVAILABLE_ITEM;

  @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"test-Price\"])[1]")
  public MobileElement FIRST_AVAILABLE_ITEM_PRICE;

  @iOSXCUITFindBy(accessibility = "test-ADD TO CART")
  public MobileElement ADD_TO_CART_BUTTON;

  @iOSXCUITFindBy(accessibility = "test-REMOVE")
  public MobileElement REMOVE_BUTTON;

  @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"1\"])[4]")
  public MobileElement CART;

  public void verifyTheElementsOnTheProductsPage() {
    Waiters.waitUntilMobileElementVisible(FIRST_AVAILABLE_ITEM);
    Waiters.waitUntilMobileElementVisible(FIRST_AVAILABLE_ITEM_PRICE);
  }

  public void clickOnTheFirstAvailableItem() {
    FIRST_AVAILABLE_ITEM.click();
    Logger.logInfo("Clicked on the first available item on the page");
  }

  public void addTheItemToTheSHoppingCart() {
    MobileUtils.scrollFromBottomToUpWithJavascriptExecutor();
    Waiters.waitUntilMobileElementVisible(ADD_TO_CART_BUTTON);
    ADD_TO_CART_BUTTON.click();
    Logger.logInfo("The item has been added to the shopping cart");
    Waiters.waitUntilMobileElementVisible(REMOVE_BUTTON);
  }

  public void clickOnTheShoppingCartMenu() {
    MobileUtils.scrollFromUpToBottomWithJavascriptExecutor();
    CART.click();
    Logger.logInfo("Clicked on the shopping cart menu and moving to Shopping cart page");
  }
}
