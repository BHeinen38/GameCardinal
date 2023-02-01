package com.example.gamecardinal.presenter;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.example.gamecardinal.api.IJsonParseError;
import com.example.gamecardinal.api.IVolleyJsonListener;
import com.example.gamecardinal.api.IVolleyStringListener;
import com.example.gamecardinal.api.RegistrationModel;
import com.example.gamecardinal.view.IRegistrationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegistrationPresenter implements IRegistrationPresenter, IVolleyJsonListener, IJsonParseError {

    private IRegistrationView view;
    private RegistrationModel model;

    public RegistrationPresenter(IRegistrationView view, Context context) {
        this.view = view;
        this.model = new RegistrationModel(context);
    }

    @Override
    public void loadRequest(String url, JSONObject userInfo) {
        model.register(url, userInfo, this);
    }

    @Override
    public void onSuccess(JSONObject result) {
        int uid = model.parseUserID(result, this);
        if (uid != -404) {
            model.changeUserID(uid);
            view.changeToHubView();
        }
        view.changeToHubView();
    }

    @Override
    public void onError(VolleyError error) {
        Log.e("RegPres", error.toString());
        view.displayErrorToast();
    }

    @Override
    public void onError(JSONException error) {
        Log.e("RegPres", error.toString());
        view.displayErrorToast();
    }

}
