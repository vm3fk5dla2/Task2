package com.example.task1.ui.main.Tab3.adapter;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Base64;
import android.util.Log;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> implements Filterable {

    private ArrayList<RecipeList> recipes = new ArrayList<>();
    private ArrayList<String> filtered_list = new ArrayList<>();
    private ArrayList<String> unfiltered_list = new ArrayList<>();
    private Context mContext;
    RecipeList recipe;
    Bitmap bitmap;

    public RecipeAdapter(Context context, ArrayList<String> list) {
        this.mContext = context;
        this.unfiltered_list = list;
        this.filtered_list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);

        return new ViewHolder(itemView);
    }

    ViewHolder viewHolder_cur;

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        recipe = recipes.get(position);

        viewHolder.Name.setText(recipe.getName());
        viewHolder.Tag.setText(filtered_list.get(position));

        final String recipe_name = recipe.getName();
        final String recipe_tags = filtered_list.get(position);
        final String recipe_data = recipe.getData();

        viewHolder.Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, RecipeActivity.class);
                intent.putExtra("name", recipe_name);
                intent.putExtra("tags", recipe_tags);
                intent.putExtra("data", recipe_data);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return filtered_list.size();
    }

    public void setRecipes(ArrayList<RecipeList> recipes) {
        this.recipes = recipes;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    filtered_list = unfiltered_list;
                } else {
                    ArrayList<String> filteringList = new ArrayList<>();
                    for (String name : unfiltered_list) {
                        if (name.toLowerCase().contains(charString.toLowerCase())) {
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
                filtered_list = (ArrayList<String>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView Name, Tag;

        ViewHolder(View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.recipe_name);
            Tag = itemView.findViewById(R.id.recipe_tag);
        }
    }
}