package com.example.exampledemos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ToggleButton;

public class TicTack extends AppCompatActivity {

    // Game variables.
    boolean gameFinished = false;
    // If false, turn X. If true, turn O.
    boolean oPlayerTurn = false;
    int turnCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tack);

        // Find back button.
        Button buttonToMain = findViewById(R.id.tic_buttonToMain);

        // Create array for game squares.
        ToggleButton[] ticSquares = new ToggleButton[9];

        // Find vertical layout and iterate through children.
        ViewGroup verticalLayout = findViewById(R.id.tic_boardVert);

        // Create a variable to track the progress of the following nested loop.
        int buttonCount = 0;

        // We now populate the array of ToggleButtons.
        for (int i = 0; i < verticalLayout.getChildCount(); i++) {
            // First, iterate through and obtain all children horizontal layouts.
            View child = verticalLayout.getChildAt(i);
            Log.d("DEBUG", "onCreate vertical layout loop: " + child.toString());
            if (child instanceof ViewGroup) {
                // Since this code executes only if the child is an instance of ViewGroup, get its
                // own children after typecasting.
                for (int j = 0; j < ((ViewGroup) child).getChildCount(); j++) {
                    View grandchild = ((ViewGroup) child).getChildAt(j);
                    // If the child is an instance of ToggleButton, add it to the array of ticSquares.
                    // Since the number of buttons should be constant, we carelessly increment the
                    // buttonCount to proceed through the array without checking if it's safe.
                    if (grandchild instanceof ToggleButton) {
                        ticSquares[buttonCount] = (ToggleButton) grandchild;
                        buttonCount++;
                        Log.d("DEBUG", "onCreate horizontal loop: " + grandchild.toString());
                    }
                }
            }
        }


        // Set back button to go to main menu.
        buttonToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), MainActivity.class));
            }
        });

        for (ToggleButton boardSquare: ticSquares) {
            boardSquare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buttonClicked(boardSquare);
                }
            });
        }
    }

    protected void buttonClicked(ToggleButton button) {
        if (oPlayerTurn) {
            Log.d("DEBUG", "TickTack buttonClicked path one," + button.toString());
            button.setTextOn(getResources().getString(R.string.tic_O));
            button.setChecked(true);
            oPlayerTurn = false;
        } else {
            Log.d("DEBUG", "TickTack buttonClicked path two," + button.toString());
            button.setTextOn(getResources().getString(R.string.tic_X));
            button.setChecked(true);
            oPlayerTurn = true;
        }
        button.setClickable(false);
    }
}