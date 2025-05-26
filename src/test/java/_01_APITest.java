import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class _01_APITest {
    /**<pre>
     * 1- Endpoint i çağırmadna önce hazırlıkların yapıldığı bölüm : Request, gidecek body, token
     * 2- Endpoint in çağrıldığı bölüm  : Endpoint in çağrılması(METOD: GET,POST ..)
     * 3- Endpoint çağrıldıktan sonraki bölüm : Response, Test(Assert), data</pre>*/

    /**<pre>
     * ### Açıklamalar:
     * - `given()` → Test öncesi ayarlar (base URI, headers vs.)
     * - `when()` → Ne yapılacak? (get, post, put, delete)
     * - `then()` → Ne bekleniyor? (status code, response body)
     * ---
     * ## ✅ Test Yapısı
     * - `@Test`: TestNG anotasyonu
     * - `statusCode(200)`: HTTP yanıt kodu doğrulaması
     * - `body("id", equalTo(1))`: JSON içindeki `id` değeri 1 mi?
     * - `Matchers`: HamcrestInfo.md kütüphanesinden gelir</pre>*/
    @Test
    public void entry() {
        given(). //`given()` → Test öncesi ayarlar (base URI, headers vs., token, body)
                when(). //`when()` → Ne yapılacak? (get, post, put, delete) gidiş metodu , endpoint, apinin çağrılma kısmı
                then();//`then()` → Ne bekleniyor? (status code, response body)
    }

    @Test
    public void statusCodeTest() {
        given().when().get("http://api.zippopotam.us/us/90210").then().log().body() // dönüş datalarını yaz / all: tüm dönüş bilgilerini yaz;
                .statusCode(200); //assertion
    }

    @Test
    public void statusCodeTest_01() {
        given().baseUri("http://api.zippopotam.us").when().get("/us/90210").then().log().body() // dönüş datalarını yaz / all: tüm dönüş bilgilerini yaz;
                .statusCode(200); // assertion
    }

    @Test
    public void contentTypeTest() {
        given().baseUri("http://api.zippopotam.us").when().get("/us/90210").then().log().body() // dönüş datalarını yaz / all: tüm dönüş bilgilerini yaz;
                .log().status().statusCode(200)//assertion
                .contentType(ContentType.JSON) // içerik tipi kontrolü JSON assertion
        ;
    }

    /**<pre>
     * ### JSON Path
     * Postman                                  RestAssured
     * body = pm.response.json()                body
     * body.id                                  body("id") -> id bilgisi verir
     * body.countryName                         body("countryName")
     *                                          body("post code")</pre>*/

    @Test
    public void checkCountryInResponseBody() {
        given().baseUri("http://api.zippopotam.us").when().get("/us/90210").then().log().body() // dönüş datalarını yaz / all: tüm dönüş bilgilerini yaz;
                .log().status().statusCode(200)//assertion
                .contentType(ContentType.JSON) // içerik tipi kontrolü JSON assertion
                .body("country", equalTo("United States")) // body içindeki country değeri "United States" mi? assertion
                .body("places[0].'place name'", equalTo("Beverly Hills")) // body içindeki places[0] 'place name' değeri "Beverly Hills" mi? assertion
                .body("places[0][\"place name\"]", equalTo("Beverly Hills")) // body içindeki places[0] 'place name' değeri "Beverly Hills" mi? assertion
        ;
    }

    @Test
    public void checkHasItem() {
        // Soru : "http://api.zippopotam.us/tr/01000"  endpoint in dönen
        // places dizisinin herhangi bir elemanında  "Dörtağaç Köyü" değerinin
        // olduğunu doğrulayınız
        Response response = RestAssured.given().baseUri("http://api.zippopotam.us").when().get("/tr/01000").then()
                //.log().body()
                .statusCode(200).body("places.'place name'", hasItem("Burun Köyü")).body("places[0].'place name'", containsString("Dervişler")) // body içindeki places[0] 'place name' değeri "Beverly Hills" mi? assertion
                //.body("places.size()",equalTo(71)) // places dizisinin boyutu 1 mi? assertion
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int placesSize = jsonPath.getList("places").size();
        System.out.println("places dizisinin boyutu: " + placesSize);
        Assert.assertEquals(true, placesSize == 71);
    }

    @Test
    public void bodyArrayHasSizeTest() {
        // Soru : "http://api.zippopotam.us/us/90210"  endpoint in dönen
        // places dizisinin dizi uzunluğunun 1 olduğunu doğrulayınız.

        given().baseUri("http://api.zippopotam.us").when().get("/us/90210").then().log().body() // dönüş datalarını yaz / all: tüm dönüş bilgilerini yaz;
                .log().status().statusCode(200)//assertion
                .body("places", hasSize(1)) // body içindeki
                .body("places.size()", equalTo(1));
    }

    @Test
    public void combiningTest() {
        given().baseUri("http://api.zippopotam.us").when().get("/us/90210").then().log().body() // dönüş datalarını yaz / all: tüm dönüş bilgilerini yaz;
                .log().status().statusCode(200) // assert
                .contentType(ContentType.JSON)  // assert
                .body("places", hasSize(1)) // assert
                .body("places.'place name'", hasItem("Beverly Hills")) // assert
                .body("country", equalTo("United States")) // assert
        ;
    }

    @Test
    public void pathParamTest() {
        given().pathParam("ulke", "us")  // değişkenler hazırlandı
                .pathParam("pk", "90210") // değişkenler hazırlandı
                .log().uri() // URI'yi logla . olusan endpointi yazdirma

                .when().get("http://api.zippopotam.us/{ulke}/{pk}").then().log().body();
    }

    @Test
    public void queryParamTest() {
        //https://gorest.co.in/public/v1/users?page=3
        given().param("page", 3) // query parametre olarak page=3
                .log().uri() // oluşacak endpoint i yazdıralım
                .when().get("https://gorest.co.in/public/v1/users?page=3").then().log().body() // dönüş datalarını yaz / all: tüm dönüş bilgilerini yaz;
        ;
    }

    @Test
    public void queryParamTest2() {
        // https://gorest.co.in/public/v1/users?page=3
        // bu linkteki 1 den 10 kadar sayfaları çağırdığınızda response daki
        // donen page degerlerinin çağrılan page nosu ile aynı
        // olup olmadığını kontrol ediniz.

        for (int p = 1; p <= 10; p++) {
            given().param("page", p).log().uri()
                    // query parametre olarak page=3
                    .when().get("https://gorest.co.in/public/v1/users").then().body("meta.pagination.page", equalTo(p)) // response body içindeki meta.pagination.page değeri p ile eşit mi?
            ;
        }

        for (int p = 1; p <= 10; p++) {
            given().param("page", p).log().uri()

                    .when().get("https://gorest.co.in/public/v1/users")

                    .then().body("meta.pagination.page", equalTo(p));
        }
    }

    @Test
    public void extractingJsonPath2() {
        // Soru : "http://api.zippopotam.us/us/90210"  endpoint indne dönen
        // place dizisinin ilk elemanının state değerinin  "California"
        // olduğunu testNG Assertion ile doğrulayınız
    }
}
