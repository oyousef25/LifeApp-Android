package com.example.lifeapp.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeapp.R;
import com.example.lifeapp.fragments.ItemsFragment;
import com.example.lifeapp.pojo.CategoryItem;
import com.example.lifeapp.pojo.Item;

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

    /*
        Properties
     */
    private ArrayList<CategoryItem> categoryItems;
    private Context context;

    /*
        different categories arrayLists
     */
    static ArrayList<Item> recipesList;
    static ArrayList<Item> exercisesList;
    static ArrayList<Item> locationsList;

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

            /*
                ArrayLists
             */
            //Adding to the recipesList
            recipesList = new ArrayList<>();
            recipesList.add(new Item(R.drawable.pizza, "Pizza", "first make the dough then add tomato sauce then add cheese with pineapple"));
            recipesList.add(new Item(R.drawable.hummus, "Hummus", "Blend chickpeas with garlic, tahini sauce and lemon"));
            recipesList.add(new Item(R.drawable.curry, "Curry", "Add the curry spice mix with your choice of meat"));
            recipesList.add(new Item(R.drawable.nachos, "Nachos" ,"Add doritos chips with your choice of veggies and meat and then add nacho cheese sauce with sour cream"));
            recipesList.add(new Item(R.drawable.icecap, "Healthy Ice Capp", "Add milk with sugar and keep mixing and then add your coffee with ice"));
            recipesList.add(new Item(R.drawable.grilled_chicken, "Grilled Chicken", ""));
            recipesList.add(new Item(R.drawable.wings, "Healthy Wings", "add chicken, butter and salt nd pepper in a pan and put them in the oven on 360 degrees for 1 hour."));

            //Adding to the exercisesList
            exercisesList = new ArrayList<>();
            exercisesList.add(new Item(R.drawable.pushups, "Push ups", "one of the most basic, yet effective, body weight moves you can perform because of the number of muscles that are recruited to perform them."));
            exercisesList.add(new Item(R.drawable.running, "Running", "running is one of the most straightforward ways to get the important benefits of exercise"));
            exercisesList.add(new Item(R.drawable.squat, "Squat", "Squats increase lower body and core strength, as well as flexibility in your lower back and hips."));
            exercisesList.add(new Item(R.drawable.plank, "Plank", "A healthy body requires a strong core at its foundation, so don’t neglect core-specific moves like the plank."));
            exercisesList.add(new Item(R.drawable.pullups, "Pull-ups", "pull ups Strengthen the back muscles, Strengthen the arm and shoulder muscles and Improve grip strength"));
            exercisesList.add(new Item(R.drawable.aerobics, "Aerobics", "Aerobics Improves cardiovascular conditioning, Decreases risk of heart disease and Lowers blood pressure"));
            exercisesList.add(new Item(R.drawable.lunge, "Lunge", "Challenging your balance is an essential part of a well-rounded exercise routine."));

            //Adding to the locationsList
            locationsList = new ArrayList<>();
            locationsList.add(new Item(R.drawable.point_pelee, "Point Pelee", "A beautiful park located in Leamington ontario that has beautiful lakes and trails."));
            locationsList.add(new Item(R.drawable.devonwood, "Devonwood Forest", "Devonwood is one of the largest forests in Windsor ontario that has a wonderful wildlife"));
            locationsList.add(new Item(R.drawable.malden_park, "Malden Park", "A wonderful park for photographers, bikers and hikers"));
            locationsList.add(new Item(R.drawable.little_river, "Little River Corridor", "An amazing trail for bikers or people who are looking for a hiking place."));
            locationsList.add(new Item(R.drawable.riverfront, "Riverfront Trail", "A beautiful trail that goes all the way from west windsor to east windsor"));
            locationsList.add(new Item(R.drawable.garden, "Spring Garden", "Spring Garden Loop is a 2.4 kilometer lightly trafficked loop trail located near Windsor, Ontario, Canada."));
            locationsList.add(new Item(R.drawable.blue_heron, "Blue Heron", "Blue Heron Loop is a 3.2 kilometer lightly trafficked loop trail located near Windsor, Ontario, Canada."));

            if (this.getLayoutPosition() == 0) {
                //Make the itemArrayList equal to our recipesList
                ItemsFragment.itemArrayList = recipesList;
            } else if (this.getLayoutPosition() == 1) {
                //Make the itemArrayList equal to our exercisesList
                ItemsFragment.itemArrayList = exercisesList;

            }else if (this.getLayoutPosition() == 2) {
                //Make the itemArrayList equal to our locationsList
                ItemsFragment.itemArrayList = locationsList;
            }
        }
    }
}
