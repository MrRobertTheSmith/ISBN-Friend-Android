package com.example.isbnfriend.View

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.isbnfriend.Model.Item
import com.example.isbnfriend.R

class MainActivity : AppCompatActivity() {

    val TAG = "APIRESPONSE"

    private lateinit var model:NetworkViewmodel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        model = ViewModelProviders.of(this).get(NetworkViewmodel::class.java)
        model.searchViaModel("9780131103627")

        val obs = Observer<Item>{
            if (it != null){
                Log.d(TAG, it.books[0].volumeInfo.title)
            }
        }

        model.jsonResponse.observe(this, obs)

    }
}
