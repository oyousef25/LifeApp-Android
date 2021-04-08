package com.example.lifeapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.lifeapp.R;
import com.example.lifeapp.databases.StoriesDB;
import com.example.lifeapp.pojo.Story;

/**
 * @author Omar Yousef
 * @version 1.0
 * @date March 19th 2021
 *
 * CreateFormPage fragment:
 * This fragment will hold the create and update story form
 */
public class CreateFormPageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateFormPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateFormPage.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateFormPageFragment newInstance(String param1, String param2) {
        CreateFormPageFragment fragment = new CreateFormPageFragment();
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
        View view = inflater.inflate(R.layout.fragment_create_form_page, container, false);

        //Locating our XML elements(EditTexts and submit button).
        EditText titleEditText = view.findViewById(R.id.titleEditText);
        EditText descriptionEditText = view.findViewById(R.id.descriptionEditText);
        Button submit = view.findViewById(R.id.submitStoryButton);

        //Creating a new Story record when the user clicks on the submit buttons
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating a new Story object
                Story story = new Story();

                //Setting story title to the user's input
                story.setTitle(titleEditText.getText().toString());

                //Setting the story description to the user's input
                story.setDescription(descriptionEditText.getText().toString());

                //Creating a new db instance
                StoriesDB db = new StoriesDB(getContext());

                //Adding the new story object to our storiesdb
                db.addStory(story);

                //closing connection with the db
                db.close();

                //Navigating back to the stories page
                Navigation.findNavController(view).popBackStack();
            }
        });


        //Return view
        return view;
    }
}