package com.build.qa.build.selenium.pageobjects.navigationbar;

import org.openqa.selenium.WebDriver;
import seleniumcore.SeleniumActions;
import tools.Tools;

public class NavigationBar {
    public static void searchNavigationBar(WebDriver driver, String searchTerm){
        String elementPath = "//input[@data-automation='search-txt']";
        SeleniumActions.waitForElementPath(driver, elementPath, 5);
        SeleniumActions.writeToElementPath(driver, elementPath, searchTerm);
        SeleniumActions.sendEnterToElementPath(driver, elementPath);
    }

}
