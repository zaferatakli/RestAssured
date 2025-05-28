```
 // Sadece Place dizisi lazım ise diğerlerini yazmak zorunda değilsin.

        // Daha önceki örneklerde (as) Clas dönüşümleri için tüm yapıya karşılık gelen
        // gereken tüm classları yazarak dönüştürüp istediğimiz elemanlara ulaşıyorduk.

        // Burada ise(JsonPath) aradaki bir veriyi clasa dönüştürerek bir list olarak almamıza
        // imkan veren JSONPATH i kullandık.Böylece tek class ile veri alınmış oldu
        // diğer class lara gerek kalmadan

        // path : class veya tip dönüşümüne imkan veremeyen direk veriyi verir. List<String> gibi
        // jsonPath : class dönüşümüne ve tip dönüşümüne izin vererek , veriyi istediğimiz formatta verir.
```
```
goRest users Api testing yapmak istiyorum

@Before Class
Başlangıç İşlemleri (ÖNCE BURASI)
: Token al ve hepsinin kullanacağı şekilde 
  başlangıç ayalrları ile (JSon)
  spec oluştur

@Test sonra
1-Create User     test metodu
2-Get User By Id  test metodu
3-Update User     test metodu
4-Delete User     test metodu
5-Delete Negative test metodu
```