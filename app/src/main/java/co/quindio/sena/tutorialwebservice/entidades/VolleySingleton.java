package co.quindio.sena.tutorialwebservice.entidades;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Sebastian on 29/11/2017.
 */

public class VolleySingleton {

    private static VolleySingleton instanciaVolley;
    private RequestQueue request;
    private static Context contexto;

    private VolleySingleton(Context context) {
        contexto = context;
        request = getRequestQueue();
    }


    public static synchronized VolleySingleton getInstanciaVolley(Context context) {
        if (instanciaVolley == null) {
            instanciaVolley = new VolleySingleton(context);
        }

        return instanciaVolley;
    }

    public RequestQueue getRequestQueue() {
        if (request == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            request = Volley.newRequestQueue(contexto.getApplicationContext());
        }

        return request;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }


}
