package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.RegistrationPage;
import utils.TestDataGenerator;

import static org.junit.Assert.assertTrue;

@DisplayName("Тесты страницы регистрации")
public class RegistrationUITests extends BaseUITest {

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    @Description("Регистрирует нового пользователя с уникальным email и валидным паролем, проверяет, что страница не показывает ошибку.")
    public void testSuccessfulRegistration() {
        RegistrationPage page = new RegistrationPage(driver);
        page.open();

        String name = TestDataGenerator.generateName();
        String email = TestDataGenerator.generateUniqueEmail();
        String password = TestDataGenerator.generateValidPassword();

        page.fillForm(name, email, password);
        page.clickRegisterButton();

        boolean errorVisible = page.isPasswordErrorVisible();
        assertTrue("При успешной регистрации не должно быть ошибки «Некорректный пароль»", !errorVisible);
    }

    @Test
    @DisplayName("Ошибка при регистрации с паролем короче 6 символов")
    @Description("Проверяет, что при вводе пароля из 5 символов отображается сообщение об ошибке «Некорректный пароль».")
    public void testRegistrationWithShortPasswordShowsError() {
        RegistrationPage page = new RegistrationPage(driver);
        page.open();

        String name = testName;
        String email = testEmail;
        String shortPassword = TestDataGenerator.generateShortPassword();

        page.fillForm(name, email, shortPassword);
        page.clickRegisterButton();

        boolean errorVisible = page.isPasswordErrorVisible();
        assertTrue("Должно отображаться сообщение «Некорректный пароль» при коротком пароле", errorVisible);
    }
}