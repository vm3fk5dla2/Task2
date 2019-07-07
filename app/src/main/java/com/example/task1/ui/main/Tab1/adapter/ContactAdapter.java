package com.example.task1.ui.main.Tab1.adapter;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task1.R;
import com.example.task1.ui.main.Tab1.model.ContactList;

import java.util.ArrayList;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private ArrayList<ContactList> items = new ArrayList<>();
    private Context mContext;
    EditText callNum;
    String num;


    public ContactAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);

        callNum = (EditText) itemView.findViewById(R.id.call);
        Button button1 = (Button) itemView.findViewById(R.id.callBtn);
        button1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                num = callNum.getText().toString();
                try {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + num));
                    mContext.startActivity(callIntent);
                } catch (ActivityNotFoundException e) {
                }
            }
        });

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        ContactList item = items.get(position);
        Bitmap bm = loadContactPhoto(mContext.getContentResolver(), item.getPersonId(),
                item.getThumnailld());

        viewHolder.Name.setText(item.getName());
        viewHolder.Phone_number.setText(item.getPhone_number());
        viewHolder.Address.setText(item.getAddress());
        viewHolder.CallNum.setText(item.getPhone_number());
        if (mContext.getContentResolver() != null && bm != null) {
            viewHolder.Picture.setImageBitmap(bm);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public Bitmap loadContactPhoto(ContentResolver cr, long id, long photo_id) {

        byte[] photoBytes = null;
        Uri photoUri = ContentUris.withAppendedId(ContactsContract.Data.CONTENT_URI, photo_id);
        Cursor c = cr
                .query(photoUri, new String[]{ContactsContract.CommonDataKinds.Photo.PHOTO}, null, null,
                        null);

        try {
            if (c.moveToFirst()) {
                photoBytes = c.getBlob(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.close();
        }

        if (photoBytes != null) {
            return resizingBitmap(BitmapFactory.decodeByteArray(photoBytes, 0, photoBytes.length));
        }

        return null;
    }

    public Bitmap resizingBitmap(Bitmap oBitmap) {
        if (oBitmap == null) {
            return null;
        }

        float width = oBitmap.getWidth();
        float height = oBitmap.getHeight();
        float resizing_size = 120;

        Bitmap rBitmap = null;
        if (width > resizing_size) {
            float mWidth = (float) (width / 100);
            float fScale = (float) (resizing_size / mWidth);
            width *= (fScale / 100);
            height *= (fScale / 100);

        } else if (height > resizing_size) {
            float mHeight = (float) (height / 100);
            float fScale = (float) (resizing_size / mHeight);

            width *= (fScale / 100);
            height *= (fScale / 100);
        }

        rBitmap = Bitmap.createScaledBitmap(oBitmap, (int) width, (int) height, true);
        return rBitmap;
    }

    public void setItems(ArrayList<ContactList> items) {
        this.items = items;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView Picture;
        TextView Name, Phone_number, Address;
        EditText CallNum;

        ViewHolder(View itemView) {
            super(itemView);

            Picture = itemView.findViewById(R.id.list_item_picture);

            Name = itemView.findViewById(R.id.list_item_name);
            Phone_number = itemView.findViewById(R.id.list_item_number);
            Address = itemView.findViewById(R.id.list_item_address);
            CallNum = itemView.findViewById(R.id.call);
        }
    }
}