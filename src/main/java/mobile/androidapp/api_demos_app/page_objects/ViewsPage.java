package mobile.androidapp.api_demos_app.page_objects;

import static mobile.androidapp.common.AndroidFactory.androidDriver;
import static mobile.utils.MobileUtils.clickMobileElement;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobile.androidapp.common.AndroidUtilities;
import org.openqa.selenium.support.PageFactory;

public class ViewsPage {

  public ViewsPage() {
    PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
  }

  private final AndroidUtilities androidUtilities = new AndroidUtilities(androidDriver);

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Drag and Drop']")
  public MobileElement DRAG_AND_DROP;

  @AndroidFindBy(accessibility = "Controls")
  public MobileElement CONTROLS;

  @AndroidFindBy(accessibility = "Date Widgets")
  public MobileElement DATE_WIDGETS;

  public void clickOnDragAndDrop(){
    clickMobileElement(DRAG_AND_DROP);
  }

  public void clickOnControls(){
    clickMobileElement(CONTROLS);
  }

  public void clickOnDateWidgets(){
    clickMobileElement(DATE_WIDGETS);
  }

}
