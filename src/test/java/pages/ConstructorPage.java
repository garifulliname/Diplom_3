package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.List;

public class ConstructorPage extends BasePage {

    private final By tabLabelLocator = By.xpath("//span[normalize-space()]");
    private final List<String> tabNames = Arrays.asList("Булки", "Соусы", "Начинки");

    public ConstructorPage(WebDriver driver) {
        super(driver);
    }

    @Step("Переключаемся на вкладку конструктора: {tabName}")
    public void switchToTab(String tabName) {
        closeModalIfPresent();

        String xpath = "//span[normalize-space()='" + tabName + "']";
        WebElement tabLabel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tabLabel);

        wait.until(driver -> {
            List<WebElement> tabs = driver.findElements(tabLabelLocator);
            return tabs.stream().anyMatch(t -> t.getText().trim().equals(tabName));
        });
    }

    @Step("Проверяем наличие всех вкладок конструктора")
    public void verifyAllTabsPresent() {
        List<WebElement> labels = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(tabLabelLocator));
        for (String name : tabNames) {
            boolean found = labels.stream().anyMatch(l -> l.getText().trim().equals(name));
            if (!found) {
                throw new AssertionError("Вкладка '" + name + "' не найдена");
            }
        }
    }
}