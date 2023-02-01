package com.example.gamecardinal.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gamecardinal.ComposeMessage;
import com.example.gamecardinal.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Eric Schwartz
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactListFragment extends Fragment {

    // The fragment initialization parameters, e.g. ARG_ITEM_NUMBER.
    private static final String ARG_USER_ID = "userID";
    final ComposeMessage composeMessage = new ComposeMessage();
    private int mUserID, userId, userPageStepper;

    public ContactListFragment() {
        // Required empty public constructor.
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param userID User ID of the user for whom to return the
     *               contact list of.
     * @return A new instance of fragment ContactListFragment.
     */
    public static ContactListFragment newInstance(int userID) {
        ContactListFragment fragment = new ContactListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_USER_ID, userID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUserID = getArguments().getInt(ARG_USER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact_list, container, false);
        userPageStepper = 0;
        String username, userImage;

        //trying to pull from backend
        String url = getString(R.string.server_path) + "/user/all";
//        try {
            JSONObject tester = new JSONObject();
            // TODO GET USER DATA TO PUT INTO USERS NAME AND USERS IMAGES BELOW
            // TODO GET username, id, and image
//             usersPhoneNumber = tester.getString("phonenumber");
//        }catch (JSONException e) {
//            Log.e("Error on json data grab", e.toString());
//        }

        //Contact names
        TextView firstContactName = (TextView) rootView.findViewById(R.id.firstBoxName);
        TextView secondContactName = (TextView) rootView.findViewById(R.id.secondBoxName);
        TextView thirdContactName = (TextView) rootView.findViewById(R.id.thirdBoxName);
        TextView fourthContactName = (TextView) rootView.findViewById(R.id.fourthBoxName);

        //contact images
        ImageView firstContactImage = (ImageView) rootView.findViewById(R.id.firstBoxImage);
        ImageView secondContactImage = (ImageView) rootView.findViewById(R.id.secondBoxImage);
        ImageView thirdContactImage = (ImageView) rootView.findViewById(R.id.thirdBoxImage);
        ImageView fourthContactImage = (ImageView) rootView.findViewById(R.id.fourthBoxImage);

        //Buttons
        Button firstContactButton = (Button) rootView.findViewById(R.id.firstBoxButton);
        Button secondContactButton = (Button) rootView.findViewById(R.id.secondBoxButton);
        Button thirdContactButton = (Button) rootView.findViewById(R.id.thirdBoxButton);
        Button fourthContactButton = (Button) rootView.findViewById(R.id.fourthBoxButton);
        Button nextButton = (Button) rootView.findViewById(R.id.nextButton);
        Button backButton = (Button) rootView.findViewById(R.id.backButton);

        //Setting up base contacts being viewed //TODO change according to recieved data once implmented
        firstContactName.setText("User 1");
        secondContactName.setText("User 2");
        thirdContactName.setText("User 3");
        fourthContactName.setText("User 4");

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPageStepper++;
                firstContactName.setText("User "+ (1 +(userPageStepper * 4)));
                secondContactName.setText("User "+ (2 +(userPageStepper * 4)));
                thirdContactName.setText("User "+ (3 +(userPageStepper * 4)));
                fourthContactName.setText("User "+ (4 +(userPageStepper * 4)));
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userPageStepper >= 1) {
                    userPageStepper--;
                    firstContactName.setText("User "+ (1 +(userPageStepper * 4)));
                    secondContactName.setText("User "+ (2 +(userPageStepper * 4)));
                    thirdContactName.setText("User "+ (3 +(userPageStepper * 4)));
                    fourthContactName.setText("User "+ (4 +(userPageStepper * 4)));

                }

            }
        });

        firstContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userId = 1 + (userPageStepper * 4);
                composeMessage.RecipientOfMessage(userId);//page stepper is to change usersId even without backend
                Intent intent = new Intent(getActivity(), ComposeMessage.class);
                startActivity(intent);
            }
        });

        secondContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userId = 2 + (userPageStepper * 4);
                composeMessage.RecipientOfMessage(userId);//page stepper is to change usersId even without backend
                Intent intent = new Intent(getActivity(), ComposeMessage.class);
                startActivity(intent);
            }
        });

        thirdContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userId = 3 + (userPageStepper * 4);
                composeMessage.RecipientOfMessage(userId);//page stepper is to change usersId even without backend
                Intent intent = new Intent(getActivity(), ComposeMessage.class);
                startActivity(intent);
            }
        });

        fourthContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userId = 4 + (userPageStepper * 4);
                composeMessage.RecipientOfMessage(userId);//page stepper is to change usersId even without backend
                Intent intent = new Intent(getActivity(), ComposeMessage.class);
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }
}