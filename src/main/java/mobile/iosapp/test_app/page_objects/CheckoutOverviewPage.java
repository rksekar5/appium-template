package mobile.iosapp.test_app.page_objects;

import com.diconium.qa.testautomationframework.common.Logger;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import mobile.utils.MobileUtils;
import mobile.utils.Waiters;
import org.openqa.selenium.support.PageFactory;

import static mobile.driverhandler.AppFactory.getAppiumDriver;

public class CheckoutOverviewPage {
    public CheckoutOverviewPage() {
        PageFactory.initElements(new AppiumFieldDecorator(getAppiumDriver()), this);
    }

    @iOSXCUITFindBy(accessibility = "Payment Information:")
    public MobileElement PAYMENT_INFO;
    @iOSXCUITFindBy(accessibility = "test-FINISH")
    public MobileElement FINISH_PAYMENT_BUTTON;
    @iOSXCUITFindBy(accessibility = "THANK YOU FOR YOU ORDER")
    public MobileElement THANK_YOU_ORDER_TEXT;


    public void clickOnTheFinishPaymentButton() {
        Waiters.waitUntilMobileElementVisible(PAYMENT_INFO);
        MobileUtils.scrollFromBottomToUpWithJavascriptExecutor();
        FINISH_PAYMENT_BUTTON.click();
        Logger.logInfo("Clicked on the finish payment button");
    }

    public void verifyIfTheOrderIsSuccessful() {
        Waiters.waitUntilMobileElementVisible(THANK_YOU_ORDER_TEXT);
        Logger.logInfo("Order is placed successfully");
    }

}
