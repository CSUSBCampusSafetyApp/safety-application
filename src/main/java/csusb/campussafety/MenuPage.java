package csusb.campussafety;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;


public class MenuPage extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        RelativeLayout btn_aboutus   = (RelativeLayout) findViewById(R.id.aboutus);
        RelativeLayout btn_services  = (RelativeLayout) findViewById(R.id.services);
        RelativeLayout btn_resources = (RelativeLayout) findViewById(R.id.resources);
        RelativeLayout btn_tips      = (RelativeLayout) findViewById(R.id.tips);

        btn_aboutus.setOnClickListener(this);
        btn_services.setOnClickListener(this);
        btn_resources.setOnClickListener(this);
        btn_tips.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_page, menu);
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

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.services:
                startActivity(new Intent(this, Services.class));
                break;
            case R.id.resources:
                startActivity(new Intent(this, Resources.class));
                break;
            case R.id.aboutus:
                //startActivity(new Intent(this AboutUs.class));
                Log.i("Missing Class", "Not done!");
                break;
            case R.id.tips:
                startActivity(new Intent(this, AnonymousTips.class));
                break;
        }
    }
}
