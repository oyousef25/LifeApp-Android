package com.example.lifeapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeapp.R;
import com.example.lifeapp.pojo.CategoryItem;
import com.example.lifeapp.pojo.ContactItem;

import java.net.URI;
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
    //An Activity to pass the intents bundles
    private Activity activity;
    //And ArrayList to hold all contact items
    private ArrayList<ContactItem> contactItems;
    //The current state/context of the application
    private Context context;

    /**
     * @author Omar Yousef
     * @date April 13th 2021
     * @version 1.0
     *
     * ContactsAdapter() class constructor
     * it allows us to create a new instance of the class
     * @param activity
     * @param contactItems
     * @param context
     */
    public ContactsAdapter(Activity activity, ArrayList<ContactItem> contactItems, Context context) {
        this.activity = activity;
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
    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
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

            /*
                Click events
            */
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //When the user clicks on the item we would like to launch a new intent depending on its position
            if (this.getLayoutPosition() == 0){
                //email intent
                String[] emailAddress = {"mailto:w0753671@myscc.ca"};
                String[] ccEmail = {"techsupport@omarYousef.com"};
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));

                //Adding extra email, cc, subject and email body
                intent.putExtra(Intent.EXTRA_EMAIL, emailAddress);
                intent.putExtra(Intent.EXTRA_CC, ccEmail);
                intent.putExtra(Intent.EXTRA_SUBJECT, "I'm Looking to contact LifeApp':");
                intent.putExtra(Intent.EXTRA_TEXT, "I like using LifeApp it made my life easier");

                //Making sure our data is not empty before starting the activity
                if(intent.resolveActivity(activity.getPackageManager()) != null){
                    activity.startActivity(intent);
                }else{
                    //In case there was an error
                    Toast.makeText(context, "No app installed", Toast.LENGTH_SHORT).show();
                }

            }else if (this.getLayoutPosition() == 1){
                //phone intent
                Uri number = Uri.parse("tel:+12997765743");
                Intent intent = new Intent(Intent.ACTION_DIAL, number);
                //Making sure our data is not empty before starting the activity
                if (intent.resolveActivity(activity.getPackageManager()) != null){
                    activity.startActivity(intent);
                }else{
                    //In case there was an error
                    Toast.makeText(context, "No app installed", Toast.LENGTH_SHORT).show();
                }

            }else if (this.getLayoutPosition() == 2){
                //Location
                Uri location = Uri.parse("geo:22.27307,-63.0512688&q=business");
                //Creating an intent and pass the Location values
                Intent intent = new Intent(Intent.ACTION_VIEW);
                //Making sure our data is not empty before starting the activity
                if (intent.resolveActivity(activity.getPackageManager()) != null){
                    activity.startActivity(intent);
                }else{
                    //In case there was an error
                    Toast.makeText(context, "No app installed", Toast.LENGTH_SHORT).show();
                }

            }else if (this.getLayoutPosition() == 3){
                //facebook(web intent)
                //Our facebook page
                Uri website = Uri.parse("https://www.facebook.com/LifeApp-Club-100891118797112");
                //Creating an intent and pass the website URL
                Intent intent = new Intent(Intent.ACTION_VIEW, website);
                //Making sure our data is not empty before starting the activity
                if (intent.resolveActivity(activity.getPackageManager()) != null){
                    activity.startActivity(intent);
                }else{
                    //In case there was an error
                    Toast.makeText(context, "No app installed", Toast.LENGTH_SHORT).show();
                }

            }else if (this.getLayoutPosition() == 4){
                //Take them to the credits fragment
                Navigation.findNavController(view)
                        .navigate(R.id.action_nav_account_to_creditsFragment);
            }
        }
    }
}
