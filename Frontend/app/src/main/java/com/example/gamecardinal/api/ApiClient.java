package com.example.gamecardinal.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Singleton class used to access the network RequestQueue. It is through
 * this class that any and all network requests for GameCardinal should
 * be added.
 *
 * @author J. Anderson
 * @version 1.0
 */
public class ApiClient {
    private static ApiClient instance;
    private RequestQueue requestQueue;
    private static Context cnxt;

    private ApiClient(Context context) {
        cnxt = context;
        requestQueue = getRequestQueue();
    }

    /**
     * Returns the ApiClient singleton for access. If the ApiClient
     * has not yet been instantiated, this method creates it for us
     * automatically.
     *
     * @param context the current application context
     * @return  access to the ApiClient singleton
     */
    public static synchronized ApiClient getInstance(Context context) {
        // In true singleton form, create our instance if not yet instantiated.
        if (instance == null) {
            instance = new ApiClient(context);
        }
        // Return ApiClient instance unconditionally.
        return instance;
    }

    /**
     * Returns the RequestQueue managed by this ApiClient for the application.
     * If a RequestQueue has not yet been instantiated, this method will create one
     * for the ApiClient.
     *
     * @return the RequestQueue of this ApiClient
     */
    public RequestQueue getRequestQueue() {
        // Create RequestQueue if not yet initialized.
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(cnxt.getApplicationContext());
        }
        // Return RequestQueue unconditionally.
        return requestQueue;
    }

    /**
     * Adds a request to the current RequestQueue managed by the ApiClient. If the
     * RequestQueue does not yet exist, one is created via a method call before
     * subequently adding the request.
     *
     * @param req the network request to add to the ApiClient's RequestQueue
     * @param <T> generic type of the request
     */
    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }
}
