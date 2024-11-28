package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasePageObject;

public class FAQListTest {

    private WebDriver driver;

    @Before
    public void prepare() {
//        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void checkQuestionTextTest() {
        BasePageObject basePageObject = new BasePageObject(driver);
        basePageObject.clickCookie();
        basePageObject.scrollInToFAQ();
        basePageObject.recordQuestionAndAnswer();
    }

    @After
    public void closeDriver() {
        driver.quit();
    }
}