package com.example.lifeapp.fragments;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lifeapp.R;
import com.example.lifeapp.adapters.OthersAdapter;
import com.example.lifeapp.pojo.Item;

import java.util.ArrayList;

/**
 * @author Omar Yousef
 * @date April 13th 2021
 * @version 1.0
 *
 * ItemsFragment:
 * This fragment will host the category items lists(Recipes, exercises, and locations)
 */
public class ItemsFragment extends Fragment {
    public static ArrayList<Item> itemArrayList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ItemsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemsFragment newInstance(String param1, String param2) {
        ItemsFragment fragment = new ItemsFragment();
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
        View view = inflater.inflate(R.layout.fragment_items, container, false);

        //Locating our recyclerview
        RecyclerView recyclerView = view.findViewById(R.id.itemsList);

        //Creating a new OtherAdapter instance
        OthersAdapter adapter = new OthersAdapter(itemArrayList, getContext());

        //setting the adapter
        recyclerView.setAdapter(adapter);

        //setting the layoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}