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
 * @author Eric S.
 */
public class SevenUpModel {

    private Context context;

    /**
     *
     * @param context
     */
    public SevenUpModel(Context context) {
        this.context = context;
    }

    /**
     *
     * @param url
     * @param gameInfo
     * @param volleyListener
     */
    public void saveGameData(final String url, JSONObject gameInfo,
                         final IVolleyJsonListener volleyListener) {
        Log.d("SUSDReq", "Sending " + gameInfo.toString() + " to " + url);
        JsonObjectRequest regRequest = new JsonObjectRequest
                (Request.Method.POST, url, gameInfo, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("SUSDReq", "Server at " + url + " gave response " + response.toString());
                        volleyListener.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("SUSDReq", error.networkResponse.allHeaders.toString());
                        volleyListener.onError(error);
                    }
                });

        ApiClient.getInstance(context).addToRequestQueue(regRequest);
    }

    /**
     *
     * @param response
     * @param parseErrorListener
     * @return
     */
    public int parseGameID(JSONObject response, IJsonParseError parseErrorListener){
        int uid;
        try {
            uid = response.getInt("game_id");
            return uid;
        } catch (JSONException error)
        {
            parseErrorListener.onError(error);
            return -404;
        }
    }

    /**
     *
     * @param uid
     */
    public void changeGameID(int uid) {
        // Return if uid equals the value signalling a JSON error.
        Log.d("SUSDMod", "Starting changGameID.");
        if (uid == -404) {
            Log.d("SUSDMod", "Exiting changeGameID via 404 branch.");
            return;
        }

        // Create or get a Shared Preferences instance to save Game ID, alongside an
        // associated Editor.
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.prefs_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Add the value of the Game ID into the preference specifying the Game ID
        editor.putInt(context.getString(R.string.prefs_curr_uid), uid);
        editor.commit();
        Log.d("SUSDMod", "Exiting changeGameID via main branch.");
    }
}