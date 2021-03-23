package mobile.androidapp.sauceLabs.pageobjects;

import com.diconium.qa.testautomationframework.common.Logger;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobile.utils.AndroidUtils;
import mobile.utils.Waiters;
import org.openqa.selenium.support.PageFactory;

import static mobile.driverhandler.AppFactory.getAppiumDriver;
import static mobile.utils.MobileUtils.clickMobileElement;

public class ListPage {
    public ListPage() {
        PageFactory.initElements(new AppiumFieldDecorator(getAppiumDriver()), this);
    }

    private final AndroidUtils androidUtils = new AndroidUtils();

    @AndroidFindBy(accessibility = "test-WEBVIEW")
    public MobileElement LIST_WEB_VIEW;
    @AndroidFindBy(accessibility = "test-QR CODE SCANNER")
    public MobileElement LIST_QR_CODE;
    @AndroidFindBy(accessibility = "test-LOGOUT")
    public MobileElement LIST_LOGOUT;

    public void clickOnListWebView() {
        Waiters.waitUntilMobileElementVisible(LIST_WEB_VIEW);
        clickMobileElement(LIST_WEB_VIEW);
        Logger.logInfo("Web view has been clicked");
    }

    public void clickOnLogoutButton() {
        Waiters.waitUntilMobileElementVisible(LIST_LOGOUT);
        clickMobileElement(LIST_LOGOUT);
        Logger.logInfo("Logout button has been clicked");
    }

}
