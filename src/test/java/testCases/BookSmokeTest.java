package testCases;

import clients.BookingClient;
import dto.AuthDTO;
import dto.BookDTO;
import listeners.RunTestAgain;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.TestDataGenerator;

public class BookSmokeTest extends BaseTest {
    private BookingClient bookingClient = new BookingClient();
    @Test(description = "The test includes checking the Creation, Update and Deletion of a book")//, retryAnalyzer = RunTestAgain.class)
    void smokeTestFullLifeCycle(){
        BookDTO book = TestDataGenerator.getTestBook();
        Integer bookingId = bookingClient.addBook(book).extract().path("bookingid");

        bookingClient.getBook(bookingId);

        AuthDTO auth = TestDataGenerator.getTestAuth();
        String token = bookingClient.createToken(auth).extract().path("token");

        BookDTO updateBook = TestDataGenerator.updateTestBook(
                "Thompson",
                "Jim",
                222,
                false,
                "2025-02-01",
                "2025-02-10",
                "Lunch"
        );
        bookingClient.updateBook(updateBook,token,bookingId);

        BookDTO updatePatchBook = TestDataGenerator.updateTestBook("Brown");
        bookingClient.updatePatchBook(updatePatchBook,token,bookingId);

        bookingClient.getAfterPatchBook(bookingId,updatePatchBook.getLastname());

        bookingClient.deleteBook(token,bookingId);

        bookingClient.getAfterDeleteBook(bookingId);




    }
}
