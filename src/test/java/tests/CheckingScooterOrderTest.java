package tests;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.BasePageObject;
import pages.BookingScooter;
import pages.OrderPage;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CheckingScooterOrderTest {
    private WebDriver driver;
    private final int BOTTONORDERBASE;
    private final String name;
    private final String lastName;
    private final String address;
    private final String station;
    private final String phone;
    private final String date;
    private final String comment;
    private final int BOTTONORDERBOOKING;

    public CheckingScooterOrderTest(int bottonorderbase, String name, String lastName, String adress, String station, String phone, String date, String comment, int bottonorderbooking) {
        BOTTONORDERBASE = bottonorderbase;
        this.name = name;
        this.lastName = lastName;
        this.address = adress;
        this.station = station;
        this.phone = phone;
        this.date = date;
        this.comment = comment;
        BOTTONORDERBOOKING = bottonorderbooking;
    }

    @Parameterized.Parameters
    public static Object[][] getDataOrder() {
        return new Object[][] {
                {1, "Иван", "Иванов", "Москва, ул. Тветская, д. 8", "Красносельская", "+79998887766", "15.12.2024", "К первому подъезду", 1},
                {2, "Петр", "Петров", "Москва, пр. Мира", "Сокольники", "+71112223366", "20.10.2024", "Положить в гараж", 2},
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
        basePageObject.clickButtonOrderBase(BOTTONORDERBASE);
        BookingScooter bookingSqooter = new BookingScooter(driver);
        String actualTitlePage = bookingSqooter.checkTitlePage();
        assertEquals("Тест упал или неверный заголовок!", "Для кого самокат", actualTitlePage);
        bookingSqooter.inputNameForOrder(name);
        bookingSqooter.inputLasnameForOrder(lastName);
        bookingSqooter.inputAdressOrder(address);
        bookingSqooter.inputStationName(station);
        bookingSqooter.inputPhoneNumber(phone);
        bookingSqooter.enterbuttonFurther();
        String actualTitleRentalPage = bookingSqooter.checkTitlePage();
        assertEquals("Тест упал или неверный заголовок!", "Про аренду", actualTitleRentalPage);
        bookingSqooter.choosingDeliveryDate(date);
        String period = bookingSqooter.rentalPeriodScooter();
        String color = bookingSqooter.choosingColorScooter();
        bookingSqooter.inputComment(comment);
        bookingSqooter.clickBack();
        bookingSqooter.enterbuttonFurther();
        bookingSqooter.clickButtonOrderBook(BOTTONORDERBOOKING);
        bookingSqooter.clickButtonYes();
        bookingSqooter.checkTextOrder();
        String actualTextOrder = bookingSqooter.checkTextOrder();
        MatcherAssert.assertThat(actualTextOrder, containsString("Заказ оформлен"));
        bookingSqooter.clickButtonStatus();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.checkDataOrder(name, lastName, address, station, phone, date, period, color, comment);
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