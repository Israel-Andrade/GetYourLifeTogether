package calendar4.com.example.sal.calendar4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import calendar4.com.example.sal.calendar4.data.Item;
import calendar4.com.example.sal.calendar4.service.WeatherServiceCallback;
import calendar4.com.example.sal.calendar4.service.YahooWeatherService;

import calendar4.com.example.sal.calendar4.data.Channel;

public class WeatherActivity extends AppCompatActivity implements WeatherServiceCallback {

    //attributes and variables
    private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;
    private YahooWeatherService service;
    private ProgressDialog dialog;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        weatherIconImageView = (ImageView) findViewById(R.id.weatherIconImageView);
        temperatureTextView = (TextView) findViewById(R.id.temperatureTextView);
        conditionTextView = (TextView) findViewById(R.id.conditionTextView);
        locationTextView = (TextView) findViewById(R.id.locationTextView);

        //Passing in this whole instance/object into the function call
        //Calling YahooWeatherService and passing in this
        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);

        //To show the progress
        dialog.setMessage("Loading...");
        dialog.show();
        service.refreshWeather("Seaside, CA");


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        final Button button = (Button) findViewById(R.id.mainButton);
        final Button buttonMap = (Button) findViewById(R.id.mapButton);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                goMain();
            }
        });

        buttonMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                goMap();
            }
        });
    }

    //We have two methods now
    @Override
    public void serviceSuccess(Channel channel) {
        //
        dialog.hide();

        Item item = channel.getItem();
        int resourceId= getResources().getIdentifier("drawable/icon_"+item.getCondition().getCode(), null, getPackageName());

        @SuppressWarnings("deprecation")
        Drawable weatherIconDrawble = getResources().getDrawable(resourceId);

        weatherIconImageView.setImageDrawable(weatherIconDrawble);

        temperatureTextView.setText(item.getCondition().getTemperature() + "\u00B0" + channel.getUnits().getTemperature());
        conditionTextView.setText(item.getCondition().getDescription());
        locationTextView.setText(service.getLocation());


    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Weather Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://calendar4.com.example.sal.calendar4/http/host/path")

        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Weather Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://calendar4.com.example.sal.calendar4/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    //goes back to the main activity
    public void goMain(){
        Log.v("BUTTON","going back");
        Intent myIntent = new Intent(WeatherActivity.this, MainActivity.class);
        myIntent.putExtra("key", "goMain"); //Optional parameters
        WeatherActivity.this.startActivity(myIntent);
    }

    public void goMap(){
        Log.v("BUTTON","going to map");
        Intent myIntent = new Intent(WeatherActivity.this, MapsActivity.class);
        myIntent.putExtra("key", "goMap"); //Optional parameters
        WeatherActivity.this.startActivity(myIntent);
    }
}