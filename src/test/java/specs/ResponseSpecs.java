package specs;

import dto.BookDTO;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.ResponseSpecification;
import utils.TestDataGenerator;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ResponseSpecs {
    public static ResponseSpecification bookCreatedResponse(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("bookingid", notNullValue())
                .expectContentType(ContentType.JSON)
                .expectBody(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/addBook.json"))
                .build();
    }

    public static ResponseSpecification bookGetResponse(){
        BookDTO book = TestDataGenerator.getTestBook();
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("firstname", equalTo(book.getFirstname()))
                .expectContentType(ContentType.JSON)
                .expectBody(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/getBook.json"))
                .build();
    }

    public static ResponseSpecification bookUpdateResponse(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .expectBody(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/updateBook.json"))
                .build();
    }



    public static ResponseSpecification bookUpdatePatchResponse(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .expectBody(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/updateBook.json"))
                .build();
    }

    public static ResponseSpecification bookGetAfterUpdateResponse(String expectedLastname){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("lastname", equalTo(expectedLastname))
                .expectContentType(ContentType.JSON)
                .expectBody(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/getBook.json"))
                .build();
    }

    public static ResponseSpecification bookDeleteResponse(){
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectBody(equalTo("Created"))
                .build();
    }

    public static ResponseSpecification bookGetAfterDeleteResponse(){
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .expectBody(equalTo("Not Found"))
                .build();
    }

    public static ResponseSpecification bookingAuth(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("token", notNullValue())
                .expectContentType(ContentType.JSON)
                .expectBody(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/tokenAuth.json"))
                .build();
    }

}
