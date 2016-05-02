package com.weather.andrade.yahooweatherapp.service;

import com.weather.andrade.yahooweatherapp.data.Channel;

/**
 * Created by andrade on 4/29/16.
 */
public interface WeatherServiceCallback {
    //methods
    void serviceSuccess(Channel channel);
    //Exception will tell us if the service has failed
    void serviceFailure(Exception exception);
}
