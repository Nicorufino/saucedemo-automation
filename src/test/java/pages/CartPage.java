package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isProductInCart(String productName) {
        return driver.getPageSource().contains(productName);
    }

    public void removeProduct(String productName) {
        String id = "remove-" + productName.toLowerCase().replace(" ", "-");
        driver.findElement(By.id(id)).click();
    }

    public boolean isCartEmpty() {
        return driver.findElements(By.className("cart_item")).isEmpty();
    }
}
