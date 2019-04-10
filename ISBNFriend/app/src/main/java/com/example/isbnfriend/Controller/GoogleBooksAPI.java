package com.example.isbnfriend.Controller;

import com.example.isbnfriend.Model.Item;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//This is part of the Implementation of Retrofit2

interface GoogleBooksAPI {

    @GET("volumes?")
    Call<Item> fetchBookForISBN(@Query("q") String isbn);
}
