package mobile.iosapp.testapp;

import lombok.SneakyThrows;
import mobile.iosapp.common.IosBaseTest;
import mobile.iosapp.test_app.page_objects.CheckoutOverviewPage;
import mobile.iosapp.test_app.page_objects.LoginPage;
import mobile.iosapp.test_app.page_objects.ProductsPage;
import mobile.iosapp.test_app.page_objects.ShoppingCartPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class IosDemoTest extends IosBaseTest {

  private LoginPage loginPage;
  private ProductsPage productsPage;
  private ShoppingCartPage shoppingCartPage;
  private CheckoutOverviewPage checkoutOverviewPage;

  @SneakyThrows
  @BeforeMethod
  public void setup() {

    loginPage = new LoginPage();
    productsPage = new ProductsPage();
    shoppingCartPage = new ShoppingCartPage();
    checkoutOverviewPage = new CheckoutOverviewPage();
  }

  @Test(priority = 1)
  @SneakyThrows
  @Severity(SeverityLevel.BLOCKER)
  public void testLoginToTheSwagLabApp() {

    loginPage.enterUserName("standard_user");
    loginPage.enterPassword("secret_sauce");
    loginPage.clickOnLoginButton();
  }

  @Test(priority = 2)
  public void testAddingTheProductToCart() {
    productsPage.verifyTheElementsOnTheProductsPage();
    productsPage.clickOnTheFirstAvailableItem();
    productsPage.addTheItemToTheSHoppingCart();
    productsPage.clickOnTheShoppingCartMenu();
  }

  @Test(priority = 3)
  @Severity(SeverityLevel.NORMAL)
  public void testEnteringTheUserDataForCheckout() {
    shoppingCartPage.clickOnTheCheckoutButton();
    shoppingCartPage.enterUserDetails("diconium", "QA team", "10178");
    shoppingCartPage.continueToTheCheckoutPage();
  }

  @Test(priority = 4)
  @Severity(SeverityLevel.MINOR)
  public void testPaymentOnTheOverviewPage() {
    checkoutOverviewPage.clickOnTheFinishPaymentButton();
    checkoutOverviewPage.verifyIfTheOrderIsSuccessful();
  }
}
