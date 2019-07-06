package com.example.task1.ui.main.Tab2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import com.example.task1.R;
import com.example.task1.ui.main.Tab1.Fragment1;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
    new Fragment2.JSONTask().execute("http://143.248.36.217:3800/post");//AsyncTask 시작시킴
    gridViewImages = (GridView) view.findViewById(R.id.gridViewImages);
    return view;
  }

  public class JSONTask extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... urls) {
      try {
        //JSONObject를 만들고 key value 형식으로 값을 저장해준다.
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("name", "ttt");
        jsonObject.accumulate("address", "010-4458-5656");

        HttpURLConnection con = null;
        BufferedReader reader = null;

        try{
          URL url = new URL(urls[0]);
          con = (HttpURLConnection) url.openConnection();

          con.setRequestMethod("POST");//POST방식으로 보냄
          con.setRequestProperty("Cache-Control", "no-cache");//캐시 설정
          con.setRequestProperty("Content-Type", "application/json");//application JSON 형식으로 전송


          con.setRequestProperty("Accept", "text/html");//서버에 response 데이터를 html로 받음
          con.setDoOutput(true);//Outstream으로 post 데이터를 넘겨주겠다는 의미
          con.setDoInput(true);//Inputstream으로 서버로부터 응답을 받겠다는 의미
          con.connect();

          //서버로 보내기위해서 스트림 만듬
          OutputStream outStream = con.getOutputStream();
          //버퍼를 생성하고 넣음
          BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
          writer.write(jsonObject.toString());
          writer.flush();
          writer.close();//버퍼를 받아줌

          //서버로 부터 데이터를 받음
          InputStream stream = con.getInputStream();

          reader = new BufferedReader(new InputStreamReader(stream));

          StringBuffer buffer = new StringBuffer();

          String line = "";
          while((line = reader.readLine()) != null){
            buffer.append(line);
          }

          return buffer.toString();

        } catch (MalformedURLException e){
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        } finally {
          if(con != null){
            con.disconnect();
          }
          try {
            if(reader != null){
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
