package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.CartPage;
import pages.LoginPage;
import utils.BaseTest;
import annotations.TestCaseId;

public class CartTest extends BaseTest {

    @Test
    @TestCaseId("TC-08")
    public void agregarMultiplesProductosAlCarrito() {
        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");

        InventoryPage inventory = new InventoryPage(driver);
        String prod1 = "Sauce Labs Backpack";
        String prod2 = "Sauce Labs Bike Light";
        inventory.addProductToCart(prod1);
        inventory.addProductToCart(prod2);

        inventory.openCart();

        CartPage cart = new CartPage(driver);
        Assert.assertTrue(cart.isProductInCart(prod1), "Producto 1 debería estar en el carrito");
        Assert.assertTrue(cart.isProductInCart(prod2), "Producto 2 debería estar en el carrito");
    }

    @Test
    @TestCaseId("TC-09")
    public void removerProductoDelCarrito() {
        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");

        InventoryPage inventory = new InventoryPage(driver);
        String producto = "Sauce Labs Backpack";
        inventory.addProductToCart(producto);
        inventory.openCart();

        CartPage cart = new CartPage(driver);
        Assert.assertTrue(cart.isProductInCart(producto), "Producto debería estar en el carrito");

        cart.removeProduct(producto);

        Assert.assertFalse(cart.isProductInCart(producto), "Producto debería haber sido removido del carrito");
    }


    // Este test va a fallar intencionalmente correspondiendo con lo pedido en el punto 4
    @Test
    @TestCaseId("TC-10")
    public void contadorProductosEnIconoDelCarrito() {
        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");

        InventoryPage inventory = new InventoryPage(driver);
        String prod1 = "Sauce Labs Backpack";
        String prod2 = "Sauce Labs Bike Light";
        inventory.addProductToCart(prod1);
        inventory.addProductToCart(prod2);

        String contador = driver.findElement(org.openqa.selenium.By.className("shopping_cart_badge")).getText();

        int cantidad = Integer.parseInt(contador);
        // Intencional: esperamos 3 cuando hay 2 productos agregados
        Assert.assertEquals(cantidad, 3, "Este test está hecho para fallar: contador esperado 3, real " + cantidad);
    }
}