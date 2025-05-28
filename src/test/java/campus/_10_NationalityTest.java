package campus;

// https://test.mersys.io/school-service/api/nationality
// id ve name

import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class _10_NationalityTest extends CampusParent {
    String nationalityID = "";


    @Test
    public void createNationality() {
        String natName = faker.nation().nationality() + faker.number().digits(5);

        Map<String, String> newNat = new HashMap<>();
        newNat.put("name", natName);

        nationalityID = given().spec(reqSpec).body(newNat)

                .when().post("/school-service/api/nationality")

                .then().log().body().statusCode(201).extract().path("id");

        System.out.println("nationalityID = " + nationalityID);
    }

    @Test(dependsOnMethods = "createNationality")
    public void updateNationality() {
        Map<String, String> updNat = new HashMap<>();
        updNat.put("id", nationalityID);
        updNat.put("name", faker.nation().nationality() + faker.number().digits(5));

        given().spec(reqSpec).body(updNat)

                .when().put("/school-service/api/nationality")

                .then().log().body().statusCode(200);
    }

    @Test(dependsOnMethods = "updateNationality")
    public void deleteNationality() {
        given().spec(reqSpec)

                .when().delete("/school-service/api/nationality/" + nationalityID)

                .then().log().body().statusCode(200);
    }

    @Test(dependsOnMethods = "deleteNationality")
    public void deleteNegativeNationality() {
        given().spec(reqSpec)

                .when().delete("/school-service/api/nationality/" + nationalityID)

                .then().log().body().statusCode(400);
    }


    // TODO: GetNationalityId ->  get("school-service/api/nationality/"+nationalityID) bir tane nationality get
    //       AllNationalityId ->  get("school-service/api/nationality"); id leri list şeklinde alıcaksınız
    //1- GetNationalityId yi Create den sonra ya ekleyiniz.
    //2- CreateNationalityNegative   Create den sonra ya ekleyiniz.
    //3- Bütün Nationality leri siliniz. (Günün sorusu)
}
