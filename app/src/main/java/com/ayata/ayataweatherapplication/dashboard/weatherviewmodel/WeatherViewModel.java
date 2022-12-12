package com.ayata.ayataweatherapplication.dashboard.weatherviewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ayata.ayataweatherapplication.dashboard.weathermodel.ModelCurrentWeather;
import com.ayata.ayataweatherapplication.network.ApiService;
import com.ayata.ayataweatherapplication.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewModel extends AndroidViewModel {

    private double lat= 44.34;
    private double lon=10.99;
    private static String appid="25f8d7b78dcd2c24bdf8e40294f4a2f3";


    private ApiService openWeatherApi;
    private MutableLiveData<ModelCurrentWeather> modelCurrentWeather;
  //  private MutableLiveData<Boolean> progressBarObservable;

    public WeatherViewModel(@NonNull Application application) {
        super(application);
        openWeatherApi = RetrofitClient.getInstance().getApi();
        modelCurrentWeather = new MutableLiveData<>();
    }

    public LiveData<ModelCurrentWeather> getModelCurrentWeather()
    {
      //  progressBarObservable.setValue(true);
        openWeatherApi.getModelCurrentWeather(lat,lon,appid).enqueue(new Callback<ModelCurrentWeather>() {
            @Override
            public void onResponse(Call<ModelCurrentWeather> call, Response<ModelCurrentWeather> response) {
      //          progressBarObservable.setValue(false);
                if(response.isSuccessful())
                {
                    modelCurrentWeather.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ModelCurrentWeather> call, Throwable t) {
                Log.e("tag",t.getMessage());
    //            progressBarObservable.setValue(false);
            }
        });


        return modelCurrentWeather;
    }

//    public LiveData<Boolean> getProgressBarObservable()
//    {
//        return progressBarObservable;
//    }
}
