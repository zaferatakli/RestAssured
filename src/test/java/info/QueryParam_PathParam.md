Kesinlikle! Şimdi sana **çok basit**, günlük konuşma diliyle anlatayım. Amacım şunu yapabilmek:
**Bir daha karıştırmamak için kafanda net bir resim oluşturalım.**

---

## 🍕 ÖRNEK 1 – PİZZA DÜKKANI (URL üzerinden sipariş)

Hayal et:
Bir pizza dükkanının web sitesi var.
Sen bu URL'leri kullanarak sipariş veriyorsun.

---

### 🔷 1. PATH PARAM → Kime, Neye gideceğini söylersin

```
https://pizza.com/menu/12
```

Bu URL ne demek?

* `menu` → Menüden
* `12` → 12 numaralı pizzayı istiyorum

🔹 `12` burada bir **path param**.
🔸 Çünkü: **Adresi belirtiyor.**
➡️ O pizzaya doğrudan gitmek istiyorsun.

> ✅ Path param = "Adresin içi", yani doğrudan hangi pizzaya baktığını söylüyorsun.

---

### 🔷 2. QUERY PARAM → Nasıl bir pizza istiyorsun?

```
https://pizza.com/menu?size=large&topping=cheese
```

Bu URL ne demek?

* Menüde pizza arıyorum ama...
* `size=large` → Büyük boy
* `topping=cheese` → Üzerine peynir

🔹 Burada `size` ve `topping` **query param**.

> ✅ Query param = **Sipariş notu, istek** gibidir
> ➡️ URL’nin sonuna `?` ile başlar, filtreleme veya seçenek belirleme için kullanılır.

---

## 💡 FARK NETLEŞSİN DİYE:

| Kullanım            | URL Örneği                      | Ne demek?                           |
| ------------------- | ------------------------------- | ----------------------------------- |
| `GET /users/5`      | `https://site.com/users/5`      | 5 numaralı kullanıcıyı getir 🧑     |
| `GET /users?page=2` | `https://site.com/users?page=2` | 2. sayfadaki kullanıcıları getir 🔍 |

---

## 🔧 JAVA REST ASSURED’DA:

### ✅ Path Param:

```java
given()
  .pathParam("userId", 5)
.when()
  .get("https://site.com/users/{userId}");
```

👉 `/users/5` şeklinde çağırır.

---

### ✅ Query Param:

```java
given()
  .queryParam("page", 2)
.when()
  .get("https://site.com/users");
```

👉 `/users?page=2` şeklinde çağırır.

---

## 🎯 HAFIZADA TUTMAK İÇİN TÜYO:

| 🔑 Anahtar Kelime | Anlamı                         | Ne için?                  |
| ----------------- | ------------------------------ | ------------------------- |
| Path = **Yol**    | URL’nin gövdesinde ne var?     | Kime, hangi kaynağa?      |
| Query = **Soru**  | URL’nin sonunda ne soruyorsun? | Filtre, sırala, arama vs. |

---

## 🧠 Hafızada Kalması İçin Cümle:

> 👉 *"Path param kime gittiğimi söyler, Query param ne istediğimi anlatır."*

---

İstersen küçük bir uygulamalı örnek vereyim: Sana 1 tane `path param`, 1 tane `query param` içeren `Rest Assured` testi yazayım ve çıktıyı birlikte inceleyelim. Yapalım mı?



# Path Param ve Query Param Nedir? Nasıl Kullanılır?

---

## 👣 1. ÖNCE BİR BENZETME:

### 🏢 Bir ofis düşün:

Ofiste bir **dosya dolabı** var.
Dolabın içindeki **çekmecelerde dosyalar** var.
Sen bir dosyayı arıyorsun.

---

### 🔷 **Path Param (Yol Parametresi)**:

**“Hangi çekmecedeki dosya?”**
Yani doğrudan **hedefi işaret eder**.
🟢 Adresin bir parçasıdır.

📌 Örnek:

```
https://api.site.com/users/5
```

Burada:

* `users` → Kullanıcılar çekmecesi
* `5` → 5 numaralı kullanıcı

🟡 `5` burada bir **path parametresidir**.

