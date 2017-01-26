package com.ccdle.christophercoverdale.hikerswatch.Fragments;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ccdle.christophercoverdale.hikerswatch.R;
import com.ccdle.christophercoverdale.hikerswatch.Singleton.CurrentLocation;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;

import static android.content.Context.LOCATION_SERVICE;
import static com.ccdle.christophercoverdale.hikerswatch.MainActivity.DEBUG;


/**
 * A simple {@link Fragment} subclass.
 */
public class HikersWatch extends Fragment implements LocationListener {
    LocationManager mLocationManager;
    String mProvider;
    private TextView mLatittude;
    private TextView mLongitude;
    private TextView mAccuracy;
    private TextView mSpeed;
    private TextView mBearing;
    private TextView mAltitude;
    private TextView mAddress;
    private Location mCurrentLocation;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 400;

    public HikersWatch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hikers_watch, container, false);

        createLocationManagerAndProvider();
        findAllViews(view);
        requestLocationUpdate();

        return view;
    }



    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        updateUiWithLocation();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(DEBUG, "onPause called: Hiker's Watch");
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLocationManager.removeUpdates(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.i(DEBUG, "onResume called: Hiker's Watch");
        requestLocationUpdate();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    private void findAllViews(View view) {
        // Retrieve all the text views from layout
        mLatittude = (TextView) view.findViewById(R.id.latitude);
        mLongitude = (TextView) view.findViewById(R.id.longitude);
        mAccuracy = (TextView) view.findViewById(R.id.accuracy);
        mSpeed = (TextView) view.findViewById(R.id.speed);
        mBearing = (TextView) view.findViewById(R.id.bearing);
        mAltitude = (TextView) view.findViewById(R.id.altitude);
        mAddress = (TextView) view.findViewById(R.id.address);
    }

    private void createLocationManagerAndProvider() {
        // Create location manager and provider
        mLocationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        mProvider = mLocationManager.getBestProvider(new Criteria(), false);
    }

    private void requestLocationUpdate() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
    }

    private void updateUiWithLocation() {

        mLatittude.setText("Latitude: " + String.valueOf(mCurrentLocation.getLatitude()));
        mLongitude.setText("Longitude: " + String.valueOf(mCurrentLocation.getLongitude()));
        mAccuracy.setText("Accuracy: " + String.valueOf(mCurrentLocation.getAccuracy()));
        mSpeed.setText("Speed: " + String.valueOf(mCurrentLocation.getSpeed()));
        mBearing.setText("Bearing: " + String.valueOf(mCurrentLocation.getBearing()));
        mAltitude.setText("Altitude: " + String.valueOf(mCurrentLocation.getAltitude()));

        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());

        try {
            List<Address> listAddresses = geocoder.getFromLocation(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude(), 1);

            if (listAddresses != null && listAddresses.size() > 0) {

                String addressHolder = "";
                String locationForSingleton = "";

                for (int i = 0 ; i <= listAddresses.get(0).getMaxAddressLineIndex(); i++) {
                    addressHolder += listAddresses.get(0).getAddressLine(i) + "\n";
                }
//              Send currentLocation to Singleton
                CurrentLocation.setCurrentLocation(addressHolder);
                mAddress.setText("Address: \n" + addressHolder);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
