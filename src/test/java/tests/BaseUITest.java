package tests;

import api.ApiClient;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
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

        Response response = ApiClient.registerUser(testEmail, testPassword, testName);
        int status = response.statusCode();

        if (!(status == HttpStatus.SC_OK || status == HttpStatus.SC_CREATED)) {
            throw new RuntimeException(
                    "Не удалось создать тестового пользователя: ожидались статусы 200 (OK) или 201 (Created), получен " + status
            );
        }

        Response loginResponse = ApiClient.loginUser(testEmail, testPassword);
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