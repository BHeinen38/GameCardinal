package com.example.gamecardinal.api;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface IVolleyJsonListener {
    void onSuccess(JSONObject result);
    void onError(VolleyError error);
}
