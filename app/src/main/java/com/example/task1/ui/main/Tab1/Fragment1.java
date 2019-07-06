package com.example.task1.ui.main.Tab1;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task1.R;
import com.example.task1.ui.main.Tab1.adapter.ContactAdapter;
import com.example.task1.ui.main.Tab1.model.ContactList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

import static com.example.task1.MainActivity.getContextOfApplication;


public class Fragment1 extends Fragment {

    static final int REQUEST_PERMISSION_KEY = 1;
    RecyclerView recyclerView;
    ContactAdapter adapter = new ContactAdapter(getContextOfApplication());
    JSONArray jsonArray;
    String name;
    String phNum;
    String address;
    Long person;
    Long photo_id;
    String contact_id;
    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment1, container, false);


        fab_open = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_close);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab1 = (FloatingActionButton) view.findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) view.findViewById(R.id.fab2);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();
                anim();
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anim();
                new JSONTask_send().execute("http://143.248.36.208:3500/post");//AsyncTask 시작시킴
                Toast.makeText(getContextOfApplication(), "Complete Uploading", Toast.LENGTH_SHORT).show();
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anim();
                new JSONTask_get().execute("http://143.248.36.208:3500/get_students");//AsyncTask 시작시킴
            }
        });

        String[] PERMISSION_1 = {Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CALL_PHONE};
        if (!hasPermissions(getContextOfApplication(), PERMISSION_1)) {
            ActivityCompat.requestPermissions(getActivity(), PERMISSION_1, REQUEST_PERMISSION_KEY);
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return view;
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission)
                        != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
    public void anim() {

        if (isFabOpen) {
            fab.setImageResource(R.drawable.menu);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
        } else {
            fab.setImageResource(R.drawable.exit);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
        }
    }


    public class JSONTask_send extends AsyncTask<String, String, String> {

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pd = ProgressDialog.show(Fragment1.this.getContext(), "Saving Contacts", "Please Wait");
        }
        @Override
        protected String doInBackground(String... urls) {
            try {
                Uri uri_phone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String[] projection = new String[]{
                        ContactsContract.CommonDataKinds.Phone.NUMBER,
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.Contacts.PHOTO_ID,
                        ContactsContract.Contacts._ID,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                };
                String[] selectionArgs = null;
                Cursor c_phone = getActivity().getContentResolver()
                        .query(uri_phone, projection, null, null, null);
                assert c_phone != null;
                while (c_phone.moveToNext()) {
                    long photo_id = c_phone.getLong(2);
                    long person = c_phone.getLong(3);
                    String contactName = c_phone.getString(1);
                    String phNumber = c_phone.getString(0);
                    String contact_id = c_phone.getString(4);

                    //같은 key 에 대해서 accumulate 는 값을 계속 추가, element 는 덮어쓰기.
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.accumulate("phNum", phNumber);
                    jsonObject.accumulate("name", contactName);
                    jsonObject.accumulate("photo_id", photo_id);
                    jsonObject.accumulate("person", person);
                    jsonObject.accumulate("contact_id", contact_id);

                    Uri uri_email = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
                    Cursor ce = getActivity().getContentResolver()
                            .query(uri_email, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                                    new String[]{contact_id}, null);
                    String email = "Empty Email Address";
                    jsonObject.put("address", email);
                    if (ce != null && ce.moveToFirst()) {
                        email = ce.getString(ce.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                        jsonObject.put("address", email);
                        ce.close();
                    }

                    HttpURLConnection con = null;
                    BufferedReader reader = null;
                    try {
                        //URL url = new URL("http://143.248.36.208:3500/users");
                        URL url = new URL(urls[0]);
                        //연결을 함
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
                        writer.close();//버퍼를 닫아줌

                        //서버로 부터 데이터를 받음
                        InputStream stream = con.getInputStream();
                        reader = new BufferedReader(new InputStreamReader(stream));
                        StringBuffer buffer = new StringBuffer();
                        String line = "";
                        while ((line = reader.readLine()) != null) {
                            buffer.append(line);
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
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
                }
                c_phone.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "OK!";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pd.cancel();
        }
    }

    public class JSONTask_get extends AsyncTask<String, String, String> {

        ProgressDialog pd_get;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pd_get = ProgressDialog.show(Fragment1.this.getContext(), "Loading Contacts", "Please Wait");
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

                try {
                    phNum = jsonObject.getString("phNum");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    address = jsonObject.getString("address");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    person = jsonObject.getLong("person");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    photo_id = jsonObject.getLong("photo_id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    contact_id = jsonObject.getString("contact_id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                contacts.add(new ContactList(name, phNum, address, person, photo_id, contact_id));
            }
            adapter.setItems(contacts);
            recyclerView.setAdapter(adapter);
            pd_get.cancel();
        }
    }
}