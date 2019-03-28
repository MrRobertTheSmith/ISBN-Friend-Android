package com.example.isbnfriend.View

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.isbnfriend.Controller.RetrofitClient
import com.example.isbnfriend.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val controller = RetrofitClient()

        controller.fetchBookForISBN("9780131103627")
    }
}
