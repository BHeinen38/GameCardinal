package com.example.gamecardinal.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gamecardinal.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TicTacToeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TicTacToeFragment extends Fragment {

    // The fragment initialization parameters containing player identification.
    private static final String ARG_PLAYER1 = "param1";
    private static final String ARG_PLAYER2 = "param2";

    private String mPlayerOneID;
    private String mPlayerTwoID;

    public TicTacToeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param playerOneID ID of Player One. Potentially utilized in
     *                    API calls to check game state.
     * @param playerTwoID ID of Player Two. Potentially utilized in
     *                    API calls to check game state.
     * @return A new instance of fragment TicTacToeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TicTacToeFragment newInstance(String playerOneID, String playerTwoID) {
        TicTacToeFragment fragment = new TicTacToeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PLAYER1, playerOneID);
        args.putString(ARG_PLAYER2, playerTwoID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPlayerOneID = getArguments().getString(ARG_PLAYER1);
            mPlayerTwoID = getArguments().getString(ARG_PLAYER2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);
    }
}