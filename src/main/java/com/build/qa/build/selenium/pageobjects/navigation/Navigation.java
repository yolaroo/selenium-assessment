package com.build.qa.build.selenium.pageobjects.navigation;

import org.openqa.selenium.WebDriver;
import seleniumcore.SeleniumActions;


public class Navigation {

        public static void navigateToHomePage(WebDriver driver){
            String url = "https://www.build.com";
            SeleniumActions.navigateToURL(driver, url);
        }

        public static void navigateToPage(WebDriver driver, String url) {
            SeleniumActions.navigateToURL(driver, url);
        }
}
