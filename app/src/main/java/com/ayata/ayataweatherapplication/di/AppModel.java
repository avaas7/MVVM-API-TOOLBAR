package com.ayata.ayataweatherapplication.di;

import android.content.Context;

import androidx.room.Room;

import com.ayata.ayataweatherapplication.network.ApiService;
import com.ayata.ayataweatherapplication.room.Database;

import java.lang.reflect.GenericSignatureFormatError;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModel {

    @Provides
    @Singleton
    ApiService provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService.class);
    }

    @Provides
    @Singleton
    public static synchronized Database getInstance(@ApplicationContext Context context) {
        return Room.databaseBuilder(context,
                        Database.class, "data_database")
                .fallbackToDestructiveMigration()
                .build();
    }


}
