package users;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import shared.BaseTest;

import static io.restassured.RestAssured.given;

public class ListUserTest extends BaseTest {
    @Test
    public void ListUserTest(ITestContext context)
    {
        RequestSpecification request = given();
        request.param("page", 2);
        Response response = request.get("/api/users");
        response.then().assertThat().statusCode(200);
        System.out.println(response.asString());
        context.setAttribute("id_user",response.jsonPath().getInt("data[0].id"));
    }
}
