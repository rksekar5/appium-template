package mobile.androidapp.apidemos.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.SneakyThrows;
import mobile.utils.MobileUtils;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;
import static mobile.driverhandler.AndroidFactory.appiumDriver;

public class DragAndDropPage {

  public DragAndDropPage() {
    PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
  }

  @AndroidFindBy(xpath = "//android.view.View[@index=1]")
  public MobileElement DOT_1;

  @AndroidFindBy(xpath = "//android.view.View[@index=2]")
  public MobileElement DOT_2;

  @AndroidFindBy(xpath = "//android.view.View[@index=4]")
  public MobileElement DOT_3;

  @AndroidFindBy(xpath = "//android.view.View[@index=5]")
  public MobileElement DOT_4;


  @SneakyThrows
  public void dragAndDropCircles() {
    MobileUtils.dragAndDrop(DOT_1,DOT_3);
    sleep(2000);
    MobileUtils.dragAndDrop(DOT_3,DOT_4);
    sleep(2000);
  }

}
