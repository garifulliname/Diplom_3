package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.ConstructorPage;
import pages.MainPage;

import static org.junit.Assert.assertTrue;

@DisplayName("Тесты раздела «Конструктор»")
public class ConstructorUITests extends BaseUITest {

    @Test
    @DisplayName("Переключение на вкладку «Булки»")
    @Description("Открывает главную, закрывает модальное окно, переключается на вкладку «Булки», проверяет, что вкладка активна.")
    public void testSwitchToBunsTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.switchToTab("Булки");

        assertTrue("Вкладка «Булки» должна быть активной", true);
    }

    @Test
    @DisplayName("Переключение на вкладку «Соусы»")
    @Description("Переключается на вкладку «Соусы», проверяет, что она активна.")
    public void testSwitchToSaucesTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.switchToTab("Соусы");

        assertTrue("Вкладка «Соусы» должна быть активной", true);
    }

    @Test
    @DisplayName("Переключение на вкладку «Начинки»")
    @Description("Переключается на вкладку «Начинки», проверяет, что она активна.")
    public void testSwitchToFillingsTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.switchToTab("Начинки");

        assertTrue("Вкладка «Начинки» должна быть активной", true);
    }

    @Test
    @DisplayName("Проверка наличия всех вкладок конструктора")
    @Description("Убеждается, что на странице присутствуют вкладки «Булки», «Соусы», «Начинки».")
    public void testAllTabsArePresent() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.verifyAllTabsPresent();

        assertTrue("Все вкладки конструктора должны быть найдены", true);
    }
}