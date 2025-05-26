# Hamcrest Matcher'lar Hakkında Detaylı Bilgi


# REST Assured ve Hamcrest Matchers - Detaylı Türkçe Açıklamalar

## 🔁 Genel Kullanılan Matcher'lar

### ✅ `equalTo(X)`

- **Açıklama:** Gerçekleşen (actual) değerin, önceden belirlenmiş (beklenen) `X` değerine eşit olup olmadığını kontrol
  eder.
- **Örnek:**
  ```java
  body("status", equalTo("success"));


### ✅ `hasItem("deger")`

* **Açıklama:** Bir koleksiyon (dizi, liste, JSON array vs.) içerisinde belirli bir değerin bulunup bulunmadığını
  kontrol eder.
* **Örnek:**

  ```java
  body("data.names", hasItem("Ali"));
  ```

### ✅ `hasSize(3)`

* **Açıklama:** Bir koleksiyondaki öğe sayısının belirli bir sayıya eşit olup olmadığını doğrular.
* **Örnek:**

  ```java
  body("data.items", hasSize(3));
  ```

### 🚫 `not(equalTo(X))`

* **Açıklama:** Verilen matcher'ın tersini alır. Yani, değer `X`'e eşit **değilse** test geçer.
* **Örnek:**

  ```java
  body("status", not(equalTo("error")));
  ```

---

## 🔢 Sayı İle İlgili Doğrulamalar (Number Matchers)

### ✅ `equalTo`

* **Açıklama:** JSON yanıtındaki sayısal değerin, beklenen sayıya eşit olup olmadığını kontrol eder.
* **Örnek:**

  ```java
  body("user.age", equalTo(30));
  ```

### ✅ `greaterThan`

* **Açıklama:** JSON’dan çıkarılan sayının, belirli bir değerden **büyük** olup olmadığını kontrol eder.
* **Örnek:**

  ```java
  body("order.total", greaterThan(100));
  ```

### ✅ `greaterThanOrEqualTo`

* **Açıklama:** Sayının, belirtilen değere **eşit veya büyük** olup olmadığını kontrol eder.
* **Örnek:**

  ```java
  body("score", greaterThanOrEqualTo(80));
  ```

### ✅ `lessThan`

* **Açıklama:** Sayının, belirtilen değerden **küçük** olup olmadığını kontrol eder.
* **Örnek:**

  ```java
  body("temperature", lessThan(40));
  ```

### ✅ `lessThanOrEqualTo`

* **Açıklama:** Sayının, verilen değere **eşit veya küçük** olup olmadığını kontrol eder.
* **Örnek:**

  ```java
  body("duration", lessThanOrEqualTo(5));
  ```

---

## 🔤 String (Metin) İle İlgili Doğrulamalar

### ✅ `equalTo`

* **Açıklama:** JSON'dan çıkarılan metin değeri, beklenen metinle **tam olarak aynı mı** kontrol eder.
* **Örnek:**

  ```java
  body("message", equalTo("İşlem başarılı"));
  ```

### ✅ `equalToIgnoringCase`

* **Açıklama:** Harf büyüklüğüne (case-sensitive) bakmadan eşitlik kontrolü yapar.
* **Örnek:**

  ```java
  body("status", equalToIgnoringCase("SUCCESS"));
  ```

### ✅ `equalToIgnoringWhiteSpace`

* **Açıklama:** Boşluk karakterlerini (whitespace) dikkate almaz, içerik aynıysa eşit sayar.
* **Örnek:**

  ```java
  body("note", equalToIgnoringWhiteSpace(" Merhaba Dünya "));
  ```

### ✅ `containsString`

* **Açıklama:** Belirli bir alt string’in (substring) olup olmadığını kontrol eder.
* **Örnek:**

  ```java
  body("description", containsString("başarılı"));
  ```

### ✅ `startsWith`

* **Açıklama:** Metnin belirli bir karakter ya da kelimeyle başlayıp başlamadığını kontrol eder.
* **Örnek:**

  ```java
  body("username", startsWith("user"));
  ```

### ✅ `endsWith`

* **Açıklama:** Metnin belirli bir karakter ya da kelimeyle bitip bitmediğini kontrol eder.
* **Örnek:**

  ```java
  body("email", endsWith("@example.com"));
  ```

---

## 📝 Notlar

* Tüm bu matcher’lar `Hamcrest` kütüphanesinden gelir ve `Rest Assured` testleri içerisinde yaygın olarak kullanılır.
* Her biri, API testlerinde belirli bir alanın değeri, yapısı ya da formata uygun olup olmadığını otomatikleştirmek için
  kullanılır.

---

## 📚 Kaynaklar

Genel kullanılanlar

equalTo(X) - used to check whether an actual element value is equal to a pre-specified expected element value
hasItem("value") - used to see whether a collection of elements contains a specific pre-specified item value
hasSize(3) - used to verify the actual number of elements in a collection
not(equalTo(X)) - inverts any given matcher that exists in the Hamcrest

Number related assertions

equalTo – It checks whether the retrieved number from response is equal to the expected number.
greaterThan – checks extracted number is greater than the expected number.
greaterThanOrEqualTo – checks whether the extracted number is greater than equal to the expected number.
lessThan – It checks whether the retrieved number from response is lesser than to the expected number.
lessThanOrEqualTo – It checks whether the retrieved number from response is lesser than or equal to the expected number.

String related Assertions

equalTo – It checks whether the extracted string from JSON is equal to the expected string.
equalToIgnoringCase – It checks whether the extracted string from JSON is equal to the expected string without
considering the case (small or capital).
equalToIgnoringWhiteSpace – It checks whether the extracted string from JSON is equal to the expected string by
considering the white spaces.
containsString – It checks whether the extracted string from JSON contains the expected string as a substring.
startsWith – It checks whether the extracted string from JSON is starting with a given string or character.
endsWith – It checks whether the extracted string from JSON is ending with a given string or character.