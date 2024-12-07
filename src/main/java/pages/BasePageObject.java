package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.time.Duration;
import java.util.Random;

public class BasePageObject{

    private final WebDriver driver;
    //Список вопросов и ответов
//    private final String[][] etalonText = {{"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
//            {"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
//            {"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
//            {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
//            {"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
//            {"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
//            {"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
//            {"Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}};
    //Кнопка подтверждения Cookie
    private static final By COOKIEBUTTON = By.id("rcc-confirm-button");
    //Раздел Вопросы о важном
    private static final By QUESTIONABOUTIMPORTANTTITLE = By.xpath(".//div[text()='Вопросы о важном']");
    //Вопрос в списке
    private final String QUESTION = "accordion__heading-%d";
    //Ответ в списке
    private final String ANSWER = ".//div[@id='accordion__panel-%d']//p";
    //Кнопка Заказать
    private final By BUTTONSORDER = By.xpath(".//button[text()='Заказать']");
    //Текст положительной проверки текста вопроса
//    private final String positiveQestion = "Текст %d вопроса корректный.";
    //Текст отрицательной проверки текста вопроса
//    private final String negativeQestion = "Текст %d вопроса не соответствует оригиналу!";
    //Текст положительной проверки текста ответа
//    private final String positiveAnswer = "Текст %d ответа корректный.";
    //текст отрицательной проверки текста ответа
//    private final String negativeAnswer = "Текст %d ответа не соответствует оригиналу!";


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
                .until(ExpectedConditions.elementToBeClickable(((COOKIEBUTTON))));
        driver.findElement(COOKIEBUTTON).click();
    }

    //Метод прокрутки страницы до раздела Вопросы о важном
    public void scrollInToFAQ() {
        WebElement element = driver.findElement(QUESTIONABOUTIMPORTANTTITLE);
        scrollPage(element);
    }

    //Метод получение текста вопроса
    public String checkFAQQuestion(int number) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.id(String.format(QUESTION, number))));
        return driver.findElement(By.id(String.format(QUESTION, number))).getText();
    }

    //Метод нажатия на вопрос
    public void clickQuestion(int numberQuestion) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.id(String.format(QUESTION, numberQuestion))));
        driver.findElement(By.id(String.format(QUESTION, numberQuestion))).click();
    }

    //Метод пролучения текста ответа
    public String checkFAQAnswer(int numberAnswer) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(ANSWER, numberAnswer))));
        return driver.findElement(By.xpath(String.format(ANSWER, numberAnswer))).getText();
    };

    //Метод записи текста вопросов и ответов в массив
//    public void recordQuestionAndAnswer() {
//        for (int i = 0; i < etalonText.length; i++) {
//            for (int j = 0; j < 2; j++) {
//                if (j == 0) {
//                    String question = checkFAQQuestion(i);
//                    String originQuestion = etalonText[i][j];
//                    if (!originQuestion.equals(question)) {
//                        System.out.println(String.format(negativeQestion, i + 1));
//                        System.out.println("Текст оригинального вопроса: " + originQuestion);
//                        System.out.println("Текст полученного вопроса:   " + question);
//                    }
//                    clickQuestion(i);
//                } else {
//                    String answer = checkFAQAnswer(i);
//                    String originAnswer = etalonText[i][j];
//                    if (!originAnswer.equals(answer)) {
//                        System.out.println(String.format(negativeAnswer, i + 1));
//                        System.out.println("Текст оригинального ответа: " + originAnswer);
//                        System.out.println("Текст полученного ответа:   " + answer);
//                    }
//                }
//            }
//        }
//    }

    //Метод нажатия на кнопку заказать
    public void clickButtonOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(BUTTONSORDER));
        List<WebElement> buttonsElement = driver.findElements(BUTTONSORDER);
        Random random = new Random();
        int randomIndex = random.nextInt(buttonsElement.size());
        buttonsElement.get(randomIndex).click();
    }
}