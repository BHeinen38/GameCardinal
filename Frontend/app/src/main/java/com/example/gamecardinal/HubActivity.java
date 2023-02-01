package com.example.gamecardinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.gamecardinal.fragments.ContactListFragment;
import com.example.gamecardinal.fragments.LeaderboardFragment;
import com.example.gamecardinal.fragments.RecentMsgsFragment;
import com.example.gamecardinal.fragments.SettingsFragment;
import com.example.gamecardinal.fragments.UserProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Primary Activity that should handle the Contact List, User Profile,
 * Leaderboard, Settings, and Recent Messages Fragments.
 *
 * @author J. Anderson
 * @version 1.0
 */
public class HubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);

        // Fetch UID for fragment-related reasons.
        final SharedPreferences sharedPrefs = getSharedPreferences(getString(R.string.prefs_key), Context.MODE_PRIVATE);
        final int loggedOutUID = getResources().getInteger(R.integer.prefs_logged_out_uid);
        int userID = sharedPrefs.getInt(getString(R.string.prefs_curr_uid), loggedOutUID);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        final BottomNavigationView bottomNavigationView = findViewById(R.id.hub_bottomNavigation);

        // Creation of fragments.
        UserProfileFragment userProfileFragment = UserProfileFragment.newInstance(userID);
        RecentMsgsFragment recentMsgsFragment = RecentMsgsFragment.newInstance(userID);
        ContactListFragment contactListFragment = ContactListFragment.newInstance(userID);
        LeaderboardFragment leaderboardFragment = LeaderboardFragment.newInstance(userID);
        SettingsFragment settingsFragment = SettingsFragment.newInstance(userID);

        // Set item selection listener.
        bottomNavigationView.setOnItemSelectedListener( item -> {
            // Haha, apparently resource IDs are non-final so I can't use a switch statement here.
            // User profile transition.
            if (item.getItemId() == R.id.action_profile) {
                fragmentManager.beginTransaction()
                        .replace(R.id.hub_fragmentContainer, userProfileFragment)
                        .commit();
            // Recent messages transition.
            } else if (item.getItemId() == R.id.action_recent_messages) {
                fragmentManager.beginTransaction()
                        .replace(R.id.hub_fragmentContainer, recentMsgsFragment)
                        .setReorderingAllowed(true)
                        .commit();
            // Contacts transition.
            } else if (item.getItemId() == R.id.action_contacts) {
                fragmentManager.beginTransaction()
                        .replace(R.id.hub_fragmentContainer, contactListFragment)
                        .setReorderingAllowed(true)
                        .commit();
            // Leaderboard transition.
            } else if (item.getItemId() == R.id.action_leaderboard) {
                fragmentManager.beginTransaction()
                        .replace(R.id.hub_fragmentContainer, leaderboardFragment)
                        .setReorderingAllowed(true)
                        .commit();
            // Settings transition.
            } else if (item.getItemId() == R.id.action_settings) {
                fragmentManager.beginTransaction()
                        .replace(R.id.hub_fragmentContainer, settingsFragment)
                        .setReorderingAllowed(true)
                        .commit();
            }
            return true;
        });

        bottomNavigationView.setSelectedItemId(R.id.action_contacts);
    }
}