package com.suhaili.a15017519.map;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btnN,btnC,btnE;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LatLng north = new LatLng(1.441147, 103.772072);
        final LatLng central = new LatLng(1.298105, 103.847451);
        final LatLng east = new LatLng(1.367461, 103.927994);
        final LatLng sg = new LatLng(1.3521,103.8198);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback(){

            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;


               final Marker nhq = map.addMarker(new
                        MarkerOptions()
                        .position(north)
                        .title("HQ-North")
                        .snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am-5pm Tel:65433456")
                        .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.star_big_on)));



                final Marker chq = map.addMarker(new
                        MarkerOptions()
                        .position(central)
                        .title("HQ-Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 Operating hours: 11am-8pm Tel:67788652")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));



                final Marker ehq = map.addMarker(new
                        MarkerOptions()
                        .position(east)
                        .title("HQ-East")
                        .snippet("Block 555, Tampines Ave 3, 287788 Operating hours: 9am-5pm Tel:66776677")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener(){
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if(marker.getTitle().equals("HQ-North")){
                            Toast.makeText(MainActivity.this,nhq.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if(marker.getTitle().equals("HQ-Central")){
                            Toast.makeText(MainActivity.this, chq.getTitle(), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, ehq.getTitle(), Toast.LENGTH_SHORT).show();
                        }

                        return false;
                    }

                });



                map.moveCamera(CameraUpdateFactory.newLatLngZoom(sg, 11));
                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);

                ui.setZoomControlsEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);


                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                    return;
                }



            }

//            public boolean onMarkerClick(Marker arg0) {
//                if(arg0.getTitle().equals("HQ-East")){
//                    // if marker source is clicked
//                    Toast.makeText(MainActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
//                }else if (arg0.getTitle().equals("HQ-North")) {
//                    // if marker source is clicked
//                    Toast.makeText(MainActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();
//                }else if (arg0.getTitle().equals("HQ-Central")) {
//                    // if marker source is clicked
//                    Toast.makeText(MainActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();
//                }
//                return true;
//            }
//
        });

        btnN = (Button) findViewById(R.id.btnNorth);
        btnC = (Button) findViewById(R.id.btnCentral);
        btnE = (Button) findViewById(R.id.btnEast);

        btnN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(north,
                            15));
                }
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(central,
                            15));
                }
            }
        });

        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(east,
                            15));
                }
            }
        });



    }
}


