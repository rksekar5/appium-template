package mobile.iosapp.testapp;

import lombok.SneakyThrows;
import mobile.iosapp.common.IosBaseTest;
import mobile.iosapp.test_app.page_objects.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class SL_TestAppIos extends IosBaseTest {

    private SL_LoginPage sl_loginPage;
    private SL_ProductsPage sl_productsPage;
    private SL_ShoppingCartPage sl_shoppingCartPage;
    private SL_CheckoutOverviewPage sl_checkoutOverviewPage;

    @SneakyThrows
    @BeforeMethod
    public void setup() {

        sl_loginPage = new SL_LoginPage();
        sl_productsPage = new SL_ProductsPage();
        sl_shoppingCartPage = new SL_ShoppingCartPage();
        sl_checkoutOverviewPage = new SL_CheckoutOverviewPage();
    }

    @Test(priority = 1)
    @SneakyThrows
    @Severity(SeverityLevel.BLOCKER)
    public void testLoginToTheSwagLabApp() {

        sl_loginPage.enterUserName("standard_user");
        sl_loginPage.enterPassword("secret_sauce");
        sl_loginPage.clickOnLoginButton();
    }

    @Test(priority = 2)
    public void testAddingTheProductToCart(){
        sl_productsPage.verifyTheElementsOnTheProductsPage();
        sl_productsPage.clickOnTheShoppingCartMenu();
    }

    @Test(priority = 3)
    @Severity(SeverityLevel.NORMAL)
    public void testEnteringTheUserDataForCheckout(){
        sl_shoppingCartPage.clickOnTheCheckoutButton();
        sl_shoppingCartPage.enterUserDetails("diconium", "QA team", "10178");
    }

    @Test(priority = 4)
    @Severity(SeverityLevel.MINOR)
    public void testPaymentOnTheOverviewPage(){
        sl_checkoutOverviewPage.clickOnTheFinishPaymentButton();
        sl_checkoutOverviewPage.verifyIfTheOrderIsSuccessful();
    }


}
