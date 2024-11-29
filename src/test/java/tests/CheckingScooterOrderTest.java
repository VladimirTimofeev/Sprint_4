package tests;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.BasePageObject;
import pages.BookingScooter;
import pages.OrderPage;
import pages.RentalСonditions;

import java.sql.Time;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CheckingScooterOrderTest {
    private WebDriver driver;
    private final String name;
    private final String lastName;
    private final String address;
    private final String station;
    private final String phone;
    private final String date;
    private final String comment;

    public CheckingScooterOrderTest(String name, String lastName, String adress, String station, String phone, String date, String comment) {
        this.name = name;
        this.lastName = lastName;
        this.address = adress;
        this.station = station;
        this.phone = phone;
        this.date = date;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getDataOrder() {
        return new Object[][] {
                {"Иван", "Иванов", "Москва, ул. Тветская, д. 8", "Красносельская", "+79998887766", "15.12.2024", "К первому подъезду"},
                {"Петр", "Петров", "Москва, пр. Мира", "Сокольники", "+71112223366", "20.10.2024", "Положить в гараж"},
        };
    }


    @Before
    public void prepare() {
        driver = new FirefoxDriver();
//        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void checkOrderSqooterTest() throws InterruptedException {
        BasePageObject basePageObject = new BasePageObject(driver);
        basePageObject.clickCookie();
        basePageObject.clickButtonOrder();
        BookingScooter bookingSqooter = new BookingScooter(driver);
        String actualTitlePage = bookingSqooter.checkTitlePage();
        assertEquals("Тест упал или неверный заголовок!", "Для кого самокат", actualTitlePage);
        bookingSqooter.inputNameForOrder(name);
        bookingSqooter.inputLasnameForOrder(lastName);
        bookingSqooter.inputAdressOrder(address);
        bookingSqooter.inputStationName(station);
        bookingSqooter.inputPhoneNumber(phone);
        bookingSqooter.enterbuttonFurther();
        RentalСonditions rentalСonditions = new RentalСonditions(driver);
        String actualTitleRentalPage = rentalСonditions.checkTitlePage();
        assertEquals("Тест упал или неверный заголовок!", "Про аренду", actualTitleRentalPage);
        rentalСonditions.choosingDeliveryDate(date);
        rentalСonditions.rentalPeriodScooter();
        rentalСonditions.choosingColorScooter();
        rentalСonditions.inputComment(comment);
        rentalСonditions.clickBack();
        bookingSqooter.enterbuttonFurther();
        rentalСonditions.clickButtonOrder();
        rentalСonditions.clickButtonNo();
        rentalСonditions.clickButtonOrder();
        rentalСonditions.clickButtonYes();
        rentalСonditions.checkTextOrder();
        String actualTextOrder = rentalСonditions.checkTextOrder();
        MatcherAssert.assertThat(actualTextOrder, containsString("Заказ оформлен"));
        rentalСonditions.clickButtonStatus();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.checkDataOrder(name, lastName, address, station, phone, date);
        Thread.sleep(500);
        orderPage.ckickButtonCancelOrder();
        String resultTitleCancel = orderPage.checkTitleCancel();
        assertEquals("Тест упал или неверный заголовок!", "Хотите отменить заказ?\n ", resultTitleCancel);
        orderPage.clickButtonCancel();
        Thread.sleep(500);
        String resultTitleCancelConfirm = orderPage.checkTitleCancel();
        assertEquals("Тест упал или неверный заголовок!", "Заказ отменён\nВозвращайтесь, мы всегда вас ждём :)", resultTitleCancelConfirm);
        orderPage.clickButtonGood();
    }

    @After
    public void closeDriver() {
        driver.quit();
    }
}