package csusb.campussafety;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import models.ModelStudentLocation;

@SuppressWarnings("all")
public class StudentLocation extends Activity {

    private EditText et_firstname    = null;
    private EditText et_lastname     = null;
    private EditText et_phone_number = null;
    private Spinner  spin_location   = null;
    private Spinner  spin_service    = null;
    private EditText et_license      = null;
    private EditText et_vehicle_year = null;
    private EditText et_vehicle_make = null;
    private Button btn_submit        = null;

    private ModelStudentLocation m_studentloc = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlocation_page);

        et_phone_number = (EditText) findViewById(R.id.et_studentlocation_phone_number);
        et_firstname    = (EditText) findViewById(R.id.et_studentlocation_firstname);
        et_lastname     = (EditText) findViewById(R.id.et_studentlocation_lastname);
        spin_location   = (Spinner) findViewById(R.id.spin_studentlocation_location);
        spin_service    = (Spinner) findViewById(R.id.spin_studentlocation_service);
        et_license      = (EditText) findViewById(R.id.et_studentlocation_license);
        et_vehicle_year     = (EditText) findViewById(R.id.et_studentlocation_vehicle_year);
        et_vehicle_make     = (EditText) findViewById(R.id.et_studentlocation_vehicle_make);

        final String[] values_services  = new String[] {"[Select Service]", "Escort", "Vehicle Unlock", "Battery Jump"};
        final String[] values_locations = new String[] {"[Select Location]", "Lot A", "Lot B", "Lot C", "Lot D", "Lot E",
                                                        "Lot F", "Lot G", "Lot H", "Lot L", "Lot M"};

        final ArrayAdapter<String> adapter_services = new ArrayAdapter<>(
                this,
                R.layout.adapter_list_services,
                R.id.adapter_tv_list_services_text,
                values_services);

        final ArrayAdapter<String> adapter_locations = new ArrayAdapter<>(
                this,
                R.layout.adapter_list_locations,
                R.id.adapter_tv_list_locations_text,
                values_locations);

        spin_service.setAdapter(adapter_services);
        spin_location.setAdapter(adapter_locations);

        spin_service.setOnItemSelectedListener(oisl_item_chosen);
        spin_location.setOnItemSelectedListener(oisl_item_chosen);

        btn_submit = (Button) findViewById(R.id.btn_studentlocation_submit);
        btn_submit.setOnClickListener(cl_submit);
    }


    private View.OnClickListener cl_submit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            m_studentloc = new ModelStudentLocation(et_firstname.getText().toString(), et_lastname.getText().toString(), et_phone_number.getText().toString(),
                    spin_location.getSelectedItem().toString(), spin_service.getSelectedItem().toString(),
                    et_license.getText().toString(), Integer.parseInt(et_vehicle_year.getText().toString()), et_vehicle_make.getText().toString());

            m_studentloc.save();
        }
    };

    private AdapterView.OnItemSelectedListener oisl_item_chosen = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

}
