
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;

public class RestApi1 {

    @Test
    public void get_token_status_fail_test(){
        given().queryParam("lang","es").when()
                .log().all()
                .post("https://webapi.segundamano.mx/nga/api/v1.1/private/accounts")
                .then().statusCode(400);
    }


    @Test
    public void Test_token_fail() {
        //Request an account token without authorization header
        RestAssured.baseURI = String.format("https://webapi.segundamano.mx/nga/api/v1.1/private/accounts");
        Response response = given().log().all()
                .post();
        //validations
        System.out.println("Status expected: 400");
        System.out.println("Result: " + response.getStatusCode());
        assertEquals(400, response.getStatusCode());
        String errorCode = response.jsonPath().getString("error.code");
        System.out.println("Error Code expected: VALIDATION FAILED \nResult: " + errorCode);
        assertEquals("VALIDATION_FAILED", errorCode);
    }

    @Test
    public void Create_account() {
        RestAssured.baseURI = String.format("https://webapi.segundamano.mx/nga/api/v1.1/private/accounts?lang=es");
        Response response = given().log().all()
                .post();
        System.out.println("Result: " + response.getStatusCode());


    }

    @Test
    public void validateStatusCode() {
        given().queryParam("lang", "es").when()
                .get("https://webapi.segundamano.mx/nga/api/v1.1/private/accounts")
                .then().statusCode(401);
    }



}
