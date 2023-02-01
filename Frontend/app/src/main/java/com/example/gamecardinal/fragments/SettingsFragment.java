package com.example.gamecardinal.fragments;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.gamecardinal.R;
import com.example.gamecardinal.api.IVolleyJsonListener;
import com.example.gamecardinal.presenter.RegistrationPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * @author Eric Schwartz
 * @version 1.0
 */
public class SettingsFragment extends Fragment {

    // The fragment initialization parameters, e.g. ARG_ITEM_NUMBER.
    private static final String ARG_USER_ID = "userID";
    //private Button saveChangesButton;
    private RegistrationPresenter presenter;
    private int mUserID;
    private String usersUsername, usersName, usersEmail, usersPhoneNumber;
    private boolean usersDarkModeVal;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param userID
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(int userID) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_USER_ID, userID);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * looks and fuctionality of the setting page.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUserID = getArguments().getInt(ARG_USER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        View userSettingsListView = (View) rootView.findViewById(R.id.UserSettingsListConstraint);
        View appearenceListView = (View) rootView.findViewById(R.id.AppearenceListConstraint);

        Switch userSettingsExpander = (Switch) rootView.findViewById(R.id.UserSettingsSwitch);
        Switch appearenceListExpander = (Switch) rootView.findViewById(R.id.appearence_switch);

        Button saveChangesButton = (Button) rootView.findViewById(R.id.save_changes_button);

        EditText usernameTextField = (EditText) rootView.findViewById(R.id.username_textfield);
        EditText nameTextField = (EditText) rootView.findViewById(R.id.name_textfield);
        EditText emailTextField = (EditText) rootView.findViewById(R.id.email_textfield);
        EditText phoneNumberTextField = (EditText) rootView.findViewById(R.id.phone_number_textField);
        Switch darkModeSwitch = (Switch) rootView.findViewById(R.id.dark_mode_switch);

        //visibility setting
        userSettingsListView.setVisibility(View.GONE);
        appearenceListView.setVisibility(View.GONE);

        //setting values
        usernameTextField.setText(this.usersUsername);
        nameTextField.setText(this.usersName);
        emailTextField.setText(this.usersEmail);
        phoneNumberTextField.setText(this.usersPhoneNumber);
        darkModeSwitch.setChecked(this.usersDarkModeVal);

        //trying to pull from backend
        String url = getString(R.string.server_path) + "/user/{id}";

        try {
            JSONObject tester = new JSONObject();
            //Log.e("UsernameRecieved", tester.getString("username"));
            usersUsername = tester.getString("username");
            usersName = tester.getString("name");
            usersEmail = tester.getString("email");
            // usersPhoneNumber = tester.getString("phonenumber");
        }catch (JSONException e) {
            Log.e("Error on json data grab", e.toString());
        }

        //expands user settings
        userSettingsExpander.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    userSettingsListView.setVisibility(View.VISIBLE);
                }
                else
                    userSettingsListView.setVisibility(View.GONE);
            }
        });

        appearenceListExpander.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    appearenceListView.setVisibility(View.VISIBLE);
                }
                else
                    appearenceListView.setVisibility(View.GONE);
            }
        });

        //values to be saved to cloud to be then reintroduced when settings is opened /TODO in the future
        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newUserName = usernameTextField.getText().toString();
                String newName = nameTextField.getText().toString();
                String newEmail = emailTextField.getText().toString();
                String newPhoneNumber = phoneNumberTextField.getText().toString();
                boolean newDarkModeSwitchVal = darkModeSwitch.isChecked();

                HashMap<String, String> updatedUserInfo = new HashMap<>();
                updatedUserInfo.put("username", newUserName);
                updatedUserInfo.put("email", newEmail);
                updatedUserInfo.put("name", newName);
                JSONObject request = new JSONObject(updatedUserInfo);

                presenter.loadRequest(url, request);
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    /**
     * should receive information from outside source, in the future being the cloud to save user information to later
     * be inputted in text fields so use can change their information
     *
     * @param username
     * @param name
     * @param email
     * @param phoneNumber
     * @param darkModeSwitchVal
     */
    public void recieveUserInformation(String username, String name, String email, String phoneNumber, boolean darkModeSwitchVal)
    {
        usersUsername = username;
        usersName = name;
        usersEmail = email;
        usersPhoneNumber = phoneNumber;
        usersDarkModeVal = darkModeSwitchVal;
    }

    /**
     * caller for username information
     *
     * @return
     */
    public String getUsersUsername()
    {
        return this.usersUsername;
    }

    /**
     * caller for name of users
     *
     * @return
     */
    public String getUsersName()
    {
        return this.usersName;
    }

    /**
     * returns user email
     *
     * @return
     */
    public String getUsersEmail()
    {
        return this.usersEmail;
    }

    /**
     * returns users phone number
     *
     * @return
     */
    public String getUsersPhoneNumber()
    {
        return this.usersPhoneNumber;
    }

    /**
     * returns users prefrerence for display mode
     * @return
     */
    public boolean getUsersModePreference()
    {
        return this.usersDarkModeVal;
    }
}