package users;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import shared.BaseTest;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UpdateUserTest extends BaseTest {
    @Test
    public void UpdateUserTest(ITestContext context){
        RequestSpecification req = given();
        String path = "src/resources/schema/users/UpdateUserSchema.json";

        String newjob = "Quality Assurance";

        JSONObject params = new JSONObject();
        params.put("name",context.getAttribute("name"));
        params.put("job",newjob);

        req.body(params.toString());

        req.header("Content-type","application/json");
        Response response = req.put("/api/users/"+context.getAttribute("id_user").toString());

        response.then().assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File(path)));

        System.out.println(response.asString());
    }
}
