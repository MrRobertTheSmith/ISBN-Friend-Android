package com.example.isbnfriend;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.example.isbnfriend.Controller.RetrofitClient;
import com.example.isbnfriend.Model.Item;
import junit.framework.TestResult;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnit4.class)
public class RetrofitClientTest {
    private RetrofitClient networkC;
    private String testISBN;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Before
    public void setup(){
        networkC = new RetrofitClient();
        testISBN = "9780131103627";
    }

    @Test
    public void callGetBookForISBNWithValidISBN() {

        networkC.fetchBookForISBN(testISBN);

        assertEquals("C Programming Language", networkC.observableResponse.getValue()
                        .getBooks()
                        .get(0)
                        .getVolumeInfo()
                        .getTitle());



    }

}
