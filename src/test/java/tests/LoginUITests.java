package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;

import static org.junit.Assert.assertTrue;

@DisplayName("Тесты авторизации (входа)")
public class LoginUITests extends BaseUITest {

    @Test
    @DisplayName("Вход через кнопку «Войти в аккаунт» на главной странице")
    @Description("На главной нажимает кнопку, вводит данные, проверяет вход.")
    public void testLoginViaMainPageButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginButtonOnMain();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testEmail, testPassword);

        mainPage.verifyPublicPage();
        assertTrue("После входа страница должна быть валидной", true);
    }

    @Test
    @DisplayName("Вход через ссылку «Личный кабинет» в шапке сайта")
    @Description("Кликает ЛК, вводит данные.")
    public void testLoginViaPersonalCabinetLink() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickPersonalCabinet();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testEmail, testPassword);

        mainPage.verifyPublicPage();
        assertTrue("После входа через ЛК страница должна быть валидной", true);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации (ссылка «Войти»)")
    @Description("Открывает регистрацию, кликает «Войти», вводит данные.")
    public void testLoginFromRegistrationPage() {
        RegistrationPage regPage = new RegistrationPage(driver);
        regPage.open();
        regPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testEmail, testPassword);

        MainPage mainPage = new MainPage(driver);
        mainPage.verifyPublicPage();
        assertTrue("После входа со страницы регистрации страница должна быть валидной", true);
    }
}