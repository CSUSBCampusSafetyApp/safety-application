package csusb.campussafety;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import customactivities.NavigationGeneralActivity;


public class Menu extends NavigationGeneralActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = setInnerLayout(R.layout.activity_menu_page);

        RelativeLayout btn_aboutus    = (RelativeLayout) v.findViewById(R.id.aboutus);
        RelativeLayout btn_services   = (RelativeLayout) v.findViewById(R.id.services);
        RelativeLayout btn_resources  = (RelativeLayout) v.findViewById(R.id.resources);
        RelativeLayout btn_tips       = (RelativeLayout) v.findViewById(R.id.tips);
        RelativeLayout btn_locations  = (RelativeLayout) v.findViewById(R.id.locations);
        RelativeLayout btn_safetytips = (RelativeLayout) v.findViewById(R.id.safetytips);
        RelativeLayout btn_reports    = (RelativeLayout) v.findViewById(R.id.reports);

        btn_aboutus.setOnClickListener(this);
        btn_services.setOnClickListener(this);
        btn_resources.setOnClickListener(this);
        btn_tips.setOnClickListener(this);
        btn_safetytips.setOnClickListener(this);
        btn_locations.setOnClickListener(this);
        btn_reports.setOnClickListener(this);
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
                startActivity(new Intent(this, AboutUs.class));
                break;
            case R.id.tips:
                startActivity(new Intent(this, AnonymousTips.class));
                break;
            case R.id.safetytips:
                startActivity(new Intent(this, SecurityTips.class));
                break;
            case R.id.locations:
                startActivity(new Intent(this, StudentLocation.class));
                break;
            case R.id.reports:
                startActivity(new Intent(this, Reports.class));
                break;
        }
    }
}
