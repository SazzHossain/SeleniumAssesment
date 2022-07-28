package com.build.qa.build.selenium.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseFramework {
	protected WebDriver driver;
	protected Wait<WebDriver> wait;
	private static final Logger LOG = LoggerFactory.getLogger(BaseFramework.class);
	private static final String CONFIG_FILE = "./conf/automation.properties";
	private static final String DRIVER_FIREFOX = "firefox";
	private static final String DRIVER_CHROME = "chrome";
	private static final String DRIVER_IOS_SAFARI = "ios_safari";
	private static final String DRIVER_ANDROID_CHROME = "android_chrome";
	private static Properties configuration;

	@Rule
	public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

	@BeforeClass
	public static void beforeClass() throws IOException {
		configuration = new Properties();
		FileInputStream input;
		LOG.info("Loading in configuration file.");
		input = new FileInputStream(new File(CONFIG_FILE));
		configuration.loadFromXML(input);
		input.close();
	}

	@Before
	public void setUpBefore() {
		DesiredCapabilities capabilities;
		// Which driver to use?
		// As per CONFIG_FILE DRIVER_CHROME will run
		if (DRIVER_CHROME.equalsIgnoreCase(configuration.getProperty("BROWSER"))) {
			capabilities = DesiredCapabilities.chrome();
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(capabilities);
		} else if (DRIVER_FIREFOX.equalsIgnoreCase(configuration.getProperty("BROWSER"))) {
			capabilities = DesiredCapabilities.firefox();
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(capabilities);
		} else if (DRIVER_ANDROID_CHROME.equalsIgnoreCase(configuration.getProperty("BROWSER"))) {
			// DesireCapabilities of Android Phone
			capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.PLATFORM_NAME, "Android");
			capabilities.setCapability(CapabilityType.VERSION, "10");
			capabilities.setCapability("deviceName", "Pixel 4");
			capabilities.setCapability("automationName", "UIAutomator2");
			capabilities.setBrowserName("Chrome");
			URL url = null;
			try {
				url = new URL(configuration.getProperty("SERVER_URL"));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			driver = new RemoteWebDriver(url, capabilities);
		} else if (DRIVER_IOS_SAFARI.equalsIgnoreCase(configuration.getProperty("BROWSER"))) {
			// DesireCapabilities of iOS Phone
			capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", "iOS");
			capabilities.setCapability("platformVersion", "14");
			capabilities.setCapability("deviceName", "iPhoneX");
			capabilities.setCapability("automationName", "XCUITest");
			capabilities.setBrowserName("Safari");
			URL url = null;
			try {
				url = new URL(configuration.getProperty("SERVER_URL"));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			driver = new RemoteWebDriver(url, capabilities);
		}
		// Define fluent wait
		wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(5))
				.ignoring(NoSuchElementException.class);
	}

	protected WebDriver getDriver() {
		return driver;
	}

	protected String getConfiguration(String config) {
		return configuration.getProperty(config);
	}

	@After
	public void tearDownAfter() {
		LOG.info("Quitting driver.");
		driver.quit();
		driver = null;
	}
}
