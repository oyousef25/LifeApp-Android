package com.example.lifeapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Omar Yousef
 * @date April 13th 2021
 * @version 1.0
 *
 * SplashScreen activity:
 * Displaying a intro screen with the logo while the user is waiting for the app to load
 */
public class SplashScreen extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Sending the user to the main activity
        startActivity(new Intent(this, MainActivity.class));
    }
}
