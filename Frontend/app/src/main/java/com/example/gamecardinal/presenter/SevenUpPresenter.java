package com.example.gamecardinal.presenter;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.example.gamecardinal.api.IJsonParseError;
import com.example.gamecardinal.api.IVolleyJsonListener;
import com.example.gamecardinal.api.RegistrationModel;
import com.example.gamecardinal.api.SevenUpModel;
import com.example.gamecardinal.view.ISevenUpView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Eric S.
 */
public class SevenUpPresenter implements ISevenUpPresenter, IVolleyJsonListener, IJsonParseError {

    private ISevenUpView view;
    private SevenUpModel model;

    public SevenUpPresenter(ISevenUpView view, Context context) {
        this.view = view;
        this.model = new SevenUpModel(context);
    }

    @Override
    public void loadRequest(String url, JSONObject gameInfo) {
        model.saveGameData(url, gameInfo, this);
    }

    @Override
    public void onSuccess(JSONObject result) {
        Log.e("SUSDSuccess", result.toString());
        int uid = model.parseGameID(result, this);
        if (uid != -404) {
            model.changeGameID(uid);
            //view.changeToHubView();
        }
        //view.changeToHubView();
    }

    @Override
    public void onError(VolleyError error) {
        Log.e("SUSDPres", error.toString());
        //view.displayErrorToast();
    }

    @Override
    public void onError(JSONException error) {
        Log.e("SUSDPres", error.toString());
        //view.displayErrorToast();
    }
}

