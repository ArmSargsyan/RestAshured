import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import javax.naming.spi.ResolveResult;

public class BaseTest {

    @BeforeClass
    public void setupClass(){
        RestAssured.baseURI = "https://gorest.co.in/";
        RestAssured.basePath = "public/v2/";
    }
}
