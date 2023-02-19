package ui.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

abstract public class BasePage {
    protected static WebDriver driver;
    protected static Actions action;

    protected static WebDriverWait wait;

    public static WebDriver setDriver(WebDriver webDriver) {
        return driver = webDriver;
    }

    public static Actions setAction() {
        return action = new Actions(driver);
    }

    public static WebDriverWait setWaitDriver() {
        return wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

}
