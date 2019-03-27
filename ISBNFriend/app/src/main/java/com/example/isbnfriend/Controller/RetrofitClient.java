package com.example.isbnfriend.Controller;

import android.util.Log;
import com.example.isbnfriend.Model.Book;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retroFit;
    private static final String baseURL = "https://www.googleapis.com/books/v1/";

    public static Retrofit getRetroFit(){
        if (retroFit == null){
            retroFit = new retrofit2.Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retroFit;
    }

}
