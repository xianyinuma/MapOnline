package com.sota.message;

import com.google.gson.annotations.SerializedName;
import entity.WeatherData;

/**
 * Created by wenjin on 2017/5/9.
 */
public class UploadResponse {

    @SerializedName("imageID")
    private int imageID;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("adultOrNot")
    private int adultOrNot;
    @SerializedName("tags")
    private String[] tags;
    @SerializedName("weatherData")
    private WeatherData weatherData;

    public UploadResponse() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageID() {
        return imageID;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public int getAdultOrNot() {
        return adultOrNot;
    }

    public void setAdultOrNot(int adultOrNot) {
        this.adultOrNot = adultOrNot;
    }
}
