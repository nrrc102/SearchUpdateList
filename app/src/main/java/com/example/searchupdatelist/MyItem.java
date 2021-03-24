package com.example.searchupdatelist;

import android.net.Uri;

public class MyItem {
    private Uri imageUri;
    private String name;

    public MyItem(Uri imageUri, String name) {
        this.imageUri = imageUri;
        this.name = name;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
