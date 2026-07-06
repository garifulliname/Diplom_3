package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserFactory {

    public static WebDriver createDriver(String browserName) {
        if (browserName == null || browserName.trim().isEmpty()) {
            browserName = "chrome";
        }
        browserName = browserName.toLowerCase();

        switch (browserName) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
                ChromeOptions chromeOptions = new ChromeOptions();
                return new ChromeDriver(chromeOptions);

            case "yandex":
                System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
                ChromeOptions yandexOptions = new ChromeOptions();
                String yandexPath = System.getenv("YANDEXBROWSER_PATH");
                if (yandexPath != null) {
                    yandexOptions.setBinary(yandexPath);
                } else {
                    yandexOptions.setBinary("C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe");
                }
                return new ChromeDriver(yandexOptions);

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
    }
}