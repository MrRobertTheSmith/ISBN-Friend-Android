package com.example.isbnfriend.View

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.example.isbnfriend.Model.Book
import com.example.isbnfriend.Model.Item
import com.example.isbnfriend.R
import kotlinx.android.synthetic.main.activity_main.*

/*
This is the main activity, It accesses the ViewController and observes the LiveData on the ViewController
The button being pressed calls the search method on the view controller. Changes are observed and lead to
updates to the View.
Invalid repsonses from the data are watched for and trigger UI updates
 */

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
                book_name_textView.text = it.books?.get(0)?.volumeInfo?.title
                contentTextView.text = it.books?.get(0)?.volumeInfo?.description
            }

            if (it?.books == null){
                displayToast("No book for this ISBN")
            }
        }

        model.jsonResponse.observe(this, obs)

    }

    fun searchPressed(view: View){
        val stringEntered = search_editText.text.toString()

        if(!model.searchViaModel(stringEntered)){
            displayToast("Invalid ISBN")
        }
    }

    fun scanPressed(view: View){
        val frag = BarcodeScannerFragment.newInstance()

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        transaction.add(R.id.constraintLayout, frag).addToBackStack(null).commit()
    }

    //Private helper method to build a toast; this is used when there is an input
    //validation issue or no valid API response
    private fun displayToast(message:String){
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
}
