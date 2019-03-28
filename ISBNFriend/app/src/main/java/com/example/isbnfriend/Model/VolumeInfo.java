package com.example.isbnfriend.Model;

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

    public VolumeInfo(String title, String desc, List<String> auths, String prev){
        this.title = title;
        this.description = desc;
        this.authors = auths;
        this.previewLink = prev;
    }

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
