package com.ayata.ayataweatherapplication.dashboard.weatherviewmodel;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ayata.ayataweatherapplication.dashboard.weathermodel.ModelCurrentWeather;
import com.ayata.ayataweatherapplication.network.ApiService;
import com.ayata.ayataweatherapplication.room.Dao;
import com.ayata.ayataweatherapplication.room.Database;
import com.ayata.ayataweatherapplication.room.Entity;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    private double lat = 44.34;
    private double lon = 10.99;
    private static String appid = "25f8d7b78dcd2c24bdf8e40294f4a2f3";

    Dao dao;
    private LiveData<List<Entity>> allData;

    private ApiService openWeatherApi;
    private MutableLiveData<ModelCurrentWeather> modelCurrentWeather;
    //  private MutableLiveData<Boolean> progressBarObservable;

    @Inject
    public WeatherViewModel(ApiService apiService, Database database) {
        this.openWeatherApi = apiService;
        modelCurrentWeather = new MutableLiveData<>();

        dao = database.dao();
        allData = dao.getAllData();
    }

    public LiveData<ModelCurrentWeather> getModelCurrentWeather() {
        //  progressBarObservable.setValue(true);
        openWeatherApi.getModelCurrentWeather(lat, lon, appid).enqueue(new Callback<ModelCurrentWeather>() {
            @Override
            public void onResponse(Call<ModelCurrentWeather> call, Response<ModelCurrentWeather> response) {
                //          progressBarObservable.setValue(false);
                if (response.isSuccessful()) {
                    modelCurrentWeather.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ModelCurrentWeather> call, Throwable t) {
                Log.e("tag", t.getMessage());
                //            progressBarObservable.setValue(false);
            }
        });
        return modelCurrentWeather;
    }

    public void insert(Entity entity) {
        new InsertDataAysncTask(dao).execute(entity);
    }
    public void update(Entity entity) {
        new UpdateDataAysncTask(dao).execute(entity);
    }
    public void delete(Entity entity) {
        new DeleteDataAysncTask(dao).execute(entity);
    }
    public void deleteAllData() {
        new DeleteAllDataAysncTask(dao).execute();
    }


    public LiveData<List<Entity>> getAllData() {
        return allData;
    }

    public LiveData<Entity> getData(String username) {
        return dao.getData(username);
    }

    private static class InsertDataAysncTask extends AsyncTask<Entity, Void, Void> {
        private Dao dao;

        private InsertDataAysncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Entity... entities) {
            dao.insert(entities[0]);
            return null;
        }
    }

    private static class UpdateDataAysncTask extends AsyncTask<Entity, Void, Void> {
        private Dao dao;

        private UpdateDataAysncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Entity... entities) {
            dao.update(entities[0]);
            return null;
        }
    }

    private static class DeleteDataAysncTask extends AsyncTask<Entity, Void, Void> {
        private Dao dao;

        private DeleteDataAysncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Entity... entities) {
            dao.delete(entities[0]);
            return null;
        }
    }

    private static class DeleteAllDataAysncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;

        private DeleteAllDataAysncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllData();
            return null;
        }
    }

//    public LiveData<Boolean> getProgressBarObservable()
//    {
//        return progressBarObservable;
//    }
}
