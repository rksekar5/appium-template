package mobile.androidapp.apidemo;

import static com.diconium.qa.testautomationframework.common.Logger.logInfo;
import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import lombok.SneakyThrows;
import mobile.androidapp.api_demos_app.page_objects.ControlsPage;
import mobile.androidapp.api_demos_app.page_objects.DateWidgetPage;
import mobile.androidapp.api_demos_app.page_objects.DependenciesPage;
import mobile.androidapp.api_demos_app.page_objects.DialogPage;
import mobile.androidapp.api_demos_app.page_objects.DragAndDropPage;
import mobile.androidapp.api_demos_app.page_objects.HomePage;
import mobile.androidapp.api_demos_app.page_objects.LightThemePage;
import mobile.androidapp.api_demos_app.page_objects.PreferencesPage;
import mobile.androidapp.api_demos_app.page_objects.ViewsPage;
import mobile.androidapp.common.AndroidBaseTest;
import mobile.androidapp.common.AndroidUtilities;
import mobile.androidapp.common.TestData;
import mobile.utils.MobileUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ApiDemoTestAndroid extends AndroidBaseTest {

  private HomePage homePage;
  private PreferencesPage preferencesPage;
  private DependenciesPage dependenciesPage;
  private ViewsPage viewsPage;
  private DragAndDropPage dragAndDropPage;
  private ControlsPage controlsPage;
  private LightThemePage lightThemePage;
  private AndroidUtilities androidUtilities;
  private DateWidgetPage dateWidgetPage;
  private DialogPage dialogPage;

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

    androidUtilities = new AndroidUtilities(androidDriver);
  }

  @Test(dataProvider = "InputData", dataProviderClass = TestData.class)
  public void clickAndSendTextTest(String input){
    homePage.clickOnPreference();

    preferencesPage.clickOnDependencies();

    dependenciesPage.clickWifiCheckbox();
    dependenciesPage.clickWifiSettingsOption();
    dependenciesPage.enterInputInWifiSettings(input);
    dependenciesPage.clickOnOkButtonOnWifiSettings();
  }

  @SneakyThrows
  @Test
  public void dragAndDropTest() {
    homePage.clickOnView();

    viewsPage.clickOnDragAndDrop();
    sleep(2000);

    dragAndDropPage.dragAndDropCircles();
  }

  @SneakyThrows
  @Test
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

    androidUtilities.hideAndroidKeyboard();

    lightThemePage.turnOnToggle1();
    lightThemePage.verifyToggle2IsTurnedOff();

    lightThemePage.selectSpinnerDropdown();
    lightThemePage.selectPlanetFromList("Venus");

    androidUtilities.scrollToText("(And all inside of a ScrollView!)");
  }

  @SneakyThrows
  @Test
  public void changeScreenOrientationTest() {
    MobileUtils.switchScreenToLandscape();
    sleep(2000);
    MobileUtils.switchScreenToPortrait();
    sleep(2000);
  }

  @Test
  public void deviceLocationTest(){
    androidUtilities.getDeviceLocation();
    androidUtilities.setDeviceLocation(49, 123, 10);
  }

  @SneakyThrows
  @Test
  public void swipeUpAndDownTest(){
    homePage.clickOnView();

    viewsPage.clickOnControls();

    controlsPage.clickLightTheme();

    androidUtilities.hideAndroidKeyboard();

    androidUtilities.swipeDown();
    sleep(2000);

    androidUtilities.swipeUp();
    sleep(2000);

  }

  @SneakyThrows
  @Test
  public void datePickerTest(){
    homePage.clickOnView();

    viewsPage.clickOnDateWidgets();

    dateWidgetPage.clickOnDialog();

    dialogPage.clickOnPickDate();
    sleep(2000);
    dialogPage.clickCancelOnDatePicker();

    dialogPage.clickOnPickDate();
    sleep(2000);
    logInfo(String
        .format("Current date from the header is %s %s", dialogPage.getDateFromDatePicker(),
            dialogPage.getYearFromDatePicker()));

    dialogPage.selectPreviousMonth();
    dialogPage.selectNextMonth();

    dialogPage.selectDateOntheDatePicker(15);
    dialogPage.clickOkOnDatePicker();

    assertEquals(dialogPage.extractDateFromString(), 15);


  }

  @SneakyThrows
  @Test
  public void timePickerTest(){
    homePage.clickOnView();

    viewsPage.clickOnDateWidgets();

    dateWidgetPage.clickOnDialog();
    dialogPage.clickOnPickTime();
    sleep(2000);
  }

  public void pressAndMoveTest(){
    homePage.clickOnView();

    viewsPage.clickOnDateWidgets();

    dateWidgetPage.clickOnInline();

  }






  // Create tests for the following
  // Views -> Drag and Drop
  // Views -> Date widget -> Dialog (Date picker)
  // Views -> Date widget -> Inline (long press and move)
  // Views -> controls -> Light theme (Checkbox, radio button and dropdown)
  // Content -> Storage -> External Storage (tap and check enable or disable)
  // app -> Alert dialogs (Alert handling)
  // app -> Menu -> Inflate (Inflate and deflate context menu)
  // app -> Alarm -> Alarm controller (Capture Toast message)
  // SMS testing scenario
  // Push notification scenario



}
