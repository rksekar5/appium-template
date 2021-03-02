package mobile.iosapp.testapp;

import com.diconium.qa.testautomationframework.common.Logger;
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
  public void setup() {
    homePage = new HomePage();
  }

  @Test
  public void testSendKeysToInput() {

    String value = homePage.getTextFieldValue();
    Assert.assertNull(value);

    homePage.enterValueInTextField("Hello World!");

    value = homePage.getTextFieldValue();
    Assert.assertEquals(value, "Hello World!");
    Logger.logInfo("The entered value is : " +value);
  }

  @Test
  public void testOpenAndCloseAlert() {

    homePage.clickOnShowAlertButton();
    String alertTitle = homePage.getTitleFromAlertBox();
    Assert.assertEquals(alertTitle, "Cool title");
    homePage.clickOnOkButtonOnAlert();
    homePage.clickOnShowAlertButton();
    homePage.clickOnCancelButtonOnAlert();
  }
}
