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
     * - `Matchers`: Hamcrest kütüphanesinden gelir</pre>*/
    @Test
    public void Test() {
        given(). //`given()` → Test öncesi ayarlar (base URI, headers vs., token, body)
                when(). //`when()` → Ne yapılacak? (get, post, put, delete) gidiş metodu , endpoint, apinin çağrılma kısmı
                then();//`then()` → Ne bekleniyor? (status code, response body)
    }

    @Test
    public void Test01(){
        given()
                .when().get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body(); // dönüş datalarını yaz / all: tüm dönüş bilgilerini yaz;
    }
    @Test
    public void Test01_01(){
        given().baseUri("http://api.zippopotam.us")
                .when().get("/us/90210")
                .then()
                .log().body(); // dönüş datalarını yaz / all: tüm dönüş bilgilerini yaz;
    }
}
