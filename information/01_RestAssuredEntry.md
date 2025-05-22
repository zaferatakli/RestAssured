# 📘 REST Assured Eğitimi (Java) – Bölüm 1: Giriş ve Temeller

## 🎯 Hedef Kitle
Bu eğitim, Postman'da manuel API testi yapabilen; Java, TestNG ve Cucumber gibi teknolojilere aşina olan ama **REST Assured** konusunda 
hiç deneyimi olmayan testerlar içindir.

---

## 🧠 REST Assured Nedir?

**REST Assured**, Java dilinde yazılmış bir **REST API test otomasyon framework'üdür**.
- Java ile yazılır.
- HTTP istekleri (GET, POST, PUT, DELETE) gönderip yanıtları doğrulamak için kullanılır.
- Postman'da manuel yaptığın API testlerini, **Java kodu ile otomatik hale** getirmeni sağlar.

---

## ⚙️ REST Assured'in Gücü Nereden Gelir?
- Basit ve okunabilir `given-when-then` yapısı
- JSON, XML gibi formatları destekler
- Serileştirme ve nesne eşleştirme desteği
- JUnit, TestNG ve Cucumber ile kolay entegrasyon

---

## 🔧 Projeye REST Assured Nasıl Eklenir?

### Maven kullanıyorsan:
```xml
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>5.3.0</version>
    <scope>test</scope>
</dependency>
```

### Gradle kullanıyorsan:
```groovy
testImplementation 'io.rest-assured:rest-assured:5.3.0'
```

> Not: Eğer JSON ile çalışacaksan `json-path` ve `jackson` gibi ek kütüphaneler de eklenebilir.

---

## 🛠 İlk REST Assured Testi – GET Metodu

```java
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetExampleTest {

    @Test
    public void getSinglePost() {
        given()
            .baseUri("https://jsonplaceholder.typicode.com")
        .when()
            .get("/posts/1")
        .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("userId", equalTo(1))
            .body("title", notNullValue());
    }
}
```

### Açıklamalar:
- `given()` → Test öncesi ayarlar (base URI, headers vs.)
- `when()` → Ne yapılacak? (get, post, put, delete)
- `then()` → Ne bekleniyor? (status code, response body)

---

## ✅ Test Yapısı

- `@Test`: TestNG anotasyonu
- `statusCode(200)`: HTTP yanıt kodu doğrulaması
- `body("id", equalTo(1))`: JSON içindeki `id` değeri 1 mi?
- `Matchers`: Hamcrest kütüphanesinden gelir

---

## 🚀 Sonraki Bölüm
- POST, PUT, DELETE istekleri
- Request Body ve Header kullanımı
- JSON yanıt verisini nesneye (POJO) çevirme

---

📂 Dosya: `rest-assured-bolum1-giris.md`