package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.InventoryPage;
import pages.CartPage;
import pages.CheckoutPage;

public class SauceDemoE2ETest extends BaseTest {

    @Test(priority = 1)
    public void verifySuccessfulLogin() {
        String username = config.getProperty("username");
        String password = config.getProperty("password");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html"),
                "Login failed: inventory page was not displayed"
        );
    }

    @Test(priority = 2)
    public void verifyAddProductToCart() {
        String username = config.getProperty("username");
        String password = config.getProperty("password");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        InventoryPage inventoryPage = new InventoryPage(driver);

        // Uses the first product's Add to cart button.
        inventoryPage.addFirstProductToCart();

        // Verify the cart badge is displayed.
        Assert.assertTrue(
                driver.findElement(
                        org.openqa.selenium.By.className("shopping_cart_badge")
                ).isDisplayed(),
                "Cart badge was not displayed"
        );
    }

    @Test(priority = 3)
    public void verifyCheckoutPage() {
        String username = config.getProperty("username");
        String password = config.getProperty("password");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addFirstProductToCart();

        driver.findElement(
                org.openqa.selenium.By.className("shopping_cart_link")
        ).click();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("cart.html"),
                "Cart page was not displayed"
        );

        driver.findElement(
                org.openqa.selenium.By.id("checkout")
        ).click();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("checkout-step-one.html"),
                "Checkout information page was not displayed"
        );
    }
}