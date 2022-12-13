package com.ayata.ayataweatherapplication.dashboard.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ayata.ayataweatherapplication.dashboard.weathermodel.WeatherInfo;
import com.ayata.ayataweatherapplication.databinding.WeatherLayoutItemBinding;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder>{

    List<WeatherInfo> weatherInfos;

    public WeatherAdapter(List<WeatherInfo> weatherInfos) {
        this.weatherInfos = weatherInfos;
    }

    public void setFilteredList(List<WeatherInfo> filteredList)
    {
        this.weatherInfos = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(WeatherLayoutItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent,false));
  }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        double temperarure = weatherInfos.get(position).getTemperarure();
        double highPressure = weatherInfos.get(position).getHighPressure();
        double lowPressure = weatherInfos.get(position).getLowPressure();
        String city = weatherInfos.get(position).getCity();
        String country = weatherInfos.get(position).getCountry();
        int imageWeatherCondition = weatherInfos.get(position).getImageWeatherCondition();
        String wind = weatherInfos.get(position).getWind();

   String pressureDesc = "H:"+highPressure+"°  L:"+lowPressure+"°";

        holder.weatherLayoutItemBinding.rvWeatherTemperature.setText(String.valueOf(temperarure)+"°");
        holder.weatherLayoutItemBinding.rvPressure.setText(String.valueOf(pressureDesc));
        holder.weatherLayoutItemBinding.rvCityPlace.setText(String.valueOf(city+", "+country));
        holder.weatherLayoutItemBinding.rvImageViewLayout.setImageResource(imageWeatherCondition);
        holder.weatherLayoutItemBinding.rvWeatherWind.setText(String.valueOf(wind));

    }

    @Override
    public int getItemCount() {
        return weatherInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        WeatherLayoutItemBinding weatherLayoutItemBinding;

        public ViewHolder(@NonNull WeatherLayoutItemBinding weatherLayoutItemBinding){

                super(weatherLayoutItemBinding.getRoot());
                this.weatherLayoutItemBinding = weatherLayoutItemBinding;
 }
    }
}