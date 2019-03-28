package com.example.isbnfriend.View;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.example.isbnfriend.Controller.RetrofitClient;
import com.example.isbnfriend.Model.Item;

public class NetworkViewmodel extends ViewModel {

    private MutableLiveData<Item> jsonResponse;
    private RetrofitClient networkController;

    public NetworkViewmodel(){

        networkController = new RetrofitClient();
        jsonResponse = networkController.observableResponse;
    }

    public void searchViaModel(String isbn){
        networkController.fetchBookForISBN(isbn);
    }

    public MutableLiveData<Item> getJsonResponse() {
        if (jsonResponse == null) {
            jsonResponse = new MutableLiveData<Item>();
        }

        return jsonResponse;
    }
}
