package com.example.gamecardinal.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.gamecardinal.HubActivity;
import com.example.gamecardinal.R;
import com.example.gamecardinal.presenter.SevenUpPresenter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gamecardinal.presenter.RegistrationPresenter;
import com.example.gamecardinal.view.IRegistrationView;
import com.example.gamecardinal.view.ISevenUpView;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Fragment that displays the game seven up seven down.
 *
 * @author Eric Schwartz
 * @version 1.0
 */
public class SevenUpSevenDownFragment extends Fragment implements ISevenUpView {

    private SevenUpPresenter presenter;
    private String url;
    private boolean winTorLoseF;
    private int betAmt, numGuessed, numActual;

    public SevenUpSevenDownFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment SevenUpSevenDownFragment.
     */
    public static SevenUpSevenDownFragment newInstance() {
        SevenUpSevenDownFragment fragment = new SevenUpSevenDownFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    /**
     * gives functionality to the game of seven up seven down
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ImageView imageView = (ImageView) getView().findViewById(R.id.imageView4);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //for json and hash mapping
        presenter = new SevenUpPresenter(this, getActivity().getApplicationContext());
        url = getString(R.string.server_path) + "/susd";

        //views used
        View rootView = inflater.inflate(R.layout.fragment_seven_up_seven_down, container, false);
        View dieRolling = (View) rootView.findViewById(R.id.ThrowDiceLayout);
        View bettingAndReveal = (View) rootView.findViewById(R.id.ResultConstraintLayout);

        /**
         * Rolling die screen objects
         */
        //No matter the visibily when testing it corrects intself
        dieRolling.setVisibility(View.VISIBLE);
        bettingAndReveal.setVisibility(View.GONE);

        ImageView dieLeft = (ImageView) rootView.findViewById(R.id.leftImage);
        ImageView dieRight = (ImageView) rootView.findViewById(R.id.rightImage);

        Button rollButton = (Button) rootView.findViewById(R.id.RollButton);
        Button betButton = (Button) rootView.findViewById(R.id.BetButton);

        final double rightDieResult = Math.random() * 7;
        final double leftDieResult = Math.random() * 7;
        double dieTotal = rightDieResult + leftDieResult;
        numActual = (int) dieTotal;

        /**
         * betting screen objects
         */
        ImageView dieLeftBet = (ImageView) rootView.findViewById(R.id.leftImageBetScreen);
        ImageView dieRightBet = (ImageView) rootView.findViewById(R.id.rightImageBetScreen);
        TextView choiceChosen = (TextView) rootView.findViewById(R.id.ChoiceChosen);
        TextView resultFinal = (TextView) rootView.findViewById(R.id.ResultFinal);

        Button finalChoiceButton = (Button) rootView.findViewById(R.id.FinalChoiceButton);
        finalChoiceButton.setVisibility(View.GONE);
        Button underSevenButton = (Button) rootView.findViewById(R.id.underSevenButton);
        Button sevenButton = (Button) rootView.findViewById(R.id.sevenButton);
        Button overSevenButton = (Button) rootView.findViewById(R.id.overSevenButton);

        int updatesScore = 0;

        /**
         * Rolling the die button listener.
         */
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animates left die and replace image
                dieLeft.animate()
                        .setDuration(500)
                        .rotationX(360f)
                        .translationY(-500f);
                dieLeft.setImageResource(R.drawable.questionmark);

                //animates right die and replace image
                dieRight.animate()
                        .setDuration(500)
                        .rotationX(360f)
                        .translationY(-500f);
                dieRight.setImageResource(R.drawable.questionmark);

                rollButton.setVisibility(View.GONE);
                betButton.setVisibility(View.VISIBLE);

            }
        });

        /**
         * This is the start of the betting screen of the game seven up seven down
         */
        //takes users to the betting view of this fragment
        betButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dieRolling.setVisibility(View.GONE);
                bettingAndReveal.setVisibility(View.VISIBLE);
            }
        });

        /**
         * Actions of the buttons on the betting view on this fragment
         */
        //Under Seven Bet
        underSevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceChosen.setText("Under Seven");
                numGuessed = 6;
                finalChoiceButton.setVisibility(View.VISIBLE);
            }
        });

        //Straight Seven Bet
        sevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceChosen.setText("Seven");
                numGuessed = 7;
                finalChoiceButton.setVisibility(View.VISIBLE);
            }
        });

        //Over Seven Bet
        overSevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceChosen.setText("Over Seven");
                numGuessed = 8;
                finalChoiceButton.setVisibility(View.VISIBLE);
            }
        });

        //final betting results given.
        finalChoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch ((int) leftDieResult)
                {
                    case 1:
                        dieLeftBet.setImageResource(R.drawable.dice1);
                        break;
                    case 2:
                        dieLeftBet.setImageResource(R.drawable.dice2);
                        break;
                    case 3:
                        dieLeftBet.setImageResource(R.drawable.dice3);
                        break;
                    case 4:
                        dieLeftBet.setImageResource(R.drawable.dice4);
                        break;
                    case 5:
                        dieLeftBet.setImageResource(R.drawable.dice5);
                        break;
                    case 6:
                        dieLeftBet.setImageResource(R.drawable.dice);
                        break;
                }

                switch ((int) rightDieResult)
                {
                    case 1:
                        dieRightBet.setImageResource(R.drawable.dice1);
                        break;
                    case 2:
                        dieRightBet.setImageResource(R.drawable.dice2);
                        break;
                    case 3:
                        dieRightBet.setImageResource(R.drawable.dice3);
                        break;
                    case 4:
                        dieRightBet.setImageResource(R.drawable.dice4);
                        break;
                    case 5:
                        dieRightBet.setImageResource(R.drawable.dice5);
                        break;
                    case 6:
                        dieRightBet.setImageResource(R.drawable.dice);
                        break;
                }

                //reads chosen bet and sees if you have won or lost
                if (((int)dieTotal < 7 && choiceChosen.getText().equals("Under Seven")) ||
                        ((int)dieTotal == 7 && choiceChosen.getText().equals("Seven")) ||
                        ((int)dieTotal > 7 && choiceChosen.getText().equals("Over Seven")))
                {
                    resultFinal.setText("You Win");
                    winTorLoseF = true;
                    sendGameInfo();
                }
                else
                {
                    resultFinal.setText("You Lose");
                    winTorLoseF = false;
                    sendGameInfo();
                }
            }
        });

        //Used for updating leaderboard
        if (resultFinal.getText().equals("You Win"))
        {
            updatesScore = 1;
        }
        else if (resultFinal.getText().equals("You Lose"))
        {
            updatesScore = -1;
        }

        // Inflate the layout for this fragment
        return rootView;

    }

    /**
     * Send game information when called, used to send information once all data in aquired
     */
    public void sendGameInfo()
    {
        /**
         * Sending information to the back end
         */
        HashMap<String, String> GameInfo = new HashMap<>();
        GameInfo.put("gameType", "SUSD");
        GameInfo.put("player1", "dummy");
        GameInfo.put("betAmt", "999");
        GameInfo.put("isWinner", String.valueOf(winTorLoseF));
        GameInfo.put("numGuessed", String.valueOf(numGuessed));
        GameInfo.put("numActual", String.valueOf(numActual));


        JSONObject request = new JSONObject(GameInfo);

        presenter.loadRequest(url, request);
    }

    @Override
    public void displayErrorToast() {
        Context context = getActivity().getApplicationContext();
        int toastLength = Toast.LENGTH_SHORT;
        Toast toast =  Toast.makeText(context, R.string.susd_error_occurred, toastLength);
        toast.show();
    }

//    @Override
//    public void changeToHubView() {
//        startActivity(new Intent(this.getActivity().getApplicationContext(), HubActivity.class));
//    }
}
