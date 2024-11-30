package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BookingScooter {

    private final WebDriver driver;

    //Поле заголовка страницы
    private final By titlePage = By.className("Order_Header__BZXOb");
    //Поле ввода Имя
    private final By inputName = By.xpath(".//div[@class='Input_InputContainer__3NykH']//input[@placeholder='* Имя']");
    //Поле ввода Фамилия
    private final By inputLastName = By.xpath(".//div[@class='Input_InputContainer__3NykH']//input[@placeholder='* Фамилия']");
    //Поле ввода адреса
    private final By inputAdress = By.xpath(".//div[@class='Input_InputContainer__3NykH']//input[@placeholder='* Адрес: куда привезти заказ']");
    //Поле ввода станции метро
    private final By inputNameStetion = By.xpath(".//div[@class='select-search__value']//input[@placeholder='* Станция метро']");
    //Поле ввода номера телефона
    private final By inputPhone = By.xpath(".//div[@class='Input_InputContainer__3NykH']//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка Далее
    private final By buttonFurther = By.xpath(".//div[@class='Order_NextButton__1_rCA']//button[text()='Далее']");
    //Поле выбора даты доставки самоката
    private final By deliveryDate = By.xpath(".//div[@class='react-datepicker__input-container']//input");
    //Поле выбора срока аренды
    private final By rentalPeriod = By.className("Dropdown-placeholder");
    //Меню срока аренды
    private final By menuRenta = By.className("Dropdown-option");
    //Чек-бокс цвета самоката
    private final By colorSqooterCheckBox = By.className("Checkbox_Label__3wxSf");
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


    public BookingScooter(WebDriver driver) {
        this.driver = driver;
    }

    //Метод проверки заголовка
    public String checkTitlePage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(titlePage));
        return driver.findElement(titlePage).getText();
    }

    //Метод заполнения поля Имя
    public void inputNameForOrder(String name) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(inputName));
        driver.findElement(inputName).click();
        driver.findElement(inputName).sendKeys(name);
    }

    //Метод заполнения поля Фамилия
    public void inputLasnameForOrder(String lastName) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(inputLastName));
        driver.findElement(inputLastName).click();
        driver.findElement(inputLastName).sendKeys(lastName);
    }

    //Метод заполнения поля адрес
    public void inputAdressOrder(String address) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(inputAdress));
        driver.findElement(inputAdress).click();
        driver.findElement(inputAdress).sendKeys(address);
    }

    //Метод выбора станции метро
    public void inputStationName(String station) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(inputNameStetion));
        WebElement stationElement = driver.findElement(inputNameStetion);
        stationElement.click();
        stationElement.sendKeys(station);
        stationElement.sendKeys(Keys.ARROW_DOWN);
        stationElement.sendKeys(Keys.ENTER);
    }

    //Метод ввода номера телефона
    public void inputPhoneNumber(String phone) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(inputPhone));
        driver.findElement(inputPhone).click();
        driver.findElement(inputPhone).sendKeys(phone);
    }

    //Метод нажатия на кнопку Далее
    public void enterbuttonFurther() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(buttonFurther));
        driver.findElement(buttonFurther).click();
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
    public String rentalPeriodScooter() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(rentalPeriod));
        driver.findElement(rentalPeriod).click();
        List<WebElement> listElements = driver.findElements(menuRenta);
        Random random = new Random();
        int randomIndex = random.nextInt(listElements.size());
        listElements.get(randomIndex).click();
        return driver.findElement(rentalPeriod).getText();
    }

    //Метод выбора цвета самоката
    public String choosingColorScooter() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(colorSqooterCheckBox));
        List<WebElement> colorCheckbox = driver.findElements(colorSqooterCheckBox);
        Random random = new Random();
        int randomIndex = random.nextInt(colorCheckbox.size());
        colorCheckbox.get(randomIndex).click();
        return driver.findElement(colorSqooterCheckBox).getText();
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