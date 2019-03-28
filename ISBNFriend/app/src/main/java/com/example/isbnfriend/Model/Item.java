package com.example.isbnfriend.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {

    @SerializedName("items")
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
