package com.example.task1.ui.main.Tab3.adapter;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task1.R;
import com.example.task1.ui.main.Tab3.model.RecipeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> implements Filterable {

    private ArrayList<RecipeList> recipes = new ArrayList<>();
    private ArrayList<String> filtered_list = new ArrayList<>();
    private ArrayList<String> unfiltered_list = new ArrayList<>();
    private Context mContext;

    public RecipeAdapter(Context context, ArrayList<String> list) {
        this.mContext = context;
        this.unfiltered_list = list;
        this.filtered_list = list;
    }

    @NonNull
    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recipe, parent, false);

        return new RecipeAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder viewHolder, int position) {

        RecipeList recipe = recipes.get(position);
//        Bitmap bm = loadContactPhoto(mContext.getContentResolver(), recipe.getPersonId(),
//                recipe.getThumnailld());

        viewHolder.Name.setText(recipe.getName());
        viewHolder.Tag.setText(filtered_list.get(position));
//        if (mContext.getContentResolver() != null && bm != null) {
//            viewHolder.Picture.setImageBitmap(bm);
//        }
    }

    @Override
    public int getItemCount() { return filtered_list.size(); }


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

    public void setRecipes(ArrayList<RecipeList> recipes) { this.recipes = recipes; }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if(charString.isEmpty()) {
                    filtered_list = unfiltered_list;
                } else {
                    ArrayList<String> filteringList = new ArrayList<>();
                    for(String name : unfiltered_list) {
                        if(name.toLowerCase().contains(charString.toLowerCase())) {
                            filteringList.add(name);
                        }
                    }
                    filtered_list = filteringList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered_list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filtered_list = (ArrayList<String>)results.values;
                notifyDataSetChanged();
            }
        };
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView Picture;
        TextView Name, Tag;

        ViewHolder(View itemView) {
            super(itemView);

            Picture = itemView.findViewById(R.id.list_item_picture);

            Name = itemView.findViewById(R.id.recipe_name);
            Tag = itemView.findViewById(R.id.recipe_tag);

        }
    }
}
