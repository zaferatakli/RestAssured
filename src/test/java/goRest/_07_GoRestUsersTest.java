package goRest;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _07_GoRestUsersTest {
    RequestSpecification reqSpec;
    Faker faker = new Faker();
    int userID = 0;

    @BeforeClass
    public void Setup(){ // başlangıç işlemleri
        // token ve başlangıç set ayarları için spec oluşturuluyor
        reqSpec = new RequestSpecBuilder()   // istek paketi setlenmesi
                .setContentType(ContentType.JSON)  // giden body cinsi
                .addHeader("Authorization", "Bearer 09bc6d97af908d7ed5425a1d4854d97faedb5021c0515e1f1a4de7a7a0b890fc")
                .log(LogDetail.URI)  // log.uri
                .build();
    }

    @Test
    public void CreateUser() {
        /**<pre>
         *  giden body ye ihtiyacım
         *  giden body hazırlama :  1.Yöntem
         *
         *  String bodyUser="{" +
         * "  \"name\":\"{{$randomFullName}}\"," +
         * "  \"gender\":\"male\"," +
         * "  \"email\":\"{{$randomEmail}}\"," +
         * "  \"status\":\"active\"" +
         * "}";
         *
         * giden body hazırlama :  2.Yöntem
         *       User newUser2=new User();
         *       newUser2.setName("İsmet Temur");
         *       newUser2.setGender("male");
         *       newUser2.setEmail("İsmetTemur@gmail.com");
         *       newUser2.setStatus("active");</pre>*/

        //  giden body hazırlama :  3.Yöntem
        String rndFullName = faker.name().fullName();
        String rndEmail = faker.internet().emailAddress();

        Map<String, String> newUser = new HashMap<>();
        newUser.put("name", rndFullName);
        newUser.put("gender", "male");
        newUser.put("email", rndEmail);
        newUser.put("status", "active");

        userID =   //collection varible daki userID y set ettim
                given().spec(reqSpec) //json ı seçtim token ı verdim
                        .body(newUser)  // giden body yi verdim

                        .when().post("https://gorest.co.in/public/v2/users")

                        .then().statusCode(201).log().body()  // gelen data yı yazdır
                        .extract().path("id");

        System.out.println("userID = " + userID);
    }

    //GetUserById testini yapınız
    @Test(dependsOnMethods = "CreateUser")
    public void GetUserById() {
        given().spec(reqSpec)
                .when().get("https://gorest.co.in/public/v2/users/" + userID)

                .then().statusCode(200)  // status validation
                .body("id", equalTo(userID));  // data validation;
    }

    // UpdateUser testini yapınız
    @Test(dependsOnMethods = "GetUserById")
    public void UpdateUser() {
        Map<String, String> uptUser = new HashMap<>();
        uptUser.put("name", faker.name().fullName());
        given().spec(reqSpec).body(uptUser).when().put("https://gorest.co.in/public/v2/users/" + userID)

                .then().statusCode(200).log().body();
    }

    // DeleteUser testini yapınız
    @Test(dependsOnMethods = "UpdateUser")     // bu aşamadan sonra class çalıştırılmalı
    public void DeleteUser() {
        given().spec(reqSpec)

                .when().delete("https://gorest.co.in/public/v2/users/" + userID)

                .then().statusCode(204);
    }

    @Test(dependsOnMethods = "DeleteUser")
    public void DeleteUserNegative() {
        given().spec(reqSpec)
                .when().delete("https://gorest.co.in/public/v2/users/" + userID)
                .then()
                .log().status()  // log.status
                .statusCode(404);
    }
}