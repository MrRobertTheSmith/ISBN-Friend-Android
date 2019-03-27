package com.example.isbnfriend.Controller;

import android.util.Log;
import com.example.isbnfriend.Model.Book;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NetworkController {

    Retrofit retroFit = RetrofitClient.getRetroFit();

    GoogleBooksAPI apiFromInterface = retroFit.create(GoogleBooksAPI.class);


    public void fetchBookForISBN(String isbn){

        Call call = apiFromInterface.fetchBookForISBN(isbn);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.body() != null){
                    Book book = (Book) response.body();
                    Log.d("BOOK", String.valueOf(book));
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
