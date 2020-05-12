package mobile.androidapp.api_demos_app.page_objects;

import static com.diconium.qa.testautomationframework.common.Logger.logError;
import static com.diconium.qa.testautomationframework.common.Logger.logInfo;
import static java.lang.Thread.sleep;
import static mobile.androidapp.common.AndroidFactory.androidDriver;
import static mobile.utils.MobileUtils.clickMobileElement;
import static mobile.utils.MobileUtils.getTextFromMobileElement;
import static mobile.utils.MobileUtils.isCheckboxChecked;
import static mobile.utils.MobileUtils.waitUntilMobileElementInvisible;
import static mobile.utils.MobileUtils.waitUntilMobileElementVisible;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import com.diconium.qa.testautomationframework.web.Waiters;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.SneakyThrows;
import mobile.androidapp.common.AndroidUtilities;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;

public class DialogPage {

  public DialogPage() {
    PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
  }

  @AndroidFindBy(id = "io.appium.android.apis:id/pickDate")
  public MobileElement CHANGE_DATE;

  @AndroidFindBy(id = "io.appium.android.apis:id/pickTime")
  public MobileElement CHANGE_TIME;

  @AndroidFindBy(id = "io.appium.android.apis:id/dateDisplay")
  public MobileElement DATE_ON_DISPLAY;

  @AndroidFindBy(id = "android:id/datePicker")
  public MobileElement DATE_PICKER;

  @AndroidFindBy(id = "android:id/date_picker_header_year")
  public MobileElement HEADER_YEAR;

  @AndroidFindBy(id = "android:id/date_picker_header_date")
  public MobileElement HEADER_DATE;

  @AndroidFindBy(id = "android:id/prev")
  public MobileElement PREVIOUS_MONTH;

  @AndroidFindBy(id = "android:id/next")
  public MobileElement NEXT_MONTH;

  @AndroidFindBy(id = "android:id/timePicker")
  public MobileElement TIME_PICKER;

  @AndroidFindBy(id = "android:id/button1")
  public MobileElement DATE_PICKER_OK;

  @AndroidFindBy(id = "android:id/button2")
  public MobileElement DATE_PICKER_CANCEL;


  private final AndroidUtilities androidUtilities = new AndroidUtilities(androidDriver);


  public void clickOnPickDate() {
    logInfo("Click on Pick date");
    clickMobileElement(CHANGE_DATE);
    waitUntilMobileElementVisible(DATE_PICKER);
    logInfo("Date picker displayed as expected");
  }

  public void clickOnPickTime() {
    logInfo("Click on Pick time");
    clickMobileElement(CHANGE_TIME);
    waitUntilMobileElementVisible(DATE_PICKER);
    logInfo("Time picker displayed as expected");
  }

  public String getYearFromDatePicker(){
    return getTextFromMobileElement(HEADER_YEAR);
  }

  public String getDateFromDatePicker(){
    return getTextFromMobileElement(HEADER_DATE);
  }

  public void selectPreviousMonth(){
    clickMobileElement(PREVIOUS_MONTH);
    logInfo("Previous month button clicked");
  }

  public void selectNextMonth(){
    clickMobileElement(NEXT_MONTH);
    logInfo("Next month button clicked");
  }

  public void selectDateOntheDatePicker(int date){
    try{
      MobileElement mobileElement = androidDriver.findElementByXPath("//android.view.View[@text='"+date+"']");
      clickMobileElement(mobileElement);
      assertTrue(isCheckboxChecked(mobileElement));
      logInfo(date+" is selected as date");
    } catch(Exception e){
      logError("Invalid date");
      e.printStackTrace();
    }
  }

  public String getDateFromDialogPage(){
    return getTextFromMobileElement(DATE_ON_DISPLAY);
  }

  public int extractDateFromString(){
    final String date = getDateFromDialogPage();
    return Integer.parseInt(StringUtils.substringBetween(date, "-", "-"));
  }

  @SneakyThrows
  public void clickOkOnDatePicker(){
    clickMobileElement(DATE_PICKER_OK);
    sleep(2000);
    waitUntilMobileElementInvisible("//*[@id='android:id/datePicker']");
    logInfo("Ok button on the date picker clicked successfully");
  }

  @SneakyThrows
  public void clickCancelOnDatePicker(){
    clickMobileElement(DATE_PICKER_CANCEL);
    sleep(2000);
    waitUntilMobileElementInvisible("//*[@id='android:id/datePicker']");
    logInfo("Cancel button on the date picker clicked successfully");
  }



}
