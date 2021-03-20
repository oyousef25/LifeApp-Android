package com.example.lifeapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lifeapp.R;

/**
 * @author Omar Yousef
 * @version 1.0
 * @date March 19th 2021
 *
 * HomeViewpagerFragment Fragment:
 *
 */
public class HomeViewpagerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private Integer mParam1;

    public HomeViewpagerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment HomeViewpagerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeViewpagerFragment newInstance(Integer param1) {
        HomeViewpagerFragment fragment = new HomeViewpagerFragment();
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
        View view = inflater.inflate(R.layout.fragment_home_viewpager, container, false);

        if (mParam1 != null){
            //Locating our pager image and storing it in a variable
            ImageView pagerImage = view.findViewById(R.id.pagerImage);

            //setting the pager image to the passed image
            pagerImage.setImageResource(mParam1);
        }

        return view;
    }
}