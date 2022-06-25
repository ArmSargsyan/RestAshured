package buisness;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import model.User;

public class ResponseUtils {
    public static <T> T getObjectFromResponse(ValidatableResponse response, Class<T> type) {

        return response
                .extract().as(type);
    }

    public static int getIntFromResponse(ValidatableResponse response, String path) {
        return response
                .extract()
                .jsonPath()
                .getInt(path);
    }

    public static void validateResponseAgainstSchema(ValidatableResponse response, String filePath) {
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory
                .newBuilder()
                .setValidationConfiguration(ValidationConfiguration
                        .newBuilder()
                        .setDefaultVersion(SchemaVersion.DRAFTV4)
                        .freeze())
                .freeze();
        response
                .assertThat()
                 .body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(filePath)
                        .using(jsonSchemaFactory));
    }

    public static <T> T getValueFromResponseUnderJsonPath(ValidatableResponse response, String jsonPath) {
       return response
                .extract()
                .jsonPath()
                .get(jsonPath);
    }
}
