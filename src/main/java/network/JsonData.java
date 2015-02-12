package network;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonData {
    private static JsonData instance = null;

    protected JsonData() {}

    public static JsonData Instance() {
        if( instance == null )
            instance = new JsonData();

        return instance;
    }

    public JSONObject parseString(String content) {
        JSONObject json_obj = null;

        try {
            json_obj = new JSONObject( content );
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data: " + e.toString());
        }

        return json_obj;
    }

}
