package mobile.androidapp.apidemos.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.SneakyThrows;
import mobile.utils.AndroidUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;

import static com.diconium.qa.testautomationframework.common.Logger.logError;
import static com.diconium.qa.testautomationframework.common.Logger.logInfo;
import static java.lang.Thread.sleep;
import static mobile.driverhandler.AndroidFactory.appiumDriver;
import static mobile.utils.MobileUtils.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DialogPage {

  public DialogPage() {
    PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
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
  public MobileElement PICKER_OK;

  @AndroidFindBy(id = "android:id/button2")
  public MobileElement PICKER_CANCEL;

  @AndroidFindBy(id = "android:id/hours")
  public MobileElement TIME_HOUR;

  @AndroidFindBy(id = "android:id/minutes")
  public MobileElement TIME_MINUTES;

  @AndroidFindBy(id = "android:id/pm_label")
  public MobileElement PM_LABEL;

  @AndroidFindBy(id = "android:id/am_label")
  public MobileElement AM_LABEL;

  @AndroidFindBy(id = "android:id/toggle_mode")
  public MobileElement TOGGLE_BUTTON;

  @AndroidFindBy(id = "android:id/input_hour")
  public MobileElement HOUR_INPUT;

  @AndroidFindBy(id = "android:id/input_minute")
  public MobileElement MINUTE_INPUT;

  @AndroidFindBy(id = "android:id/am_pm_spinner")
  public MobileElement AM_PM_SPINNER;

  @AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='AM']")
  public MobileElement AM_DROPDOWN;

  @AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='PM']")
  public MobileElement PM_DROPDOWN;


  private final AndroidUtils androidUtils = new AndroidUtils();


  public void clickOnPickDate() {
    logInfo("Click on Pick date");
    clickMobileElement(CHANGE_DATE);
//    waitUntilMobileElementVisible(DATE_PICKER);
    logInfo("Date picker displayed as expected");
  }

  public void clickOnChangeTime() {
    logInfo("Click on Pick time");
    clickMobileElement(CHANGE_TIME);
//    waitUntilMobileElementVisible(TIME_PICKER);
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

  public void selectDateOnTheDatePicker(int date){
    try{
      MobileElement mobileElement = appiumDriver
          .findElementByXPath("//android.view.View[@text='"+date+"']");
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
    clickMobileElement(PICKER_OK);
    sleep(2000);
//    waitUntilMobileElementVisible(CHANGE_DATE);
    logInfo("Ok button on the date picker clicked successfully");
  }

  @SneakyThrows
  public void clickCancelOnDatePicker(){
    clickMobileElement(PICKER_CANCEL);
    sleep(2000);
//    waitUntilMobileElementVisible(CHANGE_DATE);
    logInfo("Cancel button on the date picker clicked successfully");
  }

  public int getHours(){
    return Integer.parseInt(getTextFromMobileElement(TIME_HOUR));
  }

  public int getMinutes(){
    return Integer.parseInt(getTextFromMobileElement(TIME_MINUTES));
  }

  public String getAmOrPm(){
    if(isCheckboxChecked(AM_LABEL)) {
      return getTextFromMobileElement(AM_LABEL);
    } else{
      return getTextFromMobileElement(PM_LABEL);
    }
  }

  public String getCurrentTimeFromTimePicker(){
    final String currentTime = getHours()+getMinutes()+getAmOrPm();
    logInfo("The current time is "+currentTime);
    return currentTime;
  }

  public void setHoursOnTimePicker(int hours){
    clickMobileElement(TIME_HOUR);
    final MobileElement hourElement = androidUtils.getMobileElementWithAccessibilityId(String.valueOf(hours));
    clickMobileElement(hourElement);
    assertEquals(hours, getHours());
    logInfo("Hours set on the clock successfully");
  }

  public void setMinutesOnTimePicker(int minutes){
    clickMobileElement(TIME_MINUTES);
    final MobileElement minutesElement = androidUtils.getMobileElementWithAccessibilityId(String.valueOf(minutes));
    clickMobileElement(minutesElement);
    assertEquals(minutes, getMinutes());
    logInfo("Minutes set on the clock successfully");
  }

  private void selectAmOrPmFromClock(String AmOrPm){
    if(Objects.equals(AmOrPm.toLowerCase(), "am")){
      clickMobileElement(AM_LABEL);
      logInfo("AM is selected");
    }else{
      clickMobileElement(PM_LABEL);
      logInfo("PM is selected");
    }
  }

  @SneakyThrows
  public void clickOkOnTimePicker(){
    clickMobileElement(PICKER_OK);
    sleep(2000);
//    waitUntilMobileElementVisible(CHANGE_TIME);
    logInfo("Ok button on the time picker clicked successfully");
  }

  @SneakyThrows
  public void clickCancelOnTimePicker(){
    clickMobileElement(PICKER_CANCEL);
    sleep(2000);
//    waitUntilMobileElementVisible(CHANGE_TIME);
    logInfo("Cancel button on the time picker clicked successfully");
  }

  public void clickToggleMode(){
    clickMobileElement(TOGGLE_BUTTON);
  }

  private void setHourInInputField(int hours){
    sendKeysToMobileElement(HOUR_INPUT, String.valueOf(hours));
  }

  private void setMinutesInInputField(int minutes){
    sendKeysToMobileElement(MINUTE_INPUT, String.valueOf(minutes));
  }

  private void selectAmOrPmFromDropdown(String AmOrPm){
    clickMobileElement(AM_PM_SPINNER);
    if(Objects.equals(AmOrPm.toLowerCase(), "am")){
      clickMobileElement(AM_DROPDOWN);
      logInfo("AM is selected");
    }else{
      clickMobileElement(PM_DROPDOWN);
      logInfo("PM is selected");
    }
  }

  public void setTimeViaInputField(int hours,int minutes, String AmOrPm){
    setHourInInputField(hours);
    setMinutesInInputField(minutes);
    selectAmOrPmFromDropdown(AmOrPm);
  }

  public void setTimeViaClock(int hours,int minutes, String AmOrPm){
    setHoursOnTimePicker(hours);
    setMinutesOnTimePicker(minutes);
    selectAmOrPmFromClock(AmOrPm);
  }

}
