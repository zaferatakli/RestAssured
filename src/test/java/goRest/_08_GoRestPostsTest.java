package goRest;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _08_GoRestPostsTest {
    RequestSpecification reqSpec;
    Faker faker = new Faker();
    int postID = 0;
    int userID = 0; // Kullanıcı ID'si, post oluşturmak için gerekli

    @BeforeClass
    public void Setup() // başlangıç işlemleri
    {
        // token ve başlangıç set ayarları için spec oluşturuluyor
        reqSpec = new RequestSpecBuilder()   // istek paketi setlenmesi
                .setContentType(ContentType.JSON)  // giden body cinsi
                .addHeader("Authorization", "Bearer f92bf3f56439b631d9ed928b3540968e747c8e75309c876420fb349cbb420ed1").log(LogDetail.URI)  // log.uri
                .build();
    }

    @Test
    public void getPost() {
        userID = given().spec(reqSpec)

                .when().get("https://gorest.co.in/public/v2/posts")

                .then().statusCode(200).extract().path("user_id[0]");

        System.out.println("User ID = " + userID);
    }

    @Test(dependsOnMethods = "getPost")
    public void CreatePost() {
        String rndTitle = faker.lorem().sentence();
        String rndParagraph = faker.lorem().paragraph();

        Map<String, String> newPost = new HashMap<>();
        newPost.put("user_id", String.valueOf(userID)); // Kullanıcı ID'sini ekliyoruz
        newPost.put("title", rndTitle);
        newPost.put("body", rndParagraph);

        postID = given().spec(reqSpec).body(newPost)

                .when().post("https://gorest.co.in/public/v2/posts")

                .then().statusCode(201).log().body().extract().path("id");

        System.out.println("newPost = " + newPost);
    }

    @Test(dependsOnMethods = "CreatePost")
    public void GetPostById() {
        given().spec(reqSpec)
                .when().get("https://gorest.co.in/public/v2/posts/" + postID)
                .then().statusCode(200).body("id", equalTo(postID));
    }

    @Test(dependsOnMethods = "GetPostById")
    public void UpdatePost() {
        Map<String, String> newPost = new HashMap<>();
        newPost.put("title", faker.lorem().sentence());

        given().spec(reqSpec)
                .body(newPost)
                .when().put("https://gorest.co.in/public/v2/posts/" + postID)
                .then().log().body().statusCode(200);
    }

    @Test(dependsOnMethods = "UpdatePost")
    public void DeletePost() {
        given().spec(reqSpec)
                .when().delete("https://gorest.co.in/public/v2/posts/" + postID)
                .then().statusCode(204);
    }

    @Test(dependsOnMethods = "DeletePost")
    public void DeletePostNegative() {
        given().spec(reqSpec)

                .when().delete("https://gorest.co.in/public/v2/posts/" + postID)

                .then().statusCode(404);
    }
}
