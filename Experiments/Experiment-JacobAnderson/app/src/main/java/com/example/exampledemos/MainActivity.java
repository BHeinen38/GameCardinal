package com.example.exampledemos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // BUTTON LOGIC
        Button buttonToThrow = findViewById(R.id.main_buttonToThrow);
        Button buttonToTack = findViewById(R.id.main_buttonToTack);

        // Create button to enter Throw screen.
        buttonToThrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), Throw.class));
            }
        });

        // Create button to enter Tic-Tack screen.
        buttonToTack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), TicTack.class));
            }
        });
    }
}