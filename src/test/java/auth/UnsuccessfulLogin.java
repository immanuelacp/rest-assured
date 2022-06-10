package auth;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;
import shared.BaseTest;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UnsuccessfulLogin extends BaseTest {
    @Test
    public void UnsuccessfulLogin (){
        RequestSpecification req = given(); //detail inputan (header, path parameter, query parameter, request body/payload
        String path = "src/resources/schema/auth/UnsuccessfulLoginUserSchema.json";

        String email = "eve.holt@reqres.in"; //membuat variabel email dg tipe data string yg berisi eve.holt@reqres.in

        JSONObject params = new JSONObject(); //membuat variabel params yang berisi JSON object kosong
        params.put("email",email); //menambahkan object key email yg berisi value dari variabel email yg sudah dibuat sebelumnya pada variabel params

        req.body(params.toString()); //menambahkan JSON object params ke body

        req.header("Content-type","application/json");

        Response response = req.post("/api/login");
        response.then().assertThat()
                .statusCode(400)
                .body(JsonSchemaValidator.matchesJsonSchema(new File(path)));
        System.out.println(response.asString());
    }
}
