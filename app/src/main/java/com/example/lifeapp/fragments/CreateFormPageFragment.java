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
    Story story;
    public static final int UPDATE = 1;
    public static final int CREATE = 2;

    public static final String STORY = "STORY";
    public static final String ACTION_TYPE = "action_type";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_form_page, container, false);

        //Locating our XML elements(EditTexts and submit button).
        EditText titleEditText = view.findViewById(R.id.titleEditText);
        EditText descriptionEditText = view.findViewById(R.id.descriptionEditText);
        Button submit = view.findViewById(R.id.submitStoryButton);

        //If there is no arguments(User got to the form using the bottom nav)
        if (getArguments() == null){
            //Create a new bundle
            Bundle extra = new Bundle();
            //And set the default action type to CREATE
            extra.putInt(ACTION_TYPE, CREATE);
            //And Adding it to our arguments
            setArguments(extra);
        }

        //If we have information passed
        if (getArguments() != null){
            //And these arguments are UPDATE
            if (getArguments().getInt(ACTION_TYPE) == UPDATE){
                //Getting our parcelable data
                story = getArguments().getParcelable(STORY);

                //Seeting our button's text to Update
                submit.setText("Update Story!");
                //If the parcelable object is not empty
                if (story != null){
                    //Set the edit texts to the old values so the user can change them
                    titleEditText.setText(story.getTitle());
                    descriptionEditText.setText(story.getDescription());
                }
            }else{
                //Otherwise(If its not UPDATE) create a new story
                story = new Story();
                //And set the button's text to CREATE
                submit.setText("Create Story!");
            }

            //Creating a new Story record when the user clicks on the submit buttons
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Creating a new Story object
//                    Story story = new Story();

                    //Setting story title to the user's input
                    story.setTitle(titleEditText.getText().toString());

                    //Setting the story description to the user's input
                    story.setDescription(descriptionEditText.getText().toString());

                    //Creating a new db instance
                    StoriesDB db = new StoriesDB(getContext());

                    if (getArguments().getInt(ACTION_TYPE) == UPDATE){
                        db.updateStory(story);
                    }else if (getArguments().getInt(ACTION_TYPE) == CREATE){
                        //Adding the new story object to our storiesdb
                        db.addStory(story);
                    }

                    //closing connection with the db
                    db.close();

                    //Navigating back to the stories page
                    Navigation.findNavController(view).popBackStack();
                }
            });
        }
        //Return view
        return view;
    }
}