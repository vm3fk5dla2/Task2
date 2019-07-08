package com.example.task1.ui.main.Tab3.model;

import android.graphics.Bitmap;

public class RecipeList {
    private String name;
    private String data;

    public RecipeList(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public String getName() { return name; }
    public String getData() { return data; }
}