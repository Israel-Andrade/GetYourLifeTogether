package calendar4.com.example.sal.calendar4;

import android.Manifest;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;

import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.service.notification.NotificationListenerService;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Calendar;
import java.util.TimeZone;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final Context context = this;
    private Button button;
    private String[] arraySpinner;
    private Spinner spinner;
    private TimePicker timePicker;
    private int hour;
    private int minute;
    private int day;

    private int mHour;
    private int mMinute;
    private int sample;


    static int hourFromTP = 0;
    static int minutesFromTP = 0;

    private long THEEVENT = 0;

    private static final String DEBUG_TAG = "ACTIVITYBRO";
    public static final String[] INSTANCE_PROJECTION = new String[] {
            CalendarContract.Instances.EVENT_ID,      // 0
            CalendarContract.Instances.BEGIN,         // 1
            CalendarContract.Instances.TITLE          // 2
    };

    public void deleteEvent(Long eventID){
        ContentResolver cr = getContentResolver();
        ContentValues values = new ContentValues();
        Uri deleteUri = null;
        deleteUri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, eventID);
        int rows = getContentResolver().delete(deleteUri, null, null);
        Log.v("DELETE", "Rows deleted: " + rows);
        Log.v("DELETE", "deleted event: " + String.valueOf(eventID));
        sendHeadsUpNotification("DELETE", "Deleted event: "+String.valueOf(eventID) );
    }
    public void getEvents(){

        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, Calendar.MAY, 14, 0, 0, 0);
        long startDay = calendar.getTimeInMillis();
        calendar.set(2014, Calendar.MAY, 14, 23, 59, 59);
        long endDay = calendar.getTimeInMillis();

        String[] projection = new String[] { BaseColumns._ID, CalendarContract.Events.TITLE, CalendarContract.Events.DTSTART };
        String selection = CalendarContract.Events.DTSTART + " >= ? AND " + CalendarContract.Events.DTSTART + "<= ?";
        String[] selectionArgs = new String[] { Long.toString(startDay), Long.toString(endDay) };

        ContentResolver cr = getContentResolver();


        /*
        String[] projection = null;
        //By default  you have the following calendars in your android phone like
        //My Calendar, Gmail calendar,  Indian holydays or if you have installed some
        //third party calendar app etc. They will be automatically given an unique id by OS.
        //In the next line "selectedCalenderId" is indicating a perticular calendar like My Calendar or Gmail calendar.
        //You need to write seperate code to get those particular calendar ids. here "selectedCalenderId" is that type of id(in my case value may be 0(My Calendar). 1(Gmail Calendar), or 3(Indian Holydays))
        String selection = "calendar_id=" + 1 +" and dtstart between ? and ?";

        long startMillis = 0;
        long endMillis = 0;
        Calendar beginTime = Calendar.getInstance();
        //year, month, day, hour of day, minute, second
        beginTime.set( 2014, 5, 11, 1, 0 );
        startMillis = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.set( 2016, 5, 12, 1, 0 );
        endMillis = endTime.getTimeInMillis();

        //provide the time range(in system mili seconds)  from what you want to get the events.
        String[] selectionArgs = new String[] {String.valueOf(beginTime), String.valueOf(endTime)};

        Cursor cursor = null;

        Uri calenderContentUri = Uri.parse("content://com.android.calendar/events");
        try
        {

            cursor = getContentResolver().query(calenderContentUri, projection, selection, selectionArgs, "dtstart DESC, dtend DESC");
            if (cursor.moveToFirst())
            {
                int increment = 0;

                String eventName;
                String calendarOwnerName;
                String location;
                long eventBeginTime;
                long eventEndTime;

                String description;
                String calendarSyncId;

                String customCalendarEventId ;

                do
                {
                    eventName = cursor.getString(cursor.getColumnIndex("title"));
                    eventBeginTime = cursor.getLong(cursor.getColumnIndex("dtstart"));
                    eventEndTime = cursor.getLong(cursor.getColumnIndex("dtend"));
                    location = cursor.getString(cursor.getColumnIndex("eventLocation"));
                    calendarOwnerName = cursor.getString(cursor.getColumnIndex("ownerAccount"));
                    description = cursor.getString(cursor.getColumnIndex("description"));

                    //Watch that I am combining the 4 columns
                    calendarSyncId = eventName +"-" +calendarOwnerName +"-" +location +"-" +eventBeginTime+"-" +eventEndTime;

                    Log.v("THEM TITLES", eventName);
                    //Making MD5 encryption to use it as unique key
                    //customCalendarEventId =  yourMD5EncryptionLogic(calendarSyncId);

                    //TODO ::: Do the rest of your coding for OS version higher than 14.
                }
                while(cursor.moveToNext());
            }
        }
        finally  {
            if(cursor != null) {
                cursor.close();
            }
        }
        */
    }




    public void getCoordinatesOfPlace(){
        Geocoder geocoder = new Geocoder(getApplicationContext().getApplicationContext(), Locale.US);
        List<Address> listOfAddress;

    }
    public void showCustomDialog(){

        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom);
        dialog.setTitle("Title...");

        // set the custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.text);
        text.setText("Android custom dialog example!");
        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        image.setImageResource(R.drawable.ic_menu_camera);

        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        timePicker = (TimePicker) findViewById(R.id.timePicker);

        //gets current time
        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        day = c.get(Calendar.DAY_OF_MONTH);

        Log.d("something", String.valueOf(hour));

        // force the timepicker to loose focus and the typed value is available !
        //timePicker.clearFocus();
        // re-read the values, in my case i put them in a Time object.
        //time.hour   = timePicker.getCurrentHour();
        //time.minute = timePicker.getCurrentMinute();

        dialog.show();

        //int hour = timePicker.getCurrentHour();
        sendHeadsUpNotification(Integer.toString(hour), Integer.toString(minute));
    }
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    public void addCalendarEvent3(int start) {
        start = this.minutesFromTP;
        ///
        //gets the calendar
        Uri uri2 = CalendarContract.Calendars.CONTENT_URI;
        String[] projection = new String[]{
                CalendarContract.Calendars._ID,
                CalendarContract.Calendars.ACCOUNT_NAME,
                CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
                CalendarContract.Calendars.NAME,
                CalendarContract.Calendars.CALENDAR_COLOR
        };

        //use this to get calendar event IDs
        Cursor calendarCursor = managedQuery( uri2, projection, null, null, null );


        //String desc = calendarCursor.getString(calendarCursor.getColumnIndex("description"));

        //Log.v("DESCRIPTION", desc);





        // Construct event details
        long startMillis = 0;
        long endMillis = 0;
        Calendar beginTime = Calendar.getInstance();
        //year, month day, hour of day, minute, second
        beginTime.set( 2016, 9, 14, 7, 30 );
        startMillis = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.set( 2016, 9, 14, 8, 45 );
        endMillis = endTime.getTimeInMillis();


// Insert Event
        Calendar cal = Calendar.getInstance();

        ContentResolver cr = getContentResolver();
        ContentValues values = new ContentValues();
        TimeZone timeZone = TimeZone.getDefault();

        //use startMillis
        //use endMillis
        Log.v("STARTVALUE", String.valueOf(start));
        values.put( CalendarContract.Events.DTSTART, cal.getTimeInMillis() + start * 60 * 1000 );
        //start will contain the value to go forward by in minutes
        values.put( CalendarContract.Events.DTEND, cal.getTimeInMillis() + (60+start) * 60 * 1000 );
        values.put( CalendarContract.Events.EVENT_TIMEZONE, timeZone.getID() );
        values.put( CalendarContract.Events.TITLE, "HAI "+ String.valueOf(cal.getTimeInMillis()) );
        values.put( CalendarContract.Events.DESCRIPTION, "Eat all the Pizza" );
        values.put( CalendarContract.Events.EVENT_LOCATION, "CSUMB" );
        values.put( CalendarContract.Events.CALENDAR_ID, 1 );


        //Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
        //Uri uri = cr.insert( Uri.parse( "content://com.android.calendar/events" ), values );
        if (ActivityCompat.checkSelfPermission( this, Manifest.permission.WRITE_CALENDAR ) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //inserts the event
        Uri uri = cr.insert( CalendarContract.Events.CONTENT_URI, values );

        // Retrieve ID for new event
        String eventIDString = uri.getLastPathSegment();
        long eventID = Long.parseLong(uri.getLastPathSegment());

        THEEVENT = eventID;


        Log.v("EVENTID", eventIDString);


        //updateEvent(eventID);
    }


    //adds a calendar event dynamically
    public void addCalendarEvent2() {
///

   ///
        //gets the calendar
        Uri uri2 = CalendarContract.Calendars.CONTENT_URI;
        String[] projection = new String[]{
                CalendarContract.Calendars._ID,
                CalendarContract.Calendars.ACCOUNT_NAME,
                CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
                CalendarContract.Calendars.NAME,
                CalendarContract.Calendars.CALENDAR_COLOR
        };

        //use this to get calendar event IDs
        Cursor calendarCursor = managedQuery( uri2, projection, null, null, null );



        //String desc = calendarCursor.getString(calendarCursor.getColumnIndex("description"));

        //Log.v("DESCRIPTION", desc);






        // Construct event details
        long startMillis = 0;
        long endMillis = 0;
        Calendar beginTime = Calendar.getInstance();
        //year, month day, hour of day, minute, second
        beginTime.set( 2016, 9, 14, 7, 30 );
        startMillis = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.set( 2016, 9, 14, 8, 45 );
        endMillis = endTime.getTimeInMillis();


// Insert Event
        Calendar cal = Calendar.getInstance();

        ContentResolver cr = getContentResolver();
        ContentValues values = new ContentValues();
        TimeZone timeZone = TimeZone.getDefault();

        //use startMillis
        //use endMillis
        values.put( CalendarContract.Events.DTSTART, cal.getTimeInMillis() );
        values.put( CalendarContract.Events.DTEND, cal.getTimeInMillis() + 60 * 60 * 1000 );
        values.put( CalendarContract.Events.EVENT_TIMEZONE, timeZone.getID() );
        values.put( CalendarContract.Events.TITLE, "Don't stress out "+ String.valueOf(cal.getTimeInMillis()) );
        values.put( CalendarContract.Events.DESCRIPTION, "Eat all the Pizza" );
        values.put( CalendarContract.Events.EVENT_LOCATION, "CSUMB" );
        values.put( CalendarContract.Events.CALENDAR_ID, 1 );


        //Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
        //Uri uri = cr.insert( Uri.parse( "content://com.android.calendar/events" ), values );
        if (ActivityCompat.checkSelfPermission( this, Manifest.permission.WRITE_CALENDAR ) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //inserts the event
        Uri uri = cr.insert( CalendarContract.Events.CONTENT_URI, values );

        // Retrieve ID for new event
        String eventIDString = uri.getLastPathSegment();
        long eventID = Long.parseLong(uri.getLastPathSegment());

        THEEVENT = eventID;

        Log.v("EVENTID", eventIDString);


        //updateEvent(eventID);
    }

    //updates an event based on the event ID (long)
    public void updateEvent(long eventID){

        Calendar cal = Calendar.getInstance();

        ContentResolver cr = getContentResolver();
        ContentValues values = new ContentValues();
        Uri updateUri = null;
// The new title for the event
        values.put(CalendarContract.Events.TITLE, "Kickboxing UPDATE");
        //will update by 30 mins
        values.put(CalendarContract.Events.DTSTART, cal.getTimeInMillis() + 15 * 60 * 1000);

        updateUri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, eventID);
        int rows = getContentResolver().update(updateUri, values, null, null);

        Log.v("ROW UPDATE", "Rows updated: " + rows);
        Log.v("UPDATED EVENT ", String.valueOf(eventID));

        sendHeadsUpNotification("UPDATE", String.valueOf(eventID) );
    }
    public void addCalendarEvent(){
        Calendar cal = Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", cal.getTimeInMillis());
        intent.putExtra("allDay", true);
        intent.putExtra("rrule", "FREQ=YEARLY");
        intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
        intent.putExtra("title", "A Test Event from android app");
        startActivity(intent);
    }
    public void sendHeadsUpNotification(String arg, String arg2) {
        //build notification
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_menu_send)
                        .setContentTitle(arg)
                        .setContentText(arg2)
                        .setDefaults(Notification.DEFAULT_ALL) // must requires VIBRATE permission
                        .setPriority(NotificationCompat.PRIORITY_HIGH); //must give priority to High, Max which will considered as heads-up notification4
/*
//set intents and pending intents to call service on click of "dismiss" action button of notification
        Intent dismissIntent = new Intent(this, MyService.class);
        dismissIntent.setAction(ACTION_DISMISS);
        PendingIntent piDismiss = PendingIntent.getService(this, 0, dismissIntent, 0);

//set intents and pending intents to call service on click of "snooze" action button of notification
        Intent snoozeIntent = new Intent(this, MyService.class);
        snoozeIntent.setAction(ACTION_SNOOZE);
        PendingIntent piSnooze = PendingIntent.getService(this, 0, snoozeIntent, 0);
*/
// Gets an instance of the NotificationManager service
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//to post your notification to the notification bar with a id. If a notification with same id already exists, it will get replaced with updated information.
        notificationManager.notify(0, builder.build());
    }

    public void sendNotification() {

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_menu_manage)
                .setContentTitle("BRUHH")
                .setContentText("WASSUP!");


// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
/* Adds the back stack for the Intent (but not the Intent itself) */
        stackBuilder.addParentStack(MainActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(1, mBuilder.build());
    }

    public void showTimePickerDialog() {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
    String temp;
    //timepickerFragment

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker


            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            //int hour = 0;
            //int minute = 0;
            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            Log.v("HOUR", String.valueOf(hourOfDay));
            Log.v("MINUTE", String.valueOf(minute));


            /*
            MainActivity test = new MainActivity();

=======


            /*
            MainActivity test = new MainActivity();

>>>>>>> 56221d071724623b3237e39d68e1bd13c40e011f
            test.sendHeadsUpNotification(String.valueOf(hourOfDay),String.valueOf(minute) );
            */
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Added New Event", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                //adds the event dynamically
                addCalendarEvent2();
            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.map) {

            //calls on another activiy
            //map activity will be called from here
            Intent myIntent = new Intent(MainActivity.this, MapsActivity.class);
            myIntent.putExtra("key", "lel"); //Optional parameters
            MainActivity.this.startActivity(myIntent);
            //sendNotification();
            // Handle the camera action
        } else if (id == R.id.weather) {
            Intent myIntent = new Intent(MainActivity.this, WeatherActivity.class);
            //myIntent.putExtra("key", "lel"); //Optional parameters
            MainActivity.this.startActivity(myIntent);

            //map activity will be called from here
            //sendNotification();
            // Handle the camera action
        } else if (id == R.id.weather) {

            //weather application will be sent from here
            //sendHeadsUpNotification("hola");

        } else if (id == R.id.addEventPrompt) {
            addCalendarEvent();
            //showTimePickerDialog();

        } else if (id == R.id.customDialog) {
            showCustomDialog();

        } else if (id == R.id.deleteEvent) {
            //addCalendarEvent2();
            //getEvents();
            deleteEvent(THEEVENT);
        }else if (id == R.id.showTimePicker) {
            showTimePickerDialog();
            //the static variables will be populated with the timepicker values

            //addCalendarEvent3(minutesFromTP);
            //addCalendarEvent2();
            //getEvents();
            //deleteEvent(THEEVENT);
        } else if (id == R.id.update) {

            Log.v("THE EVENT ID", String.valueOf(THEEVENT));
            updateEvent(THEEVENT);
            //showTimePickerDialog();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
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
                "Main Page", // TODO: Define a title for the content shown.
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
}
