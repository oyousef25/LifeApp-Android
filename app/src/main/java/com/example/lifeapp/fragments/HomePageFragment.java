package com.example.lifeapp.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lifeapp.R;
import com.example.lifeapp.adapters.CustomViewpager2Adapter;
import com.example.lifeapp.adapters.HomePageAdapter;
import com.example.lifeapp.pojo.CategoryItem;

import java.util.ArrayList;

/**
 * @author Omar Yousef
 * @version 1.0
 * @date March 19th 2021
 *
 * HomePage fragment
 * This fragment will be the home page. The home page will have the following technologies:
 * a ViewPager2
 * a RecyclerView(Exercises, recipes, and locations)
 */
public class HomePageFragment extends Fragment {

    //Creating a viewpager2 variable to connect with our XML file viewpager
    ViewPager2 homePager;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private Integer mParam1;

    public HomePageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment HomePage.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePageFragment newInstance(Integer param1) {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        //Populating the viewpager
        homePager = view.findViewById(R.id.homeViewpager);
        //Setting our adapter
        homePager.setAdapter(new CustomViewpager2Adapter(getActivity()));

        //setting the viewpager's animations


        //Locate the recyclerView
        RecyclerView recyclerView = view.findViewById(R.id.weatherList);

        //Create an arrayList
        ArrayList<CategoryItem> categoryItems = new ArrayList<>();

        //Add to the arrayList
        categoryItems.add(new CategoryItem(R.drawable.recipes, "Recipes"));
        categoryItems.add(new CategoryItem(R.drawable.exercises, "Exercises"));
        categoryItems.add(new CategoryItem(R.drawable.locations, "Locations"));


        //Set the Layout manager(Our XML layout)
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Set the Adapter
        recyclerView.setAdapter(new HomePageAdapter(categoryItems, getContext()));

        //Returning the view
        return view;
    }


    /**
     * @author Omar Yousef
     * @version 1.0
     * @date March 20th 2021
     *
     * ZoomOutPage transformer created by android:
     * It's a zoom out animation class helps us with adding animations to our viewpager2
     */

    /**
     * @author Omar Yousef
     * @version 1.0
     * @date March 20th 2021
     *
     * DepthPageTransformer transformer created by android:
     * It's a Depth Page Transformer animation class helps us with adding animations to our viewpager2
     */
}