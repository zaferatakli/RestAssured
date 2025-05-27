Harika, şimdi adım adım gidelim:
🧪 **Hem Postman'de hem Java'da nasıl çalışıyorlar**, örnekle görelim.
🎯 Amacımız: Hem **`path param`** hem **`query param`** nedir,
**Postman'de nasıl girilir**, **Java Rest Assured’da nasıl kodlanır**,
hepsini anlamak.

---

## 🔍 1. TEORİK BİLGİ (Kısaca Tekrar):

| Özellik      | Path Param (Yol)                  | Query Param (Sorgu)                     |
| ------------ | --------------------------------- | --------------------------------------- |
| Ne?          | URL’nin gövdesi                   | URL’nin sonunda, `?` ile başlar         |
| Kullanımı    | `/users/123`                      | `/users?page=2&gender=male`             |
| Anlamı       | Tam olarak **hangi kullanıcıyı**  | **Filtre, arama, sıralama** yapmak için |
| Postman yeri | Params sekmesinde "Path Variable" | Params sekmesinde "KEY = VALUE" kısmı   |
| Java’da      | `.pathParam("id", 123)`           | `.queryParam("page", 2)`                |

---

## 📦 2. ÖRNEK API: `https://gorest.co.in/public/v1/users`

Bu API ile kullanıcı listesine erişiyoruz.
Detayları:

| Amaç  | URL                 | Açıklama                         |
| ----- | ------------------- | -------------------------------- |
| Liste | `GET /users?page=2` | Sayfa 2'deki kullanıcıları getir |
| Detay | `GET /users/123`    | ID’si 123 olan kullanıcıyı getir |

---

## 🛠️ 3. POSTMAN’DE NASIL YAPILIR?

### 🔸 Query Param (Sayfa Seçimi):

#### Adımlar:

1. `GET` metodunu seç.
2. URL'ye yaz: `https://gorest.co.in/public/v1/users`
3. Üstteki sekmelerden `Params`'a tıkla.
4. Aşağıya şu şekilde gir:

   | KEY  | VALUE |
      | ---- | ----- |
   | page | 2     |
5. Bu, `?page=2` olarak URL'ye eklenir.

---

### 🔸 Path Param (Belirli Kullanıcı):

1. `GET` metodunu seç.
2. URL'ye yaz: `https://gorest.co.in/public/v1/users/123`
   (ya da: `https://gorest.co.in/public/v1/users/:id`)
3. Eğer `:id` yazdıysan, Postman sana “Path Variable” kısmında `id = 123` girmeni ister.
4. `Send` deyince: ID’si 123 olan kullanıcı gelir.

---

## ☕ 4. JAVA REST ASSURED ÖRNEĞİ (Açıklamalı)

### 🔷 A. Query Param Kullanımı

```java
@Test
public void testQueryParam() {
    // Sayfa 2'deki kullanıcıları çağır
    given()
        .queryParam("page", 2)
        .log().uri()
    .when()
        .get("https://gorest.co.in/public/v1/users")
    .then()
        .statusCode(200)
        .body("meta.pagination.page", equalTo(2));
}
```

📌 Bu ne yapar?

* URL şu hale gelir: `https://gorest.co.in/public/v1/users?page=2`
* Sayfa 2 çağrılır.
* Gelen cevaptaki `meta.pagination.page` değeri `2` mi diye kontrol edilir.

---

### 🔷 B. Path Param Kullanımı

```java
@Test
public void testPathParam() {
    int userId = 123; // örnek kullanıcı ID'si

    given()
        .pathParam("id", userId)
        .log().uri()
    .when()
        .get("https://gorest.co.in/public/v1/users/{id}")
    .then()
        .statusCode(200)
        .body("data.id", equalTo(userId));
}
```

📌 Bu ne yapar?

* URL şu hale gelir: `https://gorest.co.in/public/v1/users/123`
* ID’si 123 olan kullanıcıyı getirir.
* Cevaptaki `data.id` değeri 123 mü diye kontrol eder.

---

## 🧠 HAFIZADA KALSIN DİYE BASİT ÖZET

\| “Sokağın Adresi” gibi olan        | **Path Param** → `/users/123`           |
\| “Siparişim şu olsun” diyen filtre | **Query Param** → `/users?page=2`       |

---

## ❓EKSTRA: Postman'de hangisi nerede?

| POSTMAN’de Göreceğin Yer     | Açıklama                                 |
| ---------------------------- | ---------------------------------------- |
| `Params > Key=page, Value=2` | Query param olarak `?page=2`             |
| `Path Variables > id = 123`  | URL’deki `{id}` yerine `123` yerleştirir |

---

İstersen bir de **`Post` veya `Delete` metoduyla nasıl kullanılır?** veya **birden fazla query param nasıl verilir?** gibi ek senaryolarla devam edebiliriz.
Hazır mısın?
