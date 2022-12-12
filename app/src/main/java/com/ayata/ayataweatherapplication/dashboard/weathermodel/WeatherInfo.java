package com.ayata.ayataweatherapplication.dashboard.weathermodel;

public class WeatherInfo {

    private double temperarure;
    private double highPressure;
    private double lowPressure;
    private String city;
    private String country;
    private int imageWeatherCondition;
    private String wind;

    public WeatherInfo(double temperarure, double highPressure, double lowPressure, String city, String country, int imageWeatherCondition, String wind) {
        this.temperarure = temperarure;
        this.highPressure = highPressure;
        this.lowPressure = lowPressure;
        this.city = city;
        this.country = country;
        this.imageWeatherCondition = imageWeatherCondition;
        this.wind = wind;
    }

    public double getTemperarure() {
        return temperarure;
    }

    public void setTemperarure(double temperarure) {
        this.temperarure = temperarure;
    }

    public double getHighPressure() {
        return highPressure;
    }

    public void setHighPressure(double highPressure) {
        this.highPressure = highPressure;
    }

    public double getLowPressure() {
        return lowPressure;
    }

    public void setLowPressure(double lowPressure) {
        this.lowPressure = lowPressure;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getImageWeatherCondition() {
        return imageWeatherCondition;
    }

    public void setImageWeatherCondition(int imageWeatherCondition) {
        this.imageWeatherCondition = imageWeatherCondition;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }
}