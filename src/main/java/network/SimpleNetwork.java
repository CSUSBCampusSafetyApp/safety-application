package network;

import android.util.Log;

import org.apache.http.NameValuePair;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class SimpleNetwork {

    public static void sendPost(String action, ArrayList<NameValuePair> http_parameters, IGeneralRun onAfter, Object... obj) {
        // Internet request
        final BasicNetwork request = new BasicNetwork( true );
        URL url1 = null; //URL object instance
        URL url2 = null; //URL object instance

        //set data for request and set url
        request.setParameters(http_parameters);

        try {
            url1 = new URL( NetworkDef.SKENGO_PROTOCAL_STANDARD + NetworkDef.DOMAIN1 + action );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            url2 = new URL( NetworkDef.SKENGO_PROTOCAL_STANDARD + NetworkDef.DOMAIN2 + action );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        request.postResultRun( onAfter, obj[0] );
        Log.i("SimpleNetwork Param:Object Check", Integer.toString(obj.length));

        // if the url is set then run the request
        if( url1 != null || url2 != null)
            request.execute( url1, url2 );
    }
}
