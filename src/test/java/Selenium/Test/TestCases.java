package Selenium.Test;


import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCases {
	
	/*
	 * geckoDriver is a proxy for using W3C WebDriver-compatible
	 * clients to interact with Gecko-based browsers i.e. 
	 * Mozilla Firefox in this case. As Selenium 3 will not have
	 * any native implementation of FF, we have to direct all 
	 * the driver commands through Gecko Driver. Gecko Driver is
	 * an executable file that you need to have in one of the 
	 * system path before starting your tests.
	 */
	public static final String GECKO_DRIVER_PATH="src/test/resources/geckodriver.exe"; 
	WebDriver driver;

	@Before
	public void doBefore(){
		File geckodriverPath = new File(GECKO_DRIVER_PATH);
		System.setProperty("webdriver.gecko.driver", geckodriverPath.getAbsolutePath());
		driver = new FirefoxDriver();
	}
	
	
	/* In this testCase I decided to find the elements by cssSeletor
	 *  because I was easy to find the elements,However, not all 
	 *  browsers support CSS identification.
	 */
	@Test
	public void testCase1(){
		driver.get("https://www.intersysconsulting.com/");
        driver.findElement(By.cssSelector("#searchToggle")).click();
        driver.findElement(By.cssSelector("input[name=s]")).sendKeys("Subscription Services");
        driver.findElement(By.cssSelector("button[type=submit]")).click();
        String company =driver.findElement(By.cssSelector("#menu-footer-menu > li.menu-item.menu-company > a")).getText();
        Assert.assertEquals(company,"Company");
	}
	
	
	/* In this testCase I decided to find the email and password
	 * elements by ID because the id in these elements never change.
	 * However, Log in Button and Not you elements change the id sometimes,
	 * so I decided to find the elements with csselector,
	 * selecting with data-testid property.
	 */
	@Test
	public void testCase2(){
		driver.get("https://www.facebook.com/");
        driver.findElement(By.id("email")).sendKeys("luisvalero0808@hotmail.com");;
        driver.findElement(By.id("pass")).sendKeys("invalid password");
        driver.findElement(By.cssSelector("input[data-testid=royal_login_button]")).click();
        driver.findElement(By.cssSelector("a[data-testid=login_not_me_link]")).click();
	}

}
