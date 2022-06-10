import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;
import shared.BaseTest;

import java.util.Base64;

import static io.restassured.RestAssured.given;

public class GetResponseData extends BaseTest {
    @Test //penanda kalo method second itu untuk testing
    public void GetResponseData() {
        RequestSpecification req = given();
        req.param("page", 1);

        Response response = req.get("/api/users");
        response.then().assertThat().statusCode(200);
        System.out.println(response.asString());

        String page = response.jsonPath().getString("page");
        System.out.println("page : "+page);
        String name = response.jsonPath().getString("data[0].first_name");
        System.out.println("name : "+name);
    }
}
