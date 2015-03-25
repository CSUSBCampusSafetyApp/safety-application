package customactivities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import csusb.campussafety.AboutUs;
import csusb.campussafety.AnonymousTips;
import csusb.campussafety.Menu;
import csusb.campussafety.R;
import csusb.campussafety.Reports;
import csusb.campussafety.Resources;
import csusb.campussafety.SecurityTips;
import csusb.campussafety.Services;
import csusb.campussafety.StudentLocation;

/**
 * NavigationGeneralActivity mean a general/generic navigation activity
 * This implements a custom action bar and defaults to the pages(activities)
 * available in this build through a ListView.
 *
 * Simply extending from this class will make create the custom actionbar and fill
 * in the the sliding menu.
 *
 * setInnerLayout is what you use to set the actual activity you plan to use which returns
 * a View object of that layout so you can call findViewById on the correct view and add
 * OnClickListeners or whatever.
 */

public class NavigationGeneralActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout_general);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_custom);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        NavigationFragmentGeneral nfg_drawer = (NavigationFragmentGeneral) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        nfg_drawer.setUp(R.id.fragment_navigation_drawer, (DrawerLayout)findViewById(R.id.dl_drawer_layout_general), toolbar);

        ListView lv = (ListView) nfg_drawer.getActivity().findViewById(R.id.lv_fragment_navigation_list);
        lv.setOnItemClickListener(oicl_navigation_list);

    }

    public View setInnerLayout(int layout_id) {
        RelativeLayout r = (RelativeLayout) findViewById(R.id.rl_inclusion_space);
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(layout_id, null, false);
        r.addView(v, 1);

        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        p.addRule(RelativeLayout.BELOW, R.id.toolbar_custom);
        v.setLayoutParams(p);

        return v;
    }

    private ListView.OnItemClickListener oicl_navigation_list = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch(position) {
                case 0:
                    Log.i("Item Clicked", "Menu");
                    startActivity(new Intent(NavigationGeneralActivity.this, Menu.class));
                    break;
                case 1:
                    Log.i("Item Clicked", "About Us");
                    startActivity(new Intent(NavigationGeneralActivity.this, AboutUs.class));
                    break;
                case 2:
                    Log.i("Item Clicked", "Services");
                    startActivity(new Intent(NavigationGeneralActivity.this, Services.class));
                    break;
                case 3:
                    Log.i("Item Clicked", "Anonymous Tips");
                    startActivity(new Intent(NavigationGeneralActivity.this, AnonymousTips.class));
                    break;
                case 4:
                    Log.i("Item Clicked", "Resources");
                    startActivity(new Intent(NavigationGeneralActivity.this, Resources.class));
                    break;
                case 5:
                    Log.i("Item Clicked", "Security Tips");
                    startActivity(new Intent(NavigationGeneralActivity.this, SecurityTips.class));
                    break;
                case 6:
                    Log.i("Item Clicked", "Location");
                    startActivity(new Intent(NavigationGeneralActivity.this, StudentLocation.class));
                    break;
                case 7:
                    Log.i("Item Clicked", "Reports");
                    startActivity(new Intent(NavigationGeneralActivity.this, Reports.class));
                    break;
            }
        }
    };
}
