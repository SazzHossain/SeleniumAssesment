package com.build.qa.build.selenium.tests;

import org.junit.Assert;
import org.junit.Test;
import com.build.qa.build.selenium.framework.BaseFramework;
import com.build.qa.build.selenium.pageobjects.AddToCartPage;
import com.build.qa.build.selenium.pageobjects.BathroomSinkPage;
import com.build.qa.build.selenium.pageobjects.HomePage;
import com.build.qa.build.selenium.pageobjects.ShoppingCartPage;

public class FergTest extends BaseFramework {

	/**
	 * Extremely basic test that outlines some basic functionality and page objects
	 * as well as assertJ
	 */

	@Test
	public void navigateToHomePage() {
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);
		Assert.assertTrue("The website should load up with the Build.com desktop theme.", homePage.onHomePage());
	}

	/**
	 * Search for the Moen m6702bn from the search bar
	 * 
	 * @throws InterruptedException
	 * @assert: That the product page we land on is what is expected by checking the
	 *          product brand and product id
	 * @difficulty Easy
	 */

	@Test
	public void searchForProductLandsOnCorrectProduct() throws InterruptedException {
		// TODO: Implement this test
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);
		driver.manage().window().maximize();
		homePage.enterkeysOnSearch();
		Assert.assertTrue("Moen", homePage.textVerify());
	}

	/**
	 * Go to the Bathroom Sinks category directly
	 * (https://www.ferguson.com/category/bathroom-plumbing/bathroom-faucets/bathroom-sink-faucets/_/N-zbq4i3)
	 * and add the second product on the search results (Category Drop) page to the
	 * cart.
	 * 
	 * @throws InterruptedException
	 * @assert: the product that is added to the cart is what is expected
	 * @difficulty Easy-Medium
	 */

	@Test
	public void addProductToCartFromCategoryDrop() throws InterruptedException {
		// TODO: Implement this test
		driver.get(getConfiguration("BATHROOM_SINK_FAUCETS"));
		driver.manage().window().maximize();
		BathroomSinkPage bathroomSinkPage = new BathroomSinkPage(driver, wait);
		bathroomSinkPage.secondItem();
		AddToCartPage addToCartPage = new AddToCartPage(driver, wait);
		String expectedString = addToCartPage.getTitlte().trim();
		addToCartPage.clickingOnAddCartbtn();
		addToCartPage.cartClick();
		ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver, wait);
		String actualString = shoppingCartPage.getTitle().trim();
		Assert.assertTrue(actualString.contains(expectedString));
	}

	/**
	 * Add two different finishes of a product (such as Moen m6702bn) to cart,
	 * change the quantity of each finish on the cart page
	 * 
	 * @throws InterruptedException
	 * @assert that the product and cart total update as expected when the quantity
	 *         is changed
	 * @difficulty Medium-Hard
	 */

	@Test
	public void addMultipleCartItemsAndChangeQuantity() throws InterruptedException {
		// TODO: Implement this test
		driver.get(getConfiguration("BATHROOM_SINK_FAUCETS"));
		driver.manage().window().maximize();
		BathroomSinkPage bathroomSinkPage = new BathroomSinkPage(driver, wait);
		AddToCartPage addToCartPage = new AddToCartPage(driver, wait);
		ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver, wait);
		bathroomSinkPage.secondItem();
		addToCartPage.clickingOnAddCartbtn();
		driver.navigate().back();
		bathroomSinkPage.selectingNickelFaucet();
		addToCartPage.clickingOnAddCartbtn();
		addToCartPage.cartClick();
		shoppingCartPage.nickleFaucetQuantity();
		shoppingCartPage.chromeFaucetQuantity();
		shoppingCartPage.totalAmountDisplay();
		Assert.assertTrue("$713.84", shoppingCartPage.totalAmountDisplay());
	}

	/**
	 * Go to a category drop page (such as Bathroom Faucets) and narrow by at least
	 * two filters (facets), e.g: Finish=Chromes and Brand=Brizo
	 * 
	 * @throws InterruptedException
	 * 
	 * @assert that the correct filters are being narrowed, and the result count is
	 *         correct, such that each facet selection is narrowing the product
	 *         count.
	 * @difficulty Hard
	 */

	@Test
	public void facetNarrowBysResultInCorrectProductCounts() throws InterruptedException {
		// TODO: Implement this test
		driver.get(getConfiguration("BATHROOM_SINK_FAUCETS"));
		driver.manage().window().maximize();
		BathroomSinkPage bathroomSinkPage = new BathroomSinkPage(driver, wait);
		bathroomSinkPage.totalSearchDisplay();
		bathroomSinkPage.clickShowMore();
		bathroomSinkPage.clickFaucetsBrand();
		bathroomSinkPage.searchResultNarrowDownAfterSelectingBrand();
		bathroomSinkPage.clickFaucetsColor();
		bathroomSinkPage.searchResultNarrowDownAfterSelectingColor();
	}
}
