package com.example.task1.ui.main.Tab2;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import com.example.task1.R;
import com.example.task1.ui.main.Tab1.Fragment1;
import com.example.task1.ui.main.Tab1.model.ContactList;
import com.google.gson.JsonObject;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.task1.MainActivity.getContextOfApplication;

public class Fragment2 extends Fragment {

  GridView gridViewImages;
  Context mContext;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mContext = this.getContext();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.layout_fragment2, container, false);
    new JSONTask_get().execute("http://143.248.36.217:3800/post");
    gridViewImages = (GridView) view.findViewById(R.id.gridViewImages);

    Button button = (Button) view.findViewById(R.id.buttonGreen);
    button.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View view) {
        new JSONTask_send().upload();
      }
    });

    Button button2 = (Button) view.findViewById(R.id.buttonRed);
    button2.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View view) {
        new JSONTask_get().execute("http://143.248.36.217:3800/post");
      }
    });

    return view;
  }

  public class JSONTask_send {

    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
      ByteArrayOutputStream stream = new ByteArrayOutputStream();
      bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
      byte[] byteFormat = stream.toByteArray();
      // get the base 64 string
      String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);

      return imgString;
    }

    public void upload() {
      try {
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] proj = {
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.SIZE
        };

        String[] selectionArgs = null;
        Cursor c = mContext.getContentResolver().query(uri, proj, null, null, null);
        assert c != null;
        while (c.moveToNext()) {
          String thumbsID;
          String thumbsImageID;
          String thumbsData;

          int thumbsIDCol = c.getColumnIndex(MediaStore.Images.Media._ID);
          int thumbsDataCol = c.getColumnIndex(MediaStore.Images.Media.DATA);
          int thumbsImageIDCol = c.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);

          thumbsID = c.getString(thumbsIDCol);
          thumbsData = c.getString(thumbsDataCol);
          thumbsImageID = c.getString(thumbsImageIDCol);


          try {

            String encoded = getEncoded64ImageStringFromBitmap(BitmapFactory.decodeFile(thumbsData));

            Ion.with(getContext())
                    .load("http://143.248.36.217:2040/imgpost")
                    .setBodyParameter("name", thumbsImageID.toString())
                    .setBodyParameter("imgtype", "image/png")
                    .setBodyParameter("imgdata", encoded).asJsonObject();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        c.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
      return;
    }

  }


  public class JSONTask_get extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... urls) {
      try {
        HttpURLConnection con = null;
        BufferedReader reader = null;

        try {
          URL url = new URL(urls[0]);
          con = (HttpURLConnection) url.openConnection();

          con.setRequestMethod("POST");//POST방식으로 보냄
          con.setRequestProperty("Cache-Control", "no-cache");//캐시 설정
          con.setRequestProperty("Content-Type", "application/json");//application JSON 형식으로 전송

          con.setRequestProperty("Accept", "text/html");//서버에 response 데이터를 html로 받음
          con.setDoOutput(true);//Outstream으로 post 데이터를 넘겨주겠다는 의미
          con.setDoInput(true);//Inputstream으로 서버로부터 응답을 받겠다는 의미
          con.connect();

          //서버로 부터 데이터를 받음
          InputStream stream = con.getInputStream();

          reader = new BufferedReader(new InputStreamReader(stream));

          StringBuffer buffer = new StringBuffer();

          String line = "";
          while ((line = reader.readLine()) != null) {
            buffer.append(line);
          }

          return buffer.toString();

        } catch (MalformedURLException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          if (con != null) {
            con.disconnect();
          }
          try {
            if (reader != null) {
              reader.close();//버퍼를 닫아줌
            }
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

      return null;
    }

    @Override
    protected void onPostExecute(String result) {
      super.onPostExecute(result);
      ImageAdapter imageGridAdapter = new ImageAdapter(mContext, result);
      gridViewImages.setAdapter(imageGridAdapter);
    }
  }
}