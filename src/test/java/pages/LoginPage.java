package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By submitButtonLocator = By.xpath("//button[normalize-space()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Step("Поиск поля Email по метке 'Email'")
    public WebElement findEmailField() {
        String xpath = "//label[contains(text(), 'Email')]/following-sibling::input[1]";
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    @Step("Поиск поля Пароль по метке 'Пароль'")
    public WebElement findPasswordField() {
        String xpath = "//label[contains(text(), 'Пароль')]/following-sibling::input[1]";
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    @Step("Ввод текста '{text}' в поле")
    private void clearAndSendKeys(WebElement element, String text) {
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