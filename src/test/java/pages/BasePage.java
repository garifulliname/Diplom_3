package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected final WebDriver driver;
    public final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Закрываем модальное окно, если оно есть")
    public void closeModalIfPresent() {
        try {
            By modalLocator = By.cssSelector("[class*='Modal_modal_overlay'], [class*='modal-overlay']");
            WebElement modal = wait.until(ExpectedConditions.presenceOfElementLocated(modalLocator));
            if (modal.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", modal);
                wait.until(ExpectedConditions.invisibilityOf(modal));
            }
        } catch (TimeoutException ignored) {}
    }

    protected void clearAndSendKeys(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }
}