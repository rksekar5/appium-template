package mobile.androidapp.apidemos.pageobjects;

import static com.diconium.qa.testautomationframework.common.Logger.logInfo;
import static mobile.androidapp.common.AndroidFactory.androidDriver;
import static mobile.utils.MobileUtils.clickMobileElement;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobile.androidapp.common.AndroidUtilities;
import org.openqa.selenium.support.PageFactory;

public class DateWidgetPage {

  public DateWidgetPage() {
    PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
  }

  @AndroidFindBy(accessibility = "1. Dialog")
  public MobileElement DIALOG;

  @AndroidFindBy(accessibility = "2. Inline")
  public MobileElement INLINE;


  private final AndroidUtilities androidUtilities = new AndroidUtilities(androidDriver);


  public void clickOnDialog() {
    logInfo("Click on Dialog");
    clickMobileElement(DIALOG);
  }

  public void clickOnInline() {
    logInfo("Click on Inline");
    clickMobileElement(INLINE);
  }

}
