package seleniumcore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SeleniumDriver {

    private static final String SEPARATOR = java.nio.file.FileSystems.getDefault().getSeparator();
    private static final String USR_DIRECTORY = System.getProperty("user.dir");

    //================================================================================
    //Chrome
    //================================================================================

    public static WebDriver fetchChromeWebDriver() {
        System.setProperty("webdriver.chrome.driver", directoryChromeDriver());
        System.setProperty("webdriver.chrome.silentOutput", "true");

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);

        ChromeOptions chrome_options = new ChromeOptions();
        chrome_options.setExperimentalOption("prefs", prefs);
        WebDriver driver = new ChromeDriver(chrome_options);


        //driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(35, TimeUnit.SECONDS);
        return driver;
    }

    public static WebDriver fetchFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", directoryFirefoxDriver());

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("dom.webnotifications.enabled", false);
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);

        WebDriver driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(35, TimeUnit.SECONDS);
        return driver;
    }

    public static void closeWebDriver (WebDriver driver) {
        if (driver != null) {
            try {
                driver.close();
            } catch (Exception e) {
                System.out.print("Could not close webdriver");
            }
        }
        else {
            System.out.print("Webdriver null");
        }
    }

    //================================================================================
    // Directories
    //================================================================================

    public static String directoryChromeDriver () {
        String pathBase = SEPARATOR + "src" + SEPARATOR + "main" + SEPARATOR + "resources" + SEPARATOR + "WebDrivers" + SEPARATOR;
        return USR_DIRECTORY + pathBase + "chromedriver.exe";
    }

    public static String directoryFirefoxDriver () {
        String pathBase = SEPARATOR + "src" + SEPARATOR + "main" + SEPARATOR + "resources" + SEPARATOR + "WebDrivers" + SEPARATOR;
        return USR_DIRECTORY + pathBase + "geckodriver.exe";
    }

}
