import model.ZipCode;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class _04_APITestPOJO {
    @Test
    public void extractJsonAll_POJO(){

        ZipCode zipCodeObject =
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")
                        .then()
                        .log().body()
                        .statusCode(200)
                        .extract().body().as(ZipCode.class); // POJO sınıfına dönüştürme
        System.out.println("zipCodeObject.getCountry() = " + zipCodeObject.getCountry());
        System.out.println("zipCodeObject.getPlaces() = " + zipCodeObject.getPlaces());
        System.out.println("zipCodeObject = " + zipCodeObject);
    }
}
