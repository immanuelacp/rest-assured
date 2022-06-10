package users;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.core.Is;
import org.json.JSONObject;
import org.json.JSONString;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import shared.BaseTest;

import java.io.File;

import static io.restassured.RestAssured.given;

public class CreateUserTest extends BaseTest {
    @Test
    public void CreateUserTest(ITestContext context) {
        RequestSpecification req = given();
        String path = "src/resources/schema/users/SuccessCreateUserSchema.json";

        //initiate JSON object
        JSONObject params = new JSONObject(); //membuat variabel params yang berisi JSON object kosong
        params.put("name","Immanuela"); //menambahkan object key name dg value Immanuela pada variabel params
        params.put("job","leader"); //menambahkan object key job dg value leader pada variabel params

        //add JSON object to body
        req.body(params.toString());

        //set content type
        req.header("Content-type","application/json");

        Response response = req.post("/api/users");
        response.then().assertThat()
                .statusCode(201)
                .body(JsonSchemaValidator.matchesJsonSchema(new File(path)));
        System.out.println(response.asString());

        context.setAttribute("id_user",response.jsonPath().getInt("id"));
        context.setAttribute("name",response.jsonPath().getString("name"));
        context.setAttribute("job",response.jsonPath().getString("job"));
    }
}
