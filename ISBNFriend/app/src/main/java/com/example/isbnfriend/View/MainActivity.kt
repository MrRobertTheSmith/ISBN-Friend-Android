package com.example.isbnfriend.View

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.isbnfriend.Controller.RetrofitClient
import com.example.isbnfriend.Model.Book
import com.example.isbnfriend.Model.Item
import com.example.isbnfriend.R
import java.util.*

class MainActivity : AppCompatActivity() {

    val TAG = "APIRESPONSE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val network = RetrofitClient()

        network.fetchBookForISBN("9780131103627")

        val obs = Observer<Item>{
            if (it != null) {
                Log.d(TAG, it!!.books[0].volumeInfo.title)
            }
            else{
                Log.d(TAG, "NULL BOOK")
            }
        }

        network.observableResponse.observe(this, obs)
    }
}
