package base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;
import java.time.Duration;

/**
 * BasePage class
 * ----------------
 * This class provides all reusable Selenium interactions (click, sendKeys, waits, etc.)
 * Every page object will extend this class to avoid code duplication (DRY principle).
 */
public class BasePage {
    protected WebDriver driver;
    private final WebDriverWait wait;
    protected Actions actions;

    /** Constructor now gets the driver from the factor */
    public BasePage() {
        this.driver = WebDriverFactory.getDriver();
        int waitTime = Integer.parseInt(ConfigReader.getProperty("explicitWaitTimeout"));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        this.actions = new Actions(driver);
    }

    /**
     * Clicks on a given WebElement after waiting for it to be clickable.
     */
    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /**
     * Types text into a field after ensuring it's visible.
     */
    protected void type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Returns the visible text from an element.
     */
    protected String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    /**
     * Checks if an element is displayed.
     */
    public void isDisplayed(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException ignored) {
        }
    }

    public void moveToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        actions.moveToElement(element).perform();
    }

    /**
     * Scrolls the page until the specified element is visible.
     */
    public void scrollToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollAndClick(By locator) {
        scrollToElement(locator);
        click(locator);
    }

    public void scrollAndType(By locator, String text) {
        scrollToElement(locator);
        type(locator, text);
    }

    public void selectByText(By locator, String text) {
        WebElement dropdownElement = wait.until(ExpectedConditions.elementToBeClickable(locator));
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(text);
    }


    public void acceptCookies(By firstLocator, By secondLocator){
        wait.until(ExpectedConditions.elementToBeClickable(firstLocator)).click();
        wait.until(ExpectedConditions.elementToBeClickable(secondLocator)).click();
    }
}