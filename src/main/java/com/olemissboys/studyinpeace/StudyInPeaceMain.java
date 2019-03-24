package com.olemissboys.studyinpeace;

import com.google.android.gms.maps.model.LatLng;
import com.olemissboys.studyinpeace.heatmapping.BuildingLocationsobj;
import com.olemissboys.studyinpeace.heatmapping.HeatMappingMain;
import com.olemissboys.studyinpeace.locationregisters.MiscLocationsRegistry;

import java.io.IOException;

public class StudyInPeaceMain {
    public static boolean toggle = true;

    public static void init(){
        try {
            MiscLocationsRegistry.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HeatMappingMain.fillFakeUsersAlabama();
    }
}
