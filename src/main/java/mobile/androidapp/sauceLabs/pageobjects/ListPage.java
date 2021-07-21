package mobile.androidapp.sauceLabs.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobile.common.Logger;
import mobile.utils.AndroidUtils;
import mobile.utils.Waiters;
import org.openqa.selenium.support.PageFactory;

import static mobile.driverhandler.AppFactory.appiumDriver;
import static mobile.utils.MobileUtils.clickMobileElement;

public class ListPage {
  public ListPage() {
    PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
  }

  private final AndroidUtils androidUtils = new AndroidUtils();

  @AndroidFindBy(accessibility = "test-WEBVIEW")
  public MobileElement LIST_WEB_VIEW;

  @AndroidFindBy(accessibility = "test-QR CODE SCANNER")
  public MobileElement LIST_QR_CODE;

  @AndroidFindBy(accessibility = "test-LOGOUT")
  public MobileElement LIST_LOGOUT;

  @AndroidFindBy(accessibility = "test-GEO LOCATION")
  public MobileElement LIST_GEO_LOCATION;

  @AndroidFindBy(
      xpath = "//android.widget.Button[contains(@text,'Allow only while using the app')]")
  public MobileElement GEO_LOCATION_ALLOW_BUTTON;

  public void clickOnListWebView() {
    Waiters.waitUntilMobileElementVisible(LIST_WEB_VIEW);
    clickMobileElement(LIST_WEB_VIEW);
    Logger.logInfo("Web view has been clicked");
  }

  public void clickOnLogoutButton() {
    Waiters.waitUntilMobileElementVisible(LIST_LOGOUT);
    clickMobileElement(LIST_LOGOUT);
    Logger.logInfo("Logout button has been clicked");
  }

  public void clickOnGeoLocationButton() {
    Waiters.waitUntilMobileElementVisible(LIST_GEO_LOCATION);
    clickMobileElement(LIST_GEO_LOCATION);
    Logger.logInfo("Clicked geo location");
  }

  public void clickOnGeoLocationAllowButton() {
    Waiters.waitUntilMobileElementVisible(GEO_LOCATION_ALLOW_BUTTON);
    clickMobileElement(GEO_LOCATION_ALLOW_BUTTON);
    Logger.logInfo("Clicked geo location Allow button");
  }
}
