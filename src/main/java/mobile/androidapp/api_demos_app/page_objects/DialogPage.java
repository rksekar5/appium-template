package mobile.androidapp.api_demos_app.page_objects;

import static com.diconium.qa.testautomationframework.common.Logger.logInfo;
import static mobile.androidapp.common.AndroidFactory.androidDriver;
import static mobile.utils.MobileUtils.clickMobileElement;
import static mobile.utils.MobileUtils.waitUntilElementVisible;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobile.androidapp.common.AndroidUtilities;
import org.openqa.selenium.support.PageFactory;

public class DialogPage {

  public DialogPage() {
    PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
  }

  @AndroidFindBy(id = "io.appium.android.apis:id/pickDate")
  public MobileElement PICK_DATE;

  @AndroidFindBy(id = "io.appium.android.apis:id/pickTime")
  public MobileElement PICK_TIME;

  @AndroidFindBy(id = "android:id/datePicker")
  public MobileElement DATE_PICKER;

  @AndroidFindBy(id = "android:id/timePicker")
  public MobileElement TIME_PICKER;


  private final AndroidUtilities androidUtilities = new AndroidUtilities(androidDriver);


  public void clickOnPickDate() {
    logInfo("Click on Pick date");
    clickMobileElement(PICK_DATE);
    waitUntilElementVisible(DATE_PICKER);
    logInfo("Date picker displayed as expected");
  }

  public void clickOnPickTime() {
    logInfo("Click on Pick time");
    clickMobileElement(PICK_TIME);
    waitUntilElementVisible(TIME_PICKER);
    logInfo("Time picker displayed as expected");
  }




}
