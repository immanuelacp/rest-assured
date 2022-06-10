package register;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.core.Is;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import shared.BaseTest;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SuccessRegister extends BaseTest {
    @Test
    public void SuccessfulRegister (ITestContext context){
        RequestSpecification req = given();
        String path = "src/resources/schema/register/SuccessRegisterUserSchema.json";

        String email = "eve.holt@reqres.in";
        String password = "pistol";

        JSONObject params = new JSONObject();
        params.put("email",email);
        params.put("password",password);

        //add JSON object to body
        req.body(params.toString());

        req.header("Content-type","application/json");

        Response response = req.post("/api/register");
        response.then().assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File(path)));
        System.out.println(response.asString());

        context.setAttribute("email",email);
        context.setAttribute("pass",password);
    }
}
