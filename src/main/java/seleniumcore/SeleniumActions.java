package seleniumcore;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class SeleniumActions {

    private final static String CLASS_NAME = "SeleniumActions";

    //================================================================================
    // Element is Visible
    //================================================================================

    public static Boolean waitForElementPath(WebDriver driver, String elementPath, int waitTime) {
        boolean hasPassed = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            WebElement element = SeleniumActions.fetchWebElement(driver, elementPath);
            wait.until(presenceOfElementLocated((By) element));
            hasPassed = true;
        }
        catch (Exception e){
            //pass
        }
        if (!hasPassed){
            try {
                SeleniumActions.moveToElement(driver, elementPath);
                WebDriverWait wait = new WebDriverWait(driver, waitTime);
                WebElement element = SeleniumActions.fetchWebElement(driver, elementPath);
                wait.until(presenceOfElementLocated((By) element));
                hasPassed = true;
            }
            catch (Exception e){
                //pass
            }
        }
        return hasPassed;
    }

    public static Boolean elementIsVisible( WebDriver driver, String elementPath) {
        WebElement webElement = fetchWebElement(driver, elementPath);
        return webElement != null && webElement.isDisplayed();
    }

    //================================================================================
    // Click Element
    //================================================================================

    public static Boolean clickElement(WebDriver driver, String elementPath) {
        WebElement webElement = fetchWebElement(driver, elementPath);
        if (webElement != null && webElement.isDisplayed()) {
            boolean clickHasFailed = wasClickUnsuccessful(webElement);
            if (clickHasFailed) {
                moveToElement(driver, elementPath);
                boolean clickHasFailedAgain = wasClickUnsuccessful(webElement);
                if (clickHasFailedAgain) {
                    boolean hasFailedThrice = sendEnterToElementPath(driver, elementPath);
                    if (hasFailedThrice) {
                        // error
                    }
                    else {
                        // third
                        return true;
                    }
                }
                else {
                    // second
                    return true;
                }
            }
            else {
                //first
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    private static Boolean wasClickUnsuccessful(WebElement webElement) {
        try {
            webElement.click();
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    //================================================================================
    // Move to Element
    //================================================================================

    public static Boolean moveToElement (WebDriver driver, String elementPath) {
        WebElement webElement = fetchWebElement(driver, elementPath);
        if (webElement != null) {
            try {
                Actions actions = new Actions(driver);
                actions.moveToElement(webElement).perform();
                return true;
            } catch (Exception e) {
                String errorMessage = ("Error - " + CLASS_NAME + " (moveToElement) - Could not move to element");
                System.out.print(errorMessage);
                return false;
            }
        }
        else {
            String errorMessage = ("Error - " + CLASS_NAME + " (moveToElement) - Element was null");
            System.out.print(errorMessage);
            return false;
        }
    }

    //================================================================================
    // send to element
    //================================================================================

    public static Boolean sendEnterToElementPath(WebDriver driver, String elementPath) {
        WebElement webElement = fetchWebElement(driver, elementPath);
        if (webElement != null && webElement.isDisplayed()) {
            try {
                webElement.sendKeys(Keys.ENTER);
                return  false;
            }
            catch (Exception e){
                return true;
            }
        }
        else {
            String errorMessage = ("Error - "+CLASS_NAME+" (sendEnterToElement) - Element not visible ");
            System.out.print(errorMessage);
            return true;
        }
    }

    //================================================================================
    // Read Element
    //================================================================================

    public static String readWebElementText (WebDriver driver, String elementPath) {
        WebElement webElement = fetchWebElement(driver, elementPath);
        if (webElement != null && webElement.isDisplayed()) {
            try {
                return webElement.getText();
            } catch (Exception e) {
                String errorMessage = ("Error - " + CLASS_NAME + " (readWebElementText) - Could not read text : " + elementPath);
                System.out.print(errorMessage);
                return null;
            }
        }
        else {
            String errorMessage = ("Error - " + CLASS_NAME + " (readWebElementText) - Element not visible: " + elementPath);
            System.out.print(errorMessage);
            return null;
        }
    }

    //================================================================================
    // Write To Element
    //================================================================================

    public static Boolean writeToElementPath(WebDriver driver, String elementPath, String stringText) {
        WebElement webElement = fetchWebElement(driver, elementPath);
        if (webElement != null && webElement.isDisplayed()) {
            try {
                webElement.sendKeys(stringText);
                return true;
            }
            catch (Exception e) {
                String errorMessage = ("Error - "+CLASS_NAME+" (writeToElement) - Could not write to element : " + elementPath);
                System.out.print(errorMessage);
                return false;
            }
        }
        else {
            String errorMessage = ("Error - "+CLASS_NAME+" (writeToElement) - Element is hidden : " + elementPath);
            System.out.print(errorMessage);
            return false;
        }
    }

    //================================================================================
    // Navigate
    //================================================================================

    public static Boolean navigateToURL(WebDriver driver, String url) {
        try {
            driver.get(url);
            return true;
        }
        catch (Exception e) {
            String errorMessage = "Error - "+CLASS_NAME+" (navigateToURL) - Could not navigate";
            System.out.print(errorMessage);
            return false;
        }
    }

    //================================================================================
    // Find Element
    //================================================================================

    public static WebElement fetchWebElement(WebDriver driver, String elementPath) {
        if (elementPath != null && elementPath.length() > 0) {
            WebElement webElement = null;

            webElement = findByXpath(elementPath, driver);

            if (webElement != null) {
                return webElement;
            } else {
                String errorMessage = ("Error - " + CLASS_NAME + " (findElement) - No Element : " + elementPath);
                System.out.print(errorMessage);
                return null;
            }
        } else {
            String errorMessage = ("Error - " + CLASS_NAME + " (findElement) - No Path : " + elementPath);
            System.out.print(errorMessage);
            return null;
        }
    }

    //================================================================================
    // Selenium Find Methods
    //================================================================================

    public static WebElement findByXpath(String elementPath, WebDriver driver) {
        try {
            return driver.findElement(By.xpath(elementPath));
        } catch (Exception e) {
            return null;
        }
    }

}
