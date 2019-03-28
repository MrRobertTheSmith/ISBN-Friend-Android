package com.example.isbnfriend.Model;

import android.arch.lifecycle.MutableLiveData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VolumeInfo {

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("authors")
    private List<String> authors;

    @SerializedName("previewLink")
    private String previewLink;

    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public String getPreviewLink(){
        return previewLink;
    }
    public List<String> getauthors(){
        return authors;
    }
}
