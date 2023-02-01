package com.example.gamecardinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gamecardinal.presenter.LoginPresenter;
import com.example.gamecardinal.view.ILoginView;

/**
 * Activity that takes users information and allows access from database.
 *
 * @author Eric Schwartz
 * @author J. Anderson
 * @version 1.0
 */
public class LoginActivity extends AppCompatActivity implements ILoginView {

    private Button registerButton, loginButton;
    private EditText usernameInput, passwordInput;
    private String username, password, url;
    private TextView loginResponse;

    /**
     * gathers information from user.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginPresenter presenter = new LoginPresenter(this, getApplicationContext());

        registerButton = (Button) findViewById(R.id.login_registerButton);
        loginButton = (Button) findViewById(R.id.login_loginButton);
        usernameInput = (EditText) findViewById(R.id.login_usernameInput);
        passwordInput = (EditText) findViewById(R.id.login_passwordInput);
        loginResponse = (TextView) findViewById(R.id.login_responseText);

        url = getString(R.string.server_path) + "/user/login";

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), RegistrationActivity.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameInput.getText().toString();
                password = passwordInput.getText().toString();

                Log.d("LoginActivity", username + ", " + password);

                presenter.loadRequest(url, username, password);
            }
        });
    }

    /**
     * Shows if users in unable to enter with given information
     *
     * @param s
     */
    @Override
    public void setResponseText(String s) {
        loginResponse.setText(s);
    }

    /**
     * on completed login takes you to main hub activity
     */
    @Override
    public void changeToHubView() {
        startActivity(new Intent(this.getApplicationContext(), HubActivity.class));
    }
}
