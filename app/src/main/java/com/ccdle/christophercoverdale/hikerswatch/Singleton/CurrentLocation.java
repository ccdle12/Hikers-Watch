package com.ccdle.christophercoverdale.hikerswatch.Singleton;

import android.util.Log;

import com.ccdle.christophercoverdale.hikerswatch.MainActivity;

/**
 * Created by USER on 1/25/2017.
 */

public class CurrentLocation {
    private CurrentLocation() {};

    private static String currentLocation = "";

    public static String getCurrentLocation() {
        return currentLocation;
    }

    public static void setCurrentLocation(String currentLocation) {
        String[] splitCurrentLocation = currentLocation.split("\\W");

        for (int i = 0; i < splitCurrentLocation.length; i++) {

            String word = splitCurrentLocation[i];

            if (i == splitCurrentLocation.length - 1) CurrentLocation.currentLocation += word;
            else CurrentLocation.currentLocation += word + "+";

        }
    }
}
