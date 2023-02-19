package ui;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import ui.core.BaseTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class IntershopTest extends BaseTest {

    @Test
    public void checkSuccessLogin() {

        LoginPage loginPage = new LoginPage();

        loginPage.successLogin();

        Assert.assertTrue(loginPage.getHelloUserMessage().isDisplayed());
        Assert.assertTrue(loginPage.getHelloUserMessage().getText().contains("Привет uma"));

    }

    @Test
    public void checkLoginWithoutPassword() {

        LoginPage loginPage = new LoginPage();

        loginPage.loginWithoutPassword();

        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().getText().contains("Пароль обязателен."));

    }

    @Test
    public void checkLoginWithoutUsername() {

        LoginPage loginPage = new LoginPage();

        loginPage.loginWithoutUsername();

        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().getText().contains("Имя пользователя обязательно."));

    }

    @Test
    public void checkSuccessSearchGood() {

        LoginPage loginPage = new LoginPage();
        CatalogPage catalogPage = new CatalogPage();

        loginPage.successLogin().searchGood();

        Assert.assertTrue(catalogPage.getGoodsList().stream().allMatch(WebElement::isDisplayed));

        List<String> goodListText = catalogPage.getGoodsList().stream().map(WebElement::getText).toList();

        Assert.assertTrue(goodListText.toString().contains("Холодильник"));

    }

    @Test
    public void checkAddGoodToBasketWithAuth() {

        LoginPage loginPage = new LoginPage();
        BasketPage basketPage = new BasketPage();

        loginPage.successLogin()
                .searchGood()
                .addGoodToBasket();

        Assert.assertTrue(basketPage.getFormInBasket().isDisplayed());

    }

    @Test
    public void checkAddGoodToBasketWithoutAuth() {

        LoginPage loginPage = new LoginPage();
        BasketPage basketPage = new BasketPage();

        loginPage.searchGood().addGoodToBasket();

        Assert.assertTrue(basketPage.getBasketIsEmptyMessage().isDisplayed());
        Assert.assertTrue(basketPage.getBasketIsEmptyMessage().getText().contains("Корзина пуста"));

    }
}
