package com.example.isbnfriend.Controller;

import android.arch.lifecycle.MutableLiveData;
import com.example.isbnfriend.Model.Item;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
This is one of the common design patterns to implement Retrofit2.
Retrofit client is a class that creates an instance of Retrofit when initialised and
also has an instance method that searches the Google books API based on a string.
My approach is to use LiveData here which is observable from another part of the app.
 */

public class RetrofitClient{
    private static Retrofit retroFit;
    private static final String baseURL = "https://www.googleapis.com/books/v1/";
    private GoogleBooksAPI gbAPI;

    public MutableLiveData<Item> observableResponse;

    //Constructor which works the same way as init in swift
    public RetrofitClient() {

        retroFit = new retrofit2.Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        observableResponse = new MutableLiveData<Item>();
    }

    //This method searches The API and updates the value of the live data.
    public void fetchBookForISBN(String isbn) {

        gbAPI = retroFit.create(GoogleBooksAPI.class);

        Call<Item> call = gbAPI.fetchBookForISBN(isbn);

        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if (response.isSuccessful()){
                    observableResponse.setValue(response.body());
                }
                else{
                    //Clear the item to trigger the observer
                    observableResponse.setValue(new Item());
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
            }
        });

    }

}
