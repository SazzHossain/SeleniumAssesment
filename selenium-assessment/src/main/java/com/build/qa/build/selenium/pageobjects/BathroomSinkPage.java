package com.build.qa.build.selenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class BathroomSinkPage extends BasePage {

	public BathroomSinkPage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);

	}

	@FindBy(xpath = "//*[@id=\"sku7289400\"]/div[3]/div/a/img")
	WebElement AddToCart2ndItem;
	@FindBy(xpath = "//*[@id=\"sku7289400\"]/div[4]/a/p")
	WebElement chromeProduct;
	@FindBy(xpath = "//*[@id=\"sku7289400\"]/div[3]/div/div/label[2]/div/span")
	WebElement nickelProductRadiobtn;
	@FindBy(xpath = "//*[@data-productid=\"7289400\"]")
	WebElement addChromeProduct;
	@FindBy(xpath = "//p[text()='Pfister Pfirst Series™ Single Handle Monoblock Bathroom Sink Faucet in Brushed Nickel']")
	WebElement nickelFaucet;
	@FindBy(xpath = "//*[@id=\"wrapper\"]/main/div/div/div[1]/div/div[2]/div[3]/ul/li[11]/a")
	WebElement showMoreElement;
	@FindBy(xpath = "//p[text()='Brizo']")
	WebElement faucetsBrandElement;
	@FindBy(xpath = "//p[text()='Chromes']")
	WebElement faucetsFinishCatagoryElement;
	@FindBy(xpath = "//div[@class='word total-record']")
	WebElement totalSearchResult;

	public int searchResultNarrowDownAfterSelectingBrand() {
		wait.until(ExpectedConditions.visibilityOf(totalSearchResult));
		String actual = totalSearchResult.getAttribute("data-total-record");
		System.out.println("The number of the search result after narrowing down the brand is : " + actual);
		return Integer.parseInt(actual);
	}

	public int searchResultNarrowDownAfterSelectingColor() {
		wait.until(ExpectedConditions.visibilityOf(totalSearchResult));
		String actual = totalSearchResult.getAttribute("data-total-record");
		System.out.println("The number of the search result after narrowing down the colors is : " + actual);
		return Integer.parseInt(actual);
	}

	public int totalSearchDisplay() {
		wait.until(ExpectedConditions.visibilityOf(totalSearchResult));
		String actual = totalSearchResult.getAttribute("data-total-record");
		System.out.println("Total number of the search result is : " + actual);
		return Integer.parseInt(actual);
	}

	public void secondItem() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(AddToCart2ndItem));
		AddToCart2ndItem.click();
	}

	public void selectingChromeFaucet() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(chromeProduct));
		chromeProduct.click();
		wait.until(ExpectedConditions.visibilityOf(addChromeProduct));
		addChromeProduct.click();
	}

	public void selectingNickelFaucet() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(nickelProductRadiobtn));
		nickelProductRadiobtn.click();
		wait.until(ExpectedConditions.visibilityOf(nickelFaucet));
		nickelFaucet.click();
	}

	public void clickShowMore() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(showMoreElement));
		showMoreElement.click();
	}

	public void clickFaucetsBrand() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(faucetsBrandElement));
		faucetsBrandElement.click();
		Thread.sleep(2000);
	}

	public void clickFaucetsColor() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(faucetsFinishCatagoryElement));
		faucetsFinishCatagoryElement.click();
		Thread.sleep(3000);
	}
}
