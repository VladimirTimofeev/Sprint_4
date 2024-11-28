package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;


public class BasePageObject{

    private final WebDriver driver;

    private final String[][] etalonText = {{"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
            {"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
            {"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
            {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
            {"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
            {"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
            {"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
            {"Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}};

    //Кнопка подтверждения Cookie
    private static final By cookieButton = By.id("rcc-confirm-button");
    //Раздел Вопросы о важном
    private static final By questionAboutImportantTitle = By.xpath(".//div[text()='Вопросы о важном']");
    //Вопрос в списке
    private static final String listQuestion = "accordion__heading-%d";
    //Ответ в списке
    private static final String answer = ".//div[@id='accordion__panel-%d']//p";
    //Верхняя кнопка Заказать
    private final By upButtonOrder = By.xpath(".//div[@class='Header_Nav__AGCXC']//button[text()='Заказать']");
    //Нижняя кнопка Заказать
    private final By downButtonOrder = By.xpath(".//div[@class='Home_FinishButton__1_cWm']//button");


    public BasePageObject(WebDriver driver) {
        this.driver = driver;
    }

    //Метод прокрутки страницы до необходимого элемента
    public void scrollPage(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //Метод согласия с Cookie
    public void clickCookie() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(((cookieButton))));
        driver.findElement(cookieButton).click();
    }

    //Метод прокрутки страницы до раздела Вопросы о важном
    public void scrollInToFAQ() {
        WebElement element = driver.findElement(questionAboutImportantTitle);
        scrollPage(element);
    }

    //Метод получения текста заголовка вопросов о важном
    public String checkFAQTitle() {
        WebElement element = driver.findElement(questionAboutImportantTitle);
        scrollPage(element);
        return driver.findElement((questionAboutImportantTitle)).getText();
    }

    //Метод получение текста вопроса
    public String checkFAQQuestion(int itemIndex) {
        return driver.findElement(By.id(String.format(listQuestion, itemIndex))).getText();
    }

    //Метод нажатия на вопрос
    public void clickQuestion(int itemIndex) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.id(String.format(listQuestion, itemIndex))));
        driver.findElement(By.id(String.format(listQuestion, itemIndex))).click();
    }

    //Метод пролучения текста ответа
    public String checkFAQAnswer(int itemIndex) {
        return driver.findElement(By.xpath(String.format(answer, itemIndex))).getText();
    }

    //Метод записи текста вопросов и ответов в массив
    public void recordQuestionAndAnswer() {
        String[][] questionAnswerText = new String[8][2];
        for (int i = 0; i < questionAnswerText.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    String question = checkFAQQuestion(i);
                    questionAnswerText[i][j] = question;
                    clickQuestion(i);
                } else {
                    String answer = checkFAQAnswer(i);
                    questionAnswerText[i][j] = answer;
                }
            }
        }
        assertEquals("Текстовка вопросов и ответов отличается от эталонного!", etalonText, questionAnswerText);
    }

    //Метод нажатия на верхнюю кнопку заказать
    public void clickHeadButtonOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(upButtonOrder));
        driver.findElement(upButtonOrder).click();
    }

    //Метод нажатия на нижнюю кнопку Заказать
    public void clickFootButtonOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(downButtonOrder));
        driver.findElement(downButtonOrder).click();
    }
}