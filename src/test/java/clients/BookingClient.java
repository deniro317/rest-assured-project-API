package clients;

import dto.AuthDTO;
import dto.BookDTO;
import io.restassured.response.ValidatableResponse;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BookingClient {
    public ValidatableResponse addBook(BookDTO book){
        System.out.println("\nPOST POSITIVE\n");
        return given().spec(RequestSpecs.bookSpec())
                .log().uri()
                .body(book)
                .post("/booking")
                .then()
                .spec(ResponseSpecs.bookCreatedResponse())
                .log().body();
    }

    public ValidatableResponse getBook(Integer bookingId){
        System.out.println("\nGET POSITIVE\n");
        return given().spec(RequestSpecs.bookSpec())
                .log().uri()
                .pathParam("bookingId", bookingId)
                .get("/booking/{bookingId}")
                .then()
                .spec(ResponseSpecs.bookGetResponse())
                .log().body();
    }

    public ValidatableResponse createToken(AuthDTO auth){
        System.out.println("\nPOST TOKEN POSITIVE\n");
        return given().spec(RequestSpecs.bookSpec())
                .log().uri()
                .body(auth)
                .post("/auth")
                .then()
                .spec(ResponseSpecs.bookingAuth())
                .log().body();
    }

    public ValidatableResponse updateBook(BookDTO book, String token, Integer bookingId){
        Map<String, Object> updateBody = new HashMap<>();
        updateBody.put("firstname", book.getFirstname());
        updateBody.put("lastname", book.getLastname());
        updateBody.put("totalprice", book.getTotalprice());
        updateBody.put("depositpaid", book.isDepositpaid());
        updateBody.put("bookingdates", book.getBookingdates());
        updateBody.put("additionalneeds", book.getAdditionalneeds());
        System.out.println("\nPUT POSITIVE\n");
        return given().spec(RequestSpecs.bookUpdateSpec(token))
                .log().uri()
                .pathParam("bookingId", bookingId)
                .body(updateBody)
                .put("/booking/{bookingId}")
                .then()
                .spec(ResponseSpecs.bookUpdateResponse())
                .log().body();
    }

    public ValidatableResponse updatePatchBook(BookDTO book, String token, Integer bookingId){
        Map<String, Object> updateBody = new HashMap<>();
        updateBody.put("lastname", book.getLastname());
        System.out.println("\nPATCH POSITIVE\n");
        return given().spec(RequestSpecs.bookUpdateSpec(token))
                .log().uri()
                .pathParam("bookingId", bookingId)
                .body(updateBody)
                .patch("/booking/{bookingId}")
                .then()
                .spec(ResponseSpecs.bookUpdatePatchResponse())
                .log().body();
    }

    public ValidatableResponse getAfterPatchBook(Integer bookingId, String expectedLastname){
        System.out.println("\nGET (PATCH) POSITIVE\n");
        return given().spec(RequestSpecs.bookSpec())
                .log().uri()
                .pathParam("bookingId", bookingId)
                .get("/booking/{bookingId}")
                .then()
                .spec(ResponseSpecs.bookGetAfterUpdateResponse(expectedLastname))
                .log().body();
    }



    public ValidatableResponse deleteBook(String token, Integer bookingId){
        System.out.println("\nDELETE POSITIVE\n");
        return given().spec(RequestSpecs.bookUpdateSpec(token))
                .log().uri()
                .pathParam("bookingId", bookingId)
                .delete("/booking/{bookingId}")
                .then()
                .spec(ResponseSpecs.bookDeleteResponse())
                .log().body();
    }

    public ValidatableResponse getAfterDeleteBook(Integer bookingId){
        System.out.println("\nGET (DELETE) NEGATIVE\n");
        return given().spec(RequestSpecs.bookSpec())
                .log().uri()
                .pathParam("bookingId", bookingId)
                .get("/booking/{bookingId}")
                .then()
                .spec(ResponseSpecs.bookGetAfterDeleteResponse())
                .log().body();
    }

}
