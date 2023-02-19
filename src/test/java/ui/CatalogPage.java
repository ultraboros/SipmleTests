package ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.core.BasePage;

import java.util.List;

public class CatalogPage extends BasePage {

    @FindBy(xpath = "//h1[@class='entry-title ak-container']")
    private WebElement goodsTitle;

    @FindBy(xpath = "//ul[@class='products columns-4']")
    private List <WebElement> goodsList;

    @FindBy(xpath = "//a[@data-product_id='240012']")
    private WebElement addGoodLink;

    @FindBy(xpath = "//li[@id='menu-item-29']")
    private WebElement basketOnNavMenu;

    public BasketPage addGoodToBasket() {

        addGoodLink.click();
        basketOnNavMenu.click();
        return new BasketPage();
    }

    public CatalogPage() {
        PageFactory.initElements(driver, this);
    }

    /** GETTERS */

    public WebElement getGoodsTitle() {
        return goodsTitle;
    }

    public List<WebElement> getGoodsList() {
        return goodsList;
    }

    public WebElement getAddGoodLink() {
        return addGoodLink;
    }

    public WebElement getBasketOnNavMenu() {
        return basketOnNavMenu;
    }

}
