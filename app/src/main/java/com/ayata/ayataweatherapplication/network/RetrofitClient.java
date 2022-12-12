package com.ayata.ayataweatherapplication.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://api.openweathermap.org/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient() {
       retrofit = new Retrofit.Builder()
               .baseUrl(BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance==null)
        {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public ApiService getApi()
    {
        return  retrofit.create(ApiService.class);
    }

}
