package com.example.gamecardinal.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gamecardinal.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecentMsgsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecentMsgsFragment extends Fragment {

    private static final String ARG_USER_ID = "userID";

    private int mUserID;

    public RecentMsgsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param userID User ID of the current user; used to
     *               retrieve their messages.
     * @return A new instance of fragment RecentMsgsFragment.
     */
    public static RecentMsgsFragment newInstance(int userID) {
        RecentMsgsFragment fragment = new RecentMsgsFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recent_msgs, container, false);
    }
}