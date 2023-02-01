package com.example.gamecardinal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gamecardinal.fragments.ContactListFragment;
import com.example.gamecardinal.fragments.SettingsFragment;
import com.example.gamecardinal.fragments.SevenUpSevenDownFragment;

/**
 * Activity that allows fragments to be shown.
 *
 * @author Eric Schwartz
 * @version 1.0
 */
public class FragmentTesterActivity extends AppCompatActivity {

    /**
     * Displays fragment in frame layout
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_tester);

        //Fragment fragment = new SevenUpSevenDownFragment();
        //Fragment fragment = new SettingsFragment();
        Fragment fragment = new ContactListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.FragmentContainer, fragment);
        transaction.commit();

    }

}
