package tests;

import api.ApiClient;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import utils.BrowserFactory;
import utils.TestDataGenerator;

public abstract class BaseUITest {

    protected WebDriver driver;
    protected String testEmail;
    protected String testName;
    protected String testPassword;
    protected String accessToken;
    protected String refreshToken;

    @Before
    public void setUp() {
        String browserName = System.getProperty("browser", "chrome");
        driver = BrowserFactory.createDriver(browserName);

        testName = TestDataGenerator.generateName();
        testEmail = TestDataGenerator.generateUniqueEmail();
        testPassword = TestDataGenerator.generateValidPassword();

        var response = ApiClient.registerUser(testEmail, testPassword, testName);
        if (response.statusCode() != 200 && response.statusCode() != 201) {
            throw new RuntimeException("Не удалось создать тестового пользователя. Статус: " + response.statusCode());
        }

        var loginResponse = ApiClient.loginUser(testEmail, testPassword);
        accessToken = loginResponse.jsonPath().getString("accessToken").replace("Bearer ", "");
        refreshToken = loginResponse.jsonPath().getString("refreshToken");
    }

    @After
    public void tearDown() {
        ApiClient.deleteUser(accessToken);
        if (driver != null) {
            driver.quit();
        }
    }
}