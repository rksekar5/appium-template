package mobile.androidapp.api_demos_app.page_objects;

import static mobile.androidapp.common.AndroidUtilities.clickMobileElement;
import static mobile.androidapp.common.AndroidUtilities.sendKeysToMobileElement;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import java.util.List;
import org.openqa.selenium.support.PageFactory;

public class DependencyPage {

  public DependencyPage(AndroidDriver<MobileElement> driver) {
    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
  }

  @AndroidFindBy(id = "android:id/checkbox")
  public MobileElement wifiCheckBox;

  @AndroidFindBy(xpath = "(//android.widget.RelativeLayout)[2]")
  public MobileElement wifiSettingsOption;

  @AndroidFindBy(className = "android.widget.EditText")
  public MobileElement wifiSettingsInputField;


  public void clickWifiCheckbox(){
    clickMobileElement(wifiCheckBox);
  }

  public void clickWifiSettingsOption(){
    clickMobileElement(wifiSettingsOption);
  }

  public void enterInputInWifiSettings(String input){
    sendKeysToMobileElement(wifiSettingsInputField,input);
  }

}
