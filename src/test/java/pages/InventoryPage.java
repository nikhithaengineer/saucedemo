package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstAddToCartButton =
            By.cssSelector(".inventory_item button");

    private final By cartBadge =
            By.className("shopping_cart_badge");

    private final By shoppingCartLink =
            By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    public void addFirstProductToCart() {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        firstAddToCartButton
                )
        ).click();
    }

    public boolean isCartBadgeDisplayed() {
        return !driver.findElements(cartBadge).isEmpty();
    }

    public void openCart() {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        shoppingCartLink
                )
        ).click();
    }
}