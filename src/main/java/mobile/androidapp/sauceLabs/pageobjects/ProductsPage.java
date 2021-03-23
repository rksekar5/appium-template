package mobile.androidapp.sauceLabs.pageobjects;

import com.diconium.qa.testautomationframework.common.Logger;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobile.utils.AndroidUtils;
import mobile.utils.Waiters;
import org.openqa.selenium.support.PageFactory;

import static mobile.driverhandler.AppFactory.appiumDriver;
import static mobile.driverhandler.AppFactory.getAppiumDriver;
import static mobile.utils.MobileUtils.clickMobileElement;

public class ProductsPage {
    public ProductsPage() {
        PageFactory.initElements(new AppiumFieldDecorator(getAppiumDriver()), this);
    }

    private final AndroidUtils androidUtils = new AndroidUtils();

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Toggle\"]/android.widget.ImageView")
    public MobileElement FIRST_PRODUCT_ON_THE_LIST;
    @AndroidFindBy(accessibility = "test-Modal Selector Button")
    public MobileElement PRODUCTS_FILTER;
    @AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"Selector container\"]/android.view.ViewGroup/android.view.ViewGroup[4]/android.view.ViewGroup")
    public MobileElement PRODUCTS_FILTER_PRICE_LOW_TO_HIGH;
    @AndroidFindBy(accessibility = "test-Menu")
    public MobileElement LEFT_PANEL_LIST;

    public void clickOnProductListView() {
        Waiters.waitUntilMobileElementVisible(FIRST_PRODUCT_ON_THE_LIST);
        clickMobileElement(FIRST_PRODUCT_ON_THE_LIST);
        Logger.logInfo("Product list view has been clicked");
    }

    public void clickOnProductsFilter() {
        Waiters.waitUntilMobileElementVisible(PRODUCTS_FILTER);
        clickMobileElement(PRODUCTS_FILTER);
        Logger.logInfo("Product filter has been clicked");

    }

    public void sortTheItemsOnProductListViewBasedOnPrice() {
        clickOnProductsFilter();
        Waiters.waitUntilMobileElementVisible(PRODUCTS_FILTER_PRICE_LOW_TO_HIGH);
        clickMobileElement(PRODUCTS_FILTER_PRICE_LOW_TO_HIGH);
        Logger.logInfo("The products have been sorted based on the price");
    }

    public void clickOnLeftPanelList(){
        Waiters.waitUntilMobileElementVisible(LEFT_PANEL_LIST);
        clickMobileElement(LEFT_PANEL_LIST);
        Logger.logInfo("Clicked on the Left panel list");
    }


}
