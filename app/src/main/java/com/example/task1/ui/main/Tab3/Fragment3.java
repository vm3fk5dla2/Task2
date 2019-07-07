package com.example.task1.ui.main.Tab3;

import android.os.Bundle;
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
import com.example.task1.ui.main.Tab3.adapter.RecipeAdapter;
import com.example.task1.ui.main.Tab3.data.SampleData;
import com.example.task1.ui.main.Tab3.model.RecipeList;;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.task1.MainActivity.getContextOfApplication;


public class Fragment3 extends Fragment implements TextWatcher {

    RecipeAdapter adapter;

    ArrayList<String> tags = new ArrayList<>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_fragment3, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recipe_recycler_view);
        EditText search_bar = (EditText) view.findViewById(R.id.search_bar);
        search_bar.addTextChangedListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));


        tags.add("#AAA");
        tags.add("#BBB");
        tags.add("#CCC");
        tags.add("#DDD");
        tags.add("#EEE");
        tags.add("#FFF");


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
}