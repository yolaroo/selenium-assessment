package com.build.qa.build.selenium.pageobjects.checkout;

import org.openqa.selenium.WebDriver;
import seleniumcore.SeleniumActions;
import tools.Tools;

public class CheckoutPage {

    public static String fetchProductText(WebDriver driver){
        String headingPath = "//a[@data-automation='cart-item-description']/span[1]";
        String descriptionPath = "//a[@data-automation='cart-item-description']/span[2]";

        SeleniumActions.waitForElementPath(driver, headingPath, 5);
        String titleText = SeleniumActions.readWebElementText(driver, headingPath);

        String descriptionText = SeleniumActions.readWebElementText(driver, descriptionPath);
        return titleText + " " + descriptionText;
    }

    public static void selectEmail(WebDriver driver){
        String toolsPath = "//div[@data-automation='cart-tools-dropdown']";
        String toolsPath2 = "//div[@data-automation='cart-tools-dropdown']/div";
        String toolsPath3 = "//div[@data-automation='cart-tools-dropdown']/div/div";
        String emailSelectionPath = "//li[@data-automation='email-cart-button']";
        SeleniumActions.waitForElementPath(driver, toolsPath, 5);
        SeleniumActions.clickElement(driver, toolsPath);
        SeleniumActions.clickElement(driver, toolsPath2);
        SeleniumActions.clickElement(driver, toolsPath3);
        Tools.sleepForTime(1000);
        SeleniumActions.waitForElementPath(driver, emailSelectionPath, 5);
        SeleniumActions.clickElement(driver, emailSelectionPath);
    }

    public static boolean fillEmailForm(WebDriver driver){
        Tools.log("Filling out email form");
        String yourNamePath = "//input[@data-automation='your-name-field']";
        String yourEmailPath = "//input[@data-automation='your-email-field']";
        String recipientNamePath = "//input[@data-automation='recipient-name-field']";
        String recipientEmailPath = "//input[@data-automation='recipient-email-field']";
        String messagePath = "//textarea[@id='quoteMessage']";
        String emailButtonPath = "//button[@data-automation='confirm-email-cart-button']";
        String successPath = "//li[@class='theme-success list pt f4-ns ma0 tc']";

        String uuid = Tools.createUUID();
        String yourName = "yourname " + uuid;
        String yourEmail = "youremail"+uuid+"@gmail.com";
        String recipientName = "othername " + uuid;
        String recipientEmail = "test.automation+SeleniumTest@build.com";
        //String recipientEmail = "test"+uuid+"@gmail.com";
        String messageText = "This is "+yourName+", sending you a cart from my automation!";

        SeleniumActions.waitForElementPath(driver, yourNamePath, 5);

        SeleniumActions.writeToElementPath(driver, yourNamePath, yourName);
        SeleniumActions.writeToElementPath(driver, yourEmailPath, yourEmail);
        SeleniumActions.writeToElementPath(driver, recipientNamePath, recipientName);
        SeleniumActions.writeToElementPath(driver, recipientEmailPath, recipientEmail);
        SeleniumActions.writeToElementPath(driver, messagePath, messageText);
        Tools.sleepForTime(1000);
        SeleniumActions.clickElement(driver, emailButtonPath);

        Tools.sleepForTime(300);
        return SeleniumActions.elementIsVisible(driver, successPath);

    }


}
