package mobile.androidapp.api_demos_app.page_objects;

import static com.diconium.qa.testautomationframework.common.Logger.logInfo;
import static mobile.androidapp.common.AndroidFactory.androidDriver;
import static mobile.utils.MobileUtils.clickMobileElement;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.SneakyThrows;
import mobile.androidapp.common.AndroidUtilities;
import org.openqa.selenium.support.PageFactory;

public class ControlsPage {

  public ControlsPage() {
    PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
  }

  @AndroidFindBy(accessibility = "1. Light Theme")
  public MobileElement LIGHT_THEME;

  @AndroidFindBy(accessibility = "2. Dark Theme")
  public MobileElement DARK_THEME;


  private final AndroidUtilities androidUtilities = new AndroidUtilities(androidDriver);


  public void clickLightTheme() {
    logInfo("Click on Light theme");
    clickMobileElement(LIGHT_THEME);
  }

  public void clickDarkTheme() {
    logInfo("Click on Dark theme");
    clickMobileElement(DARK_THEME);
  }

}
