package ui;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.core.BasePage;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@name='username']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@name='login']")
    private WebElement logInButton;

    @FindBy(xpath = "(//div[@class='woocommerce-MyAccount-content']//p)[1]")
    private WebElement helloUserMessage;

    @FindBy(xpath = "//ul[@class='woocommerce-error']//li")
    private WebElement errorMessage;

    @FindBy(xpath = "//input[@class='search-field']")
    private WebElement searchField;

    private String username = "uma";
    private String password = "qwerty123";

    public LoginPage() {
        PageFactory.initElements(driver, this);
        driver.get("http://intershop3.skillbox.ru/my-account/");
    }

    public LoginPage successLogin() {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        logInButton.click();
        return this;
    }

    public LoginPage loginWithoutPassword() {
        usernameField.sendKeys(username);
        logInButton.click();
        return this;
    }

    public LoginPage loginWithoutUsername() {
        passwordField.sendKeys(password);
        logInButton.click();
        return this;
    }

    public CatalogPage searchGood() {
        searchField.sendKeys("Холодильник", Keys.ENTER);
        return new CatalogPage();
    }

    /** GETTERS */

    public WebElement getHelloUserMessage() {
        return helloUserMessage;
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }
}
