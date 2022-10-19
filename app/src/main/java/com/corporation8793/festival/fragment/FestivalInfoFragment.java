package com.corporation8793.festival.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.corporation8793.festival.room.AppDatabase;
import com.corporation8793.festival.activity.MainActivity;
import com.corporation8793.festival.R;
import com.corporation8793.festival.room.Reservation;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraAnimation;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.overlay.OverlayImage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class FestivalInfoFragment extends Fragment implements OnMapReadyCallback {

    ImageView arrow_left, infoImage;
    TextView infoFestivalText, infoFestivalPeriod, infoText, subText, subText2, subText3;
    int[] image;
    Button reservationButton;

    MapView mapView;
    NaverMap naverMap;
    double latitude, longitude;
    String address, x, y;
    // 마커 변수
    Marker marker = new Marker();

    List<Reservation> reservationList = new ArrayList<Reservation>();

    public static FestivalInfoFragment newInstance() {
        return new FestivalInfoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_festival_info, container, false);

        infoFestivalText = view.findViewById(R.id.infoFestivalText);
        infoFestivalPeriod = view.findViewById(R.id.infoFestivalPeriod);
        infoText = view.findViewById(R.id.infoText);
        infoImage = view.findViewById(R.id.infoImage);
        reservationButton = view.findViewById(R.id.reservationButton);
        subText = view.findViewById(R.id.subText);
        subText2 = view.findViewById(R.id.subText2);
        subText3 = view.findViewById(R.id.subText3);
        mapView = view.findViewById(R.id.mapView);

        image = new int[] {R.drawable.event_image1, R.drawable.event_image2};
        int imageId = (int)(Math.random() * image.length);

        Bundle bundle = getArguments();

        if(bundle.getString("이동").equals("메인")) {
            infoFestivalText.setText(bundle.getString("이름"));
            infoFestivalPeriod.setText(bundle.getString("기간"));
            infoText.setText(bundle.getString("내용"));
            subText.setText(bundle.getString("위치"));
            subText2.setText(bundle.getString("주최"));
            if(bundle.getString("도로명").isEmpty()) {
                subText3.setText(bundle.getString("지번"));
                address = bundle.getString("지번");
            } else {
                subText3.setText(bundle.getString("도로명"));
                address = bundle.getString("도로명");
            }
            if(bundle.getString("위도").isEmpty() || bundle.getString("경도").isEmpty()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        requestGeocode();
                    }
                }).start();
            } else {
                latitude = Double.parseDouble(bundle.getString("위도"));
                longitude = Double.parseDouble(bundle.getString("경도"));
            }
            infoImage.setBackgroundResource(image[imageId]);
            reservationButton.setVisibility(View.GONE);
        } else {
            infoFestivalText.setText(bundle.getString("이름"));
            infoFestivalPeriod.setText(bundle.getString("기간"));
            infoText.setText(bundle.getString("내용"));
            subText.setText(bundle.getString("위치"));
            subText2.setText(bundle.getString("주관"));
            if(bundle.getString("도로명").isEmpty()) {
                subText3.setText(bundle.getString("지번"));
                address = bundle.getString("지번");
            } else {
                subText3.setText(bundle.getString("도로명"));
                address = bundle.getString("도로명");
            }
            if(bundle.getString("위도").isEmpty() || bundle.getString("경도").isEmpty()) {
                requestGeocode();
            } else {
                latitude = Double.parseDouble(bundle.getString("위도"));
                longitude = Double.parseDouble(bundle.getString("경도"));
            }
            infoImage.setBackgroundResource(image[imageId]);
        }

        arrow_left = view.findViewById(R.id.arrow_left);
        arrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        reservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle1 = new Bundle();
                bundle1.putString("예약축제이름", bundle.getString("이름"));
                bundle1.putInt("사용자구별", bundle.getInt("예약구별"));
                bundle1.putString("예약축제기간", bundle.getString("기간"));

                AppDatabase db = AppDatabase.getDBInstance(getActivity());
                reservationList = db.reservationDao().getAllReservation();
                boolean check = false;

                //이미 예약되어있는 축제인 경우 안내메시지
                for(int i=0; i < reservationList.size(); i++) {
                    if((reservationList.get(i).uid == bundle.getInt("예약구별")) &&
                            reservationList.get(i).rFestival.equals(bundle.getString("이름"))) {
                        check = true;
                    }
                }

                if(check) {
                    Toast.makeText(getActivity(), "이미 예약되어있는 축제입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    FragmentTransaction transaction = ((MainActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                    ReservationFragment reservationFragment = new ReservationFragment();

                    reservationFragment.setArguments(bundle1);
                    transaction.replace(R.id.containers,reservationFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;

        naverMap.setLayerGroupEnabled(naverMap.LAYER_GROUP_BUILDING, true);     //건물
        naverMap.setLayerGroupEnabled(naverMap.LAYER_GROUP_TRANSIT, true);      //대중교통

        //latitude, longitude
        CameraPosition cameraPosition = new CameraPosition(new LatLng(latitude, longitude), 15);

        //Marker
        setMarker(marker, latitude, longitude, R.drawable.ic_baseline_place_24);

        marker.setOnClickListener(new Overlay.OnClickListener() {
            @Override
            public boolean onClick(@NonNull Overlay overlay) {
                CameraUpdate cameraUpdate = CameraUpdate.scrollAndZoomTo(new LatLng(latitude, longitude),18).animate(CameraAnimation.Fly, 1000);

                naverMap.moveCamera(cameraUpdate);

                return false;
            }
        });

        naverMap.setCameraPosition(cameraPosition);
    }

    private void setMarker(Marker marker, double latitude, double longitude, int resourceID) {
        //아이콘 지정
        marker.setIcon(OverlayImage.fromResource(resourceID));
        //마커 위치
        marker.setPosition(new LatLng(latitude, longitude));
        //마커 표시
        marker.setMap(naverMap);
    }

    public void requestGeocode() {
        try {
            BufferedReader bufferedReader;
            StringBuilder stringBuilder = new StringBuilder();
            String query = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + URLEncoder.encode(address,"UTF-8");
                    //+ "&X-NCP-APIGW-API-KEY-ID=6rhjpfnap7&X-NCP-APIGW-API-KEY=PxOFqrhmSDXcCSHQPMun5n1dRHqFKzpzrf4ReTen";
            URL url = new URL(query);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if(connection != null) {
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.setRequestMethod("GET");
                connection.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "6rhjpfnap7");
                connection.setRequestProperty("X-NCP-APIGW-API-KEY", "PxOFqrhmSDXcCSHQPMun5n1dRHqFKzpzrf4ReTen");
                connection.setDoInput(true);

                int responseCode = connection.getResponseCode();

                if(responseCode == 200) {
                    bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                } else {
                    bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                }

                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }

                int indexFirst, indexLast;

                indexFirst = stringBuilder.indexOf("\"x\":\"");
                indexLast = stringBuilder.indexOf("\",\"y\":");
                x = stringBuilder.substring(indexFirst + 5, indexLast);

                indexFirst = stringBuilder.indexOf("\"y\":\"");
                indexLast = stringBuilder.indexOf("\",\"distance\":");
                y = stringBuilder.substring(indexFirst + 5, indexLast);

                latitude = Double.parseDouble(x);
                longitude = Double.parseDouble(y);

                bufferedReader.close();
                connection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}