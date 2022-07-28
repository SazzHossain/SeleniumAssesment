package com.build.qa.build.selenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class ShoppingCartPage extends BasePage {

	public ShoppingCartPage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);	
	}

	@FindBy(xpath = "//span[text()='Part #PLG1420600']")
	WebElement faucetPartNo;
	@FindBy(xpath = "//div[@class='cl-name']/a/p")WebElement titlElement;
	@FindBy(xpath = "(//input[@name='updateQuantity'])[1]")
	WebElement nickleQuantity;
	@FindBy(xpath = "(//input[@name='updateQuantity'])[2]")
	WebElement chromeQuantity;
	@FindBy(xpath = "//b[text()='$713.84']")
	WebElement totalAmouont;

	public boolean partsNumberDisplay() {
		boolean actual = faucetPartNo.isDisplayed();
		System.out.println("Parts Number : PLG1420600 is displayed : " + actual);
		return actual;
	}
	
	public String getTitle() {
		wait.until(ExpectedConditions.visibilityOf(titlElement));
		System.out.println(titlElement.getText());
		return titlElement.getText();
	}

	public boolean totalAmountDisplay() {
		wait.until(ExpectedConditions.visibilityOf(totalAmouont));
		boolean acutal = totalAmouont.isDisplayed();
		System.out.println("Total amount after quantity changed : " + acutal);
		return acutal;
	}

	public void nickleFaucetQuantity() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		nickleQuantity.clear();
		wait.until(ExpectedConditions.visibilityOf(nickleQuantity));
		nickleQuantity.sendKeys("2");
	}

	public void chromeFaucetQuantity() {
		chromeQuantity.clear();
		wait.until(ExpectedConditions.visibilityOf(chromeQuantity));
		chromeQuantity.sendKeys("3");
	}
}
