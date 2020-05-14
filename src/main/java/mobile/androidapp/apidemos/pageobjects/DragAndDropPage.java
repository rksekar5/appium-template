package mobile.androidapp.apidemos.pageobjects;

import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.lang.Thread.sleep;
import static java.time.Duration.ofSeconds;
import static mobile.androidapp.common.AndroidFactory.androidDriver;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.SneakyThrows;
import mobile.androidapp.common.AndroidUtilities;
import org.openqa.selenium.support.PageFactory;

public class DragAndDropPage {

  public DragAndDropPage() {
    PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
  }

  @AndroidFindBy(xpath = "//android.view.View[@index=1]")
  public MobileElement DOT_1;

  @AndroidFindBy(xpath = "//android.view.View[@index=2]")
  public MobileElement DOT_2;

  @AndroidFindBy(xpath = "//android.view.View[@index=4]")
  public MobileElement DOT_3;

  @AndroidFindBy(xpath = "//android.view.View[@index=5]")
  public MobileElement DOT_4;

  private final AndroidUtilities androidUtilities = new AndroidUtilities(androidDriver);

  @SneakyThrows
  public void dragAndDropCircles() {
    androidUtilities.dragAndDrop(DOT_1,DOT_3);
    sleep(3000);
    androidUtilities.dragAndDrop(DOT_3,DOT_4);
    sleep(3000);
    androidUtilities.dragAndDrop(DOT_4,DOT_1);
    sleep(3000);
  }

}
