package com.pictlibrary.dev.controller;

import com.pictlibrary.dev.model.AppCategory;
import com.pictlibrary.dev.model.Book;
import com.pictlibrary.dev.service.AppCategoryService;
import com.pictlibrary.dev.service.BookService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class bookController {
    @Autowired
    private AppCategoryService appCategoryService;

    @Autowired
    private BookService bookService;

   
    @GetMapping("/getBook")
    public String getBook() {
        JSONArray jsonArray = new JSONArray();
        
        Iterable<Book> listBook = bookService.getAllBook();
        listBook.forEach((Book book) -> {
            JSONObject obj = new JSONObject();
            try {
                obj.put("id", book.getId());
                obj.put("name", book.getBookName());
                obj.put("description",book.getBookDescription());
                obj.put("category",book.getAppCategory().getCategoryName());
                System.out.println(book.getId());
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            jsonArray.put(obj);
        });
        return jsonArray.toString();
    }

    @GetMapping("/searchBook")
    public String searchBook(@RequestParam String searchValue)
    {
       // JSONObject data = new JSONObject(payload);
        //String key = data.getString("searchValue");
        String key = searchValue;
        JSONArray jsonArray = new JSONArray();
        Iterable<Book> listBook = bookService.searchBook(key);
        listBook.forEach((Book book) -> {
            JSONObject obj = new JSONObject();
            try {
                obj.put("id", book.getId());
                obj.put("name", book.getBookName());
                obj.put("description",book.getBookDescription());
                obj.put("category",book.getAppCategory().getCategoryName());
                System.out.println(book.getId());
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            jsonArray.put(obj);
        });
        return jsonArray.toString();
    }

    @GetMapping("/getCategory")
    public String getCategory()
    {
        JSONArray array = new JSONArray();
        Iterable<AppCategory>  listCategory = appCategoryService.getAllCategory();
        listCategory.forEach((AppCategory cat)->{
            JSONObject obj = new JSONObject();
            obj.put("categoryId", cat.getId());
            obj.put("categoryName", cat.getCategoryName());
            array.put(obj);
        });
        return array.toString();
    }

    @PostMapping("/addBook")
    public String addBook(@RequestBody String payload)
    {
        JSONObject data = new JSONObject(payload);
        String bookName = data.getString("bookName");
        String authorName = data.getString("authorName");
        String bookDescription = data.getString("bookDescription");
        int categoryId = data.getInt("categoryId");
        AppCategory appCategory = appCategoryService.getAppCategoryById(categoryId);

        Book book = new Book();
        book.setBookName(bookName);
        book.setAuthorName(authorName);
        book.setAppCategory(appCategory);
        book.setBookDescription(bookDescription);

        book = bookService.addBook(book);
        
        JSONObject response = new JSONObject();
        response.put("status", "success");
        return response.toString();
    }
}

