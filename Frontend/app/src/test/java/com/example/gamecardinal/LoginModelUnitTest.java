package com.example.gamecardinal;

import static org.mockito.Mockito.*;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.gamecardinal.api.LoginModel;
import com.example.gamecardinal.presenter.LoginPresenter;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginModelUnitTest {
    // Declare mock objects.
    @Mock JSONObject response;
    @Mock LoginPresenter presenter;
    @Mock Context context;
    @Mock SharedPreferences sharedPrefs;
    @Mock SharedPreferences.Editor sharedPrefsEditor;

    // parseUserID returns int (Test -404 case and valid UID case)
    @Test
    public void testParseUserIDFailure() {
        // Initialize mocks.
        response = mock(JSONObject.class);
        presenter = mock(LoginPresenter.class);
        context = mock(Context.class);
        // Create model.
        LoginModel model = new LoginModel(context);
        int uid = 0;

        try {
            when(response.getInt("id")).thenThrow(JSONException.class);
            uid = model.parseUserID(response, presenter);
        } catch (JSONException e) {
            // Test onError was called up to presenter properly.
            verify(presenter, times(1)).onError(e);
            // Verify UID is the logout value.
            Assert.assertEquals(-404, uid);
        }
    }

    @Test
    public void testParseUserIDSuccess() {
        // Initialize mocks.
        response = mock(JSONObject.class);
        presenter = mock(LoginPresenter.class);
        context = mock(Context.class);
        // Create model.
        LoginModel model = new LoginModel(context);
        int uid = 0;

        try {
            // Check that no exception is erroneously thrown.
            when(response.getInt("id")).thenReturn(50);
            uid = model.parseUserID(response, presenter);
            Assert.assertEquals(50, uid);
        } catch (JSONException e) {
            Assert.fail("Exception thrown.");
        }
    }

    // changeUserID (Mock SharedPreferences, verified information stored)
    // Do later
}
