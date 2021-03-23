package mobile.iosapp.test_app.page_objects;

import com.diconium.qa.testautomationframework.common.Logger;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import mobile.utils.MobileUtils;
import mobile.utils.Waiters;
import org.openqa.selenium.support.PageFactory;

import static mobile.driverhandler.AppFactory.getAppiumDriver;
import static mobile.utils.MobileUtils.clickMobileElement;

public class SL_ProductsPage {

    public SL_ProductsPage() {
        PageFactory.initElements(new AppiumFieldDecorator(getAppiumDriver()), this);
    }

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"test-Item title\"])[1]")
    public MobileElement SL_BACK_PACK_LINK;
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"test-Price\"])[1]")
    public MobileElement SL_BACK_PACK_PRICE;
    @iOSXCUITFindBy(accessibility = "test-ADD TO CART")
    public MobileElement SL_ADD_TO_CART_BUTTON;
    @iOSXCUITFindBy(accessibility = "test-REMOVE")
    public MobileElement SL_REMOVE_BUTTON;
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"1\"])[4]")
    public MobileElement SL_CART;


    public void verifyTheElementsOnTheProductsPage() {
        Waiters.waitUntilMobileElementVisible(SL_BACK_PACK_LINK);
        Waiters.waitUntilMobileElementVisible(SL_BACK_PACK_PRICE);
        SL_BACK_PACK_LINK.click();
        MobileUtils.scrollFromBottomToUpWithJavascriptExecutor();
        Waiters.waitUntilMobileElementVisible(SL_ADD_TO_CART_BUTTON);
        SL_ADD_TO_CART_BUTTON.click();
        Logger.logInfo("The backpack has been added to the shopping cart");
        Waiters.waitUntilMobileElementVisible(SL_REMOVE_BUTTON);
    }

    public void clickOnTheShoppingCartMenu() {
        MobileUtils.scrollFromUpToBottomWithJavascriptExecutor();
        SL_CART.click();
        Logger.logInfo("Clicked on the shopping cart menu and moving to Shopping cart page");
    }
}

