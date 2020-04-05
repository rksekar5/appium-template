package mobile.androidapp.flutter_app.page_objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class HomePage {


  public HomePage(AndroidDriver<MobileElement> driver) {
    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
  }

  @AndroidFindBy(xpath = "//android.view.View[@text='Counter App Home Page']")
  public MobileElement PageHeader;

  @AndroidFindBy(xpath = "//android.view.View[@text='counter_tooltip']")
  public MobileElement ToolTipCounter;

  @AndroidFindBy(xpath = "//android.widget.Button[@text='Increment']")
  public MobileElement Increment;

  @AndroidFindBy(xpath = "//android.widget.Button[@text='StimulateHostCardKey']")
  public MobileElement StimulateHostCardButton;

}
