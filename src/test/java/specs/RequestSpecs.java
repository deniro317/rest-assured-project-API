package specs;

import config.RestfulBookerConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;

public class RequestSpecs {
    public static RequestSpecification bookSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(RestfulBookerConfig.BASE_URI)
                .setContentType(JSON)
                .build();
    }

    public static RequestSpecification bookUpdateSpec(String token){
        return new RequestSpecBuilder()
                .setBaseUri(RestfulBookerConfig.BASE_URI)
                .addCookie("token", token)
                .setContentType(JSON)
                .build();
    }
}
