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

public class LoginPage {
  public LoginPage() {
    PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
  }

  private final AndroidUtils androidUtils = new AndroidUtils();

  @AndroidFindBy(accessibility = "test-Username")
  public MobileElement USERNAME_TEXTBOX;

  @AndroidFindBy(accessibility = "test-Password")
  public MobileElement PASSWORD_TEXTBOX;

  @AndroidFindBy(accessibility = "test-LOGIN")
  public MobileElement LOGIN_BUTTON;

  public void enterUserName(String userName) {
    Waiters.waitUntilMobileElementVisible(USERNAME_TEXTBOX);
    clickMobileElement(USERNAME_TEXTBOX);
    USERNAME_TEXTBOX.sendKeys(userName);
    Logger.logInfo("Username has been entered");
  }

  public void enterPassword(String password) {
    Waiters.waitUntilMobileElementVisible(PASSWORD_TEXTBOX);
    clickMobileElement(PASSWORD_TEXTBOX);
    PASSWORD_TEXTBOX.sendKeys(password);
    Logger.logInfo("Password has been entered");
  }

  public void clickOnLoginButton() {
    clickMobileElement(LOGIN_BUTTON);
    Logger.logInfo("Login button has been clicked");
  }
}
