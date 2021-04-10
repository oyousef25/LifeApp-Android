package com.example.lifeapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lifeapp.R;
import com.example.lifeapp.adapters.ContactsAdapter;
import com.example.lifeapp.pojo.ContactItem;

import java.util.ArrayList;

/**
 * @author Omar Yousef
 * @version 1.0
 * @date March 19th 2021
 *
 * AccountPage Fragment:
 * This fragment class will have all the account page's functionality
 */
public class AccountPageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountPage.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountPageFragment newInstance(String param1, String param2) {
        AccountPageFragment fragment = new AccountPageFragment();
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
        View view = inflater.inflate(R.layout.fragment_account_page, container, false);

        //1. Locate the recyclerview
        RecyclerView contactList = view.findViewById(R.id.contactList);

        //2. create an arrayList of the object
        ArrayList<ContactItem> contactItems = new ArrayList<>();

        //3. add to the arrayList
        //Email
        contactItems.add(new ContactItem(R.drawable.email, "Our Email!", "https://www.facebook.com/"));
        //Phone Number
        contactItems.add(new ContactItem(R.drawable.phone, "Call Us!", "https://www.facebook.com/"));
        //Location
        contactItems.add(new ContactItem(R.drawable.our_location, "Our Location!", "https://www.facebook.com/"));
        //Facebook page
        contactItems.add(new ContactItem(R.drawable.facebook, "Follow our FaceBook page!", "https://www.facebook.com/"));

        //4. set the recyclerview adapter
        contactList.setAdapter(new ContactsAdapter(contactItems, getContext()));

        //5. Set the recyclerview layout manager
        contactList.setLayoutManager(new LinearLayoutManager(getContext()));

        //Returning view
        return view;
    }
}