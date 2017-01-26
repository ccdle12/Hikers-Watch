package com.ccdle.christophercoverdale.hikerswatch;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ccdle.christophercoverdale.hikerswatch.Fragments.HikersWatch;
import com.ccdle.christophercoverdale.hikerswatch.Fragments.WeatherAtLocation;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class MainActivity extends AppCompatActivity implements MaterialTabListener {
    public static final String DEBUG = "CRC";
    private HikersWatch mHikersWatchFragment;
    private WeatherAtLocation mWeatherAtLocation;
    private MaterialTabHost mTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createFragments();
        createMaterialTabBar();
        showMainFragment();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        int position = tab.getPosition();
        Log.i(DEBUG, "Current selected position: " + position);

        mTabBar.setSelectedNavigationItem(position);

        android.support.v4.app.Fragment fragment = null;

        switch(position) {
            case 0:
                fragment = mHikersWatchFragment;
                break;
            case 1:
                fragment = mWeatherAtLocation;
                break;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    private void createFragments() {
        mHikersWatchFragment = new HikersWatch();
        mWeatherAtLocation = new WeatherAtLocation();
    }

    private void createMaterialTabBar() {
        //      Adding the Material Tab Bar
        mTabBar = (MaterialTabHost) findViewById(R.id.tab_bar);
        mTabBar.addTab(mTabBar.newTab().setText("Watch").setTabListener(this));
        mTabBar.addTab(mTabBar.newTab().setText("Weather").setTabListener(this));
        mTabBar.addTab(mTabBar.newTab().setText("Compass").setTabListener(this));
        mTabBar.addTab(mTabBar.newTab().setText("Map").setTabListener(this));
        mTabBar.setPrimaryColor(Color.parseColor("#3F51B5"));
    }

    private void showMainFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, mHikersWatchFragment)
                .commit();
    }
}
