import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class _02_APITestSpec {
    RequestSpecification reqSpec;
    ResponseSpecification resSpec;

    @BeforeClass
    public void Setup() {
        ///  1- Endpoint i çağırmadna önce hazırlıkların yapıldığı bölüm : Request, gidecek body, token
        reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)// istek paketi setlenmesi
                .log(LogDetail.URI)
                .build(); // log.uri
                //token de buraya yazilabilir.

        /// 2- Endpoint in çağrıldığı bölüm  : Endpoint in çağrılması(METOD: GET,POST ..)
        resSpec = new ResponseSpecBuilder() // cevap geldikten sonraki yapılacaklar
                .log(LogDetail.BODY)
                .expectContentType(ContentType.JSON)
                .build() // log.body
        ;
    }

    @Test
    public void Test01() {
        given().contentType(ContentType.JSON) // giden body cinsi
                .log().uri() // log.uri
                //token de buraya yazilabilir.
                .when().get("https://gorest.co.in/public/v1/users") // endpoint çağrılıyor

                .then().contentType(ContentType.JSON)
                .log().body()
        ;
    }

    @Test
    public void Test01_01() {
        given()
                .spec(reqSpec) // request spec kullanılıyor
                .when().get("https://gorest.co.in/public/v1/users") // endpoint çağrılıyor
                .then()
                .spec(resSpec) // response spec kullanılıyor
        ;
    }

    @Test
    public void Test02() {
        given().contentType(ContentType.JSON) // giden body cinsi
                .log().uri() // log.uri
                //token de buraya yazilabilir.
                .when().get("https://gorest.co.in/public/v1/users") // endpoint çağrılıyor

                .then().contentType(ContentType.JSON)
                .log().body()
        ;
    }
    @Test
    public void Test02_01() {
        given()
                .spec(reqSpec) // request spec kullanılıyor
                .when().get("https://gorest.co.in/public/v1/users") // endpoint çağrılıyor
                .then()
                .spec(resSpec) // response spec kullanılıyor
        ;
    }
}
