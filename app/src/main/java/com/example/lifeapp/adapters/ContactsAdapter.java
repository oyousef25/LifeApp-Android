package com.example.lifeapp.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeapp.R;
import com.example.lifeapp.pojo.CategoryItem;
import com.example.lifeapp.pojo.ContactItem;

import java.util.ArrayList;

/**
 * @author Omar Yousef
 * @version 1.0
 * @date March 19th 2021
 *
 * ContactsAdapter class:
 * This class will be the adapter for the contacts recyclerView.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.CustomViewHolder>{
    /*
        Properties
     */
    private ArrayList<ContactItem> contactItems;
    private Context context;

    public ContactsAdapter(ArrayList<ContactItem> contactItems, Context context) {
        this.contactItems = contactItems;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Creating a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_account_recyclerview, parent, false);

        //passing our view to the CustomViewHolder
        return new ContactsAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        //Create a new contact object
        ContactItem contactItem = contactItems.get(position);

        //Setting their values to the arraylist item's value
        holder.image.setImageResource(contactItem.getImage());
        holder.contactTitle.setText(contactItem.getTitle());
    }

    @Override
    public int getItemCount() {
        //Returning the count of the arrayList's length
        if (contactItems != null){
            return contactItems.size();
        }else{
            return 0;
        }
    }

    /**
     * @author Omar Yousef
     * @version 1.0
     * @date April 10th 2021
     *
     * CustomViewHolder()
     * CustomViewHolder class that we are going to use in our adapter
     */
    class CustomViewHolder extends RecyclerView.ViewHolder{
        /*
            Properties
         */
        protected ImageView image;
        protected TextView contactTitle;

        /**
         * @author Omar Yousef
         * @version 1.0
         * @date April 10th 2021
         * @param itemView
         *
         * The CustomViewHolder constructor
         */
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            //Locating our ui elements in the Layout.xml file and connecting them with our properties
            this.image = itemView.findViewById(R.id.contactImage);
            this.contactTitle = itemView.findViewById(R.id.contactTitle);
        }
    }
}
