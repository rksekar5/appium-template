package mobile.androidapp.apidemos.pageobjects;

import com.diconium.qa.testautomationframework.common.Logger;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.SneakyThrows;
import mobile.utils.AndroidUtils;
import mobile.utils.MobileUtils;
import org.openqa.selenium.support.PageFactory;

import static com.diconium.qa.testautomationframework.common.Logger.logInfo;
import static java.lang.Thread.sleep;
import static mobile.driverhandler.AndroidFactory.appiumDriver;
import static mobile.utils.MobileUtils.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class LightThemePage {

  public LightThemePage() {
    PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
  }

  @AndroidFindBy(id = "io.appium.android.apis:id/edit")
  public MobileElement TEXT_FIELD;

  @AndroidFindBy(accessibility = "Checkbox 1")
  public MobileElement CHECKBOX_1;

  @AndroidFindBy(accessibility = "Checkbox 2")
  public MobileElement CHECKBOX_2;

  @AndroidFindBy(id = "io.appium.android.apis:id/radio1")
  public MobileElement RADIO_BUTTON_1;

  @AndroidFindBy(id = "io.appium.android.apis:id/radio2")
  public MobileElement RADIO_BUTTON_2;

  @AndroidFindBy(id = "io.appium.android.apis:id/star")
  public MobileElement STAR_BUTTON;

  @AndroidFindBy(id = "io.appium.android.apis:id/toggle1")
  public MobileElement TOGGLE_1;

  @AndroidFindBy(id = "io.appium.android.apis:id/toggle2")
  public MobileElement TOGGLE_2;

  @AndroidFindBy(id = "io.appium.android.apis:id/spinner1")
  public MobileElement SPINNER_DROPDOWN;

  @AndroidFindBy(id = "android:id/select_dialog_listview")
  public MobileElement DROPDOWN_LIST_BOX;

  @AndroidFindBy(id = "android:id/text1")
  public MobileElement SPINNER_DROPDOWN_TEXT;

  @AndroidFindBy(accessibility = "(And all inside of a ScrollView!)")
  public MobileElement SCROLL_VIEW;

  private final AndroidUtils androidUtils = new AndroidUtils();

  public void sendTextToInputField(String text) {
    sendKeysToMobileElement(TEXT_FIELD, text);
    Logger.logInfo("Text has been entered on the input field");
  }

  public void checkOnCheckbox1() {
    MobileUtils.checkOnCheckbox(CHECKBOX_1);
    Logger.logInfo("Checkbox 1 has been clicked");
  }

  public void uncheckCheckbox1() {
    MobileUtils.uncheckOnCheckbox(CHECKBOX_1);
    Logger.logInfo("Checkbox 1 has been unchecked");
  }

  public void checkOnCheckbox2() {
    MobileUtils.checkOnCheckbox(CHECKBOX_2);
    Logger.logInfo("Checkbox 2 has been checked");
  }

  public void selectRadioButton1() {
    MobileUtils.clickMobileElement(RADIO_BUTTON_1);
    Logger.logInfo("Radio button 1 has been selected");
  }

  public void selectRadioButton2() {
    MobileUtils.clickMobileElement(RADIO_BUTTON_2);
    Logger.logInfo("Radio button 1 has been selected");
  }

  public void turnOnStar() {
    MobileUtils.checkOnCheckbox(STAR_BUTTON);
    Logger.logInfo("Star button has been turned on");
  }

  public void turnOffStar() {
    MobileUtils.uncheckOnCheckbox(STAR_BUTTON);
    Logger.logInfo("Star button has been turned off");
  }

  public void turnOnToggle1() {
    MobileUtils.checkOnCheckbox(TOGGLE_1);
    Logger.logInfo("Toggle 1 has been turned on");
  }

  public void verifyToggle2IsTurnedOff() {
    assertFalse(isCheckboxChecked(TOGGLE_2));
    logInfo("Toggle 2 is turned off as expected");
  }

  public void selectSpinnerDropdown() {
    clickMobileElement(SPINNER_DROPDOWN);
    Logger.logInfo("Spinner dropdown has been selected");
  }

  @SneakyThrows
  public void selectPlanetFromList(String planetName) {
    MobileElement planetToBeSelected =
        appiumDriver.findElementByXPath(
            "//android.widget.CheckedTextView[@text='" + planetName + "']");
    clickMobileElement(planetToBeSelected);
    logInfo("Clicked on the " + planetName + " successfully");
    sleep(3000);
    assertEquals(getTextFromMobileElement(SPINNER_DROPDOWN_TEXT).trim(), planetName);
  }
}
