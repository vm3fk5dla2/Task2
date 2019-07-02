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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task1.R;
import com.example.task1.ui.main.Tab1.adapter.ContactAdapter;
import com.example.task1.ui.main.Tab1.model.ContactList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static com.example.task1.MainActivity.getContextOfApplication;

public class Fragment1 extends Fragment {

  static final int REQUEST_PERMISSION_KEY = 1;
  RecyclerView recyclerView;
  ContactAdapter adapter = new ContactAdapter(getContextOfApplication());


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.layout_fragment1, container, false);

    FloatingActionButton fab = view.findViewById(R.id.fab);

    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
        startActivity(intent);
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
    LoadContactsAsync lca = new LoadContactsAsync();
    lca.execute();
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

  class LoadContactsAsync extends AsyncTask<Void, Void, ArrayList<ContactList>> {

    ProgressDialog pd;

    @Override
    protected void onPreExecute() {
      // TODO Auto-generated method stub
      super.onPreExecute();

      pd = ProgressDialog.show(Fragment1.this.getContext(), "Loading Contacts", "Please Wait");
    }

    @Override
    protected ArrayList<ContactList> doInBackground(Void... params) {
      // TODO Auto-generated method stub
      ArrayList<ContactList> contacts = new ArrayList<>();
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

        Uri uri_email = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        Cursor ce = getActivity().getContentResolver()
            .query(uri_email, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                new String[]{contact_id}, null);
        String email = "Empty Email Address";
        if (ce != null && ce.moveToFirst()) {
          email = ce.getString(ce.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
          ce.close();
        }
        contacts.add(new ContactList(contactName, phNumber, email, person, photo_id, contact_id));
      }
      adapter.setItems(contacts);
      c_phone.close();
      return contacts;
    }

    @Override
    protected void onPostExecute(ArrayList<ContactList> contacts) {
      // TODO Auto-generated method stub
      super.onPostExecute(contacts);

      pd.cancel();

      recyclerView.setAdapter(adapter);
    }
  }
}
