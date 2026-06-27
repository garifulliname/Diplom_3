package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StellarBurgersPage {
    public final WebDriver driver;
    private final WebDriverWait wait;

    // Локатор для модального окна
    private final By modalOverlayLocator = By.cssSelector("[class*='Modal_modal_overlay'], [class*='modal-overlay']");

    public StellarBurgersPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(45));
    }

    @Step("Открываем главную страницу")
    public void openHome() {
        driver.get("https://stellarburgers.education-services.ru/");
    }

    @Step("Открываем страницу регистрации")
    public void openRegistration() {
        driver.get("https://stellarburgers.education-services.ru/register");
    }

    @Step("Закрываем модальное окно, если оно есть")
    public void closeModalIfPresent() {
        try {
            WebElement modal = driver.findElement(modalOverlayLocator);
            if (modal.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", modal);
                Thread.sleep(500);
            }
        } catch (Exception ignored) {
        }
    }

    @Step("Переключаемся на вкладку конструктора по тексту: {tabText}")
    public void switchToTabByText(String tabText) {
        closeModalIfPresent();

        String xpath = "//span[normalize-space()='" + tabText + "']";

        WebElement tabLabel = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tabLabel);

        wait.until(driver -> {
            List<WebElement> labels = driver.findElements(
                    By.xpath("//span[normalize-space()='Булки' or normalize-space()='Соусы' or normalize-space()='Начинки']")
            );
            return !labels.isEmpty() && labels.stream().anyMatch(WebElement::isDisplayed);
        });
    }

    private WebElement findInputByLabelText(String labelText) {
        String js = "return Array.from(document.querySelectorAll('label.input__placeholder'))" +
                ".find(l => l.innerText.trim() === '" + labelText + "')" +
                ".nextElementSibling;";
        return (WebElement) ((JavascriptExecutor) driver).executeScript(js);
    }

    @Step("Заполняем форму регистрации: name={name}, email={email}, password={password}")
    public void fillRegistrationForm(String name, String email, String password) {
        closeModalIfPresent();

        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(findInputByLabelText("Имя")));
        clearAndSendKeys(nameField, name);

        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(findInputByLabelText("Email")));
        clearAndSendKeys(emailField, email);

        WebElement passField = wait.until(ExpectedConditions.elementToBeClickable(findInputByLabelText("Пароль")));
        clearAndSendKeys(passField, password);
    }

    @Step("Нажимаем кнопку «Зарегистрироваться»")
    public void clickRegisterButton() {
        closeModalIfPresent();
        By btnLocator = By.cssSelector("button.button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_medium__3zxIa");
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(btnLocator));
        btn.click();
    }

    @Step("Проверяем, видно ли сообщение об ошибке «Некорректный пароль»")
    public boolean isPasswordErrorVisible() {
        closeModalIfPresent();
        By errorLocator = By.cssSelector("p.input__error.text_type_main-default");
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator));
            return error.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Кликаем ссылку «Войти» (на странице регистрации)")
    public void clickLoginLink() {
        closeModalIfPresent();
        By locator = By.cssSelector("a.Auth_link__1fOlj");
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(locator));
        link.click();
    }

    @Step("Выполняем вход: email={email}, password={password}")
    public void loginUser(String email, String password) {
        closeModalIfPresent();

        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(findInputByLabelText("Email")));
        clearAndSendKeys(emailField, email);

        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='password']")));
        clearAndSendKeys(passwordField, password);

        By loginButton = By.cssSelector("button[type='submit'], button.button_button__33qZ0");
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        btn.click();
    }

    @Step("Кликаем «Войти в аккаунт» на главной")
    public void clickLoginButtonOnMain() {
        closeModalIfPresent();
        By locator = By.cssSelector("button.button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_large__G21Vg");
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(locator));
        btn.click();
    }

    @Step("Кликаем «Личный кабинет» в шапке")
    public void clickPersonalCabinet() {
        closeModalIfPresent();
        By locator = By.cssSelector("a.AppHeader_header__link__3D_hX");
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(locator));
        link.click();
    }

    @Step("Проверяем успешный вход (заголовок «Личный кабинет»)")
    public void verifyLoggedIn() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[normalize-space()='Личный кабинет']")
        ));
    }

    private void clearAndSendKeys(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }
}