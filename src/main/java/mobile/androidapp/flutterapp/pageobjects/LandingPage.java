package mobile.androidapp.flutterapp.pageobjects;

import static mobile.androidapp.common.AndroidFactory.androidDriver;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobile.androidapp.common.AndroidUtilities;
import mobile.utils.MobileUtils;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

  public LandingPage() {
    PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
  }

  @AndroidFindBy(xpath = "//android.view.View[@text='Counter App Home Page']")
  public MobileElement PAGE_HEADER;

  @AndroidFindBy(xpath = "//android.view.View[@text='counter_tooltip']")
  public MobileElement TOOL_TIP_COUNTER;

  @AndroidFindBy(xpath = "//android.widget.Button[@text='Increment']")
  public MobileElement INCREMENT;

  @AndroidFindBy(xpath = "//android.widget.Button[@text='StimulateHostCardKey']")
  public MobileElement STIMULATE_HOST_CARD_BUTTON;

  private AndroidUtilities androidUtilities = new AndroidUtilities(androidDriver);


  public void clickOnStumilateHostCard(){
    MobileUtils.clickMobileElement(STIMULATE_HOST_CARD_BUTTON);
  }

}
