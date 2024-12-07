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
    private final int QUESTIONNUM;
    private final String QUESTION;
    private final int ANSWERNUM;
    private final String ANSWER;

    public FAQListTest(int questionNum, String quastion, int answerNum, String answer) {
        QUESTIONNUM = questionNum;
        QUESTION = quastion;
        ANSWERNUM = answerNum;
        ANSWER = answer;
    }

    @Parameterized.Parameters
    public static Object[][] getQuestionAndAnswer() {
        return new Object[][] {
                {0, "Сколько это стоит? И как оплатить?", 0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Хочу сразу несколько самокатов! Так можно?", 1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Как рассчитывается время аренды?", 2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Можно ли заказать самокат прямо на сегодня?", 3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Можно ли продлить заказ или вернуть самокат раньше?", 4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Вы привозите зарядку вместе с самокатом?", 5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Можно ли отменить заказ?", 6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Я жизу за МКАДом, привезёте?", 7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
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
        String getQuestionText = basePageObject.checkFAQQuestion(QUESTIONNUM);
        assertEquals("Тест упал! Неверный вопрос!", QUESTION, getQuestionText);
    }

    @Test
    public void checkAnswerTextTest() {
        BasePageObject basePageObject = new BasePageObject(driver);
        basePageObject.clickCookie();
        basePageObject.scrollInToFAQ();
        basePageObject.clickQuestion(QUESTIONNUM);
        String getAnswerText = basePageObject.checkFAQAnswer(ANSWERNUM);
        assertEquals("Тест упал! Неверный ответ!", ANSWER, getAnswerText);
    }

    @After
    public void closeDriver() {
        driver.quit();
    }
}