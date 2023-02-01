package com.example.gamecardinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Main page when openening app to direct user to either login or registration page
 *
 * @author Eric Schwartz
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    Button loginButton, registerButton;
    ImageButton adminPanelButton;

    /**
     * Buttons to direct user.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//activity_main

        loginButton = findViewById(R.id.main_loginButton);
        registerButton = findViewById(R.id.main_registrationButton);
        adminPanelButton = findViewById(R.id.main_adminButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(view.getContext(), LoginActivity.class));
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), RegistrationActivity.class));
            }
        });

        adminPanelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), NavigationActivity.class));
            }
        });
    }
}