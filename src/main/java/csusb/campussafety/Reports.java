package csusb.campussafety;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import customactivities.NavigationGeneralActivity;

public class Reports extends NavigationGeneralActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = setInnerLayout(R.layout.activity_reports_page);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        WebView web_view = (WebView) v.findViewById(R.id.wv_reports);
        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.setWebViewClient(new WebViewClient());
        WebSettings webSettings = web_view.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.getSettings().setDomStorageEnabled(true);
        web_view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        web_view.loadUrl("http://csupd.crimegraphics.com/2013/default.aspx");
    }

}
