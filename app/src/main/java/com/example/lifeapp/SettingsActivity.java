package com.example.lifeapp;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Omar Yousef
 * @date April 13th 2021
 * @version 1.0
 *
 * SettingsActivity:
 * This class will take the user to the preferences page to pick their preferred settings
 */
public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new MySettingsFragment()).commit();
    }

    public static class MySettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            //Connecting the activity with the preferences xml file
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}
