package com.build.qa.build.selenium.pageobjects.sinkspage;

import org.openqa.selenium.WebDriver;
import seleniumcore.SeleniumActions;
import tools.Tools;

public class SinksPage {

    public static void selectSecondProduct(WebDriver driver){
        String headingPath = "//h1[@data-automation='product-search-header']";
        SeleniumActions.waitForElementPath(driver, headingPath, 5);

        String secondProductPath = "//div[@id='category-product-drop']/div[2]";
        SeleniumActions.clickElement(driver, secondProductPath);
        Tools.sleepForTime(2000);
    }



}
