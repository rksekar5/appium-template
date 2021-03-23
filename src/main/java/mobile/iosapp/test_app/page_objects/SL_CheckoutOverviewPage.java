package mobile.iosapp.test_app.page_objects;

import com.diconium.qa.testautomationframework.common.Logger;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import mobile.utils.MobileUtils;
import mobile.utils.Waiters;
import org.openqa.selenium.support.PageFactory;

import static mobile.driverhandler.AppFactory.getAppiumDriver;

public class SL_CheckoutOverviewPage {
    public SL_CheckoutOverviewPage() {
        PageFactory.initElements(new AppiumFieldDecorator(getAppiumDriver()), this);
    }

    @iOSXCUITFindBy(accessibility = "Payment Information:")
    public MobileElement SL_PAYMENT_INFO;
    @iOSXCUITFindBy(accessibility = "test-FINISH")
    public MobileElement SL_FINISH_PAYMENT_BUTTON;
    @iOSXCUITFindBy(accessibility = "THANK YOU FOR YOU ORDER")
    public MobileElement SL_THANK_YOU_ORDER_TEXT;


    public void clickOnTheFinishPaymentButton() {
        Waiters.waitUntilMobileElementVisible(SL_PAYMENT_INFO);
        MobileUtils.scrollFromBottomToUpWithJavascriptExecutor();
        SL_FINISH_PAYMENT_BUTTON.click();
        Logger.logInfo("Clicked on the finish payment button");
    }

    public void verifyIfTheOrderIsSuccessful() {
        Waiters.waitUntilMobileElementVisible(SL_THANK_YOU_ORDER_TEXT);
        Logger.logInfo("Order is placed successfully");
    }

}
