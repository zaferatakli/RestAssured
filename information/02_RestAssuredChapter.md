# 📘 REST Assured Eğitimi (Java) – Bölüm 2: POST, PUT, DELETE & Body/Headers

Bu bölümde, REST Assured kullanarak API'ye veri gönderme (POST), veri güncelleme (PUT), veri silme (DELETE) ve isteklerde gövde (body) ile başlık (header) nasıl kullanılır öğreneceğiz.

---

## 📮 1. POST – Yeni Veri Gönderme

```java
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostExampleTest {

    @Test
    public void createPost() {
        given()
            .baseUri("https://jsonplaceholder.typicode.com")
            .header("Content-type", "application/json")
            .body("{ "title": "foo", "body": "bar", "userId": 1 }")
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
            .body("title", equalTo("foo"))
            .body("body", equalTo("bar"))
            .body("userId", equalTo(1));
    }
}
```

### Açıklama:
- `.header(...)`: API’ye gönderilen başlık (örnek: JSON formatı)
- `.body(...)`: Gönderilen veri (string olarak JSON)
- `.post(...)`: HTTP POST isteği gönderir
- `statusCode(201)`: "Created" yanıt kodu

---

## 🛠 2. PUT – Mevcut Veriyi Güncelleme

```java
@Test
public void updatePost() {
    given()
        .baseUri("https://jsonplaceholder.typicode.com")
        .header("Content-type", "application/json")
        .body("{ "id": 1, "title": "updated", "body": "changed", "userId": 1 }")
    .when()
        .put("/posts/1")
    .then()
        .statusCode(200)
        .body("title", equalTo("updated"))
        .body("body", equalTo("changed"));
}
```

---

## 🗑️ 3. DELETE – Veri Silme

```java
@Test
public void deletePost() {
    given()
        .baseUri("https://jsonplaceholder.typicode.com")
    .when()
        .delete("/posts/1")
    .then()
        .statusCode(200); // veya 204
}
```

---

## 🧾 4. JSON Body’yi POJO Sınıfı ile Göndermek (Daha Temiz Kod)

```java
public class Post {
    public String title;
    public String body;
    public int userId;

    public Post(String title, String body, int userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }
}
```

```java
@Test
public void createPostWithPOJO() {
    Post post = new Post("title with POJO", "body content", 1);

    given()
        .baseUri("https://jsonplaceholder.typicode.com")
        .header("Content-type", "application/json")
        .body(post)
    .when()
        .post("/posts")
    .then()
        .statusCode(201)
        .body("title", equalTo("title with POJO"));
}
```

---

## 🎯 Özet

| İstek Türü | Açıklama                | Method     |
|------------|--------------------------|------------|
| GET        | Veri okuma               | `.get()`   |
| POST       | Yeni veri oluşturma      | `.post()`  |
| PUT        | Mevcut veriyi güncelleme | `.put()`   |
| DELETE     | Veri silme               | `.delete()`|

---

## 🚀 Sonraki Bölüm
- Response verisiyle detaylı çalışma
- JSON verilerini okuma (path, map, POJO)
- Dinamik assertion’lar

---

📂 Dosya: `rest-assured-bolum2-post-put-delete.md`