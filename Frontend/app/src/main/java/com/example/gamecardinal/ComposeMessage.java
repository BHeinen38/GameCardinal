package com.example.gamecardinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.gamecardinal.R;
import com.example.gamecardinal.api.ApiClient;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * activity that in the future will recieve messages from differing users and send messages from current user.
 *
 * @author Eric Schwartz
 * @version 1.0
 */
public class ComposeMessage extends AppCompatActivity {

    public int RecievingUserId;

    //public constructor
    public ComposeMessage() {

    }

    /**
     * Displayes messages that current users will send
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compose_message);

        //TextInputLayout messageInput = this.getView().findViewById(R.id.messageField);
        TextView messageDisplayedTEST = findViewById(R.id.MessageInputTEST);

        //reads users input to them put inside messagesDIsplayed
        TextInputEditText messageInputEditText = findViewById(R.id.messageFieldEditText);
//        messageInputEditText.addTextChangedListener(new TextWatcher()
//        {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {//Should change messageDispalyedField into the message inputted
//
//                Log.d("userInput", messageInputEditText.getText().toString());
//                messageDisplayedTEST.setText(messageInputEditText.getText());
//
//                String newMessage = messageInputEditText.getText().toString();
//            }
//        });

        messageInputEditText.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            String message = messageInputEditText.getText().toString();
                            message = message.replaceAll("//s", "");
                            if (message.equals("") == false)
                            {
                                messageDisplayedTEST.setText(messageInputEditText.getText());
                                messageInputEditText.setText("");
                            }
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        //Button Listener for file picking
        Button fileButton = findViewById(R.id.FilePickerButton);

        // Create button to enter Throw screen.
        fileButton.setOnClickListener(new View.OnClickListener() {
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
     * used to get image that user wants to send
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case 1234:
                if(resultCode == RESULT_OK){
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

    };

    public void composeMessage(View view, String MessageSent) {
        // Construct the URL.
        String url = getString(R.string.server_path) + "/composeMessage";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            if (response.equals("-404")) {
                TextView loginResponse = findViewById(R.id.login_responseText);
                loginResponse.setText(getString(R.string.login_bad_credentials));
            } else {
                // Create or get a Shared Preferences instance to save user ID, alongside an
                // associated Editor.
                SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.prefs_key),
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                // Add the value of the user ID into the preference specifying the user ID
                // of the currently logged in user.
                editor.putInt(getString(R.string.prefs_curr_uid), Integer.parseInt(response));
                editor.commit();
                // Transition to Hub Activity with user ID now stored.
                startActivity(new Intent(view.getContext(), HubActivity.class));
            }
        }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                TextView loginResponse = findViewById(R.id.login_responseText);
//                loginResponse.setText(getString(R.string.login_error_occurred));
                Log.e("ComposeMessage", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> requestData = new HashMap<String, String>();
                requestData.put("Message Sent", MessageSent);
                return requestData;
            }
        };

        ApiClient.getInstance(this).addToRequestQueue(request);
    }

    public void RecipientOfMessage(int id)
    {
        this.RecievingUserId = id;
    }
}
