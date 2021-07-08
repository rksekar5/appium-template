package mobile.androidapp.androidnativedemo;

import lombok.SneakyThrows;
import mobile.androidapp.apidemos.pageobjects.*;
import mobile.androidapp.common.AndroidBaseTest;
import mobile.androidapp.common.TestData;
import mobile.utils.AndroidUtils;
import mobile.utils.MobileUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static java.lang.Thread.sleep;
import static mobile.common.Logger.logInfo;
import static org.testng.Assert.assertEquals;

/** The API demo tests can only be executed for Android platform version 8 and 9 */
public class ApiDemoTestAndroid extends AndroidBaseTest {

  private HomePage homePage;
  private PreferencesPage preferencesPage;
  private DependenciesPage dependenciesPage;
  private ViewsPage viewsPage;
  private DragAndDropPage dragAndDropPage;
  private ControlsPage controlsPage;
  private LightThemePage lightThemePage;
  private DateWidgetPage dateWidgetPage;
  private DialogPage dialogPage;

  private AndroidUtils androidUtils;

  @BeforeMethod
  public void setup() {
    homePage = new HomePage();
    preferencesPage = new PreferencesPage();
    dependenciesPage = new DependenciesPage();
    viewsPage = new ViewsPage();
    dragAndDropPage = new DragAndDropPage();
    controlsPage = new ControlsPage();
    lightThemePage = new LightThemePage();
    dateWidgetPage = new DateWidgetPage();
    dialogPage = new DialogPage();

    androidUtils = new AndroidUtils();
  }

  @Test(dataProvider = "InputData", dataProviderClass = TestData.class)
  @Severity(SeverityLevel.NORMAL)
  @Description("This is a sample test to perform click and send text actions")
  public void clickAndSendTextTest(String input) {
    homePage.clickOnPreference();

    preferencesPage.clickOnDependencies();

    dependenciesPage.clickWifiCheckbox();
    dependenciesPage.clickWifiSettingsOption();
    dependenciesPage.enterInputInWifiSettings(input);
    dependenciesPage.clickOnOkButtonOnWifiSettings();
  }

  @SneakyThrows
  @Test
  @Severity(SeverityLevel.NORMAL)
  @Description("This is a sample test to perform drag and drop actions")
  public void dragAndDropTest() {
    homePage.clickOnView();

    viewsPage.clickOnDragAndDrop();
    sleep(2000);

    dragAndDropPage.dragAndDropCircles();
  }

  @SneakyThrows
  @Test
  @Severity(SeverityLevel.CRITICAL)
  @Description("This is a sample test to perform various user interactions")
  public void inputSelectionTest() {
    homePage.clickOnView();

    viewsPage.clickOnControls();

    controlsPage.clickLightTheme();
    sleep(2000);

    lightThemePage.checkOnCheckbox1();
    lightThemePage.checkOnCheckbox2();
    lightThemePage.uncheckCheckbox1();

    lightThemePage.selectRadioButton1();
    lightThemePage.selectRadioButton2();

    lightThemePage.turnOnStar();
    lightThemePage.turnOffStar();
    lightThemePage.turnOnStar();

    androidUtils.hideAndroidKeyboard();

    lightThemePage.turnOnToggle1();
    lightThemePage.verifyToggle2IsTurnedOff();

    lightThemePage.selectSpinnerDropdown();
    lightThemePage.selectPlanetFromList("Venus");

    androidUtils.scrollToText("(And all inside of a ScrollView!)");
  }

  @SneakyThrows
  @Test
  @Severity(SeverityLevel.MINOR)
  @Description("This is a sample test to switch screen orientation")
  public void changeScreenOrientationTest() {
    MobileUtils.switchScreenToLandscape();
    sleep(2000);
    MobileUtils.switchScreenToPortrait();
    sleep(2000);
  }

  @Test
  @Severity(SeverityLevel.NORMAL)
  @Description("This is a sample test to get and set geo location for the device")
  public void deviceLocationTest() {
    MobileUtils.getDeviceLocation();
    MobileUtils.setDeviceLocation(49, 123, 10);
  }

  @SneakyThrows
  @Test
  @Severity(SeverityLevel.CRITICAL)
  @Description("This is a sample test to perform swipe up and down actions on the device")
  public void swipeUpAndDownTest() {
    homePage.clickOnView();

    viewsPage.clickOnControls();

    controlsPage.clickLightTheme();

    androidUtils.hideAndroidKeyboard();

    MobileUtils.swipeDown();
    sleep(2000);

    MobileUtils.swipeUp();
    sleep(2000);
  }

  @SneakyThrows
  @Test
  @Severity(SeverityLevel.BLOCKER)
  @Description("This is a sample test to set date for the device via date picker")
  public void datePickerTest() {
    homePage.clickOnView();

    viewsPage.clickOnDateWidgets();

    dateWidgetPage.clickOnDialog();

    dialogPage.clickOnPickDate();
    sleep(2000);
    dialogPage.clickCancelOnDatePicker();

    dialogPage.clickOnPickDate();
    sleep(2000);
    logInfo(
        String.format(
            "Current date from the header is %s %s",
            dialogPage.getDateFromDatePicker(), dialogPage.getYearFromDatePicker()));

    dialogPage.selectPreviousMonth();
    dialogPage.selectNextMonth();

    dialogPage.selectDateOnTheDatePicker(15);
    dialogPage.clickOkOnDatePicker();

    assertEquals(dialogPage.extractDateFromString(), 15);
  }

  @SneakyThrows
  @Test
  @Severity(SeverityLevel.BLOCKER)
  @Description("This is a sample test to set time for the device via time picker")
  public void timePickerTest() {
    homePage.clickOnView();

    viewsPage.clickOnDateWidgets();

    dateWidgetPage.clickOnDialog();

    dialogPage.clickOnChangeTime();
    dialogPage.clickCancelOnTimePicker();

    dialogPage.clickOnChangeTime();
    logInfo("Current time is" + dialogPage.getCurrentTimeFromTimePicker());
    dialogPage.setTimeViaClock(5, 45, "am");
    dialogPage.clickOkOnTimePicker();

    dialogPage.clickOnChangeTime();
    logInfo("Now the new time set is" + dialogPage.getCurrentTimeFromTimePicker());
    dialogPage.clickToggleMode();
    dialogPage.setTimeViaInputField(9, 19, "pm");
    dialogPage.clickOkOnTimePicker();
    dialogPage.clickOnChangeTime();
    dialogPage.clickToggleMode();
    logInfo("Now the new time set is" + dialogPage.getCurrentTimeFromTimePicker());
    dialogPage.clickCancelOnTimePicker();
  }
}
