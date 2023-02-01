package com.example.gamecardinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity used for coder to quickly move through each activity in a direct fashion
 *
 * @author Eric Schwartz
 * @version 1.0
 */
public class NavigationActivity extends AppCompatActivity {

    public NavigationActivity(){

    }

    /**
     * Buttons used to start activities, straigt forward mehtod of viewing different activites at once
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_navigation);

        Button loginButton = findViewById(R.id.login);
        // Create button to enter Throw screen.
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), LoginActivity.class));
            }
        });

        Button registerButton = findViewById(R.id.register);
        // Create button to enter Throw screen.
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), RegistrationPage.class));
            }
        });

        Button composeMsgButton = findViewById(R.id.composeMsg);
        // Create button to enter Throw screen.
        composeMsgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), ComposeMessage.class));
            }
        });

        Button fragmentTesterButton = findViewById(R.id.FragmentTesterButton);
        // Create button to enter Throw screen.
        fragmentTesterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), FragmentTesterActivity.class));
            }
        });
    }
}
