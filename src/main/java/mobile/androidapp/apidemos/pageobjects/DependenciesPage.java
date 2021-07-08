package mobile.androidapp.apidemos.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static mobile.common.Logger.logInfo;
import static mobile.driverhandler.AndroidFactory.appiumDriver;
import static mobile.utils.MobileUtils.clickMobileElement;
import static mobile.utils.MobileUtils.sendKeysToMobileElement;

public class DependenciesPage {

  public DependenciesPage() {
    PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
  }

  @AndroidFindBy(id = "android:id/checkbox")
  public MobileElement WIFI_CHECK_BOX;

  @AndroidFindBy(xpath = "(//android.widget.RelativeLayout)[2]")
  public MobileElement WIFI_SETTINGS_OPTIONS;

  @AndroidFindBy(className = "android.widget.EditText")
  public MobileElement WIFI_SETTINS_INPUT_FIELD;

  @AndroidFindBy(className = "android.widget.Button")
  public List<MobileElement> BUTTONS;

  public void clickWifiCheckbox() {
    clickMobileElement(WIFI_CHECK_BOX);
    logInfo("WIFI checkbox has been clicked");
  }

  public void clickWifiSettingsOption() {
    clickMobileElement(WIFI_SETTINGS_OPTIONS);
    logInfo("WIFI settings option has been clicked");
  }

  public void enterInputInWifiSettings(String input) {
    sendKeysToMobileElement(WIFI_SETTINS_INPUT_FIELD, input);
    logInfo("WIFI settings input field has been clicked");
  }

  public void clickOnOkButtonOnWifiSettings() {
    BUTTONS.get(1).click();
    logInfo("Ok button has been clicked successfully");
  }

  public void clickOnCancelButtonOnWifiSettings() {
    BUTTONS.get(0).click();
    logInfo("Cancel button has been clicked on successfully");
  }
}
