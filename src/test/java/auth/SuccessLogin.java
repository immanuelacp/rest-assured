package auth;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import shared.BaseTest;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SuccessLogin extends BaseTest {
    @Test
    public void SuccessLogin (ITestContext context){
        RequestSpecification req = given();
        String path = "src/resources/schema/auth/SuccessLoginUserSchema.json";

        JSONObject params = new JSONObject(); //membuat variabel params yang berisi JSON object kosong
        params.put("email",context.getAttribute("email")); //menambahkan object key email dg value morpheus pada variabel params
        params.put("password",context.getAttribute("pass"));

        //add JSON object to body
        req.body(params.toString());

        req.header("Content-type","application/json");

        Response response = req.post("/api/login");
        response.then().assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File(path)));
        System.out.println(response.asString());


    }
}
