package com.olemissboys.studyinpeace.locationregisters;

import com.google.android.gms.maps.model.LatLng;
import com.olemissboys.studyinpeace.MapsActivity;
import com.olemissboys.studyinpeace.heatmapping.BuildingLocationsobj;
import com.olemissboys.studyinpeace.heatmapping.HeatMappingMain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MiscLocationsRegistry {
    public static final String UOfAlabama = "universityofalabama";

    public static void init() throws IOException {
        //Alabama
        HeatMappingMain.addCity(UOfAlabama);
        regAlabamaLocations();
       /* BuildingLocationsobj build = new BuildingLocationsobj(MiscLocationsRegistry.readFile("uofalabama.txt"));
        HeatMappingMain.indexedMasterMap.get(UOfAlabama).ImportantLocations.add(build);*/
        MapsActivity.isHeatMapReady = true;
    }

    public static void regAlabamaLocations(){
        ArrayList<LatLng> list = HeatMappingMain.indexedMasterMap.get(UOfAlabama).ImportantLocations;
        list.add(new LatLng(33.2082752,-87.5503836));
        list.add(new LatLng(33.2087442,-87.5486402));
        list.add(new LatLng(33.2110348,-87.5522581));
        list.add(new LatLng(33.2073156,-87.5172896));
        list.add(new LatLng(33.2118705,-87.54601));
        list.add(new LatLng(33.2118736,-87.5511566));
        list.add(new LatLng(33.2172126,-87.5472597));
        list.add(new LatLng(33.2139148,-87.5492735));
        list.add(new LatLng(33.21176,-87.5505663));
        list.add(new LatLng(33.2166331,-87.5460046));
        list.add(new LatLng(33.2109919,-87.543692));
        list.add(new LatLng(33.2131612,-87.5441012));
        list.add(new LatLng(33.21316,-87.5594222));
        list.add(new LatLng(33.2112418,-87.5459754));
        list.add(new LatLng(33.2090071,-87.5503775));
        list.add(new LatLng(33.2149982,-87.548418));
        list.add(new LatLng(33.2074677,-87.5465997));
        list.add(new LatLng(33.2151773,-87.546818));
        list.add(new LatLng(33.212517,-87.5477304));
        list.add(new LatLng(33.2119897,-87.5482556));
        list.add(new LatLng(33.2125167,-87.5542965));
        list.add(new LatLng(33.2125316,-87.5484237));
        list.add(new LatLng(33.2029888,-87.5419798));
        list.add(new LatLng(33.2104325,-87.551177));
        list.add(new LatLng(33.2075994,-87.5403061));
        list.add(new LatLng(33.2065047,-87.5473198));
        list.add(new LatLng(33.2065047,-87.5473198));
        list.add(new LatLng(33.2155857,-87.5457866));
        list.add(new LatLng(33.2095529,-87.5445657));
        list.add(new LatLng(33.2130029,-87.550612));
        list.add(new LatLng(33.2134819,-87.544942));
        list.add(new LatLng(33.2142438,-87.5434455));
        list.add(new LatLng(33.2143979,-87.5460068));
        list.add(new LatLng(33.2119011,-87.54607));
        list.add(new LatLng(33.212387,-87.5338467));
        list.add(new LatLng(33.2144948,-87.5490803));
        list.add(new LatLng(33.2087674,-87.5178408));
    }
    public static String readFile(String filename) throws IOException {
        String ret = "";
        URL path = ClassLoader.getSystemResource(filename);
        try {
            //File file = new File(path.getFile());
            //File file = new File("src/main/java/com/olemissboys/studyinpeace/locationregisters/" + filename);
            File file = new File(filename);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
            fileReader.close();
            ret = stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException();
        }
        return ret;
    }


}
