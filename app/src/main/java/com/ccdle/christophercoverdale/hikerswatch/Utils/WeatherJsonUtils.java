package com.ccdle.christophercoverdale.hikerswatch.Utils;

import android.content.Context;
import android.util.Log;

import com.ccdle.christophercoverdale.hikerswatch.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by USER on 1/26/2017.
 */

public class WeatherJsonUtils {
    private WeatherJsonUtils(){};

    public static String getStringFromJson(Context context, String forecastJsonStr) throws JSONException {
       JSONObject content = new JSONObject(forecastJsonStr);
        JSONArray weather = content.getJSONArray("weather");
        JSONObject main = content.getJSONObject("main");
//        JSONObject visibility = content.getJSONObject("visibility");
        JSONObject wind = content.getJSONObject("wind");
        JSONObject clouds = content.getJSONObject("clouds");
        JSONObject sys = content.getJSONObject("sys");

        for (int i = 0;  i < weather.length(); i++) {
            Log.d(MainActivity.DEBUG, "Weather: " + weather.get(i).toString());
        }

        Log.d(MainActivity.DEBUG, "Main: " + main.toString());
//        Log.d(MainActivity.DEBUG, "Visibility: " + visibility.toString());
        Log.d(MainActivity.DEBUG, "Wind: " + wind.toString());
        Log.d(MainActivity.DEBUG, "Clouds: " + clouds.toString());
        Log.d(MainActivity.DEBUG, "sys: " + sys.toString());
        return "test";
    }
}
