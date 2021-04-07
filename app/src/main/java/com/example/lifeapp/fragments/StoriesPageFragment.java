package com.example.lifeapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lifeapp.R;
import com.example.lifeapp.adapters.StoriesAdapter;
import com.example.lifeapp.databases.StoriesDB;
import com.example.lifeapp.pojo.Story;

import java.util.ArrayList;

/**
 * @author Omar Yousef
 * @version 1.0
 * @date March 19th 2021
 *
 * StoriesPage fragment:
 * This fragment will hold all different stories that have been created by the user. The user will have the ability
 * to update, create and delete a story.
 */
public class StoriesPageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StoriesPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StoriesPage.
     */
    // TODO: Rename and change types and number of parameters
    public static StoriesPageFragment newInstance(String param1, String param2) {
        StoriesPageFragment fragment = new StoriesPageFragment();
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
        View view = inflater.inflate(R.layout.fragment_stories_page, container, false);

        /*
            Reading the Stories data from the StoriesDB
         */
        //Creating a new readable connection with the StoriesDB
        StoriesDB db = new StoriesDB(getContext());

        //Reading all stories data and storing them in an arrayList
        ArrayList<Story> storyArrayList = db.getAllStories();

        //Closing the db connection
        db.close();


        /*
            Stories Recyclerview
         */
        //Locating our recyclerview
        RecyclerView recyclerView = view.findViewById(R.id.storiesList);

        //Creating a new adapter instance
        StoriesAdapter adapter = new StoriesAdapter(storyArrayList, getContext());

        //Setting our adapter
        recyclerView.setAdapter(adapter);

        //Setting our Layout Manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Returning our view
        return view;
    }
}