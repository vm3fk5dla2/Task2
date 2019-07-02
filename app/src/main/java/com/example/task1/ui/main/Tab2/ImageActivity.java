package com.example.task1.ui.main.Tab2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import com.example.task1.R;

public class ImageActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.tab2_imageview);

    ImageView imageView = (ImageView) findViewById(R.id.imageView);
    setImage(imageView);
  }

  private void setImage(ImageView imageView) {
    Intent receivedIntent = getIntent();
    Bundle extras = receivedIntent.getExtras();
    String imgPath = extras.getString("image ID");

    BitmapFactory.Options bfo = new BitmapFactory.Options();
    Bitmap bm = BitmapFactory.decodeFile(imgPath, bfo);
    Bitmap resized = Bitmap.createBitmap(bm);
    imageView.setImageBitmap(resized);

  }
}
