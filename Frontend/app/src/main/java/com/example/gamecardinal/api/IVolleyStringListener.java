package com.example.gamecardinal.api;

import com.android.volley.VolleyError;

public interface IVolleyStringListener {
    void onSuccess(String result);
    void onError(VolleyError error);
}
