package csusb.campussafety;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import models.ModelAnonTips;
import network.BasicNetwork;
import network.IGeneralRun;

public class AnonymousTips extends Activity {

    private EditText et_subject = null;
    private EditText et_message= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anonymoustips_page); /// Set activity page

        /** Initialize variables to get data from text-fields and get action from button */
        et_subject = (EditText) findViewById(R.id.et_anonymoustips_subject);
        et_message = (EditText) findViewById(R.id.et_anonymoustips_message);
        Button btn_submit = (Button) findViewById(R.id.btn_anonymoustips_submit);

        /** Set click/touch listener for when the user presses down on button */
        btn_submit.setOnClickListener(submit);
    }

    /** Set click/touch listener for when the user presses down on button.
     * This will make sure the user enters a subject and message before the
     * submit will take. */
    private View.OnClickListener submit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            boolean exist_subject = true;
            boolean exist_message = true;

            if( et_subject.length() <= 0 ) {
                Log.e("TextField:Subject", "Subject is empty!");
                exist_subject = false;
            }

            if( et_message.length() <= 0 ) {
                Log.e("TextField:Message", "Message is empty!");
                exist_message = false;
            }


            if( exist_subject && exist_message ) {
                Log.i("Button:Submit", "Submitted data!");
                ModelAnonTips model = new ModelAnonTips(et_subject.getText().toString(), et_subject.getText().toString());
                model.save(after_save);
            }
            else {
                Log.e("Button:Submit", "Unable to submit data!");
            }
        }
    };

    private IGeneralRun after_save = new IGeneralRun() {
        @Override
        public void execute(BasicNetwork request, Object o) {
            // request = null
            boolean success = (boolean)o;
            if(success) {
                Log.i("(Success)Going to...", "AnonymousTips");
                final Intent page = new Intent( AnonymousTips.this, AnonymousTips.class );
                startActivity( page );
                finish();
            }
            else {
                Log.i("(Failed)Going to...", "AnonymousTips");
                final Intent page = new Intent( AnonymousTips.this, AnonymousTips.class );
                startActivity( page );
                finish();
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /// Handle action bar item clicks here. The action bar will
        /// automatically handle clicks on the Home/Up button, so long
        /// as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        ///noinspection SimplifiableIfStatement
        return id == R.id.action_settings || super.onOptionsItemSelected(item);

    }
}
