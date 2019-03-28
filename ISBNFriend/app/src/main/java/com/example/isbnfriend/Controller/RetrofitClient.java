package com.example.isbnfriend.Controller;

import android.util.Log;
import com.example.isbnfriend.Model.Book;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient{
    private static Retrofit retroFit;
    private static final String baseURL = "https://www.googleapis.com/books/v1/";
    GoogleBooksAPI gbAPI;

    //Constructor which works the same way as init in swift
    public RetrofitClient() {

        retroFit = new retrofit2.Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public void fetchBookForISBN(String isbn) {

        gbAPI = retroFit.create(GoogleBooksAPI.class);

        Call<Book> call = gbAPI.fetchBookForISBN(isbn);

        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful()){
                    Log.d("NETTAG", response.body().toString());
                }
                else{
                    Log.d("NETTAG", "Unsuccessful Response");
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                    Log.d("NETTAG", t.getMessage());
            }
        });

    }

}
