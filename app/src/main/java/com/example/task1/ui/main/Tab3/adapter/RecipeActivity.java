package com.example.task1.ui.main.Tab3.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.task1.R;

public class RecipeActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        getIncomingIntent();
    }

    private void getIncomingIntent(){
        String data = getIntent().getStringExtra("data");
        setImage(data);
    }

    private void setImage(String data){
        TextView data_ = findViewById(R.id.recipe_data);

        data_.setText(data);
    }

}