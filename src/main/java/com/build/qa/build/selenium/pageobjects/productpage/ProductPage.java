package com.build.qa.build.selenium.pageobjects.productpage;

import org.openqa.selenium.WebDriver;
import seleniumcore.SeleniumActions;
import tools.Tools;


public class ProductPage {

    public static String fetchProductText(WebDriver driver){

        String headingPath = "//h1[@data-automation='heading']/span[1]";
        String descriptionPath = "//h1[@data-automation='heading']/span[2]";

        SeleniumActions.waitForElementPath(driver, headingPath, 5);
        String titleText = SeleniumActions.readWebElementText(driver, headingPath);

        String descriptionText = SeleniumActions.readWebElementText(driver, descriptionPath);
        return titleText + " " + descriptionText;
    }

    public static void addProductToCart(WebDriver driver) {
        String cartPath = "//button[@data-automation='add-to-cart']";
        SeleniumActions.waitForElementPath(driver, cartPath, 5);
        SeleniumActions.clickElement(driver, cartPath);
        Tools.sleepForTime(1000);
    }

}
