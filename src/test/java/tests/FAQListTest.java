package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasePageObject;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FAQListTest {

    private WebDriver driver;
    private final String QUESTIONURL;
    private final String QUESTION;
    private final String ANSWERURL;
    private final String ANSWER;

    public FAQListTest(String quastionurl, String quastion, String answerrurl, String answer) {
        QUESTIONURL = quastionurl;
        QUESTION = quastion;
        ANSWERURL = answerrurl;
        ANSWER = answer;
    }

    @Parameterized.Parameters
    public static Object[][] getQuestionAndAnswer() {
        return new Object[][] {
                {"accordion__heading-0", "Сколько это стоит? И как оплатить?", ".//div[@id='accordion__panel-0']//p", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"accordion__heading-1", "Хочу сразу несколько самокатов! Так можно?!", ".//div[@id='accordion__panel-1']//p", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"accordion__heading-2", "Как рассчитывается время аренды?", ".//div[@id='accordion__panel-2']//p", "!Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"accordion__heading-3", "Можно ли заказать самокат прямо на сегодня?", ".//div[@id='accordion__panel-3']//p", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"accordion__heading-4", "Можно ли продлить заказ или вернуть самокат раньше?", ".//div[@id='accordion__panel-4']//p", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"accordion__heading-5", "Вы привозите зарядку вместе с самокатом?", ".//div[@id='accordion__panel-5']//p", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"accordion__heading-6", "Можно ли отменить заказ?", ".//div[@id='accordion__panel-6']//p", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"accordion__heading-7", "Я жизу за МКАДом, привезёте?", ".//div[@id='accordion__panel-7']//p", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

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
        String getQuestionText = basePageObject.checkFAQQuestion(QUESTIONURL);
        assertEquals("Тест упал! Неверный вопрос!", QUESTION, getQuestionText);
    }

    @Test
    public void checkAnswerTextTest() {
        BasePageObject basePageObject = new BasePageObject(driver);
        basePageObject.clickCookie();
        basePageObject.scrollInToFAQ();
        basePageObject.clickQuestion(QUESTIONURL);
        String getAnswerText = basePageObject.checkFAQAnswer(ANSWERURL);
        assertEquals("Тест упал! Неверный ответ!", ANSWER, getAnswerText);
    }

    @After
    public void closeDriver() {
        driver.quit();
    }
}