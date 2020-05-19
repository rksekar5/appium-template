package mobile.iosapp.testapp;

import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import mobile.iosapp.common.IosBaseTest;
import mobile.iosapp.test_app.page_objects.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestAppTestIos extends IosBaseTest {

  private HomePage homePage;

  @SneakyThrows
  @BeforeMethod
  public void setup(){

    appiumDriver = iosCapabilities("ios_demo_app");
    appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    homePage = new HomePage();
  }

  @Test
  public void testSendKeysToInput () {

    // Check that it doesn"t have a value
    String value = homePage.getTextFieldValue();
    Assert.assertNull(value);

    // Send keys to that input
    homePage.enterValueInTextField("Hello World!");

    // Check that the input has new value
    value = homePage.getTextFieldValue();
    Assert.assertEquals(value, "Hello World!");
  }

  @Test
  public void testOpenAndCloseAlert () {

    // Find Button element and click on it
    homePage.clickOnShowAlertButton();

    // Check the text
    String alertTitle = homePage.getTitleFromAlertBox();
    Assert.assertEquals(alertTitle, "Cool title");

    // Dismiss the alert
    homePage.clickOnOkButtonOnAlert();

    homePage.clickOnShowAlertButton();
    homePage.clickOnCancelButtonOnAlert();
  }

  // add test to press and drag slider

  // Tap on and off the switch button

  // Add sample ios to perform below actions:
    // add test for date picker
    // select from picker list
    // add test to read toast message

  // Change screen orientation


}
