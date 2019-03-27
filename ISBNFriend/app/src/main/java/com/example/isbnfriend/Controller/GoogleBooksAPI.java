package com.example.isbnfriend.Controller;

import com.example.isbnfriend.Model.Book;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleBooksAPI {

    @GET("/volumes?")
    Call<Book>fetchBookForISBN(@Query("q") String isbn);
}
