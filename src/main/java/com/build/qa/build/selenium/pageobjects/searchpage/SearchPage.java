package com.build.qa.build.selenium.pageobjects.searchpage;

import org.openqa.selenium.WebDriver;
import seleniumcore.SeleniumActions;



public class SearchPage {

        public static void selectFirstSearchResult(WebDriver driver) {

            String searchResultsTitle ="//span[@class='relative'][contains(.,'Search Results')]";
            SeleniumActions.waitForElementPath(driver, searchResultsTitle, 10);

            String firstResult = "//div[@id='category-product-drop']/div[1]/div[1]/div[1]/div[1]/a";
            SeleniumActions.clickElement(driver, firstResult);

        }

}
