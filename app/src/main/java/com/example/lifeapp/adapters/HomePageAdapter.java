package com.example.lifeapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeapp.R;
import com.example.lifeapp.pojo.CategoryItem;

import java.util.ArrayList;

/**
 * @author Omar Yousef
 * @version 1.0
 * @date March 19th 2021
 *
 * HomePageAdapter class:
 * This class will be the adapter for the home page's recyclerView.
 */
public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.CustomViewHolder>{

    //Properties
    private ArrayList<CategoryItem> categoryItems;
    private Context context;

    /**
     * HomePageAdapter constructor
     * @param categoryItems
     * @param context
     */
    public HomePageAdapter(ArrayList<CategoryItem> categoryItems, Context context) {
        this.categoryItems = categoryItems;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Creating a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_home_recyclerview, parent, false);

        //Returning our view
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        //Creating a new category item using the our categoryItems arrayList
        CategoryItem categoryItem = categoryItems.get(position);

        //Retrieving the properties data using the position and assigning them to our object's properties
        holder.image.setImageResource(categoryItem.getImage());
        holder.title.setText(categoryItem.getTitle());

    }

    @Override
    public int getItemCount() {
        //Returning the count of the arrayList's length
        if (categoryItems != null){
            return categoryItems.size();
        }else{
            return 0;
        }
    }



    /**
     * @author Omar Yousef
     * @version 1.0
     * @date April 1st 2021
     *
     * CustomViewHolder()
     * CustomViewHolder class that we are going to use in our adapter
     */
    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //Properties
        protected ImageView image;
        protected TextView title;

        /**
         * @author Omar Yousef
         * @version 1.0
         * @date April 1st 2021
         * @param itemView
         *
         * The CustomViewHolder constructor
         */
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            //Connecting our properties with the UI elements in our XML layout file
            this.image = itemView.findViewById(R.id.homeImage);
            this.title = itemView.findViewById(R.id.homeTitle);

            //Click event: Take the user to the itemsFragment when they click on a homeRecycler item
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Navigation.findNavController(view).navigate(R.id.action_nav_home_to_itemsFragment);
        }
    }
}
