package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.time.Duration;
import java.util.Random;

public class RentalСonditions {

    private WebDriver driver;

    //Поле заголовка страницы заказа
    private final By titlePageOrder = By.className("Order_Header__BZXOb");
    //Поле выбора даты доставки самоката
    private final By deliveryDate = By.xpath(".//div[@class='react-datepicker__input-container']//input");
    //Поле выбора срока аренды
    private final By rentalPeriod = By.className("Dropdown-placeholder");
    //Меню срока аренды
    private final By menuRenta = By.className("Dropdown-menu");
    //Период аренды
    private final By periodRent = By.className("Dropdown-option");
    //Чек-бокс цвета самоката
    private final By colorSqooter = By.className("Checkbox_Label__3wxSf");
    //Поле комментария
    private final By commentElement = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка Назад
    private final By back = By.xpath(".//button[text()='Назад']");
    //Кнопка Заказать
    private final By order = By.xpath(".//div//button[text()='Заказать']");
    //Кнопка Нет
    private final By buttonNo = By.xpath(".//button[text()='Нет']");
    //Кнопка Да
    private final By buttonYes = By.xpath(".//button[text()='Да']");
    //Окно заказа
    private final By ordreText = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");
    //Поле кнопки проверки статуса
    private final By buttonStatus = By.xpath(".//button[text()='Посмотреть статус']");

    public RentalСonditions(WebDriver driver) {
        this.driver = driver;
    }

    //Метод проверки заголовка страницы заказа
    public String checkTitlePage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(titlePageOrder));
        return driver.findElement(titlePageOrder).getText();

    }
    //Метод выбора даты доставки
    public void choosingDeliveryDate(String date) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(deliveryDate));
        WebElement deliveryRentDate = driver.findElement(deliveryDate);
        deliveryRentDate.click();
        deliveryRentDate.sendKeys(date);
        deliveryRentDate.sendKeys(Keys.ENTER);
    }

    //Метод выбора срока аренды
    public void rentalPeriodScooter() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(rentalPeriod));
        driver.findElement(rentalPeriod).click();
        List<WebElement> listElements = driver.findElements(menuRenta);
        Random random = new Random();
        int randomIndex = random.nextInt(listElements.size());
        listElements.get(randomIndex).click();
    }

    //Метод выбора цвета самоката
    public void choosingColorScooter() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(colorSqooter));
        List<WebElement> colorCheckbox = driver.findElements(colorSqooter);
        Random random = new Random();
        int randomIndex = random.nextInt(colorCheckbox.size());
        colorCheckbox.get(randomIndex).click();
    }

    //Метод ввода комментария
    public void inputComment(String comment) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(commentElement));
        driver.findElement(commentElement).click();
        driver.findElement(commentElement).sendKeys(comment);
    }

    //Метод нажатия кнопки Назад
    public void clickBack() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(back));
        driver.findElement(back).click();
    }

    //Метод нажатия кнопки Заказать
    public void clickButtonOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(order));
        List<WebElement> listButtonOrder = driver.findElements(order);
        Random random = new Random();
        int randomIndex = random.nextInt(listButtonOrder.size());
        listButtonOrder.get(randomIndex).click();
    }

    //Метод нажатия кнопки Нет
    public void clickButtonNo() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(buttonNo));
        driver.findElement(buttonNo).click();
    }

    //Иетод нажатия кнопки Да
    public void clickButtonYes() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonYes));
        driver.findElement(buttonYes).click();
    }

    //Метод проверки окна заказа
    public String checkTextOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(ordreText));
        return driver.findElement(ordreText).getText();
    }

    //Метод нажатмя кнопки Посмотреть статус
    public void clickButtonStatus() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(buttonStatus));
        driver.findElement(buttonStatus).click();
    }
}