package com.example.task1.ui.main.Tab3;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import com.example.task1.R;

public class TimeTableActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_timetable);
    ImageView imageView = (ImageView) findViewById(R.id.timetable);
    imageView.setImageResource(R.drawable.time_table);
  }
}
