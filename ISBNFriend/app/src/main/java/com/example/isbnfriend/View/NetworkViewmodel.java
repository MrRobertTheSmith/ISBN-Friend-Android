package com.example.isbnfriend.View;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.example.isbnfriend.Controller.RetrofitClient;
import com.example.isbnfriend.Model.Item;

/*The View model separates the Activity from any network code. Upon initialisation the Viewmodel initialises
an instance of the RetroFitClient and points some live data at it.
The View Controller also has a getter for the LiveData and a search method that directly calls
the network controller.
It also has a local private helper method to validate the length of the ISBN and we only call
the network layer if it's valid
*/

public class NetworkViewmodel extends ViewModel {

    private MutableLiveData<Item> jsonResponse;
    private RetrofitClient networkController;

    //Constructor
    public NetworkViewmodel(){

        networkController = new RetrofitClient();
        jsonResponse = networkController.observableResponse;
    }

    //The Search Method
    public Boolean searchViaModel(String isbn){
        if (!validateStringForSearch(isbn)){
            return false;
        }
        networkController.fetchBookForISBN(isbn);
        return true;
    }

    //LiveData Getter
    public MutableLiveData<Item> getJsonResponse() {
        if (jsonResponse == null) {
            jsonResponse = new MutableLiveData<Item>();
        }

        return jsonResponse;
    }

    //Private Validation Method
    private Boolean validateStringForSearch(String s){
        return s.length() == 13;

    }
}
