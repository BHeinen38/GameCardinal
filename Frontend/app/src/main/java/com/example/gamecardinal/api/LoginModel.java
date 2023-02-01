package com.example.gamecardinal.api;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.gamecardinal.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Model component of the LoginActivity dealing with data processing and network requests.
 *
 * @author J. Anderson
 * @version 1.0
 */
public class LoginModel {

    private Context context;

    /**
     * Creates a LoginModel given the specified context.
     *
     * @param context the application context
     */
    public LoginModel(Context context) {
        this.context = context;
    }

    /**
     * Attempts to log in the user via a network request utilizing the specified username
     * and password.
     *
     * @param url the URL to send the network request to
     * @param username the username for the request
     * @param password the password for the request
     * @param volleyListener a listener to respond to the network request
     */
    public void login(final String url, final String username, final String password,
                      final IVolleyJsonListener volleyListener) {
        // Bundle up request data for transmission.
        Map<String, String> requestData = new HashMap<>();
        requestData.put("username", username);
        requestData.put("password", password);

        // Create a JSON object from request data.
        JSONObject jsonReqObj = new JSONObject(requestData);

        // Formulate request.
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,
                url, jsonReqObj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                volleyListener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyListener.onError(error);
            }
        });

        ApiClient.getInstance(context).addToRequestQueue(jsonRequest);
    }

    /**
     * Returns a user ID from the JSON response of a given network request.
     *
     * @param response the JSON response returned from a registration request
     * @param parseErrorListener an error listener in the event the user ID cannot be parsed
     * @return an integer representing the user ID
     */
    public int parseUserID(JSONObject response, IJsonParseError parseErrorListener){
        int uid;
        try {
            uid = response.getInt("id");
            return uid;
        } catch (JSONException error)
        {
            parseErrorListener.onError(error);
            return -404;
        }
    }

    /**
     * Alters the user ID for the app's SharedPreferences to keep track of the user's identity.
     * If the ID is set to a value indicating that an error has occurred, this method automatically
     * returns without doing anything.
     *
     * @param uid the user ID to attempt to put into SharedPreferences; if -404, nothing happens
     */
    public void changeUserID(int uid) {
        // Return if uid equals the value signalling a JSON error.
        if (uid == -404) {
            return;
        }

        // Fetch our shared preferences. It's ugly, but it works.
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.prefs_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Slap the int we have into the current shared preferences.
        editor.putInt(context.getString(R.string.prefs_curr_uid), uid);
        editor.commit();
    }
}
