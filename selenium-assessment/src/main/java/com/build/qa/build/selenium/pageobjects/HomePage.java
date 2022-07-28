package com.build.qa.build.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class HomePage extends BasePage {

	private By homePageWrapper;

	public HomePage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		homePageWrapper = By.cssSelector("#wrapper.homepage");
	}

	@FindBy(xpath = "(//input[@name='search'])[1]")
	WebElement searchField1;
	@FindBy(xpath = "//h2[normalize-space(text())='Moen']")
	WebElement moen;

	public boolean onHomePage() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(homePageWrapper)) != null;
	}

	public  void enterkeysOnSearch() {
		searchField1.sendKeys("Moen m6702bn");
		searchField1.sendKeys(Keys.ENTER);
	}

	public boolean textVerify() {
		wait.until(ExpectedConditions.visibilityOf(moen));
		return moen.isDisplayed();
	}
}
