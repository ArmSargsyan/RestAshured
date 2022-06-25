import buisness.RequestUtils;
import buisness.ResponseUtils;
import io.restassured.response.ValidatableResponse;
import model.User;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class RestAssuredTests extends BaseTest {

    public void makeGetRequest() {
        ValidatableResponse r = RequestUtils.get("users");
        r.extract().response().prettyPrint();
//        r.extract().cookies().keySet().contains("vzgo");
//        List<Integer> l = ResponseUtils.getValueFromResponseUnderJsonPath(r,"id");
//        l.forEach(System.out::println);  //
        int x = ResponseUtils.getValueFromResponseUnderJsonPath(r, "id[0]");
        System.out.println(x); ////
        List<String> emails = ResponseUtils.getValueFromResponseUnderJsonPath(r,"email");
        Assert.assertTrue(emails.contains("deshpande_karan@cummings.co"));

        Assert.assertEquals(r.extract().statusCode(), HttpStatus.SC_OK);
        ResponseUtils.validateResponseAgainstSchema(r,"schema/getRequestSchema.json");
    }

    public void makePostRequest() {
       // String user = User.getRandomUser();
        String s = "{\n" +
                "        \"name\": \"Deb Asan\",\n" +
                "        \"email\": \"asan_deb@stoes.org\",\n" +
                "        \"gender\": \"female\",\n" +
                "        \"status\": \"inactive\"\n" +
                "    }";
        ValidatableResponse r = RequestUtils.post("users", s);
        r.extract().response().prettyPrint();
        Assert.assertEquals(r.extract().statusCode(), HttpStatus.SC_CREATED);
    }

    public void getObjFromResponse() {
        String user = User.getRandomUser();
        ValidatableResponse r = RequestUtils.post("users", user);
        r.extract().response().prettyPrint();
        Assert.assertEquals(r.extract().statusCode(), HttpStatus.SC_CREATED);

        int id = ResponseUtils.getIntFromResponse(r, "id");

       // User u = ResponseUtils.getObjectFromResponse(r, User.class, "");

        ValidatableResponse r2 = RequestUtils.get("users/" + id);
        r2.extract().response().prettyPrint();

//        ValidatableResponse r2 = RequestUtils.get("users/3174");
//       User u = ResponseUtils.getObjectFromResponse(r2, User.class);
//        System.out.println(u);

    }
}
