package com.weather.andrade.yahooweatherapp.data;

import org.json.JSONObject;

/**
 * Created by andrade on 4/27/16.
 */
public class Condition implements JSONpopulator  {

    private int code;
    private int temperature;
    private String description;

    public int getCode() {
        return code;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void populate(JSONObject data) {
        //Setting our private values that we set previously before
        //all of our private variables

        code = data.optInt("code");
        temperature = data.optInt("temp");
        description = data.optString("text");

    }
}
