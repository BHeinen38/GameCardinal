package com.example.gamecardinal.presenter;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.example.gamecardinal.api.IJsonParseError;
import com.example.gamecardinal.api.IVolleyJsonListener;
import com.example.gamecardinal.api.LoginModel;
import com.example.gamecardinal.view.ILoginView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Presenter component of the LoginActivity that allows the LoginModel to communicate with its
 * associated view.
 *
 * @author J. Anderson
 * @version 1.0
 */
public class LoginPresenter implements ILoginPresenter, IVolleyJsonListener, IJsonParseError {

    private ILoginView view;
    private LoginModel model;

    /**
     * Creates a presenter with an associated view and generated LoginModel.
     *
     * @param view the login view
     * @param context an application context to generate the LoginModel with
     */
    public LoginPresenter(ILoginView view, Context context) {
        this.view = view;
        this.model = new LoginModel(context);
    }

    /**
     * Sends down a login request to the LoginModel given a specified URL, username,
     * and password.
     *
     * @param url the URL to send the network request to
     * @param username the username to include in the request
     * @param password the password to include in the request
     */
    @Override
    public void loadRequest(String url, String username, String password) {
        model.login(url, username, password, this);
    }

    /**
     * Method called upon a successful Volley request from LoginModel. Uses the model to
     * validate and save the user ID from the request response.
     *
     * @param result a JSONObject response received from the login request
     */
    @Override
    public void onSuccess(JSONObject result) {
        Log.d("LoginAct", result.toString());

        int uid = model.parseUserID(result, this);
        if (uid != -404) {
            model.changeUserID(uid);
            view.changeToHubView();
        }
    }

    /**
     * Handles errors involving the login network requests by telling the view to respond
     * appropriately. Tells the view to display a message indicating the error was with
     * the network request.
     *
     * @param error a VolleyError passed up to this method
     */
    @Override
    public void onError(VolleyError error) {
        Log.e("LoginPres", error.toString());
        view.setResponseText("A network or authorization error occurred while attempting to log in.");
    }

    /**
     * Handles error regarding data parsing by telling the view to respond appropriately. Tells the
     * view to display a message indicating there was something wrong with the data parsing.
     *
     * @param error a JSONException passed up to this method
     */
    @Override
    public void onError(JSONException error) {
        Log.e("LoginPres", error.toString());
        view.setResponseText("A data parsing error occurred while attempting to log in.");
    }
}
