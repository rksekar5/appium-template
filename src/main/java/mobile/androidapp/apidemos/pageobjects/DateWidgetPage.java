package mobile.androidapp.apidemos.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobile.utils.AndroidUtilities;
import org.openqa.selenium.support.PageFactory;

import static com.diconium.qa.testautomationframework.common.Logger.logInfo;
import static mobile.driverhandler.AndroidFactory.appiumDriver;
import static mobile.utils.MobileUtils.clickMobileElement;

public class DateWidgetPage {

  public DateWidgetPage() {
    PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
  }

  @AndroidFindBy(accessibility = "1. Dialog")
  public MobileElement DIALOG;

  @AndroidFindBy(accessibility = "2. Inline")
  public MobileElement INLINE;


  private final AndroidUtilities androidUtilities = new AndroidUtilities();


  public void clickOnDialog() {
    logInfo("Click on Dialog");
    clickMobileElement(DIALOG);
  }

  public void clickOnInline() {
    logInfo("Click on Inline");
    clickMobileElement(INLINE);
  }

}
