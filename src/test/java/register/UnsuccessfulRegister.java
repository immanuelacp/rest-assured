package register;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;
import shared.BaseTest;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UnsuccessfulRegister extends BaseTest {
    @Test
    public void UnsuccessfulRegister (){
        RequestSpecification req = given();
        String path = "src/resources/schema/register/UnsuccessfulRegisterUserSchema.json";

        String email = "eve.holt@reqres.in";

        JSONObject params = new JSONObject();
        params.put("email",email);

        //add JSON object to body
        req.body(params.toString());

        req.header("Content-type","application/json");

        Response response = req.post("/api/register");
        response.then().assertThat()
                .statusCode(400)
                .body(JsonSchemaValidator.matchesJsonSchema(new File(path)));
        System.out.println(response.asString());
    }
}
