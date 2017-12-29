package co.quindio.sena.tutorialwebservice.Utilities;

import android.content.Context;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import static android.content.ContentValues.TAG;

/**
 * Created by manuel on .
 */

public class Http {
    static String baseURL="http://192.168.250.1";
    private static int codeP;
    private static String resultP;
    Context myContext;
    public Http(Context myContext) {
        this.myContext = myContext;
        this.codeP = 0;
        this.resultP = "";
    }
    public static String get(String url, String params) throws JSONException {
        InputStream content = null;
        String result = "";
        String add_params = "?";
        if (!params.isEmpty()) {
            JSONObject data = new JSONObject(params);
            for (int i = 0; i < data.names().length(); i++) {
                Log.d("Provandoooo", "key = " + data.names().getString(i) + " value = " + data.get(data.names().getString(i)));
                add_params += data.names().getString(i)+"="+ data.get(data.names().getString(i))+"&";
                Log.d("resdddddd", add_params);
            }
        }

        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet http_get = new HttpGet(baseURL+url+add_params);
            HttpResponse response = httpclient.execute(http_get);
            HttpEntity entity = response.getEntity();
            int code = response.getStatusLine().getStatusCode();
            // Get our response as a String.
            content = entity.getContent();
            codeP = code;
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("error", e.getMessage());
        }

        //convert response to string
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(content, "utf-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                result += line;
            }
            content.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultP = result;
        return result;
    }

    public static void post(String key, Context context) {
        String x;
    }

    public static void put(String key, Context context) {
        String x;
    }
    public static void delete(String key, Context context) {
        String x;
    }
    public static int getCode() {
        return codeP;
    }

    public static JSONObject getResult() throws JSONException {
        JSONObject response = new JSONObject(resultP);
        return response;
    }
    public static String getError() {
        return resultP;
    }

}
