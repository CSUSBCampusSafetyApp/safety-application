package models;


import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import network.BasicNetwork;
import network.IGeneralRun;
import network.NetworkDef;
import network.SimpleNetwork;

public class ModelStudentLocation {

    private String first_name;
    private String last_name;
    private String phone_number;
    private String location;
    private String services;
    private String vehicle_license_number;
    private int    vehicle_year;
    private String vehicle_make;
    private IGeneralRun after_save;
    private boolean success_save = false;

    public ModelStudentLocation(String first_name, String last_name, String phone_number, String location, String services, String vehicle_license_number,
                                int vehicle_year, String vehicle_make) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.location = location;
        this.services = services;
        this.vehicle_license_number = vehicle_license_number;
        this.vehicle_year = vehicle_year;
        this.vehicle_make = vehicle_make;
    }

    public void save() {
        ArrayList<NameValuePair> http_parameters;
        http_parameters = new ArrayList<>();

        http_parameters.add( new BasicNameValuePair("first_name", first_name));
        http_parameters.add( new BasicNameValuePair("last_name", last_name));
        http_parameters.add( new BasicNameValuePair("first_name", phone_number));
        http_parameters.add( new BasicNameValuePair("last_name", location));
        http_parameters.add( new BasicNameValuePair("first_name", services));
        http_parameters.add( new BasicNameValuePair("last_name", vehicle_license_number));
        http_parameters.add( new BasicNameValuePair("vehicle_year", Integer.toString(vehicle_year)));
        http_parameters.add( new BasicNameValuePair("vehicle_make", vehicle_make));

        SimpleNetwork.send("PUT", "student_location/send/format/json", http_parameters, null, "");
    }

    public void save(IGeneralRun run) {
        ArrayList<NameValuePair> http_parameters;
        http_parameters = new ArrayList<>();

        http_parameters.add( new BasicNameValuePair("first_name", first_name));
        http_parameters.add( new BasicNameValuePair("last_name", last_name));

        after_save = run;
        SimpleNetwork.send("PUT", "student_location/send/format/json", http_parameters, onAfter, "");
    }

    private IGeneralRun onAfter = new IGeneralRun() {
        @Override
        public void execute(BasicNetwork request, Object o) {

            JSONObject json_result = request.getJsonObject();
            try {
                if( json_result.getInt("result") == 1 ) {
                    Log.i("Model Save: ModelStudentLocation", "Success!");
                    success_save = true;
                }
                else {
                    Log.i("Model Save: ModelStudentLocation", "Failed!");
                    success_save = false;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            after_save.execute(null, success_save);
        }
    };
}
