package buisness;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import model.User;

public final class RequestUtils {

    private RequestUtils(){

    }
    public static ValidatableResponse get(String endpoint){
        return RestAssured.given()
                .when()
                .get(endpoint)
                .then();
    } 
    public static ValidatableResponse post(String endpoint, Object body){
        return RestAssured
                .given()
                .spec(requestSpecification())
                .body(body)
                .when()
                .post(endpoint)
                .then();
    }

    private static RequestSpecification requestSpecification(){
        RequestSpecBuilder spec = new RequestSpecBuilder();
        return spec
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader("Authorization","Bearer d52e0d7f218d9f2cda7e80e26be2046e75565d6b1510030f28c5c83a44edaf96")
                .build();

    }

}
