package utils;

import config.RestfulBookerConfig;
import dto.AuthDTO;
import dto.BookDTO;
import org.testng.annotations.AfterMethod;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestDataGenerator {
    public static BookDTO getTestBook(){
        BookDTO book = new BookDTO();
        book.setFirstname("Jim");
        book.setLastname("Brown");
        book.setTotalprice(111);
        book.setDepositpaid(true);
        Map<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2018-01-01");
        bookingdates.put("checkout", "2019-01-01");
        book.setBookingdates(bookingdates);
        book.setAdditionalneeds("Breakfast");
        return book;
    }

    public static BookDTO updateTestBook(String lastname, String firstname, Integer price, Boolean depositpaid, String checkin, String checkout, String additional){
        BookDTO book = TestDataGenerator.getTestBook();
        book.setFirstname(firstname);
        book.setLastname(lastname);
        book.setTotalprice(price);
        book.setDepositpaid(depositpaid);
        Map<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", checkin);
        bookingdates.put("checkout", checkout);
        book.setBookingdates(bookingdates);
        book.setAdditionalneeds(additional);
        return book;
    }

    public static BookDTO updateTestBook(String lastname){
        BookDTO book = TestDataGenerator.getTestBook();
        book.setLastname(lastname);
        return book;
    }

    public static AuthDTO getTestAuth(){
        AuthDTO auth = new AuthDTO();
        auth.setUsername("admin");
        auth.setPassword("password123");
        return auth;
    }

}
