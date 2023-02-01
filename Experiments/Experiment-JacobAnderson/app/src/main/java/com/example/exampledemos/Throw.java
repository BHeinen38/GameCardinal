package com.example.exampledemos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class Throw extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_throw);

        // Find views from their respective IDs.
        Button buttonToMain = findViewById(R.id.throw_buttonToMain);
        BallImageView ballImageView = findViewById(R.id.throw_ballImageView);

        // Gesture detector.
        GestureDetector ballGestureListener = new GestureDetector(this, new BallGestureListener());

        buttonToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), MainActivity.class));
            }
        });

        ballImageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("DEBUG", "onTouch: " + motionEvent.toString());
                return ballGestureListener.onTouchEvent(motionEvent);
            }
        });
    }

    class BallGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent event) {
            Log.d("DEBUG", "onDown: " + event.toString());
            return true;
        }

        @Override
        public boolean onFling(MotionEvent eventDown, MotionEvent eventUp,
                               float velocityX, float velocityY) {
            Log.d("DEBUG", "onFling: (x: " + velocityX + ", y: "+ velocityY + ")");
            BallImageView ballView = findViewById(R.id.throw_ballImageView);
            FlingAnimation flingX = new FlingAnimation(ballView, DynamicAnimation.TRANSLATION_X)
                    .setStartVelocity(velocityX)
                    .setMaxValue(400)
                    .setMinValue(-400)
                    .setFriction(0.9f);
            FlingAnimation flingY = new FlingAnimation(ballView, DynamicAnimation.TRANSLATION_Y)
                    .setStartVelocity(velocityY)
                    .setMaxValue(400)
                    .setMinValue(-400)
                    .setFriction(0.9f);
            Log.d("DEBUG", "onFling: init fling animations.");
            flingX.start();
            flingY.start();
            return true;
        }
    }
}