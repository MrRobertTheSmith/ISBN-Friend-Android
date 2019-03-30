package com.example.isbnfriend.Model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

//This class models the top level of the JSON response

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
