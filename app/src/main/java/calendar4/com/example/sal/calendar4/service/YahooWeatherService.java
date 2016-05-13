package calendar4.com.example.sal.calendar4.service;

/**
 * Created by sal on 5/12/2016.
 */
import android.net.Uri;
import android.os.AsyncTask;

import calendar4.com.example.sal.calendar4.data.Channel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by andrade on 4/29/16.
 */
public class YahooWeatherService {
    //Creating an instance of WeatherServiceCallback
    private WeatherServiceCallback callback;
    //Another instance of// A string is an object
    private String location;
    private Exception error;

    //Create a constructor
    public YahooWeatherService(WeatherServiceCallback callback) {
        this.callback = callback;
    }
    //getter for the location


    public String getLocation() {
        return location;
    }

    //Create another method
    public void refreshWeather(final String l)
    {
        this.location = l;
        //What we are passing in, what we want as the progress, and the return type
        //The past in void because we don't want a return progress
        AsyncTask<String, Void, String> execute = new AsyncTask<String, Void, String>() {
            @Override
            //This returns some exceptions but not running on the UI thread
            protected String doInBackground(String... strings) {
                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")", strings[0]);
                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));
                //Throw an exception
                try{
                    URL url = new URL(endpoint);
                    URLConnection connection = url.openConnection();

                    InputStream inputStream = connection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null)
                    {
                        result.append(line);
                    }
                    return result.toString();
                }
                catch (Exception e)
                {
                    error = e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if(s == null && error != null)
                {
                    callback.serviceFailure(error);
                    return;
                }
                try
                {
                    JSONObject data = new JSONObject(s);

                    JSONObject queryResults = data.optJSONObject("query");

                    int count = queryResults.optInt("count");
                    if(count == 0)
                    {
                        //city does not exists
                        callback.serviceFailure(new LocationWeatherExeption("No weather information found for " + location));
                        return;
                    }
                    Channel channel = new Channel();
                    channel.populate(queryResults.optJSONObject("results").optJSONObject("channel"));
                    callback.serviceSuccess(channel);
                }
                catch (JSONException e)
                {
                    callback.serviceFailure(e);
                }
            }
        }.execute(location);
    }
    public class LocationWeatherExeption extends Exception{
        public LocationWeatherExeption(String detailMessage) {
            super(detailMessage);
        }
    }
}