# Sugar ORM #

### Introduce Sugar ORM ###

* It eliminates writing SQL queries to interact with SQLite db.
* It takes care of creating your database.
* It manages object relationships too.
* It provides you with clear and simple APIs for db operations

### How to install ###

* Gradle:
   


```
#!

compile 'com.github.satyan:sugar:1.4'
```


* AndroidManifest.xml:
        
         


```
#!


<application android:label="@string/app_name" android:icon="@drawable/icon"
         android:name="com.orm.SugarApp">
         .
         .
         //  * DATABASE: Name of the generated sqlite database file. eg: app_name.db
         //  * VERSION: Version of your database schema.
         //  * QUERY_LOG: Logs the generated Select queries.
         //  * DOMAIN_PACKAGE_NAME: Specify a package name where your domain/entity classes are present.This helps in smoother table creation.

         <meta-data android:name="DATABASE" android:value="sugar_example.db" />
         <meta-data android:name="VERSION" android:value="2" />
         <meta-data android:name="QUERY_LOG" android:value="true" />
         <meta-data android:name="DOMAIN_PACKAGE_NAME" android:value="com.example" />
         .
         .
         </application>
```



### How to use ###

* Create a table

```
#!


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

```




* Add entity

       

```
#!


        Book book = new Book("Title here", "2nd edition")
        book.save();
```



        
* Get list entity



  
       
```
#!

List<Book> bookList;  
       long count = Book.Count(Book.class);
       if(count>0)  
       {  
       bookList= Book.listAll(Book.class);  
       }  

```





* Get list entity with equals to:     
    


   
```
#!

      List<Book> user_list = new ArrayList<>();
      long count = Book.Count(Book.class);
      if(count>0)  
      {  
      user_list = Book.find(Book.class,"title=?","Dac Nhan Tam");  
      if(Book==null)  
      {  
      //querying list return empty, there is no record found matching the query.  
      }  
      else  
      {  
      //there are records matching your query.   
      }  
    }
```

 * Update entity





```
#!

// When you add a new data entity to table, Sugar will auto genarate a Id. You can use TableName.getId() to get it. 
Book book = Book.findById(Book.class, hotelTable.getId());
book.title = "updated title here"; // modify the values
book.edition = "3rd edition";
book.save(); // updates the previous entry with new values.
```



 * Delete entity





```
#!

Book book = Book.findById(Book.class, 1);
book.delete();

```

 
 * Bulk Operation


```
#!

List<Book> books = Book.listAll(Book.class);
Book.deleteAll(Book.class);
```




### Link ###

* Repo: [https://github.com/phutlq/SqlLiteData-Sugar-Example](Link URL)
* References: [http://satyan.github.io/sugar/getting-started.html](Link URL)
             [ http://codex2android.blogspot.com/2016/03/sugar-orm-with-sqlite-android-part-i.html](Link URL)
              [http://codex2android.blogspot.in/2016/03/sugar-orm-sqlite-android-part-2.html](Link URL)