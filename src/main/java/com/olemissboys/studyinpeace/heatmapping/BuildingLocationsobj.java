package com.olemissboys.studyinpeace.heatmapping;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;

public class BuildingLocationsobj {
    public ArrayList<Building> list = new ArrayList<Building>();

        public BuildingLocationsobj() {

        }

        public BuildingLocationsobj(ArrayList<Building> list){
            this.list = list;
        }

    public BuildingLocationsobj(String data){
        String[] strtemp = data.split("\\n");
        ArrayList<String> str = new ArrayList<String>(Arrays.asList(strtemp));
        for(int i = 0; i < str.size(); i++){
            String [] temp = str.get(i).split("/");
            this.list.get(this.list.size()).name = temp[0];
            String[] temp2 = temp[1].split(",");
            this.list.get(this.list.size()).latitude = Double.parseDouble(temp2[0]);
            this.list.get(this.list.size()).longitude = Double.parseDouble(temp2[1]);
        }
    }
        public String getBuildingName(int index){
            return this.list.get(index).name;
        }
        public double getBuildingLatitude(int index){
            return this.list.get(index).latitude;
        }
        public double getBuildingLongitude(int index){
            return this.list.get(index).longitude;
        }
        public LatLng getBuildingLatLng(int index){
            LatLng l = new LatLng(this.list.get(index).latitude, this.list.get(index).longitude);
            return l;
        }
    public ArrayList<LatLng> getBuildingLatLngList(){
        ArrayList<LatLng> ret = new ArrayList<LatLng>();
        for(int i = 0; i < this.list.size(); i++){
            ret.add(getBuildingLatLng(i));
        }
        return ret;
    }


    private class Building{
        String name;
        double latitude;
        double longitude;
        private Building(){
            name = "";
            latitude = 0.0;
            longitude = 0.0;
        }

        private Building(String name, double latitude, double longitude){
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
        }

    }
}
