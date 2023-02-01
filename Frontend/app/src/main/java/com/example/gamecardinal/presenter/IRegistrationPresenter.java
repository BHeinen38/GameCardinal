package com.example.gamecardinal.presenter;

import org.json.JSONObject;

import java.util.HashMap;

public interface IRegistrationPresenter {
    void loadRequest(String url, JSONObject userInfo);
}
