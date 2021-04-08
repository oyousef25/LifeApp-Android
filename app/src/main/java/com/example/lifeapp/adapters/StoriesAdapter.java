package com.example.lifeapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeapp.R;
import com.example.lifeapp.databases.StoriesDB;
import com.example.lifeapp.databases.WeatherDB;
import com.example.lifeapp.pojo.Story;

import java.util.ArrayList;

/**
 * @author Omar Yousef
 * @version 1.0
 * @date March 19th 2021
 *
 * StoriesAdapter class:
 * This will be the adapter for the stories recyclerview
 */
public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.CustomViewHolder>{
    //Properties
    private ArrayList<Story> storyArrayList;
    private Context context;

    /**
     * @author Omar Yousef
     * @version 1.0
     * @date April 8th 2021
     *
     * A constructor to create a new Adapter constructor
     * @param storyArrayList
     * @param context
     */
    public StoriesAdapter(ArrayList<Story> storyArrayList, Context context) {
        this.storyArrayList = storyArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Creating a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_stories_recyclerview, parent, false);

        //Returning our view
        return new StoriesAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        //Creating a new Story object instance
        Story story = storyArrayList.get(position);

        //Setting the XML elements values to our object's values
        holder.title.setText(story.getTitle());
        holder.description.setText(story.getDescription());
    }

    @Override
    public int getItemCount() {
        //Returning the count of the arrayList's length
        if (storyArrayList != null){
            return storyArrayList.size();
        }else{
            return 0;
        }
    }

    /**
     * @author Omar Yousef
     * @version 1.0
     * @date April 8th 2021
     *
     * CustomViewHolder()
     * CustomViewHolder class that we are going to use in our adapter
     */
    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener{
        //Properties
        protected TextView title;
        protected TextView description;
        //Locating our arrow imageview
        protected ImageView arrow;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            /*
                Locating our XML elements
             */
            this.title = itemView.findViewById(R.id.storyTitle);
            this.description = itemView.findViewById(R.id.storyDescription);
            this.arrow = itemView.findViewById(R.id.recyclerArrow);

            //Setting the description visibility to gone automatically
            description.setVisibility(View.GONE);
            //Making the arrow point down automatically
            arrow.setImageResource(R.drawable.down);

            //Adding action when the user long clicks on an item(Delete record)
            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
        }


        /**
         * @author Omar Yousef
         * @version 1.0
         * @date April 8th 2021
         *
         * When the user long clicks on an item it deletes the record from the db
         * @param view
         * @return
         */
        @Override
        public boolean onLongClick(View view) {
            //Building a new alert popup dialogue box using the Builder pattern to make sure the user would like to delete the record
            new AlertDialog.Builder(context)
                    //Setting the dialogue title
                    .setTitle("Delete")
                    //Setting the dialogue message
                    .setMessage("Are you sure you want to delete " +
                            storyArrayList.get(getLayoutPosition()).getTitle() + "?")
                    //Setting icon
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    //setting the positive button
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //Creating a new db connection
                            StoriesDB db = new StoriesDB(context);

                            //Delete record from the stories database
                            db.deleteStory(storyArrayList.get(getLayoutPosition()).getId());

                            //Delete the record from the story ArrayList
                            storyArrayList.remove(getLayoutPosition());

                            //Notify the RecyclerView the item was removed
                            notifyItemRemoved(getAdapterPosition());

                            //Closing connection with the stories db
                            db.close();
                        }
                    })
                    //setting the negative button
                    .setNegativeButton("No", null)
                    //Showing the dialogue box that we built
                    .show();
            return false;
        }

        @Override
        public void onClick(View view) {
            description.setVisibility(
                    description.getVisibility()
                            == View.VISIBLE
                            ? View.GONE : View.VISIBLE);

            if (description.getVisibility() == View.VISIBLE){
                arrow.setImageResource(R.drawable.up);
            }else{
                arrow.setImageResource(R.drawable.down);
            }
        }
    }


}
