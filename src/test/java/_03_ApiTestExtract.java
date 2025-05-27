import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _03_ApiTestExtract {
    @Test
    public void extractingJsonPath(){
        //var dataUserID=pm.response.json().id;
        String country =
        given()
                .when().get("http://api.zippopotam.us/us/90210")
                .then()
                .log()
                .body()
                .extract().path("country"); // PATH i country olan değeri EXTRACT yap
        System.out.println("country = " + country);
        Assert.assertEquals(country, "United States"); // TestNG ile doğrulama
    }

    @Test
    public void extractingJsonPath2(){
        /**<pre>// Soru : "http://api.zippopotam.us/us/90210"  endpoint indne dönen
         // place dizisinin ilk elemanının state değerinin  "California"
         // olduğunu testNG Assertion ile doğrulayınız
        </pre>*/

        given()
                .when().get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()
                .body("places[0].state", equalTo("California")) // body içindeki places[0] state değeri "California" mı? assertion
                ;  // extract en son komut olmalı

        String state =
                given()
                        .when().get("http://api.zippopotam.us/us/90210")
                        .then()
                        .log().body()
                        .extract().path("places[0].state");  // extract en son komut olmalı
        System.out.println("state = " + state); // kendimize kontrol
        Assert.assertEquals(state, "California"); // assert
    }
    @Test
    public void extractingJsonPath3() {
        // Soru : "https://gorest.co.in/public/v1/users"  endpoint in den dönen
        // limit bilgisinin 10 olduğunu testNG ile doğrulayınız.

        int limit =
                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .log().body()
                        .extract().path("meta.pagination.limit");

        Assert.assertTrue(limit == 10);
    }
    @Test
    public void extractingJsonPath4() {
        // Soru : "https://gorest.co.in/public/v1/users"  endpoint in den dönen
        // data daki bütün idlerin içinde 7913848 değerinin geçtiğini
        // TestNg assertion ile doğrulayınız.

        ArrayList<Integer> ids =
                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .log().body()
                        .extract().path("data.id");

        System.out.println("idler = " + ids);
        Assert.assertTrue(ids.contains(7913848));
    }

    @Test
    public void extractingJsonPath5() {
        // Soru : "https://gorest.co.in/public/v1/users"  endpoint in den dönen
        // bütün name leri in içiden "Arnesh Achari" değerinin geçtiğini
        // TestNg assertion ile doğrulayınız.

        ArrayList<String> names =
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")
                        .then()
                        .log().body()
                        .extract().path("data.name");

        System.out.println("names = " + names);
        Assert.assertTrue(names.contains("Mukul Agarwal"));
    }

    @Test
    public void extractingJsonPathResponseAll() {

        //sorgudan dönen tüm datayı aldım
        Response responseBody =
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")
                        .then()
                        .extract().response();

        ArrayList<String> names = responseBody.path("data.name");
        ArrayList<Integer> idler = responseBody.path("data.id");
        int limit =  responseBody.path("meta.pagination.limit");

        System.out.println("limit = " + limit);
        System.out.println("idler = " + idler);
        System.out.println("names = " + names);

        Assert.assertTrue(names.contains("Mukul Agarwal"));
        Assert.assertTrue(idler.contains(7913849));
        Assert.assertTrue(limit == 10);
    }
}
