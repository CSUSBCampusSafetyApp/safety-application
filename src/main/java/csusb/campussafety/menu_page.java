package csusb.campussafety;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class menu_page extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        ImageView button = (ImageView) findViewById(R.id.aboutus);
        ImageView button2 = (ImageView) findViewById(R.id.services);
        ImageView button3 = (ImageView) findViewById(R.id.resources);
        ImageView button4 = (ImageView) findViewById(R.id.tips);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

    
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
                startActivity(new Intent("csusb.services"));
                break;
            case R.id.resources:
                startActivity(new Intent("csusb.resources"));
                break;
            case R.id.aboutus:
                startActivity(new Intent("csusb.aboutus"));
                break;
            case R.id.tips:
                startActivity(new Intent("csusb.tips"));
                break;
        }
    }
}
