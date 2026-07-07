package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    private final By emailLabelLocator = By.xpath("//label[contains(text(), 'Email')]/following-sibling::input[1]");
    private final By passwordLabelLocator = By.xpath("//label[contains(text(), 'Пароль')]/following-sibling::input[1]");
    private final By submitButtonLocator = By.xpath("//button[normalize-space()='Войти']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Поиск поля Email по метке 'Email'")
    public WebElement findEmailField() {
        return wait.until(ExpectedConditions.elementToBeClickable(emailLabelLocator));
    }

    @Step("Поиск поля Пароль по метке 'Пароль'")
    public WebElement findPasswordField() {
        return wait.until(ExpectedConditions.elementToBeClickable(passwordLabelLocator));
    }

    @Step("Ввод текста '{text}' в поле")
    public void clearAndSendKeys(WebElement element, String text) {
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    @Step("Выполнение входа: email={email}, password={password}")
    public void login(String email, String password) {
        WebElement emailField = findEmailField();
        clearAndSendKeys(emailField, email);

        WebElement passwordField = findPasswordField();
        clearAndSendKeys(passwordField, password);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(submitButtonLocator));
        submitButton.click();
    }
}