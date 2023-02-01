package com.example.gamecardinal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gamecardinal.presenter.RegistrationPresenter;
import com.example.gamecardinal.view.IRegistrationView;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Class representing the registration page of the application. Predominant responsibilities
 * are that of screen transitions and retrieving input.
 *
 * @author J. Anderson
 * @version 2.0
 * @see com.example.gamecardinal.presenter.RegistrationPresenter
 * @see com.example.gamecardinal.api.RegistrationModel
 */
public class RegistrationActivity extends AppCompatActivity implements IRegistrationView {

    private EditText nameInput, usernameInput, passwordInput, emailInput, phoneInput;
    private Button registerButton;
    private String url, name, username, password, email, phoneNumber;
    private RegistrationPresenter presenter;

    /**
     * Initializes the registration view. Sets listeners for user interaction and
     * ensures the elements of the page are available to the class.
     *
     * @param savedInstanceState a state Bundle to be utilized by this Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        presenter = new RegistrationPresenter(this, getApplicationContext());

        registerButton = (Button) findViewById(R.id.regis_registerButton);
        nameInput = (EditText) findViewById(R.id.regis_nameInput);
        usernameInput = (EditText) findViewById(R.id.regis_usernameInput);
        passwordInput = (EditText) findViewById(R.id.regis_passwordInput);
        emailInput = (EditText) findViewById(R.id.regis_emailInput);
        phoneInput = (EditText) findViewById(R.id.regis_phoneInput);

        url = getString(R.string.server_path) + "/user";

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = nameInput.getText().toString();
                username = usernameInput.getText().toString();
                password = passwordInput.getText().toString();
                email = emailInput.getText().toString();
                phoneNumber = phoneInput.getText().toString();

                // Adding the hashmap here, to accommodate expansion of the User table.
                HashMap<String, String> userInfo = new HashMap<>();
                userInfo.put("username", username);
                userInfo.put("password", password);
                userInfo.put("name", name);
                userInfo.put("email", email);
                JSONObject request = new JSONObject(userInfo);

                presenter.loadRequest(url, request);
            }
        });
    }

    /**
     * Displays a toast on-screen indicating an error has occurred during the registration
     * process.
     */
    @Override
    public void displayErrorToast() {
        Context context = getApplicationContext();
        int toastLength = Toast.LENGTH_SHORT;
        Toast toast =  Toast.makeText(context, R.string.register_error_occurred, toastLength);
        toast.show();
    }

    /**
     * Transitions the current application screen to that of the primary application view,
     * HubActivity.
     *
     * @see com.example.gamecardinal.HubActivity
     */
    @Override
    public void changeToHubView() {
        startActivity(new Intent(this.getApplicationContext(), HubActivity.class));
    }
}
