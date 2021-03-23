package mobile.iosapp.test_app.page_objects;

import com.diconium.qa.testautomationframework.common.Logger;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import mobile.utils.MobileUtils;
import mobile.utils.Waiters;
import org.openqa.selenium.support.PageFactory;

import static mobile.driverhandler.AppFactory.appiumDriver;
import static mobile.driverhandler.AppFactory.getAppiumDriver;

public class SL_ShoppingCartPage {
    public SL_ShoppingCartPage() {
        PageFactory.initElements(new AppiumFieldDecorator(getAppiumDriver()), this);
    }

    @iOSXCUITFindBy(accessibility = "test-CONTINUE SHOPPING")
    public MobileElement SL_CONTINUE_SHOPPING_BUTTON;
    @iOSXCUITFindBy(accessibility = "test-CHECKOUT")
    public MobileElement SL_CHECKOUT_BUTTON;
    @iOSXCUITFindBy(accessibility = "test-First Name")
    public MobileElement SL_FIRST_NAME_TEXT_BOX;
    @iOSXCUITFindBy(accessibility = "test-Last Name")
    public MobileElement SL_LAST_NAME_TEXT_BOX;
    @iOSXCUITFindBy(accessibility = "test-Zip/Postal Code")
    public MobileElement SL_POSTAL_CODE;
    @iOSXCUITFindBy(accessibility = "test-CONTINUE")
    public MobileElement SL_CART_CONTINUE_BUTTON;

    public void clickOnTheCheckoutButton() {
        Waiters.waitUntilMobileElementVisible(SL_CONTINUE_SHOPPING_BUTTON);
        MobileUtils.clickMobileElement(SL_CHECKOUT_BUTTON);
        Logger.logInfo("Clicked on the checkout button and moving to the next screen to enter user details");
    }

    public void enterUserDetails(String firstName, String lastName, String postalCode){
        Waiters.waitUntilMobileElementVisible(SL_FIRST_NAME_TEXT_BOX);
        SL_FIRST_NAME_TEXT_BOX.sendKeys(firstName);
        SL_LAST_NAME_TEXT_BOX.sendKeys(lastName);
        SL_POSTAL_CODE.sendKeys(postalCode);
        appiumDriver.hideKeyboard();
        SL_CART_CONTINUE_BUTTON.click();
        Logger.logInfo("User detail has been entered and proceeding to the checkout page");
    }

}
