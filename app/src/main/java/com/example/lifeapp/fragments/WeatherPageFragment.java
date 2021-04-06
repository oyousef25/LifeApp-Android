package com.example.lifeapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lifeapp.R;
import com.example.lifeapp.adapters.WeatherAdapter;
import com.example.lifeapp.databases.WeatherDB;
import com.example.lifeapp.pojo.Weather;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * @author Omar Yousef
 * @version 1.0
 * @date March 19th 2021
 *
 * WeatherPage fragment:
 * This fragment will allow the user to check any city's weather by retrieving their input from the search bar
 */
public class WeatherPageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WeatherPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeatherPage.
     */
    // TODO: Rename and change types and number of parameters
    public static WeatherPageFragment newInstance(String param1, String param2) {
        WeatherPageFragment fragment = new WeatherPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_page, container, false);

        /*
            Floating action button navigating to the form
         */
        //Locating our fab button
        FloatingActionButton fab = view.findViewById(R.id.fab);
        //Firing an action when the user clicks on the fab button
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Navigating to our create form
                Navigation.findNavController(view)
                        .navigate(R.id.action_nav_weather_to_createWeatherFragment);
            }
        });


        /*
            Reading all weather data from the db
         */
        //Creating a new connection with our db
        WeatherDB db = new WeatherDB(getContext());

        //Reading all weather data
        ArrayList<Weather> weathers = db.getAllWeathers();

        //closing connection with the db
        db.close();

        /*
            Weather Recyclerview
         */
        //Locating our recyclerview
        RecyclerView weatherRecycler = view.findViewById(R.id.weatherList);

        //creating a new adapter instance
        WeatherAdapter adapter= new WeatherAdapter(weathers, getContext());

        //setting the adapter
        weatherRecycler.setAdapter(adapter);

        //setting the layout manager
        weatherRecycler.setLayoutManager(new LinearLayoutManager(getContext()));


        //return view
        return view;
    }
}