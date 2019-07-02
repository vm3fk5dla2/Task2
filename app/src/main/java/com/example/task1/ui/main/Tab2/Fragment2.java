package com.example.task1.ui.main.Tab2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
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

import static com.example.task1.MainActivity.getContextOfApplication;

public class Fragment2 extends Fragment {

  GridView gridViewImages;

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.layout_fragment2, container, false);

    gridViewImages = (GridView) view.findViewById(R.id.gridViewImages);
    ImageGridAdapter imageGridAdapter = new ImageGridAdapter(this.getContext());
    gridViewImages.setAdapter(imageGridAdapter);

    return view;

  }

}