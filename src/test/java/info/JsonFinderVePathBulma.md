# Hamcrest Matcher'lar Hakkında Detaylı Bilgi
# 📬 Postman ve Rest Assured JSON Path Kullanımı - Karşılaştırmalı Kılavuz

Bu belge, Postman ve Rest Assured ortamlarında JSON verilerine erişim yollarını karşılaştırmalı ve örneklerle açıklar.

---

## 🔍 Kullanılan JSON Örneği

```json
{
  "country": "United States",
  "country abbreviation": "US",
  "post code": "90210",
  "places": [
    {
      "place name": "Beverly Hills",
      "longitude": "-118.4065",
      "latitude": "34.0901",
      "state": "California",
      "state abbreviation": "CA"
    },
    {
      "place name": "Beverly Hills",
      "longitude": "-118.4065",
      "latitude": "34.0901",
      "state": "California",
      "state abbreviation": "CA"
    }
  ]
}
```

---

## 🧭 Online JSON Yol Bulucu

JSON path'leri hızlıca test etmek için şu siteyi kullanabilirsiniz:

👉 [https://jsonpathfinder.com/](https://jsonpathfinder.com/)

---

## 📘 Karşılaştırmalı JSON Path Erişimleri

| JSON Alanı                       | Postman Kullanımı      | Rest Assured Kullanımı    | Açıklama                                               |
| -------------------------------- | ---------------------- | ------------------------- | ------------------------------------------------------ |
| `id`                             | `body.id`              | `body("id")`              | `id` anahtarına doğrudan erişim                        |
| `countryName`                    | `body.countryName`     | `body("countryName")`     | `countryName` alanına erişim                           |
| `post code`                      | `body["post code"]`    | `body("post code")`       | Boşluk içerdiği için tırnak içinde kullanılmalı        |
| `places` (dizi olarak)           | `body.places`          | `body("places")`          | Tüm `places` dizisini getirir                          |
| İlk `places` elemanı             | `body.places[0]`       | `body("places[0]")`       | İlk öğeyi döndürür                                     |
| İlk öğedeki `state`              | `body.places[0].state` | `body("places[0].state")` | İlk öğenin `state` değerini döndürür (örn. California) |
| Tüm `state` değerleri (`places`) | `body.places[*].state` | `body("places.state")`    | Bütün öğelerin `state` değerlerini getirir             |

---

## 🧪 Rest Assured Kod Örneği

```java
given()
  .when()
    .get("https://api.zippopotam.us/us/90210")
  .then()
    .statusCode(200)
    .body("country", equalTo("United States"))
    .body("post code", equalTo("90210"))
    .body("places[0].state", equalTo("California"))
    .body("places.state", hasItem("California"));
```

---

## 🧪 Postman Kod Örneği (Tests Sekmesi)

```javascript
let body = pm.response.json();

pm.test("Ülke doğru mu?", function () {
  pm.expect(body.country).to.eql("United States");
});

pm.test("İlk eyalet California mı?", function () {
  pm.expect(body.places[0].state).to.eql("California");
});
```

---

## 📌 İpuçları

* JSON path’ler karmaşıklaştıkça `["anahtar adı"]` şeklinde erişim tercih edilebilir.
* Rest Assured, Hamcrest matchers ile birlikte çalışır, bu sayede güçlü doğrulamalar yapılabilir.
* Boşluk içeren anahtar adları her zaman tırnak içine alınmalıdır: `"post code"`, `"place name"` gibi.
* Çoklu değer içeren diziler için `hasItem`, `hasSize` gibi matcher’lar kullanılır.

---

```

Bu `.md` içeriğini, bir dosyaya `postman-vs-restassured-jsonpath.md` adıyla kaydedebilir veya doğrudan IntelliJ/VSCode gibi araçlarda belgeleriniz arasında kullanabilirsiniz. İstersen bu içeriği dosya olarak da sağlayabilirim.
```

------------------------------------------------------------------------------
Postman                                  RestAssured
body = pm.response.Json()                body
body.id                                  body("id") -> id bilgisi verir
body.countryName                          body("countryName")
                                         body("post code")
                                         body("places") tüm dizi
                                         body("places[0]") ilk eleman
                                         body("places[0].state") California
                                         body("places.state") bütün state ler

https://jsonpathfinder.com/  bu siteden locatorlar gibi kolaylıkla test edilebilir
{
    "country": "United States",
    "country abbreviation": "US",
    "post code": "90210",
    "places": [
        {
            "place name": "Beverly Hills",
            "longitude": "-118.4065",
            "latitude": "34.0901",
            "state": "California",
            "state abbreviation": "CA"
        },
        {
            "place name": "Beverly Hills",
            "longitude": "-118.4065",
            "latitude": "34.0901",
            "state": "California",
            "state abbreviation": "CA"
        }
    ]
}


