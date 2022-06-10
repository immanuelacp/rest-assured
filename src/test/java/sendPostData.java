import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.core.Is;
import org.json.JSONObject;
import org.testng.annotations.Test;
import shared.BaseTest;

import java.io.File;

import static io.restassured.RestAssured.given;

public class sendPostData extends BaseTest {
    @Test //penanda kalo method second itu untuk testing
    public void sendPostData()
    {
        RequestSpecification req = given();
        String path = "src/resources/schema/users/SuccessCreateUserSchema.json";

        //initiate JSON object
        JSONObject params = new JSONObject();
        params.put("name","Lala");
        params.put("job","QA");

        //add JSON object to body
        req.body(params.toString());

        //set content type
        req.header("Content-type","application/json");

        Response response = req.post("/api/users");
        response.then().assertThat()
                .statusCode(201)
                .body("name", Is.is("lala"))
                .body("job",Is.is("QA"))
                .body(JsonSchemaValidator.matchesJsonSchema(new File(path)));

        System.out.println(response.asString());

        //untuk menampilkan datanya
        String name = response.jsonPath().getString("name");
        System.out.println("name : "+name);
        String job = response.jsonPath().getString("job");
        System.out.println("job : "+job);


        //biar bisa tampil
        //System.out.println(params.toString());
    }
}
