package mobile.iosapp.testapp;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import mobile.iosapp.common.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAppTest extends BaseTest {

  @Test
  public void testSendKeysToInput () throws IOException {
    IOSDriver<IOSElement> driver = capabilities("ios_demo_app");
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    // Find TextField input element
    String textInputId = "TextField1";
    IOSElement textViewsEl = (IOSElement) new WebDriverWait(driver, 30)
        .until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId(textInputId)));

    // Check that it doesn"t have a value
    String value = textViewsEl.getAttribute("value");
    Assert.assertNull(value);

    // Send keys to that input
    textViewsEl.sendKeys("Hello World!");

    // Check that the input has new value
    value = textViewsEl.getAttribute("value");
    Assert.assertEquals(value, "Hello World!");
  }

  @Test
  public void testOpenAlert () throws IOException {
    IOSDriver<IOSElement> driver = capabilities("ios_demo_app");
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    // Find Button element and click on it
    String buttonElementId = "show alert";
    IOSElement buttonElement = (IOSElement) new WebDriverWait(driver, 30)
        .until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId(buttonElementId)));
    buttonElement.click();

    // Wait for the alert to show up
    String alertTitleId = "Cool title";
    IOSElement alertTitleElement = (IOSElement) new WebDriverWait(driver, 30)
        .until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId(alertTitleId)));

    // Check the text
    String alertTitle = alertTitleElement.getText();
    Assert.assertEquals(alertTitle, "Cool title");

    // Dismiss the alert
    IOSElement okButtonElement = (IOSElement) new WebDriverWait(driver, 30)
        .until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId("OK")));
    okButtonElement.click();
  }
}
