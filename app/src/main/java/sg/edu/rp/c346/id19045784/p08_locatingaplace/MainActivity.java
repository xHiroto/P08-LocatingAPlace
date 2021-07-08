package sg.edu.rp.c346.id19045784.p08_locatingaplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
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

    Button btn1, btn2, btn3;
    Spinner spinner;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                LatLng poi_Singapore = new LatLng(1.290270, 103.851959);
                LatLng poi_Admiralty = new LatLng(1.424450, 103.829849);
                LatLng poi_Orchard = new LatLng(1.304833, 103.831833);
                LatLng poi_tempines = new LatLng(1.3499986, 103.9499962);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Singapore,
                        15));
                map.moveCamera(CameraUpdateFactory.zoomTo(11));


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
                }

                //LatLng poi_CausewayPoint1 = new LatLng(1.436065, 103.786263);
                Marker cp = map.addMarker(new
                        MarkerOptions()
                        .position(poi_Admiralty)
                        .title("HQ North")
                        .snippet("Block 333, Admiralty Ave 3, 765654 ")

                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                Marker cp1 = map.addMarker(new
                        MarkerOptions()
                        .position(poi_Orchard)
                        .title("HQ Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 ")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                Marker cp2 = map.addMarker(new
                        MarkerOptions()
                        .position(poi_tempines)
                        .title("HQ East")
                        .snippet("Block 555, Tampines Ave 3, 287788 ")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        String markerLocation = marker.getTitle();
                        Toast.makeText(MainActivity.this, markerLocation, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

            }
        });

        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        spinner = findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 1){
                    LatLng poi_Admiralty = new LatLng(1.424450, 103.829849);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Admiralty,
                            15));
                }
                else if (i == 2){
                    LatLng poi_Orchard = new LatLng(1.304833, 103.831833);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Orchard,
                            15));
                }
                else if (i == 3){
                    LatLng poi_tempines = new LatLng(1.3499986, 103.9499962);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_tempines,
                            15));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

       btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    //map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    LatLng poi_Admiralty = new LatLng(1.424450, 103.829849);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Admiralty,
                            15));
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    //map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                    LatLng poi_Orchard = new LatLng(1.304833, 103.831833);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Orchard,
                            15));
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    //map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    LatLng poi_tempines = new LatLng(1.3499986, 103.9499962);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_tempines,
                            15));
                }
            }
        });

    }
}