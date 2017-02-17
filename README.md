# Sugar ORM #

Hướng dẫn sử dụng thư viện Sugar Orm trong Android

### Giới thiệu thư viện Sugar Orm ###

* Là thư viện cho phép người lập trình tương tác và sử dụng database Sql Lite đơn giản và dễ dàng hơn.
* Tạo bảng đơn giản, không cần phải sử dụng truy vấn 

### Hướng dẫn cài đặt ###

* Gradle:
   
         compile 'com.github.satyan:sugar:1.4'

* AndroidManifest.xml:
        
         <application android:label="@string/app_name" android:icon="@drawable/icon"
         android:name="com.orm.SugarApp">
         .
         .
         //  * DATABASE: Tên của database mà bạn muốn tạo 
         //  * DOMAIN_PACKAGE_NAME: Tên của package nơi chưa các class của database
         <meta-data android:name="DATABASE" android:value="sugar_example.db" />
         <meta-data android:name="VERSION" android:value="2" />
         <meta-data android:name="QUERY_LOG" android:value="true" />
         <meta-data android:name="DOMAIN_PACKAGE_NAME" android:value="com.example" />
         .
         .
         </application>

### Hướng dẫn sử dụng ###

* Tạo bảng mới

         public class Book extends SugarRecord {
         String title;
         String edition;

         public Book(){
         }

         public Book(String title, String edition){
         this.title = title;
         this.edition = edition;
        }
        }

* Thêm dữ liệu vào bảng

      
       ```
       #!java

       Book book = new Book("Title here", "2nd edition")
       book.save();
       ```


* Lấy dữ liệu theo id
       
       // Khi thêm dữ liệu vào bảng, Sugar sẽ tự động tạo id tự động tăng cho mỗi bản ghi
       Book book = Book.findById(Book.class,book.getID());

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact