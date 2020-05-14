package mobile.androidapp.apidemos.pageobjects;

import static mobile.androidapp.common.AndroidFactory.androidDriver;
import static mobile.utils.MobileUtils.clickMobileElement;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class PreferencesPage {

  public PreferencesPage() {
    PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
  }

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='3. Preference dependencies']")
  public MobileElement DEPENDENCIES;

  public void clickOnDependencies(){
    clickMobileElement(DEPENDENCIES);
  }

}
