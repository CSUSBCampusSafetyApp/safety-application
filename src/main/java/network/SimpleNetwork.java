package network;

import android.util.Log;

import org.apache.http.NameValuePair;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class SimpleNetwork {

    public static URL o_url = null;

    public static void sendPost(String string, ArrayList<NameValuePair> http_paramters, IGeneralRun onAfter, Object... obj) {
        // Internet request
        final BasicNetwork request = new BasicNetwork( true );
        URL url = null; //URL object instance

        //set data for request and set url
        request.setParameters(http_paramters);

        try {
            url = new URL( NetworkDef.SKENGO_PROTOCAL_STANDARD + NetworkDef.DOMAIN + string );
            o_url = url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        request.postResultRun( onAfter, obj[0] );
        Log.i("SimpleNetwork Param:Object Check", Integer.toString(obj.length));

        // if the url is set then run the request
        if( url != null)
            request.execute( url );
    }
}
