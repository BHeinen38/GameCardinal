package com.example.gamecardinal;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Class used to create user based on inputted information.
 *
 * @author Eric Schwartz
 * @version 1.0
 */
public class RegistrationPage extends AppCompatActivity {


    /**
     * On created activity, give utilities to components.
     * Mostly information from user on click.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        EditText usersnameField = findViewById(R.id.regis_nameInput);
        EditText usersUsernameField = findViewById(R.id.regis_usernameInput);
        EditText usersPasswordField = findViewById(R.id.regis_passwordInput);
        EditText usersEmailField = findViewById(R.id.regis_emailInput);
        EditText usersPhoneNumberField = findViewById(R.id.regis_phoneInput);

        Button registerButton = (Button) findViewById(R.id.regis_registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(view.getContext(), RegistrationPage.class));
                String usersName = usersnameField.getText().toString();
                String usersUsername = usersUsernameField.getText().toString();
                String usersPassword = usersPasswordField.getText().toString();
                String usersEmail = usersEmailField.getText().toString();
                String usersPhoneNumber = usersPhoneNumberField.getText().toString();

                //String usersImagePath = ;
            }
        });

        ImageButton usersImage = (ImageButton) findViewById(R.id.regis_pfpButton);
        usersImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                final int ACTIVITY_SELECT_IMAGE = 1234;
                startActivityForResult(i, ACTIVITY_SELECT_IMAGE);
            }
        });
    }

    /**
     * Get image from user to be latter saved
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1234:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();
                    //this will be the path for the file selected
                    Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
                }
        }
    }
}
