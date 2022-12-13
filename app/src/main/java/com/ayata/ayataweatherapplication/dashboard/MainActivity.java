package com.ayata.ayataweatherapplication.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
import java.util.Locale;

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

        //search view
        mainxml.mainSearchView.clearFocus();
        mainxml.mainSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

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

    private void filterList(String text) {
        List<WeatherInfo> weatherInfoList = new ArrayList<>();
        for (WeatherInfo item : weatherInfos)
        {
            if (item.getCity().toLowerCase().contains(text.toLowerCase())){
                weatherInfoList.add(item);
            }
        }

        if (weatherInfoList.isEmpty())
        {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
        else
        {
            weatherAdapter.setFilteredList(weatherInfoList);
        }

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
        weatherInfos.add(new WeatherInfo(temp,tempMin,tempMax,city.toLowerCase(),country, R.drawable.moon_cloud_mid_rain,wind));
        weatherInfos.add(new WeatherInfo(temp+1,tempMin+1,tempMax+2,"delhi","India", R.drawable.sun_cloud_angled_rain,wind+1));
        weatherInfos.add(new WeatherInfo(temp+2,tempMin+2,tempMax+2,"pokhara","nepal", R.drawable.sun_cloud_mid_rain,wind+2));
        weatherInfos.add(new WeatherInfo(temp+3,tempMin+3,tempMax+3,"las Vegas","USA", R.drawable.sun_cloud_angled_rain,wind+3));
        weatherInfos.add(new WeatherInfo(temp+5,tempMin+4,tempMax+4,"paris","France", R.drawable.moon_cloud_fast_wind,wind+4));
        weatherInfos.add(new WeatherInfo(temp+6,tempMin+5,tempMax+5,"london","England", R.drawable.sun_cloud_angled_rain,wind+5));


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