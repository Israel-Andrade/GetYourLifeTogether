package calendar4.com.example.sal.calendar4.service;

/**
 * Created by sal on 5/12/2016.
 */
import calendar4.com.example.sal.calendar4.data.Channel;

/**
 * Created by andrade on 4/29/16.
 */
public interface WeatherServiceCallback {
    //methods
    void serviceSuccess(Channel channel);
    //Exception will tell us if the service has failed
    void serviceFailure(Exception exception);
}