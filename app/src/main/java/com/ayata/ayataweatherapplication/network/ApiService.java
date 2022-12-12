package com.ayata.ayataweatherapplication.network;

import com.ayata.ayataweatherapplication.dashboard.weathermodel.ModelCurrentWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("data/2.5/weather")
    Call<ModelCurrentWeather> getModelCurrentWeather(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String appid);

}
