package mobile.iosapp.testapp;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import mobile.iosapp.common.BaseTest;
import mobile.iosapp.test_app.page_objects.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAppTest extends BaseTest {

  @Test
  public void testSendKeysToInput () throws IOException {
    IOSDriver<MobileElement> driver = capabilities("ios_demo_app");
    HomePage homePage = new HomePage(driver);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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
  public void testOpenAlert () throws IOException {
    IOSDriver<MobileElement> driver = capabilities("ios_demo_app");
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    HomePage homePage = new HomePage(driver);

    // Find Button element and click on it
    homePage.clickOnShowAlertButton();

    // Check the text
    String alertTitle = homePage.getTitleFromAlertBox();
    Assert.assertEquals(alertTitle, "Cool title");

    // Dismiss the alert
    homePage.clickOnOkButtonOnAlert();
  }

  // add test to press and drag slider

  // Tap on and off the switch button

  // Add sample ios to perform below actions:
    // add test for date picker
    // select from picker list
    // add test to read toast message

}
