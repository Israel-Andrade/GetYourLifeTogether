package com.weather.andrade.yahooweatherapp.data;

import org.json.JSONObject;

/**
 * Created by andrade on 4/27/16.
 */
public class Units implements JSONpopulator{
    public String getTemperature() {
        return temperature;
    }

    private String temperature;
    @Override
    //implement method
    public void populate(JSONObject data) {
        temperature = data.optString("temperature");


    }


}