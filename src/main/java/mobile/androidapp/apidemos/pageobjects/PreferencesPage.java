package mobile.androidapp.apidemos.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobile.common.Logger;
import org.openqa.selenium.support.PageFactory;

import static mobile.driverhandler.AndroidFactory.appiumDriver;
import static mobile.utils.MobileUtils.clickMobileElement;

public class PreferencesPage {

  public PreferencesPage() {
    PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
  }

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='3. Preference dependencies']")
  public MobileElement DEPENDENCIES;

  public void clickOnDependencies() {
    clickMobileElement(DEPENDENCIES);
    Logger.logInfo("Dependencies has been clicked");
  }
}
