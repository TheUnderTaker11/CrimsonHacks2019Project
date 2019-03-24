package com.olemissboys.studyinpeace.heatmapping;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.olemissboys.studyinpeace.MapsActivity;
import com.olemissboys.studyinpeace.locationregisters.MiscLocationsRegistry;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class HeatMappingMain {
    public static final int BLUE_PEOPLE_MAX = 20;
    public static final int YELLOW_PEOPLE_MAX = 40;
    public static final int RED_PEOPLE_MAX = 60;

    public static final HashMap<String, HeatMapLocationObj> indexedMasterMap = new HashMap<>();

    public static void fillFakeUsersAlabama(){
        Random rand = new Random(30);
        //Random rand = new Random();

        HeatMapLocationObj obj = indexedMasterMap.get(MiscLocationsRegistry.UOfAlabama);
        for(LatLng ll : obj.ImportantLocations){
            for(int i = 0 ; i < rand.nextInt(60) ; i++){
                obj.addUserLoc(UUID.randomUUID(), ll.latitude, ll.longitude);
            }
        }
    }
    public static void addCity(String cityKey){
        indexedMasterMap.put(cityKey, new HeatMapLocationObj());
    }
    public static void addUserLoc(String city, UUID id, double lat, double lon){
        indexedMasterMap.get(city).addUserLoc(id, lat, lon);
}
    public static void renderCurrentHeatMap(GoogleMap mMap, String city){
        mMap.clear();
        HeatMapLocationObj heatmapObj = indexedMasterMap.get(city);
        for(LatLng ll: heatmapObj.ImportantLocations){
                int peopleInRange = heatmapObj.numOfUsersInRange(ll.latitude, ll.longitude);
                byte color = 0;
                if (peopleInRange < BLUE_PEOPLE_MAX) {
                    color = 0;
                } else if (peopleInRange < YELLOW_PEOPLE_MAX) {
                    color = 1;
                } else {//if (peopleInRange < RED_PEOPLE_MAX) {
                    color = 2;
                }
                MapsActivity.drawCircle(mMap, ll, color);
        }
    }
}
