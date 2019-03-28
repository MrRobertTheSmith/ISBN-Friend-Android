package com.example.isbnfriend.View

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import com.example.isbnfriend.Model.Item
import com.example.isbnfriend.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = "APIRESPONSE"

    lateinit var model:NetworkViewmodel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contentTextView.movementMethod = ScrollingMovementMethod()

        model = ViewModelProviders.of(this).get(NetworkViewmodel::class.java)

        val obs = Observer<Item>{
            if (it != null){
                Log.d(TAG, "${it.books?.get(0)?.volumeInfo?.title}")

                book_name_textView.text = it.books?.get(0)?.volumeInfo?.title
                contentTextView.text = it.books?.get(0)?.volumeInfo?.description

            }
        }

        model.jsonResponse.observe(this, obs)

    }

    fun searchPressed(view: View){
        val stringEntered = search_editText.text.toString()
        if (stringEntered.length == 13) {
            model.searchViaModel(stringEntered)
            contentTextView.text = ""
        }
    }
}
