package mobile.iosapp.test_app.page_objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import mobile.common.Logger;
import mobile.utils.Waiters;
import org.openqa.selenium.support.PageFactory;

import static mobile.driverhandler.AppFactory.getAppiumDriver;
import static mobile.utils.MobileUtils.clickMobileElement;

public class LoginPage {

  public LoginPage() {
    PageFactory.initElements(new AppiumFieldDecorator(getAppiumDriver()), this);
  }

  @iOSXCUITFindBy(accessibility = "test-Username")
  public MobileElement USERNAME_TEXTBOX;

  @iOSXCUITFindBy(accessibility = "test-Password")
  public MobileElement PASSWORD_TEXTBOX;

  @iOSXCUITFindBy(accessibility = "test-LOGIN")
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
