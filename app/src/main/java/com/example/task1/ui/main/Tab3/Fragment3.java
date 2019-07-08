package com.example.task1.ui.main.Tab3;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task1.R;
import com.example.task1.ui.main.Tab1.Fragment1;
import com.example.task1.ui.main.Tab1.model.ContactList;
import com.example.task1.ui.main.Tab3.adapter.RecipeAdapter;
import com.example.task1.ui.main.Tab3.data.SampleData;
import com.example.task1.ui.main.Tab3.model.RecipeList;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;;
import org.json.JSONArray;
import org.json.JSONException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.task1.MainActivity.getContextOfApplication;


public class Fragment3 extends Fragment implements TextWatcher {

    RecipeAdapter adapter;
    JSONArray jsonArray;
    String name;
    ArrayList<String> tags = new ArrayList<>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_fragment3, container, false);

        new JSONTask_get().execute("http://143.248.36.208:3500/get_students");//AsyncTask 시작시킴

        RecyclerView recyclerView = view.findViewById(R.id.recipe_recycler_view);
        EditText search_bar = (EditText) view.findViewById(R.id.search_bar);
        search_bar.addTextChangedListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));


        tags.add("#계란 #전자레인지용기 #소금 #물 #쪽파");
        tags.add("#계란 #밥 #대파 #식용유 #소금 #간장 #후추 #참기름");
        tags.add("#버터 #간장 #계란 #밥");
        tags.add("#모짜렐라치즈 #참기름 #케찹 #고추장 #설탕 #밥");
        tags.add("#김치 #참기름 #밥");
        tags.add("#참치캔 #김치 #참기름 #밥");
        tags.add("#식빵 #인절미 #콩고물 #아이스크림");
        tags.add("#스파게티면 #계란 #파마산치즈가루 #다진마늘 #베이컨");
        tags.add("#참치캔 #육수 #간장 #맛술 #설탕 #양파 #계란 #밥");

        adapter = new RecipeAdapter(getContextOfApplication(), tags);
        recyclerView.setAdapter(adapter);

        //아이템 로드
        adapter.setRecipes(new SampleData().getRecipes());

        search_bar.setFocusable(true);


        return view;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        adapter.getFilter().filter(charSequence);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
    public class JSONTask_get extends AsyncTask<String, String, String> {

        ProgressDialog pd_get;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pd_get = ProgressDialog.show(Fragment3.this.getContext(), "Loading Contacts", "Please Wait");
        }


        @Override
        protected String doInBackground(String... urls) {
            try {
                HttpURLConnection con = null;
                BufferedReader reader = null;

                try {
                    //URL url = new URL("http://143.248.36.208:3500/get_students");
                    URL url = new URL(urls[0]);//url을 가져온다.
                    con = (HttpURLConnection) url.openConnection();
                    con.connect();//연결 수행

                    //입력 스트림 생성
                    InputStream stream = con.getInputStream();

                    //속도를 향상시키고 부하를 줄이기 위한 버퍼를 선언한다.
                    reader = new BufferedReader(new InputStreamReader(stream));

                    //실제 데이터를 받는곳
                    StringBuffer buffer = new StringBuffer();

                    //line별 스트링을 받기 위한 temp 변수
                    String line = "";

                    //아래라인은 실제 reader에서 데이터를 가져오는 부분이다. 즉 node.js서버로부터 데이터를 가져온다.
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    //다 가져오면 String 형변환을 수행한다. 이유는 protected String doInBackground(String... urls) 니까
                    return buffer.toString();

                    //아래는 예외처리 부분이다.
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    //종료가 되면 disconnect메소드를 호출한다.
                    if (con != null) {
                        con.disconnect();
                    }
                    try {
                        //버퍼를 닫아준다.
                        if (reader != null) {
                            reader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }//finally 부분
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        //doInBackground메소드가 끝나면 여기로 와서 텍스트뷰의 값을 바꿔준다.
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            ArrayList<ContactList> contacts = new ArrayList<>();
            try {
                jsonArray = new JSONArray(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for(int i = 0 ; i<jsonArray.length(); i++){
                JSONObject jsonObject = null;
                try {
                    jsonObject = jsonArray.getJSONObject(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    name = jsonObject.getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            pd_get.cancel();
        }
    }
}