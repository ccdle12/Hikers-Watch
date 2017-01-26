package com.ccdle.christophercoverdale.hikerswatch.Utils;

import com.ccdle.christophercoverdale.hikerswatch.R;
import com.ccdle.christophercoverdale.hikerswatch.Singleton.CurrentLocation;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by USER on 1/25/2017.
 */

public class WeatherAPIRequestUtils {

    private static final String WEATHER_API_REQUEST = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String WEATHER_API_KEY = "&APPID=106ad629232784fc9ed48d419ebeeff0";
    private static String location = CurrentLocation.getCurrentLocation();

    static OkHttpClient client = new OkHttpClient();


    public static String run() throws IOException {
        Request request = new Request.Builder()
                .url(WEATHER_API_REQUEST + CurrentLocation.getCurrentLocation() + WEATHER_API_KEY)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
