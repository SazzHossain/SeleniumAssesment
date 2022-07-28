package com.build.qa.build.selenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class AddToCartPage extends BasePage {

	public AddToCartPage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);

	}

	@FindBy(xpath = "(//i[@class='cart-icon'])[1]")
	WebElement cartBtn;
	@FindBy(xpath = "//input[@value='Add to Cart']")
	WebElement addToCartBtn;
	@FindBy(tagName = "h1")
	WebElement titlElement;

	public void clickingOnAddCartbtn() {
		wait.until(ExpectedConditions.visibilityOf(addToCartBtn));
		addToCartBtn.click();
	}

	public void cartClick() {
		wait.until(ExpectedConditions.elementToBeClickable(cartBtn));
		cartBtn.click();
	}

	public String getTitlte() {
		System.out.println(titlElement.getText());
		return titlElement.getText();
	}
}
