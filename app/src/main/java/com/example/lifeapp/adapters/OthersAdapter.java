package com.example.lifeapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeapp.R;
import com.example.lifeapp.pojo.Item;

import java.util.ArrayList;

/**
 * @author Omar Yousef
 * @version 1.0
 * @date March 19th 2021
 *
 * OthersAdapter class:
 * this will be the adapter for the other recyclerviews(Exercises, Recipes, and Locations).
 */
public class OthersAdapter extends RecyclerView.Adapter<OthersAdapter.CustomViewHolder>{
    /*
        Properties
     */
    private ArrayList<Item> itemArrayList;
    private Context context;

    /**
     * @author Omar Yousef
     * @version 1.0
     * @date April 11th
     *
     * OthersAdapter Constructor
     * This constructor will alow us to create a new instance of the Adapter class for out items recyclerview
     * @param itemArrayList
     * @param context
     */
    public OthersAdapter(ArrayList<Item> itemArrayList, Context context) {
        this.itemArrayList = itemArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Creating a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_recyclerview, parent, false);

        //Returning our view
        return new OthersAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        //Creating a new Item instance
        Item item = itemArrayList.get(position);

        //Setting the XML elements values to the object's properties values that are retrieved from the arrayList
        holder.image.setImageResource(item.getImage());
        holder.itemTitle.setText(item.getTitle()); ;
        holder.itemDescription.setText(item.getDescription()); ;
    }

    @Override
    public int getItemCount() {
        //If the arrayList is not empty
        if (itemArrayList != null){
            //return the length/size of the arrayList
            return itemArrayList.size();
        }else{
            //If the arrayList is empty return a zero
            return 0;
        }
    }

    /**
     * @author Omar Yousef
     * @version 1.0
     * @date April 11th 2021
     *
     * CustomViewHolder class.
     * 1.This class will be our adapter. the adapter class will extend this class to add functionality to the located xml elements
     * 2.This class will also handle all the recyclerview click events, LongClicks etc..
     */
    public class CustomViewHolder extends RecyclerView.ViewHolder{
        /*
            Properties
         */
        protected ImageView image;
        protected TextView itemTitle;
        protected TextView itemDescription;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            //Locating our xml elements and connecting them with our properties
            this.image = itemView.findViewById(R.id.itemImage);
            this.itemTitle = itemView.findViewById(R.id.itemTitle);
            this.itemDescription = itemView.findViewById(R.id.itemDescription);
        }
    }
}
