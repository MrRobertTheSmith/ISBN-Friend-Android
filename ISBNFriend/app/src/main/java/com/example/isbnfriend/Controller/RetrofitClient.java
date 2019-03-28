package com.example.isbnfriend.Controller;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import com.example.isbnfriend.Model.Book;
import com.example.isbnfriend.Model.Item;
import com.example.isbnfriend.Model.VolumeInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class RetrofitClient{
    private static Retrofit retroFit;
    private static final String baseURL = "https://www.googleapis.com/books/v1/";
    GoogleBooksAPI gbAPI;

    private Book book;
    private VolumeInfo vMI;
    private Item topLevelJSONObject;

    public MutableLiveData<Item> observableResponse;

    private static final String TAG = RetrofitClient.class.getName() + "NETTAG";

    //Constructor which works the same way as init in swift
    public RetrofitClient() {

        retroFit = new retrofit2.Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        book = new Book();
        vMI = new VolumeInfo();
        topLevelJSONObject = new Item();

        book.setVolumeInfo(vMI);

        observableResponse = new MutableLiveData<Item>();
    }

    public void fetchBookForISBN(String isbn) {

        gbAPI = retroFit.create(GoogleBooksAPI.class);

        Call<Item> call = gbAPI.fetchBookForISBN(isbn);

        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if (response.isSuccessful()){
                    topLevelJSONObject = response.body();
                    observableResponse.setValue(topLevelJSONObject);
                    Log.d(TAG, topLevelJSONObject.getBooks().get(0).getVolumeInfo().getTitle().toString());
                }
                else{
                    Log.d(TAG, "Unsuccessful Response");
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                    Log.d(TAG, t.getMessage());
            }
        });

    }

}
