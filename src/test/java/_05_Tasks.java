import io.restassured.http.ContentType;
import model.ToDo;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class _05_Tasks {
    /**
     * Task 1
     * create a request to https://jsonplaceholder.typicode.com/todos/2
     * expect status 200
     * expect content type JSON
     * expect title in response body to be "quis ut nam facilis et officia qui"
     */

    @Test
    public void Task01(){
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/todos/2")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title", equalTo("quis ut nam facilis et officia qui"))
                .log().body();
    }
    /**
     * Task 2
     * create a request to https://jsonplaceholder.typicode.com/todos/2
     * expect status 200
     * expect content type JSON
     * a) expect response "completed" status to be false(hamcrest)
     * b) extract "completed" field and testNG assertion(testNG)
     */

    @Test
    public void Test02(){
        // a)

        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/todos/2")
                .then()
                .statusCode(200)
                .body("completed", equalTo(false))
                .log().body();

        // b)
        boolean completedData =
                given()
                        .when()
                        .get("https://jsonplaceholder.typicode.com/todos/2")
                        .then()
                        .statusCode(200)
                        .extract().path("completed");

        Assert.assertFalse(completedData, "Completed status should be false");
    }

    /** Task 3
     * create a request to https://jsonplaceholder.typicode.com/todos/2
     * Converting Into POJO body data and write
     */

    @Test
    public void Test03(){
        ToDo todoObject =
                given()
                        .when()
                        .get("https://jsonplaceholder.typicode.com/todos/2")
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract().body().as(ToDo.class); // POJO sınıfına dönüştürme

        System.out.println("todoObject.getUserId() = " + todoObject.getUserId());
        System.out.println("todoObject.getId() = " + todoObject.getId());
        System.out.println("todoObject.getTitle() = " + todoObject.getTitle());
        System.out.println("todoObject.isCompleted() = " + todoObject.isCompleted());


    }

}
