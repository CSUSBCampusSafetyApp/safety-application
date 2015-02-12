package network;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by seanx_000 on 2/12/2015.
 */

public class BasicNetwork extends AsyncTask<URL, Integer, String> {

    private int HTTP_TIMEOUT = 10 * 1000; // 30 seconds (time on milliseconds)
    private HttpClient http_client = null;
    private ArrayList<NameValuePair> http_parameters = null;
    private boolean request_type = false;
    private JSONObject json_object = null;
    private IGeneralRun general_run = null;
    private Object obj = null;

    private HttpClient getHttpClient() {
        if( http_client == null ) {
            http_client = new DefaultHttpClient();
            final HttpParams params = http_client.getParams();
            HttpConnectionParams.setConnectionTimeout(params, HTTP_TIMEOUT);
            HttpConnectionParams.setSoTimeout(params, HTTP_TIMEOUT);
            ConnManagerParams.setTimeout(params, HTTP_TIMEOUT);
        }

        return http_client;
    }

    private String executeHttpPost(String url, ArrayList<NameValuePair> post_parameters) throws Exception  {
        BufferedReader in = null;
        try {

            HttpClient client = getHttpClient();
            HttpPost request = new HttpPost(url);
            UrlEncodedFormEntity form_entity = new UrlEncodedFormEntity(post_parameters);
            request.setEntity(form_entity);
            HttpResponse response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuilder sb = new StringBuilder("");
            String line;
            String NL = System.getProperty("line.seperator");
            while( (line = in.readLine()) != null ) {
                sb.append(line).append(NL);
            }
            in.close();

            String result = sb.toString();
            json_object = JsonData.Instance().parseString( result );
            Log.i("Http Result (Post)", result);

            return result;

        }
        finally {
            if( in != null ) {
                try {
                    in.close();
                } catch ( IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


    @Override
    protected String doInBackground(URL... params)  {
        // TODO Auto-generated method stub
        String result = "";

        if(request_type && (http_parameters != null)) {

            // Check if URL Exist : NEEDS TO BE TESTED!!!!!!!!
            // Use with Parameters being sent at the moment!!!!!
            URL m_url = params[0];
            URL c_url = params[1];
            HttpURLConnection huc;


            boolean is_ok = false;

            try {
                huc = (HttpURLConnection) m_url.openConnection();
                huc.setConnectTimeout(HTTP_TIMEOUT);
                huc.setRequestMethod("HEAD");
                huc.connect();
                is_ok = (huc.getResponseCode() == HttpURLConnection.HTTP_OK );
                huc.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
            }

            if( is_ok )
                try {
                    Log.i("NetworkThread","URL1 OK");
                    Log.i("URLString",m_url.toString());
                    result = executeHttpPost(m_url.toString(), http_parameters);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            else {
                try {
                    huc = (HttpURLConnection) c_url.openConnection();
                    huc.setConnectTimeout(HTTP_TIMEOUT);
                    huc.setRequestMethod("HEAD");
                    huc.connect();
                    is_ok = (huc.getResponseCode() == HttpURLConnection.HTTP_OK );
                    huc.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if( is_ok )
                    try {
                        Log.i("NetworkThread","URL2 OK");
                        Log.i("URLString",c_url.toString());
                        result = executeHttpPost(c_url.toString(), http_parameters);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

            }

        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        json_object = JsonData.Instance().parseString( result );
        Log.i("Http Post Result", result);

        general_run.execute(this, obj);
    }

    // Public functions (below)

    // constructor
    public BasicNetwork (boolean request_type) {
        this.request_type = request_type;
    }

    // set parameters
    public void setParameters( ArrayList<NameValuePair> http_parameters ) {

        this.http_parameters = http_parameters;

    }

    // Function/Object Ran during the execution of request
    public void postResultRun(IGeneralRun r) {
        general_run = r;
    }

    public void postResultRun(IGeneralRun r, Object obj) {
        general_run = r;
        this.obj = obj;
    }

    // return data as a jsonobject
    public JSONObject getJsonObject() {

        return json_object;

    }

}
