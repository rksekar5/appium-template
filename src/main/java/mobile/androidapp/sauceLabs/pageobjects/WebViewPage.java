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

public class WebViewPage {
    public WebViewPage() {
        PageFactory.initElements(new AppiumFieldDecorator(getAppiumDriver()), this);
    }

    private final AndroidUtils androidUtils = new AndroidUtils();

    @AndroidFindBy(accessibility = "test-enter a https url here...")
    public MobileElement WEB_VIEW_URL_TEXTBOX;
    @AndroidFindBy(accessibility = "test-GO TO SITE")
    public MobileElement WEB_VIEW_GO_TO_SITE_BUTTON;

    public void enterWebsiteURLOnTheTextBox(String url) {
        Waiters.waitUntilMobileElementVisible(WEB_VIEW_URL_TEXTBOX);
        clickMobileElement(WEB_VIEW_URL_TEXTBOX);
        WEB_VIEW_URL_TEXTBOX.sendKeys(url);
        Logger.logInfo("The website url has been entered");
    }

    public void launchTheWebsiteOnTheApp() {
        clickMobileElement(WEB_VIEW_GO_TO_SITE_BUTTON);
        Logger.logInfo("The website has been launched from the app");

    }
}
