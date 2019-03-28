package com.example.isbnfriend.Model;

import com.google.gson.annotations.SerializedName;

public class Book {

    @SerializedName("volumeInfo")
    private VolumeInfo volumeInfo;

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo v){
        this.volumeInfo = v;
    }
}
