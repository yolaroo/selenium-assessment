package com.build.qa.build.selenium.tests;

import com.build.qa.build.selenium.pageobjects.checkout.CheckoutPage;
import com.build.qa.build.selenium.pageobjects.navigation.Navigation;
import com.build.qa.build.selenium.pageobjects.navigationbar.NavigationBar;
import com.build.qa.build.selenium.pageobjects.popups.Popups;
import com.build.qa.build.selenium.pageobjects.productpage.ProductPage;
import com.build.qa.build.selenium.pageobjects.resultsidebar.ResultsSideBar;
import com.build.qa.build.selenium.pageobjects.searchpage.SearchPage;
import com.build.qa.build.selenium.pageobjects.sinkspage.SinksPage;
import org.junit.Test;

import com.build.qa.build.selenium.framework.BaseFramework;
import com.build.qa.build.selenium.pageobjects.homepage.HomePage;
import org.openqa.selenium.WebDriver;
import tools.Tools;

public class BuildTest extends BaseFramework {

	/**
	 * Extremely basic test that outlines some basic
	 * functionality and page objects as well as assertJ
	 */
	//@Test
	public void navigateToHomePage() {
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);

		softly.assertThat(homePage.onBuildTheme())
			.as("The website should load up with the Build.com desktop theme.")
			.isTrue();
	}

	/**
	 * Search for the Quoizel MY1613 from the search bar
	 * @assert: That the product page we land on is what is expected by checking the product title
	 * @difficulty Easy
	 */
	@Test
	public void searchForProductLandsOnCorrectProduct() {
		Navigation.navigateToHomePage(driver);
		Popups.closePopups(driver);

		String productName = "Quoizel MY1613";
		Tools.log("Searching for product");
 		NavigationBar.searchNavigationBar(driver, productName);

		SearchPage.selectFirstSearchResult(driver);

		Tools.log("Fetching product text");
		String actualProductText = ProductPage.fetchProductText(driver);
		String expectedProductHeadingText = "Quoizel";
		String expectedProductDescriptionText = "Monterey Mosaic 3 Light 16\" Wide Flush Mount Ceiling Fixture with Pen Shell Mosaic Shade";
		String expectedCompoundText = expectedProductHeadingText + " " + expectedProductDescriptionText;

		Tools.log(actualProductText);
		Tools.log(expectedCompoundText);
		softly.assertThat(actualProductText.equals(expectedCompoundText))
				.isTrue();
		Tools.log("Test Finished");

	}

	/**
	 * Go to the Bathroom Sinks category directly (https://www.build.com/bathroom-sinks/c108504)
	 * and add the second product on the search results (Category Drop) page to the cart.
	 * @assert: the product that is added to the cart is what is expected
	 * @difficulty Easy-Medium
	 */
	@Test
	public void addProductToCartFromCategoryDrop() {
		String pageURL = "https://www.build.com/bathroom-sinks/c108504";
		Navigation.navigateToPage(driver, pageURL);

		Popups.closePopups(driver);

		Tools.log("selecting product");
		SinksPage.selectSecondProduct(driver);

		String productPageText = ProductPage.fetchProductText(driver);
		Tools.log(productPageText);

		ProductPage.addProductToCart(driver);

		String checkoutPageText =  CheckoutPage.fetchProductText(driver);
		Tools.log(productPageText);

		softly.assertThat(productPageText.equals(checkoutPageText))
				.isTrue();
		Tools.log("Test Finished");

	}

	/**
	 * Add a product to the cart and email the cart to yourself, also to my email address: test.automation+SeleniumTest@build.com
	 * Include this message in the "message field" of the email form: "This is {yourName}, sending you a cart from my automation!"
	 * @assert that the "Cart Sent" success message is displayed after emailing the cart
	 * @difficulty Medium-Hard
	 */
	@Test
	public void addProductToCartAndEmailIt() {
		String pageURL = "https://www.build.com/bathroom-sinks/c108504";
		Navigation.navigateToPage(driver, pageURL);

		Popups.closePopups(driver);

		Tools.log("selecting product");
		SinksPage.selectSecondProduct(driver);

		Tools.log("adding to cart");
		ProductPage.addProductToCart(driver);

		Tools.log("opening email");
		CheckoutPage.selectEmail(driver);

		boolean emailHasSent = CheckoutPage.fillEmailForm(driver);

		softly.assertThat(emailHasSent)
				.isTrue();

		Tools.log("Test Finished");

	}

	/**
	 * Go to a category drop page (such as Bathroom Faucets) and narrow by
	 * at least two filters (facets), e.g: Finish=Chromes and Theme=Modern
	 * @assert that the correct filters are being narrowed, and the result count
	 * is correct, such that each facet selection is narrowing the product count.
	 * @difficulty Hard
	 */
	@Test
	public void facetNarrowBysResultInCorrectProductCounts() {
		String pageURL = "https://www.build.com/single-hole-sink-faucet/c109954";
		Navigation.navigateToPage(driver, pageURL);

		Popups.closePopups(driver);

		boolean resultsEqual = ResultsSideBar.defineSingleHoleFaucet(driver);

		softly.assertThat(resultsEqual)
				.isTrue();

		Tools.log("Test Finished");

	}
}
