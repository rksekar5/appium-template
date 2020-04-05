package mobile.androidapp.api_demos_app.page_objects;

import static mobile.utils.mobileUtils.clickMobileElement;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class PreferencesPage {

  public PreferencesPage(AndroidDriver<MobileElement> driver) {
    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
  }

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='3. Preference dependencies']")
  public MobileElement DEPENDENCIES;


  public void clickOnDependencies(){
    clickMobileElement(DEPENDENCIES);
  }

}
