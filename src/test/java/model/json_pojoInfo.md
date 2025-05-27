Kesinlikle! Sana JSON ve POJO yapısını en **basit dille**, örnekli ve detaylı şekilde anlatayım.
📌 Amacımız: "Bir JSON verisi geldiğinde Java'da onu nasıl karşılarız, neden POJO yapısı kurarız, nasıl işler?"

---

## ✅ 1. JSON NEDİR? (Kısaca ve Basitçe)

📦 JSON (JavaScript Object Notation) → Veriyi **anahtar-değer** şeklinde saklayan, okunabilir bir formattır.

### Örnek JSON:

```json
{
  "id": 1,
  "name": "Ayşe",
  "email": "ayse@example.com",
  "active": true
}
```

Bu bir kullanıcıyı temsil ediyor. `id`, `name`, `email` ve `active` alanları var.

---

## ✅ 2. POJO NEDİR?

🧩 POJO = "Plain Old Java Object"
➡️ Yani: **Sade bir Java sınıfı**, sadece veri taşır.

### Ne için kullanılır?

✔️ JSON verisini Java’da **kolayca okumak, yazmak, işlemek** için
✔️ `Rest Assured`, `Jackson`, `Gson`, `Spring` gibi kütüphaneler otomatik olarak JSON veriyi POJO nesnesine çevirir.

---

## ✅ 3. JSON → POJO ÖRNEĞİ

### 🔷 JSON:

```json
{
  "id": 1,
  "name": "Ayşe",
  "email": "ayse@example.com",
  "active": true
}
```

### 🔷 Java POJO Sınıfı:

```java
public class User {

    private int id;
    private String name;
    private String email;
    private boolean active;

    // Boş constructor (gerekli)
    public User() {}

    // Getter ve Setter'lar
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
```

---

## ✅ 4. NEDEN POJO KULLANILIR?

🎯 Çünkü JSON verisini şu şekilde kullanabilmek isteriz:

```java
User user = response.as(User.class);
System.out.println(user.getName()); // Ayşe
```

Yani: `response` içinde JSON vardı, otomatik Java nesnesine çevirdik. ✔️

---

## ✅ 5. POJO OLUŞTURMA KURALLARI

| Kural                                        | Açıklama                             |
| -------------------------------------------- | ------------------------------------ |
| 1️⃣ Alan isimleri JSON’dakiyle aynı olmalı   | `name`, `email` gibi                 |
| 2️⃣ Private field + Getter/Setter kullanılır | Java bean standardı                  |
| 3️⃣ Boş (no-arg) constructor olmalı          | JSON'dan otomatik oluşturulması için |

---

## ✅ 6. JSON İçinde Nesne veya Liste Varsa?

### 🔸 JSON içinde başka bir JSON varsa:

```json
{
  "user": {
    "id": 1,
    "name": "Ayşe"
  }
}
```

Java’da:

```java
public class ResponseWrapper {
    private User user;
    // getter, setter
}
```

---

### 🔸 JSON listesi varsa:

```json
{
  "users": [
    {"id": 1, "name": "Ayşe"},
    {"id": 2, "name": "Mehmet"}
  ]
}
```

Java’da:

```java
public class ResponseList {
    private List<User> users;
    // getter, setter
}
```

---

## ✅ 7. POJO + REST ASSURED ENTEGRASYONU

```java
User user = given()
    .when().get("https://api.site.com/user/1")
    .then().extract().as(User.class);

System.out.println(user.getEmail());
```

---

## ✅ 8. BONUS: Lombok ile POJO’yu Kolay Yazma (Ekstra)

Lombok kütüphanesi ile getter/setter yazmana gerek kalmaz:

```java
@Data // Otomatik getter/setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private String email;
    private boolean active;
}
```

---

## ✅ ÖZET

| Terim         | Açıklama                                          |
| ------------- | ------------------------------------------------- |
| **JSON**      | Veri formatı (anahtar-değer)                      |
| **POJO**      | JSON veriyi temsil eden sade Java sınıfı          |
| **Amaç**      | JSON veriyi nesne gibi kullanmak (user.getName()) |
| **Kütüphane** | Rest Assured, Jackson, Gson otomatik çevirebilir  |

---

İstersen sana verilen bir JSON yapısını POJO’ya çevireyim (örnek JSON ver, ben çevireyim)
veya senin için POJO sınıfları üretip test kodu da yazabilirim. Hangisini yapalım?
