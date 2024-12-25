package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderPage {

    private final WebDriver driver;
    //Локатор для проверки введенных значений
    private final By checkInputData = By.className("Track_Value__15eEX");
    //Кнопка отменить заказ
    private final By buttonCancelOrder = By.xpath(".//button[text()='Отменить заказ']");
    //Кнопка всплывающего окна Назад
    private final By buttonBack = By.xpath(".//button[text()='Назад']");
    //Кнопка всплывающего окна Отменить
    private final By buttonCancel = By.xpath(".//button[text()='Отменить']");
    //Кнопка Хорошо
    private final By buttonGood = By.xpath(".//button[text()='Хорошо']");
    //Заголовок отмены заказа
    private final By titleCancelOrger = By.className("Order_ModalHeader__3FDaJ");
    //Список элементов проверки
    private String[] nameDataList = {"Имя", "Фамилия", "Адрес", "Станция", "Номер телефона", "Дата доставки", "Период аренды", "Цвет самоката", "Комментарий"};


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }


    //Метод записи данных заказа
    public String[] checkDataOrder(String name, String lastName, String adress, String station, String phone, String date, String period, String color, String comment) {
        String[] expectedData = {name, lastName, adress, station, phone, date, period, color, comment};
        String[] actualDataOrder = new String[9];
        int i = 0;
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(checkInputData));
        List<WebElement> recordDataOrder = driver.findElements(checkInputData);

        for (WebElement element : recordDataOrder) {
            actualDataOrder[i] = element.getText();
            i ++;
        }

        for (int j = 0; j < expectedData.length; j++) {
            String originalData = expectedData[j];
            String dataOrder = actualDataOrder[j];
            if (!originalData.equals(dataOrder)) {
                System.out.println(nameDataList[j] + " не соответствует введенному!");
                System.out.println("Введено - " + originalData);
                System.out.println("В заказе - " + dataOrder);
            }
        }
        return actualDataOrder;
    }

    //Метод нажатия кнопки Отменить заказ
    public void ckickButtonCancelOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(buttonCancelOrder));
        driver.findElement(buttonCancelOrder).click();
    }

    //Метод проверки заголовка всплывающего окна отмены заказа
    public String checkTitleCancel() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(titleCancelOrger));
        return driver.findElement(titleCancelOrger).getText();
    }

    //Метод нажатия на кнопку Отменить
    public void clickButtonCancel() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(buttonCancel));
        driver.findElement(buttonCancel).click();
    }

    //Метод нажатия на кнопку Хорошо
    public void clickButtonGood() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(buttonGood));
        driver.findElement(buttonGood);
    }
}