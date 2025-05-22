# 📘 REST Assured Eğitimi (Java) – Bölüm 3: Yanıtlarla (Response) Çalışma ve Dinamik Doğrulamalar

Bu bölümde, bir API'den dönen yanıtı nasıl okuyacağımızı, doğrulayacağımızı ve testlerimizde nasıl kullanacağımızı öğreneceğiz.

---

## 📥 1. Response Nesnesini Almak

```java
import io.restassured.response.Response;

Response response = given()
    .baseUri("https://jsonplaceholder.typicode.com")
.when()
    .get("/posts/1");

System.out.println(response.asString()); // Yanıtı string olarak yazdırır
```

---

## 🔎 2. Yanıttan Veri Çekmek – `path()` Yöntemi

```java
String title = response.path("title");
int userId = response.path("userId");

System.out.println("Title: " + title);
System.out.println("User ID: " + userId);
```

---

## 🔁 3. Dinamik Doğrulama (Dynamic Assertions)

```java
@Test
public void verifyDynamicResponse() {
    Response response = given()
        .baseUri("https://jsonplaceholder.typicode.com")
    .when()
        .get("/posts/1");

    String title = response.path("title");

    assert title != null && !title.isEmpty() : "Başlık boş gelmemeli!";
    assert title.length() > 5 : "Başlık 5 karakterden uzun olmalı!";
}
```

---

## 🧾 4. Yanıtı Map’e Çekmek

```java
Map<String, Object> responseMap = response.as(Map.class);

System.out.println(responseMap.get("title"));
System.out.println(responseMap.get("body"));
```

> Bu yöntem JSON yanıtını Java Map'e çevirerek alanlara daha rahat erişmeni sağlar.

---

## 🧱 5. Yanıtı POJO’ya Çekmek

Diyelim ki aşağıdaki gibi bir sınıfımız var:

```java
public class Post {
    public int userId;
    public int id;
    public String title;
    public String body;
}
```

Yanıtı bu sınıfa çekmek için:

```java
Post post = response.as(Post.class);
System.out.println(post.title);
System.out.println(post.body);
```

---

## 📋 6. Headers ve Status Code Kontrolleri

```java
int statusCode = response.getStatusCode();
String contentType = response.getHeader("Content-Type");

assert statusCode == 200;
assert contentType.contains("application/json");
```

---

## 🧪 7. `extract()` ile Kısa Yoldan Veri Alma

```java
String title = 
    given()
        .baseUri("https://jsonplaceholder.typicode.com")
    .when()
        .get("/posts/1")
    .then()
        .extract()
        .path("title");

System.out.println("Başlık: " + title);
```

---

## 🎯 Özet

| Yöntem            | Açıklama                        |
|-------------------|----------------------------------|
| `response.asString()` | Tüm yanıtı string olarak verir |
| `response.path("key")` | JSON içinden veri çeker       |
| `response.as(Map.class)` | JSON'u Map'e çevirir         |
| `response.as(Class)` | JSON'u POJO’ya çevirir         |
| `extract().path()` | Doğrudan test içinde veri çeker |

---

## 🚀 Sonraki Bölüm
- Query Parametreler
- Path Parametreler
- Header, Cookie, Auth
- Cucumber ile REST Assured kullanımı

---

📂 Dosya: `rest-assured-bolum3-response.md`