> ❓ Ne işe yarar? → Belirli bir şeyi almak için.
> ❗ Neyi ifade eder? → URL’nin bir parçası.
> ✅ Nasıl kullanılır?

```java
.pathParam("userId", 5)
.get("/users/{userId}")
```

---

### 🔷 **Query Param (Sorgu Parametresi)**:

**“Çekmecedeki hangi tür dosyaları istiyorsun?”**
Yani filtreleme, arama, sıralama gibi işler yapar.

📌 Örnek:

```
https://api.site.com/users?role=admin
```

Burada:

* `role=admin` → Sadece "admin" rolündeki kullanıcıları getir.

🟡 `role=admin` burada bir **query parametresidir**.

> ❓ Ne işe yarar? → Filtre, sıralama gibi şeyler.
> ❗ Neyi ifade eder? → `?` ile başlar, URL’nin sonunda yer alır.
> ✅ Nasıl kullanılır?

```java
.queryParam("role", "admin")
.get("/users")
```

---

## 🔁 FARKLARINI TEKRARLA:

| Özellik          | Path Param               | Query Param                       |
| ---------------- | ------------------------ | --------------------------------- |
| Nerede?          | URL’nin içinde           | URL’nin sonunda (`?`)             |
| Ne işe yarar?    | Belirli kaynağı hedefler | Verileri filtreler, arar, sıralar |
| Örnek            | `/users/5`               | `/users?role=admin`               |
| Java’da kullanım | `.pathParam("id", 5)`    | `.queryParam("role", "admin")`    |

---

## 🎯 Gerçek API Örneği (reqres.in)

* Tüm kullanıcıları getir:
  `GET https://reqres.in/api/users`

* Sayfa 2’deki kullanıcıları getir (query param):
  `GET https://reqres.in/api/users?page=2`

* ID'si 5 olan kullanıcıyı getir (path param):
  `GET https://reqres.in/api/users/5`

---

## ✅ Java Kodu ile Anlatım:

### Path Param:

```java
given()
  .pathParam("id", 5)
.when()
  .get("https://reqres.in/api/users/{id}")
.then()
  .statusCode(200);
```

> ✅ URL: `https://reqres.in/api/users/5`

---

### Query Param:

```java
given()
  .queryParam("page", 2)
.when()
  .get("https://reqres.in/api/users")
.then()
  .statusCode(200);
```

> ✅ URL: `https://reqres.in/api/users?page=2`

---
## 📚 Path Param ve Query Param Nedir?

🔹 **Path Parameter** (Yol parametresi): URL'nin **bir parçasıdır** ve genellikle belirli bir kaynağa erişmek için kullanılır.
🔹 **Query Parameter** (Sorgu parametresi): URL’nin **sonuna `?` ile eklenir** ve verileri filtrelemek, sıralamak gibi işlemler için kullanılır.

---

### ✅ Örnek Senaryo:

Diyelim ki aşağıdaki iki API endpoint’imiz var:

1. `GET /users/{userId}` → Bu bir **path parametresi** içerir.
2. `GET /users?role=admin` → Bu bir **query parametresi** içerir.

---

## 🧩 1. PATH PARAM Nasıl Kullanılır?

```java
given()
    .pathParam("userId", 123)
.when()
    .get("https://api.example.com/users/{userId}")
.then()
    .statusCode(200);
```

Açıklama:

* `pathParam("userId", 123)` → `{userId}` yerine `123` koyar.
* URL: `https://api.example.com/users/123`

---

## 🧩 2. QUERY PARAM Nasıl Kullanılır?

```java
given()
    .queryParam("role", "admin")
.when()
    .get("https://api.example.com/users")
.then()
    .statusCode(200);
```

Açıklama:

* `queryParam("role", "admin")` → URL’ye `?role=admin` ekler.
* URL: `https://api.example.com/users?role=admin`

---

## 🧩 3. İkisini Birlikte Kullanma

```java
given()
    .pathParam("userId", 123)
    .queryParam("details", "true")
.when()
    .get("https://api.example.com/users/{userId}")
.then()
    .statusCode(200);
```

* URL: `https://api.example.com/users/123?details=true`

---

