package com.example.experiments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class OtherActivity extends AppCompatActivity
{
    Button selector;
    ImageView output;
    public static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        selector = findViewById(R.id.button);
        output = findViewById(R.id.imageView);

        selector.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent();
                i.setType("image/**");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Select Picture"), PICK_IMAGE);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            if (requestCode == PICK_IMAGE)
            {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri)
                {
                    output.setImageURI(selectedImageUri);
                }
            }
        }
    }
}