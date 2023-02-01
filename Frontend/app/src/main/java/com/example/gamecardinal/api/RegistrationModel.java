package com.example.gamecardinal.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.gamecardinal.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Model component of the RegistrationActivity dealing with data processing and network requests.
 *
 * @author J. Anderson
 * @version 2.0
 * @see com.example.gamecardinal.presenter.RegistrationPresenter
 * @see com.example.gamecardinal.RegistrationActivity
 */
public class RegistrationModel {

    private Context context;

    /**
     * Creates a RegistrationModel with the specified Context.
     *
     * @param context the application context
     */
    public RegistrationModel(Context context) {
        this.context = context;
    }

    /**
     * Creates a network request to register a user with the JSONObject specified.
     *
     * @param url the URL to send the network request to
     * @param userInfo the JSONObject containing the user's information
     * @param volleyListener a listener to respond to the network request
     */
    public void register(final String url, JSONObject userInfo,
                         final IVolleyJsonListener volleyListener) {
        Log.d("RegReq", "Sending " + userInfo.toString() + " to " + url);
        JsonObjectRequest regRequest = new JsonObjectRequest
                (Request.Method.POST, url, userInfo, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("RegReq", "Server at " + url + " gave response " + response.toString());
                        volleyListener.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("RegReq", error.networkResponse.allHeaders.toString());
                        volleyListener.onError(error);
                    }
                });

        ApiClient.getInstance(context).addToRequestQueue(regRequest);
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
            uid = response.getInt("user_id");
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
        Log.d("RegMod", "Starting changeUserID.");
        if (uid == -404) {
            Log.d("RegMod", "Exiting changeUserID via 404 branch.");
            return;
        }

        // Create or get a Shared Preferences instance to save user ID, alongside an
        // associated Editor.
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.prefs_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Add the value of the user ID into the preference specifying the user ID
        // of the currently logged in user.
        editor.putInt(context.getString(R.string.prefs_curr_uid), uid);
        editor.commit();
        Log.d("RegMod", "Exiting changeUserID via main branch.");
    }
}
