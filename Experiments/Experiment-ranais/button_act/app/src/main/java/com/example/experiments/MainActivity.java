package com.example.experiments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Timer;

public class MainActivity extends AppCompatActivity
{

    Button butt;
    TextView txt;
    ConstraintLayout layout;
    FloatingActionButton addActivity, nextActivity;
    boolean textToggle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       butt = findViewById(R.id.butt);
       txt = findViewById(R.id.textView);
       addActivity = findViewById(R.id.activity);
       layout =  findViewById(R.id.Constraint);
       nextActivity = findViewById(R.id.nextActivity);

       butt.setOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View view)
           {
               textToggle = !textToggle;

               if(textToggle)
               {
                   txt.setText("YOU CLICK A BUTTON");
               }
               else
               {
                   txt.setText("Hello, World");
               }
           }
       });

       addActivity.setOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View view)
            {
                if(savedInstanceState == null)
                {
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .add(R.id.fragment_container_view, ExampleFragment.class, null)
                            .commit();
                }
            }
       });

       nextActivity.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent myIntent = new Intent(MainActivity.this, OtherActivity.class);
               MainActivity.this.startActivity(myIntent);
           }
       });

    }
}