package com.example.isbnfriend;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import com.example.isbnfriend.View.NetworkViewmodel;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnit4.class)
public class RetrofitClientTest {
    private NetworkViewmodel testViewModel;
    private String testISBN;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Before
    public void setup(){
        testViewModel = new NetworkViewmodel();
        testISBN = "9780131103627";
    }

    @Test
    public void callGetBookForISBNWithValidISBN() throws Exception {

        testViewModel.searchViaModel(testISBN);

        assertEquals("C Programming Language", testViewModel.getJsonResponse().getValue()
                        .getBooks()
                        .get(0)
                        .getVolumeInfo()
                        .getTitle());
    }

}
