package com.example.lifeapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeapp.R;
import com.example.lifeapp.pojo.CategoryItem;
import com.example.lifeapp.pojo.Weather;

import java.util.ArrayList;

/**
 * @author Omar Yousef
 * @version 1.0
 * @date April 1st 2021
 *
 * Weather adapter to help us create a weather recyclerview
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.CustomViewHolder>{
    //Properties
    private ArrayList<Weather> weatherArrayList;
    private Context context;

    /**
     * WeatherAdapter constructor to allow us to create a new instance of the class
     * @param weatherArrayList
     * @param context
     */
    public WeatherAdapter(ArrayList<Weather> weatherArrayList, Context context) {
        this.weatherArrayList = weatherArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Creating a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_home_recyclerview, null);

        //Returning our view
        return new WeatherAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        //Creating a new weather item using the our weatherArrayList
        Weather weatherItem = weatherArrayList.get(position);

        //Retrieving the properties data using the position and assigning them to our object's properties
        holder.cityName.setText(weatherItem.getCityName());
    }

    @Override
    public int getItemCount() {
        //Returning the count of the arrayList's length
        if (weatherArrayList != null){
            return weatherArrayList.size();
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
    class CustomViewHolder extends RecyclerView.ViewHolder{

        //Properties
        protected TextView cityName;
        protected TextView temp;

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

            this.cityName = itemView.findViewById(R.id.cityName);
            this.temp = itemView.findViewById(R.id.tempTextView);

        }
    }
}
