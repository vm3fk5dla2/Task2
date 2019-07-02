package com.example.task1.ui.main.Tab3;

import static com.example.task1.MainActivity.getContextOfApplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.task1.MainActivity;
import com.example.task1.R;;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.geometry.LatLngBounds;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.overlay.PathOverlay;

public class Fragment3 extends Fragment implements OnMapReadyCallback {

  MapView mapView;

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.layout_fragment3, container, false);

    mapView = (MapView) view.findViewById(R.id.map_view);
    mapView.getMapAsync(this);

    FloatingActionButton fab = view.findViewById(R.id.timetableBtn);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(getContextOfApplication(), TimeTableActivity.class);
        startActivity(intent);
      }
    });

    return view;
  }

  @Override
  public void onMapReady(@NonNull NaverMap naverMap) {

    UiSettings uiSettings = naverMap.getUiSettings();
    uiSettings.setZoomControlEnabled(false);

    LatLng basicLocation = new LatLng(36.3703641, 127.3626828);
    CameraPosition cameraPosition = new CameraPosition(basicLocation, 14);
    naverMap.setCameraPosition(cameraPosition);

    naverMap.setMinZoom(14);

    LatLng NELimitLocation = new LatLng(36.3763389, 127.3707596);
    LatLng SWLimitLocation = new LatLng(36.3631224, 127.354996);
    naverMap.setExtent(new LatLngBounds(SWLimitLocation, NELimitLocation));

    final LocationData locationData = new LocationData();
    locationData.setData();
    final Marker[] stationMarker = new Marker[locationData.getStationLocSize()];

    for (int i = 0; i < locationData.getStationLocSize(); i++) {
      stationMarker[i] = new Marker();
      stationMarker[i].setPosition(locationData.stationLocList.get(i));
      stationMarker[i].setMap(naverMap);
      stationMarker[i].setCaptionText(locationData.stationNameList.get(i));
    }

    naverMap.setOnMapClickListener(new NaverMap.OnMapClickListener() {
      @Override
      public void onMapClick(@NonNull PointF pointF, @NonNull LatLng latLng) {
        for (int i = 0; i < locationData.getStationLocSize(); i++) {
          if (stationMarker[i].getInfoWindow() != null) {
            stationMarker[i].getInfoWindow().close();
          }
        }
      }
    });

    Overlay.OnClickListener listener = new Overlay.OnClickListener() {
      @Override
      public boolean onClick(@NonNull Overlay overlay) {
        Marker marker = (Marker) overlay;

        final String stationName = marker.getCaptionText();
        final int stationNum = locationData.stationNameList.indexOf(stationName);

        InfoWindow infoWindow = new InfoWindow();
        infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(getContext()) {

          @NonNull
          @Override
          public CharSequence getText(@NonNull InfoWindow infoWindow) {
            String latestTime[] = locationData.getLatestTime(stationNum);
            String infoString = "1st : " + latestTime[0] + "\n2nd : " + latestTime[1];

            return infoString;
          }
        });

        if (marker.getInfoWindow() == null) {
          // 현재 마커에 정보 창이 열려있지 않을 경우 엶
          infoWindow.open(marker);
        }
        return true;

      }
    };

    for (int i = 0; i < locationData.getStationLocSize(); i++) {
      stationMarker[i].setOnClickListener(listener);
    }

    PathOverlay path = new PathOverlay();
    path.setCoords(locationData.pathLocList);
    path.setPatternImage(OverlayImage.fromResource(R.drawable.arrow));
    path.setColor(Color.BLUE);
    path.setWidth(20);
    path.setPatternInterval(50);
    path.setMap(naverMap);


  }

  @Override
  public void onStart() {
    super.onStart();
    mapView.onStart();
  }

  @Override
  public void onResume() {
    super.onResume();
    mapView.onResume();
  }

  @Override
  public void onPause() {
    super.onPause();
    mapView.onPause();
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    mapView.onSaveInstanceState(outState);
  }

  @Override
  public void onStop() {
    super.onStop();
    mapView.onStop();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    mapView.onDestroy();
  }

  @Override
  public void onLowMemory() {
    super.onLowMemory();
    mapView.onLowMemory();
  }


}

