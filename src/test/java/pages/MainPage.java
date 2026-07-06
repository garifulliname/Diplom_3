package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static api.ApiClient.BASE_URL;

public class MainPage extends BasePage {
    private final By loginButtonOnMainLocator = By.cssSelector(
            "button.button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_large__G21Vg");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем главную страницу сайта")
    public void open() {
        driver.get(BASE_URL);
        new WebDriverWait(driver, Duration.ofSeconds(45))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButtonOnMainLocator));
    }

    @Step("Кликаем «Войти в аккаунт» на главной")
    public void clickLoginButtonOnMain() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(loginButtonOnMainLocator));
        btn.click();
    }

    @Step("Кликаем «Личный кабинет» в шапке")
    public void clickPersonalCabinet() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By locator = By.xpath("//a[normalize-space()='Личный Кабинет']");

        System.out.println(">>> Ждём ссылку «Личный кабинет»...");
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(locator));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        System.out.println(">>> Клик по «Личный кабинет» выполнен.");
    }

    @Step("Ожидаем, что заголовок «Соберите бургер» виден")
    public void verifyPublicPage() {
        wait.until(driver -> {
            try {
                WebElement h1 = driver.findElement(By.xpath("//h1[normalize-space()='Соберите бургер']"));
                return h1.isDisplayed();
            } catch (NoSuchElementException e) {
                return false;
            }
        });
    }
}