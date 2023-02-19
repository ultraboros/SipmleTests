package ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.core.BasePage;

public class BasketPage extends BasePage {

    @FindBy(xpath = "//form[@class='woocommerce-cart-form']")
    private WebElement formInBasket;

    @FindBy(xpath = "//p[@class='cart-empty woocommerce-info']")
    private WebElement basketIsEmptyMessage;

    public BasketPage() {
        PageFactory.initElements(driver, this);
    }

    /**GETTERS*/

    public WebElement getFormInBasket() {
        return formInBasket;
    }

    public WebElement getBasketIsEmptyMessage() {
        return basketIsEmptyMessage;
    }

}
