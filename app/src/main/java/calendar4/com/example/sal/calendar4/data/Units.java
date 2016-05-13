package calendar4.com.example.sal.calendar4.data;

/**
 * Created by sal on 5/12/2016.
 */
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