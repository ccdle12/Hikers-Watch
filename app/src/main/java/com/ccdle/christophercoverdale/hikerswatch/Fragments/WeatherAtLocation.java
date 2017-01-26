package com.ccdle.christophercoverdale.hikerswatch.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ccdle.christophercoverdale.hikerswatch.R;
import com.ccdle.christophercoverdale.hikerswatch.Singleton.CurrentLocation;
import com.ccdle.christophercoverdale.hikerswatch.Utils.WeatherAPIRequestUtils;
import com.ccdle.christophercoverdale.hikerswatch.Utils.WeatherJsonUtils;

import org.json.JSONException;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherAtLocation extends Fragment {


    public WeatherAtLocation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_at_location, container, false);
        TextView weather_information = (TextView) view.findViewById(R.id.weather_information);

        Observable.fromCallable(() -> requestWeatherInformation())
            .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weather -> weather_information.setText(weather));

        return view;
    }

    private String requestWeatherInformation() {
        String cleanedJson = "";

        try {
            cleanedJson = WeatherJsonUtils.getStringFromJson(getContext(), WeatherAPIRequestUtils.run());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cleanedJson;
    }

}
