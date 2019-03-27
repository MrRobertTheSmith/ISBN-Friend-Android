package com.example.isbnfriend;


import com.example.isbnfriend.Controller.NetworkController;
import com.example.isbnfriend.Controller.RetrofitClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnit4.class)
public class RetrofitClientTest {
    private NetworkController networkC;
    private String testISBN;

    @Before
    public void setup(){
        networkC = new NetworkController();
        testISBN = "9780131103627";
    }

    @Test
    public void callGetBookForISBNWithValidISBN(){
        networkC.fetchBookForISBN(testISBN);

    }

}
