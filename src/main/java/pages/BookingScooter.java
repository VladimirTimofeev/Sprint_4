package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

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


    public BookingScooter(WebDriver driver) {
        this.driver = driver;
    }

    //Метод проверки заголовка
    public String checkTitlePage() {
        String etalonTitle = "Для кого самокат";
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
}