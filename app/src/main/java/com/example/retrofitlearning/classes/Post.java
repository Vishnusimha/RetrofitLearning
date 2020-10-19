package com.example.retrofitlearning.classes;

import com.google.gson.annotations.SerializedName;

/**
 * POJO CLass
 */

public class Post {
    private int userId;
    private int id;
    private String title;

    @SerializedName("body")
    private String text;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
