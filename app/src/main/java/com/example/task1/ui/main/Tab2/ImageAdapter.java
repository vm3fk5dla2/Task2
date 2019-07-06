package com.example.task1.ui.main.Tab2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private String json_result;

    private JsonParser jsonParser;
    private JsonArray jsonArray;
    private int image_num;

    public ImageAdapter(Context c, String result) {
        mContext = c;
        json_result = result;

        jsonParser = new JsonParser();
        jsonArray = (JsonArray) jsonParser.parse(json_result);
        image_num = jsonArray.size();
    }

    @Override
    public int getCount() {
        return image_num;
    }

    public boolean deleteSelected(int sIndex) {
        return true;
    }

    @Override
    public Object getItem(int arg0) {
        return arg0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 주어진 위치(position)에 출력할 이미지를 반환함
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(500, 450));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);

        } else {
            imageView = (ImageView) convertView;
        }

        // JSON Parser
        JsonObject object = (JsonObject) jsonArray.get(position);
        JsonObject firstParse = (JsonObject) object.get("img");
        String secondParse = firstParse.get("data").toString();

        BitmapFactory.Options bo = new BitmapFactory.Options();
        bo.inSampleSize = 8;

        byte[] decodedString = Base64.decode(secondParse, Base64.DEFAULT);
        Bitmap bmp = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        Bitmap resized = Bitmap.createScaledBitmap(bmp, 80, 80, true);

        imageView.setImageBitmap(resized);
        return imageView;
    }
}
