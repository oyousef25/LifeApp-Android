package com.example.lifeapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.lifeapp.fragments.CreditsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    //Creating a new shared preferences object so we can use it to retrieve the current settings
    SharedPreferences sharedPreferences;

    //Locating out container to change its background
    ConstraintLayout constraintLayout;

    //Creating a textView for our categoryTitle
    TextView categoryTitle;

    @Override
    protected void onResume() {
        super.onResume();

        /*
            Shared Preferences (Settings menu)
         */
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        constraintLayout = findViewById(R.id.container);
        if (sharedPreferences.getBoolean("background_color", false)){
            constraintLayout.setBackgroundColor(Color.WHITE);
        }else{
            constraintLayout.setBackgroundColor(getResources().getColor(R.color.color_secondary_variant));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_stories, R.id.nav_form, R.id.nav_weather, R.id.nav_account)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}