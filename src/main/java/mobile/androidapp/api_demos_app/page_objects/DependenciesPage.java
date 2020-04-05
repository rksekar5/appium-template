package mobile.androidapp.api_demos_app.page_objects;

import static common.Logger.logInfo;
import static mobile.utils.mobileUtils.clickMobileElement;
import static mobile.utils.mobileUtils.sendKeysToMobileElement;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import java.util.List;
import org.openqa.selenium.support.PageFactory;

public class DependenciesPage {

  public DependenciesPage(AndroidDriver<MobileElement> driver) {
    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
  }

  @AndroidFindBy(id = "android:id/checkbox")
  public MobileElement WIFI_CHECK_BOX;

  @AndroidFindBy(xpath = "(//android.widget.RelativeLayout)[2]")
  public MobileElement WIFI_SETTINGS_OPTIONS;

  @AndroidFindBy(className = "android.widget.EditText")
  public MobileElement WIFI_SETTINS_INPUT_FIELD;

  @AndroidFindBy(className = "android.widget.Button")
  public List<MobileElement> BUTTONS;




  public void clickWifiCheckbox(){
    clickMobileElement(WIFI_CHECK_BOX);
  }

  public void clickWifiSettingsOption(){
    clickMobileElement(WIFI_SETTINGS_OPTIONS);
  }

  public void enterInputInWifiSettings(String input){
    sendKeysToMobileElement(WIFI_SETTINS_INPUT_FIELD,input);
  }

  public void clickOnOkButtonOnWifiSettings(){
    BUTTONS.get(1).click();
    logInfo("Ok button has been clicked on successfully");
  }

  public void clickOnCancelButtonOnWifiSettings(){
    BUTTONS.get(0).click();
    logInfo("Cancel button has been clicked on successfully");
  }

}
