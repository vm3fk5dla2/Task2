package com.example.task1.ui.main.Tab2;

import static com.example.task1.MainActivity.getContextOfApplication;

import android.content.Context;
import android.database.Cursor;
import android.graphics.*;
import android.provider.MediaStore;
import android.view.*;
import android.widget.*;
import java.util.ArrayList;

public class ImageGridAdapter extends BaseAdapter {

  Context context;

  private String imgData;
  private String geoData;
  private ArrayList<String> thumbsDataList;
  private ArrayList<String> thumbsIDList;

  int[] imageIDs;

  public ImageGridAdapter(Context context) {
    this.context = context;

    thumbsDataList = new ArrayList<String>();
    thumbsIDList = new ArrayList<String>();

    getThumbInfo(thumbsIDList, thumbsDataList);
  }


  public int getCount() {
    return thumbsIDList.size();
  }

  public Object getItem(int position) {
    return position;
  }

  public long getItemId(int position) {
    return position;
  }

  public View getView(int position, View convertView, ViewGroup parent) {
    ImageView imageView;

    if (null != convertView) {
      imageView = (ImageView) convertView;
    } else {
      imageView = new ImageView(context);
      imageView.setAdjustViewBounds(true);

      BitmapFactory.Options bo = new BitmapFactory.Options();
      Bitmap bmp = BitmapFactory.decodeFile(thumbsDataList.get(position), bo);
      Bitmap resized = Bitmap.createScaledBitmap(bmp, 320, 240, false);

      imageView.setImageBitmap(resized);

      String imgPath = getImageInfo(imgData, geoData, thumbsIDList.get(position));
      ImageClickListener imageViewClickListener
          = new ImageClickListener(context, imgPath);
      imageView.setOnClickListener(imageViewClickListener);

    }

    return imageView;
  }

  private void getThumbInfo(ArrayList<String> thumbsIDs, ArrayList<String> thumbsDatas) {
    String[] proj = {
        MediaStore.Images.Media._ID,
        MediaStore.Images.Media.DATA,
        MediaStore.Images.Media.DISPLAY_NAME,
        MediaStore.Images.Media.SIZE
    };

    Context applicationContext = getContextOfApplication();

    Cursor imageCursor = applicationContext.getContentResolver()
        .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            proj, null, null, null);

    if (imageCursor != null && imageCursor.moveToFirst()) {
      String thumbsID;
      String thumbsImageID;
      String thumbsData;
      int thumbsIDCol = imageCursor.getColumnIndex(MediaStore.Images.Media._ID);
      int thumbsDataCol = imageCursor.getColumnIndex(MediaStore.Images.Media.DATA);
      int thumbsImageIDCol = imageCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);
      int thumbsSizeCol = imageCursor.getColumnIndex(MediaStore.Images.Media.SIZE);
      int num = 0;

      do {
        thumbsID = imageCursor.getString(thumbsIDCol);
        thumbsData = imageCursor.getString(thumbsDataCol);
        thumbsImageID = imageCursor.getString(thumbsImageIDCol);
        num++;
        if (thumbsImageID != null) {
          thumbsIDs.add(thumbsID);
          thumbsDatas.add(thumbsData);
        }
      } while (imageCursor.moveToNext());
    }
    imageCursor.close();
    return;

  }

  private String getImageInfo(String ImageData, String Location, String thumbID) {
    String imageDataPath = null;
    String[] proj = {MediaStore.Images.Media._ID,
        MediaStore.Images.Media.DATA,
        MediaStore.Images.Media.DISPLAY_NAME,
        MediaStore.Images.Media.SIZE};

    Context applicationContext = getContextOfApplication();

    Cursor imageCursor = applicationContext.getContentResolver()
        .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            proj, "_ID='" + thumbID + "'", null, null);

    if (imageCursor != null && imageCursor.moveToFirst()) {
      if (imageCursor.getCount() > 0) {
        int imgData = imageCursor.getColumnIndex(MediaStore.Images.Media.DATA);
        imageDataPath = imageCursor.getString(imgData);
      }
    }
    imageCursor.close();
    return imageDataPath;
  }
}


