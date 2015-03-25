package csusb.campussafety;

import android.os.Bundle;

import customactivities.NavigationGeneralActivity;


public class AboutUs extends NavigationGeneralActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInnerLayout(R.layout.activity_aboutus_page);
    }

}
