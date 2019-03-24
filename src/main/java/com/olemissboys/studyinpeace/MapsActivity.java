package com.olemissboys.studyinpeace;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.olemissboys.studyinpeace.activities.OptionsActivity;
import com.olemissboys.studyinpeace.heatmapping.HeatMappingMain;
import com.olemissboys.studyinpeace.locationregisters.MiscLocationsRegistry;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public static boolean isHeatMapReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if(StudyInPeaceMain.toggle) {
            StudyInPeaceMain.init();
            StudyInPeaceMain.toggle = false;
        }
    }

    public void refresh(View view){
        if(mMap != null){
            HeatMappingMain.renderCurrentHeatMap(mMap, MiscLocationsRegistry.UOfAlabama);
        }
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setBuildingsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //mMap.clear();
            startActivity(new Intent(getApplicationContext(), OptionsActivity.class));
            askLocationPermissions();
            return;
        }
        mMap.setMyLocationEnabled(true);
        // Add a marker in Sydney and move the camera
        LatLng olemissTrue = new LatLng(34.3647, -89.5384); // Actual Ole Miss
        LatLng olemiss = new LatLng(33.2109919,-87.543692); //NOT Ole miss, is U of Alabama
        mMap.addMarker(new MarkerOptions().position(olemiss).title("Marker in University of Alabama"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(olemiss));
        //drawCircle(mMap, olemiss, (byte)0);
        goToLocationZoom(olemiss);
    }

    public void mapButton(View view){
        startActivity(new Intent(getApplicationContext(), OptionsActivity.class));
    }

    public void geoLocate(View view){
        try {
            EditText et = (EditText) findViewById(R.id.editText);
            String location = et.getText().toString();
            if (location == null) return;
            Geocoder gc = new Geocoder(this);
            List<Address> list = gc.getFromLocationName(location, 1);
            Address address = list.get(0);
            double lat = address.getLatitude();
            double lng = address.getLongitude();
            goToLocationZoom(new LatLng(lat, lng), 16);
        }catch(IOException e){}
        catch(Error e){}
        catch(Exception e){}
    }

    public void askLocationPermissions(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            /*if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {*/
                // No explanation needed, we can request the permission.
                String[] strArray = new String[1];
                strArray[0] = Manifest.permission.ACCESS_FINE_LOCATION;
                ActivityCompat.requestPermissions(this,
                        strArray,
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            //}
        }
    }

    /**
     * Intensity is
     * 0 for blue
     * 1 is orange
     * 2 is red
     * @param latLng
     * @param color
     * @return
     */
    public static Circle drawCircle(GoogleMap mMap, LatLng latLng, Byte color, double radius, float strokeWidth) {
        if (color == 0) {
            CircleOptions options = new CircleOptions().center(latLng).radius(radius).fillColor(0x330000FF).strokeColor(Color.BLUE).strokeWidth(strokeWidth);
            return mMap.addCircle(options);
        }
        if (color == 1) {
            CircleOptions options = new CircleOptions().center(latLng).radius(radius).fillColor(0x33FFFF00).strokeColor(Color.RED).strokeWidth(strokeWidth);
            //CircleOptions options = new CircleOptions().center(latLng).radius(radius).fillColor(0x33FD6A02).strokeColor(Color.rgb(255, 102, 0)).strokeWidth(strokeWidth);
            return mMap.addCircle(options);
        }
        if (color == 2) {
            CircleOptions options = new CircleOptions().center(latLng).radius(radius).fillColor(0x33FF0000).strokeColor(Color.RED).strokeWidth(strokeWidth);
            return mMap.addCircle(options);
        }
        else return null;
    }

    /**
     * Intensity is
     * 0 for blue
     * 1 is orange
     * 2 is red
     * @param latLng
     * @param color
     * @return
     */
    public static Circle drawCircle(GoogleMap mMap, LatLng latLng, Byte color){
        return drawCircle(mMap, latLng, color, 15, 3);
    }

    private void goToLocationZoom(LatLng latlng){
        goToLocationZoom(latlng, 16);
    }
    private void goToLocationZoom(LatLng latlng, float zoom){
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latlng, zoom);
        mMap.moveCamera(update);
    }
}
