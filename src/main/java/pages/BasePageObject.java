package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePageObject{

    private final WebDriver driver;

    //Кнопка подтверждения Cookie
    private static final By COOKIEBUTTON = By.id("rcc-confirm-button");
    //Раздел Вопросы о важном
    private static final By QUESTIONABOUTIMPORTANTTITLE = By.xpath(".//div[text()='Вопросы о важном']");
    //Вопрос в списке
    private final String QUESTION = "accordion__heading-%d";
    //Ответ в списке
    private final String ANSWER = ".//div[@id='accordion__panel-%d']//p";
    //Кнопка Заказать верхняя
    private final By BUTTONORDERUP = By.xpath(".//div[@class='Header_Nav__AGCXC']//button[text()='Заказать']");
    //Кнопка Заказать нижняя
    private final By BUTTONORDERDOWN = By.xpath(".//div[@class='Home_FinishButton__1_cWm']//button[text()='Заказать']");


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
    public void clickButtonOrderBase(int bottonOrder) {
        switch (bottonOrder) {
            case 1:
                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.elementToBeClickable(BUTTONORDERUP));
                driver.findElement(BUTTONORDERUP).click();
                break;
            case 2:
                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.elementToBeClickable(BUTTONORDERDOWN));
                driver.findElement(BUTTONORDERDOWN).click();
                break;
        }
    }
}