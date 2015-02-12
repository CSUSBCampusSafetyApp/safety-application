package models;

import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import network.BasicNetwork;
import network.IGeneralRun;
import network.SimpleNetwork;

public class ModelAnonTips {

    private String subject;
    private String message;

    public ModelAnonTips(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }

    public void save() {
        ArrayList<NameValuePair> http_parameters;
        http_parameters = new ArrayList<>();

        http_parameters.add( new BasicNameValuePair("subject", subject));
        http_parameters.add( new BasicNameValuePair("message", message));

        SimpleNetwork.sendPost("createAnonymousTip", http_parameters, onAfter, "");
    }

    private IGeneralRun onAfter = new IGeneralRun() {
        @Override
        public void execute(BasicNetwork request, Object o) {

            JSONObject json_result = request.getJsonObject();
            try {
                if( json_result.getInt("result") == 1 ) {
                    /// TODO: If connection succeeded then give user feedback.
                    Log.i("Model Save: ModelAnonTips", "Success!");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}
