package com.ayata.ayataweatherapplication.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.ayata.ayataweatherapplication.R;
import com.ayata.ayataweatherapplication.dashboard.adapter.WeatherAdapter;
import com.ayata.ayataweatherapplication.dashboard.weathermodel.ModelCurrentWeather;
import com.ayata.ayataweatherapplication.dashboard.weathermodel.WeatherInfo;
import com.ayata.ayataweatherapplication.dashboard.weatherviewmodel.WeatherViewModel;
import com.ayata.ayataweatherapplication.databinding.ActivityMainBinding;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainxml;

    LinearLayoutManager layoutManager;
    List<WeatherInfo> weatherInfos;
    WeatherAdapter weatherAdapter;
    WeatherViewModel weatherViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainxml = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainxml.getRoot());




        initRecyclerView();
 //       observeLogin();


        mainxml.includeToolbar.toolbarLeftImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "back button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        mainxml.includeToolbar.toolbarRightImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(),mainxml.includeToolbar.toolbarRightImageview);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId())
                        {
                            case R.id.item1:
                                Toast.makeText(getApplicationContext(), "item 1 clicked", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.item2:
                                Toast.makeText(getApplicationContext(), "item 2 clicked", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.item3:
                                Toast.makeText(getApplicationContext(), "item 3 clicked", Toast.LENGTH_SHORT).show();
                                break;
                             default:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.inflate(R.menu.weather_menu_item);
                popupMenu.show();
            }
        });
    }
//
//    private void observeLogin() {
//        weatherViewModel.getProgressBarObservable().observe(this, new Observer<Boolean>() {
//            @Override
//            public void onChanged(Boolean aBoolean) {
//
//            }
//        });
//    }


    private void initRecyclerView() {
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mainxml.mainRvWeather.setLayoutManager(layoutManager);

        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);

        weatherViewModel.getModelCurrentWeather().observe(this, new Observer<ModelCurrentWeather>() {
            @Override
            public void onChanged(ModelCurrentWeather modelCurrentWeather) {
                initData(modelCurrentWeather);
                weatherAdapter = new WeatherAdapter(weatherInfos);
                mainxml.mainRvWeather.setAdapter(weatherAdapter);
                weatherAdapter.notifyDataSetChanged();
            }
        });


    }

    private void initData(ModelCurrentWeather modelCurrentWeather) {

        weatherInfos = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.#");
        double temp = Double.parseDouble(df.format(celsius(modelCurrentWeather.getMain().getTemp())));
        double tempMin = Double.parseDouble(df.format(celsius(modelCurrentWeather.getMain().getTempMin())));
        double tempMax = Double.parseDouble(df.format(celsius(modelCurrentWeather.getMain().getTempMin())));
        String city = modelCurrentWeather.getName();
        String country = modelCurrentWeather.getSys().getCountry();
        String wind = "Speed : " + modelCurrentWeather.getWind().getSpeed().toString();


        weatherInfos = new ArrayList<>();
        weatherInfos.add(new WeatherInfo(temp,tempMin,tempMax,city,country, R.drawable.moon_cloud_mid_rain,wind));
        weatherInfos.add(new WeatherInfo(temp,tempMin,tempMax,city,country, R.drawable.moon_cloud_mid_rain,wind));
        weatherInfos.add(new WeatherInfo(temp,tempMin,tempMax,city,country, R.drawable.moon_cloud_mid_rain,wind));
        weatherInfos.add(new WeatherInfo(temp,tempMin,tempMax,city,country, R.drawable.moon_cloud_mid_rain,wind));
        weatherInfos.add(new WeatherInfo(temp,tempMin,tempMax,city,country, R.drawable.moon_cloud_mid_rain,wind));
        weatherInfos.add(new WeatherInfo(temp,tempMin,tempMax,city,country, R.drawable.moon_cloud_mid_rain,wind));


        Log.e("tag",weatherInfos.toString());

    }

    public double celsius(double kelvin)
    {
        double c = kelvin- 273.15;
        return  c;
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(//layout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }*/
}