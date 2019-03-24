package com.olemissboys.studyinpeace.heatmapping;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.UUID;

public class HeatMapLocationObj {
    private ArrayList<User> UserList= new ArrayList<>();
    public ArrayList<LatLng> ImportantLocations= new ArrayList<>();
    ///*Key in the hashmap this Object is tied to.*/
    //private String key;
    public HeatMapLocationObj(){

    }

    public void addUserLoc(UUID id, double lat, double lon){
        UserList.add(new User(id, lat, lon));
    }

    public void addImportantLocation(LatLng ll){
        if(!ImportantLocations.contains(ll)){
            ImportantLocations.add(ll);
        }
    }
    public void removeImportantLocation(LatLng ll){
        ImportantLocations.remove(ll);
    }
    /**
     * Defaults to 48 FT range
     */
    public int numOfUsersInRange(double lat, double lon){
        return numOfUsersInRange(lat, lon, 0.0018D); //.003 to big, .002 close but a bit to big, .001 to small
        //TODO CHANGE THIS TO A REASONABLE RANGE
    }
    /**
     *
     * @param lat
     * @param lon
     * @param range in feet
     * @return
     */
    public int numOfUsersInRange(double lat, double lon, double range){
        double trueRange = range/5280D/69.2D;
        int count = 0;
        for(User u : UserList){
            double latDif = Math.abs(lat - u.lat);
            double lonDif = Math.abs(lon - u.lon);
            double distance = Math.hypot(latDif, lonDif);
            if(distance <= range)
                count += 1;
        }
        return count;
    }

    private class User{
        /** Automatically set when this obj is created or updated */
        public final long timeCreated;
        /** Will be randomly assigned and re-assigned at different times*/
        private final UUID id;
        private double lat;
        private double lon;

        private User(UUID ID, double lat, double lon){
            timeCreated = System.currentTimeMillis();
            id = ID;
            this.lat = lat;
            this.lon = lon;
        }
        public void updateUser(double lat, double lon){
            this.lat = lat;
            this.lon = lon;
        }
    }
}
