package calendar4.com.example.sal.calendar4.data;

/**
 * Created by sal on 5/12/2016.
 */

import org.json.JSONObject;

/**
 * Created by andrade on 4/27/16.
 */
public class Item implements JSONpopulator {
    public Condition getCondition() {
        return condition;
    }

    private Condition condition;

    @Override
    public void populate(JSONObject data) {
        //we are populating our new instance of Condition
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));

    }
}