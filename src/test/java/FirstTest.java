import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import shared.BaseTest;

import static io.restassured.RestAssured.given;

@Test
public class FirstTest extends BaseTest {
    public void first()
    {
        //RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification request = given();
        request.param("page", 3); //query parameter
        //request.header("Authorization","Bearer 1");
        Response response = request.get("/api/users");
        response.then().assertThat().statusCode(200);
        System.out.println(response.asString());

    }
}
