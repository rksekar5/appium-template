package mobile.androidapp.apidemos.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobile.common.Logger;
import org.openqa.selenium.support.PageFactory;

import static mobile.driverhandler.AndroidFactory.appiumDriver;
import static mobile.utils.MobileUtils.clickMobileElement;

public class HomePage {

  public HomePage() {
    PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
  }

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Preference']")
  public MobileElement PREFERENCE;

  @AndroidFindBy(accessibility = "Views")
  public MobileElement VIEW;

  public void clickOnPreference() {
    clickMobileElement(PREFERENCE);
    Logger.logInfo("Preference has been clicked");
  }

  public void clickOnView() {
    clickMobileElement(VIEW);
    Logger.logInfo("View has been clicked");
  }
}
