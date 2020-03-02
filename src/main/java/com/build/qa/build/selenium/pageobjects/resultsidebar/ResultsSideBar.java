package com.build.qa.build.selenium.pageobjects.resultsidebar;

import org.openqa.selenium.WebDriver;
import seleniumcore.SeleniumActions;
import tools.Tools;


public class ResultsSideBar {

    public static boolean defineSingleHoleFaucet(WebDriver driver){

        String colorPath = "//li[@data-groupname='Colors']";
        String themePath = "//li[@data-groupname='Theme']";

        String chromePath = "//label[@data-facet-value='Chromes']/input";
        String modernPath = "//label[@data-facet-value='Modern']/input";

        String modernNumberPath = "//label[@data-facet-value='Modern']/span[2]";

        String resultsPath = "//span[@data-automation='results-total']/span";

        SeleniumActions.waitForElementPath(driver, colorPath, 5);
        SeleniumActions.clickElement(driver, colorPath);
        SeleniumActions.waitForElementPath(driver, chromePath, 5);
        SeleniumActions.clickElement(driver, chromePath);

        SeleniumActions.clickElement(driver, themePath);
        SeleniumActions.waitForElementPath(driver, modernPath, 5);
        SeleniumActions.clickElement(driver, modernPath);

        Tools.sleepForTime(1000);

        String numberText = SeleniumActions.readWebElementText(driver, modernNumberPath);

        String resultsText = SeleniumActions.readWebElementText(driver, resultsPath);

        boolean resultsEqual = false;
        if (numberText != null && resultsText != null){
            String updatedNumberString = Tools.removeCharacter(numberText, "(");
            String finalNumberString = Tools.removeCharacter(updatedNumberString, ")");

            resultsEqual = finalNumberString.equals(resultsText);
        }

        return resultsEqual;
    }


}
