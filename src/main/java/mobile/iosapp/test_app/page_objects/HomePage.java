package mobile.iosapp.test_app.page_objects;

import com.diconium.qa.testautomationframework.common.Logger;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

import static com.diconium.qa.testautomationframework.common.Logger.logInfo;
import static mobile.driverhandler.AppFactory.getAppiumDriver;
import static mobile.utils.MobileUtils.*;

public class HomePage {

  public HomePage() {
    PageFactory.initElements(new AppiumFieldDecorator(getAppiumDriver()), this);
  }

  @iOSXCUITFindBy(accessibility = "TextField1")
  public MobileElement INPUT_FIELD;

  @iOSXCUITFindBy(accessibility = "show alert")
  public MobileElement SHOW_ALERT_BUTTON;

  @iOSXCUITFindBy(accessibility = "Cool title")
  public MobileElement ALERT_BOX_TITLE;

  @iOSXCUITFindBy(accessibility = "Cancel")
  public MobileElement CANCEL_BUTTON_ALERT;

  @iOSXCUITFindBy(accessibility = "OK")
  public MobileElement OK_BUTTON_ALERT;

  public String getTextFieldValue() {
    return getMobileAttributeValue(INPUT_FIELD);
  }

  public void enterValueInTextField(String input) {
    sendKeysToMobileElement(INPUT_FIELD, input);
    Logger.logInfo("The value has been entered on the text field");
  }

  public void clickOnShowAlertButton() {
    clickMobileElement(SHOW_ALERT_BUTTON);
  }

  public String getTitleFromAlertBox() {
    final String title = getTextFromMobileElement(ALERT_BOX_TITLE);
    logInfo(String.format("Alert box title is %s", title));
    return title;
  }

  public void clickOnOkButtonOnAlert() {
    clickMobileElement(OK_BUTTON_ALERT);
    logInfo("OK button has been clicked on the alert box");
  }

  public void clickOnCancelButtonOnAlert() {
    clickMobileElement(CANCEL_BUTTON_ALERT);
    logInfo("Cancel button has been clicked on the alert box");
  }
}
