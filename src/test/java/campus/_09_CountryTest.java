package campus;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;

/**<pre>
    Bugün Campus Test etmeyi planlıyoruz
    1- Country Test
    2- Bir menu daha yapacağız

    Önce yapılacakları hazırlamıştık
    Login olunup, dönen token alınacak
    bir spec halinde sonra testleri çalıştırmıştık

    beforeClass
    Setup

    @Test
    Create
    GetById
    Update
    Delete
    DeleteNegative</pre>*/

public class _09_CountryTest {
    Faker faker = new Faker();
    RequestSpecification reqSpec;
    String countryID = "";
    String countryName = faker.address().country() + faker.number().digits(5);
    String countryCode = faker.address().countryCode() + faker.number().digits(5);
    Map<String, String> newCountry = new HashMap<>();

    @BeforeClass
    public void setUp() {
        baseURI = "https://test.mersys.io"; // RestAsured un kendi değişkeni

        // login ol , token al, spec i hazırla
        Map<String, String> credential = new HashMap<>();
        credential.put("username", "Campus25");
        credential.put("password", "Campus.2524");
        credential.put("rememberMe", "true");

        String token =
                given()
                        .contentType(ContentType.JSON)
                        .body(credential)

                        .when()
                        .post("/auth/login")// eğer http ile başlamıyorsa baseURI başa otomatik eklenir.

                        .then()
                        //.log().body()
                        .statusCode(200)
                        .extract().path("access_token");
        System.out.println("token = " + token);

        reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }

    @Test
    public void createCountry() {
        newCountry.put("name", countryName);
        newCountry.put("code", countryCode);

        countryID = given()
                .spec(reqSpec)
                .body(newCountry)

                .when()
                .post("/school-service/api/countries")

                .then()
                .log().body()
                .statusCode(201)
                .extract().path("id");

        System.out.println("countryID = " + countryID);
    }

    @Test(dependsOnMethods = "createCountry")
    public void createCountryNegative(){
        given()
                .spec(reqSpec)
                .body(newCountry)

                .when()
                .post("/school-service/api/countries")

                .then()
                .log().body()
                .statusCode(400);
    }

    @Test(dependsOnMethods = "createCountry")
    public void updateCountry() {
        Map<String, String> uptCountry = new HashMap<>();
        uptCountry.put("id", countryID);
        uptCountry.put("name", faker.country().name() + faker.number().digits(5));
        uptCountry.put("code", faker.code() + faker.number().digits(5));

        given()
                .spec(reqSpec)
                .body(uptCountry)

                .when()
                .put("/school-service/api/countries")

                .then()
                .log().body()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "updateCountry")
    public void deleteCountry() {
        given()
                .spec(reqSpec)

                .when()
                .delete("/school-service/api/countries/" + countryID)

                .then()
                .log().body()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "deleteCountry")
    public void deleteCountryNegative() {
        given()
                .spec(reqSpec)

                .when()
                .delete("/school-service/api/countries/" + countryID)

                .then()
                .log().body()
                .statusCode(400)  // Jenkins de hata görünmesi için hatalı yapıldı
        ;
    }
        /**<pre>/
         * TODO: GetCountryId ->  get("school-service/api/countries/"+countryID) bir tane country get
         * AllCountryId ->  get("school-service/api/countries"); id leri list şeklinde alıcaksınız
         * - GetCountryById yi Create den sonra ya ekleyiniz.
         * - CreateCountryNegative   Create den sonra ya ekleyiniz.
         * - Bütün Country leri siliniz. (Günün sorusu)</pre>*/
}
