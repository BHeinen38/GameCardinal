package com.example.exampledemos;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.view.GestureDetectorCompat;

public class BallImageView extends AppCompatImageView {

    // Quite frankly, I have no idea what I'm doing. But this is how
    // it seems like I'll get gestures working for my stupid ball.
    static GestureDetectorCompat gestureCompat;

    // CONSTRUCTORS
    public BallImageView(@NonNull Context context) {
        super(context);
    }

    public BallImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // ON DRAW
    @Override
    protected void onDraw (Canvas canvas) { super.onDraw(canvas); }

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        return super.onTouchEvent(event);
    }

}
