import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shared.BaseTest;

import static io.restassured.RestAssured.given;

public class SecondTest extends BaseTest {
    @Test (dataProvider = "data-users")//penanda kalo method second itu untuk testing
    public void second(String name, String job)
    {
        JSONObject params = new JSONObject();
        params.put("name",name);
        params.put("job",job);

        //biar bisa tampil
        System.out.println(params.toString());
    }

    @DataProvider(name = "data-users")
    Object[][] DataUsers()
    {
        Object[][] users = new Object[][]{
                {"John","BE"},
                {"Doe","FE"},
                {"Tere","QA"}
        };
        return users;
    }

}
