package com.build.qa.build.selenium.pageobjects.popups;

import org.openqa.selenium.WebDriver;
import seleniumcore.SeleniumActions;
import tools.Tools;


public class Popups {

    public static void closePopups(WebDriver driver){
        Tools.sleepForTime(2000);
        String bannerHeader = "//h2[contains(.,'Get a 5% Off coupon*')]";
        boolean popupIsVisible = SeleniumActions.elementIsVisible(driver, bannerHeader);

        String closeIcon = "(//span[@class='close-icon'])";
        String secondCloseIcon = "(//span[@class='close-icon'])[2]";
        String thirdCloseIcon = "//svg[contains(@class,'icon')]";

        if (popupIsVisible) SeleniumActions.clickElement(driver, closeIcon);
        Tools.sleepForTime(1000);

        popupIsVisible = SeleniumActions.elementIsVisible(driver, bannerHeader);

        if (popupIsVisible) SeleniumActions.clickElement(driver, secondCloseIcon);
        Tools.sleepForTime(1000);
        popupIsVisible = SeleniumActions.elementIsVisible(driver, bannerHeader);
        if (popupIsVisible) SeleniumActions.clickElement(driver, thirdCloseIcon);

        Tools.sleepForTime(1000);

    }

}
