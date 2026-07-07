package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends BasePage {

    private final By nameLabelLocator = By.cssSelector("label.input__placeholder");
    private final By registerButtonLocator = By.cssSelector(
            "button.button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_medium__3zxIa");
    private final By passwordErrorLocator = By.cssSelector("p.input__error.text_type_main-default");
    private final By loginLinkLocator = By.cssSelector("a.Auth_link__1fOlj");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем страницу регистрации")
    public void open() {
        driver.get("https://stellarburgers.education-services.ru/register");
        closeModalIfPresent();
    }

    @Step("Заполняем форму регистрации: name={name}, email={email}, password={password}")
    public void fillForm(String name, String email, String password) {
        WebElement nameField = findInputByLabelText("Имя");
        clearAndSendKeys(nameField, name);

        WebElement emailField = findInputByLabelText("Email");
        clearAndSendKeys(emailField, email);

        WebElement passField = findInputByLabelText("Пароль");
        clearAndSendKeys(passField, password);
    }

    @Step("Нажимаем кнопку «Зарегистрироваться»")
    public void clickRegisterButton() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(registerButtonLocator));
        btn.click();
    }

    @Step("Кликаем ссылку «Войти» (на странице регистрации)")
    public void clickLoginLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(loginLinkLocator));
        link.click();
    }

    @Step("Проверяем, видно ли сообщение об ошибке «Некорректный пароль»")
    public boolean isPasswordErrorVisible() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordErrorLocator));
            return error.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private WebElement findInputByLabelText(String labelText) {
        String js = "return Array.from(document.querySelectorAll('label.input__placeholder'))" +
                ".find(l => l.innerText.trim() === '" + labelText + "')" +
                ".nextElementSibling;";
        return (WebElement) ((JavascriptExecutor) driver).executeScript(js);
    }
}