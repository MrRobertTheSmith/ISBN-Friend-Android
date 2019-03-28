package com.example.isbnfriend;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.example.isbnfriend.Model.Item;
import com.example.isbnfriend.View.NetworkViewmodel;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.assertEquals;

@RunWith(JUnit4.class)
public class RetrofitClientTest {
    private NetworkViewmodel testViewModel;
    private String testISBN;

    CountDownLatch aLatch = new CountDownLatch(1);

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Before
    public void setup(){
        testViewModel = new NetworkViewmodel();
        testISBN = "9780131103627";
    }

    @Test
    public void callGetBookForISBNWithValidISBNReturnsValidTitle() throws Exception {

        testViewModel.searchViaModel(testISBN);

        testViewModel.getJsonResponse().observeForever(new android.arch.lifecycle.Observer<Item>() {
            @Override
            public void onChanged(@Nullable Item item) {
                aLatch.countDown();
            }
        });

        aLatch.await();

        assertEquals("C Programming Language", testViewModel.getJsonResponse().getValue()
                        .getBooks()
                        .get(0)
                        .getVolumeInfo()
                        .getTitle());
    }

    @Test
    public void callGetBookForISBNWithValidISBNReturnsValidAuthors() throws Exception {

        testViewModel.searchViaModel(testISBN);

        testViewModel.getJsonResponse().observeForever(new android.arch.lifecycle.Observer<Item>() {
            @Override
            public void onChanged(@Nullable Item item) {
                aLatch.countDown();
            }
        });

        aLatch.await();

        List<String> authors = new ArrayList<>();
        authors.add("Brian W. Kernighan");
        authors.add("Dennis Ritchie");

        assertEquals(authors, testViewModel.getJsonResponse().getValue()
                .getBooks()
                .get(0)
                .getVolumeInfo()
                .getauthors());
    }

    @Test
    public void callGetBookWithInvalidISBN() throws Exception {

        testViewModel.searchViaModel("");

        testViewModel.getJsonResponse().observeForever(new android.arch.lifecycle.Observer<Item>() {
            @Override
            public void onChanged(@Nullable Item item) {
            }
        });


        assertNull(testViewModel.getJsonResponse().getValue());

    }
}
