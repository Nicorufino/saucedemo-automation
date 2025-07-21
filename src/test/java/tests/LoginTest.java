package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.InventoryPage;
import utils.BaseTest;
import annotations.TestCaseId;

public class LoginTest extends BaseTest {

    @Test
    @TestCaseId("TC-01")
    public void loginExitoso() {
        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");

        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "https://www.saucedemo.com/inventory.html");
    }

    @Test
    @TestCaseId("TC-02")
    public void loginUsuarioInvalido() {
        LoginPage login = new LoginPage(driver);
        login.login("invalid_user", "secret_sauce");

        String error = login.getErrorMessage();
        Assert.assertTrue(error.contains("Username and password do not match"));
    }

    @Test
    @TestCaseId("TC-04")
    public void loginConCamposVacios() {
        LoginPage login = new LoginPage(driver);
        login.login("", "");

        String error = login.getErrorMessage();
        Assert.assertTrue(error.contains("Username is required"));
    }
}

