package seleniumcore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class SeleniumDriver {

    private static final String SEPARATOR = java.nio.file.FileSystems.getDefault().getSeparator();
    private static final String USR_DIRECTORY = System.getProperty("user.dir");
    private static final String OS_NAME = System.getProperty("os.name");

    //================================================================================
    //Chrome
    //================================================================================

    public static WebDriver fetchChromeWebDriver() {
        System.setProperty("webdriver.chrome.driver", directoryChromeDriver());
        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(35, TimeUnit.SECONDS);
        return driver;
    }

    public static WebDriver fetchFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", directoryFirefoxDriver());
        FirefoxOptions options = new FirefoxOptions();
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
