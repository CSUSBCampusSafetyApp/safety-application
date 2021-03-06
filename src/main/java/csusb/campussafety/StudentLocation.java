package csusb.campussafety;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import customactivities.NavigationGeneralActivity;
import models.ModelStudentLocation;
import models.Model;
import gps.GPSTracker;
import network.BasicNetwork;
import network.IGeneralRun;
import utility.ConfirmDialog;
import utility.SimpleDialog;

public class StudentLocation extends NavigationGeneralActivity {

    private EditText et_firstname    = null;
    private EditText et_lastname     = null;
    private EditText et_phone_number = null;

    private Spinner  spin_service    = null;
    private EditText et_license      = null;
    private EditText et_vehicle_year = null;
    private EditText et_vehicle_make = null;
    private String latitude = null;
    private String longitude = null;
    private GPSTracker gps = null;
    private Model model = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = setInnerLayout(R.layout.activity_studentlocation_page);

        //Page Description (AlertDialog Message)
        SimpleDialog.show("Student Location", "Here you can request a service from Campus Police. " +
                "Please wait in the requested location.", this);

        gps = new GPSTracker(this);

        et_phone_number = (EditText) v.findViewById(R.id.et_studentlocation_phone_number);
        et_firstname    = (EditText) v.findViewById(R.id.et_studentlocation_firstname);
        et_lastname     = (EditText) v.findViewById(R.id.et_studentlocation_lastname);
        spin_service    = (Spinner)  v.findViewById(R.id.spin_studentlocation_service);
        et_license      = (EditText) v.findViewById(R.id.et_studentlocation_license);
        et_vehicle_year     = (EditText) v.findViewById(R.id.et_studentlocation_vehicle_year);
        et_vehicle_make     = (EditText) v.findViewById(R.id.et_studentlocation_vehicle_make);

        final String[] values_services  = new String[] {"[Select Service]", "Escort", "Vehicle Unlock", "Battery Jump"};

        final ArrayAdapter<String> adapter_services = new ArrayAdapter<>(
                this,
                R.layout.adapter_list_services,
                R.id.adapter_tv_list_services_text,
                values_services);

        spin_service.setAdapter(adapter_services);
        spin_service.setOnItemSelectedListener(oisl_item_chosen);

        Button btn_submit = (Button) v.findViewById(R.id.btn_studentlocation_submit);
        btn_submit.setOnClickListener(cl_submit);
    }

    private View.OnClickListener cl_submit = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            if(gps.canGetLocation()){
                latitude = Double.toString(gps.getLatitude());
                longitude = Double.toString(gps.getLongitude());
                //gps.stopUsingGPS();
            }else{
                gps.showSettingsAlert();
            }

            if( et_firstname.toString().length() > 0 && et_lastname.toString().length() > 0 && et_phone_number.getText().toString().length() > 0 && latitude.length() > 0 &&
                     longitude.length() > 0 && spin_service.toString().length() > 0 && spin_service.toString().length() > 0 &&
                    et_license.toString().length() > 0 && et_vehicle_year.getText().toString().length() > 0 && et_vehicle_make.toString().length() > 0) {

                model = new ModelStudentLocation(et_firstname.getText().toString(), et_lastname.getText().toString(), et_phone_number.getText().toString(),
                        latitude, longitude, spin_service.getSelectedItem().toString(),
                        et_license.getText().toString(), Integer.parseInt(et_vehicle_year.getText().toString()), et_vehicle_make.getText().toString());
                ConfirmDialog.show("Tap \"Continue\" to send", onContinue, StudentLocation.this);

            } else {
                SimpleDialog.show("Empty Field", "A field was left empty", StudentLocation.this);
            }
        }
    };

    // Handles when a user chooses "Continue" when trying to submit tip.
    private DialogInterface.OnClickListener onContinue = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(model != null) {
                model.save(after_save, true, StudentLocation.this);
                Log.i("Button:Submit", "Submitted data!");
            }
        }
    };

    /**
     * Handles post or what happens after the send has completed and displays a message depending on
     * whether the data was sent correctly or not.
     */
    private IGeneralRun after_save = new IGeneralRun() {
        @Override
        public void execute(BasicNetwork request, Object o) {
            // request = null
            boolean success = (boolean)o;

            if(success) {
                Log.i("(Success)Going to...", "Menu");
                SimpleDialog.show(onSuccess, "", "Location has been sent!", StudentLocation.this);
            }
            else {
                Log.i("(Failed)Going to...", "StudentLocations");
                SimpleDialog.show(onFail, "", "Your tip was not successfully sent! This could be a server error sorry for the inconvenience. Try again!", StudentLocation.this);
            }
        }
    };

    // Goes to the next activity if send was successful.
    private DialogInterface.OnClickListener onSuccess = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            final Intent page = new Intent( StudentLocation.this, Menu.class );
            startActivity( page );
            finish();
        }
    };

    // Goes to the next activity if send failed.
    @SuppressWarnings("unused")
    private DialogInterface.OnClickListener onFail = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            final Intent page = new Intent( StudentLocation.this, StudentLocation.class );
            startActivity( page );
            finish();
        }
    };

    private AdapterView.OnItemSelectedListener oisl_item_chosen = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {}

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    };

}
