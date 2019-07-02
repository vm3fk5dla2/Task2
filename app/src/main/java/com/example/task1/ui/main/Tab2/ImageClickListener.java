package com.example.task1.ui.main.Tab2;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class ImageClickListener implements OnClickListener {

  Context context;
  String imageID;

  public ImageClickListener(Context context, String imageID) {
    this.context = context;
    this.imageID = imageID;
  }

  public void onClick(View v) {
    Intent intent = new Intent(context, ImageActivity.class);
    intent.putExtra("image ID", imageID);
    context.startActivity(intent);
  }
}
