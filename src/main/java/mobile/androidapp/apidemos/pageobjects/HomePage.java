package mobile.androidapp.apidemos.pageobjects;

import static mobile.androidapp.common.AndroidFactory.appiumDriver;
import static mobile.utils.MobileUtils.clickMobileElement;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;


public class HomePage {

  public HomePage() {
    PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
  }

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Preference']")
  public MobileElement PREFERENCE;

  @AndroidFindBy(accessibility = "Views")
  public MobileElement VIEW;


  public void clickOnPreference(){
    clickMobileElement(PREFERENCE);
  }

  public void clickOnView(){
    clickMobileElement(VIEW);
  }

}
