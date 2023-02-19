package ui;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import ui.core.BasePage;

public class RegisterPage extends BasePage {

    private By userNameField = By.xpath("//input[@id='reg_username']");
    private By emailField = By.xpath("//input[@id='reg_email']");
    private By passwordField = By.xpath("//input[@id='reg_password']");
    private By regButton = By.xpath("//button[@name = 'register']");
    private By regTitle = By.xpath("post-title");
    private By successfulRegMessage = By.xpath("//div[@class = 'content-page']//div");
    private By regError = By.xpath("(//strong)[1]");

    public RegisterPage() {
        PageFactory.initElements(driver, this);
    }


    /** Getters */

    public By getRegTitle() {
        return regTitle;
    }

    public By getSuccessfulRegMessage() {
        return successfulRegMessage;
    }

    public By getRegError() {
        return regError;
    }
}
