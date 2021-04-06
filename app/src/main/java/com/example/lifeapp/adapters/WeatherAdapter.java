package com.example.lifeapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.lifeapp.API.WeatherSingleton;
import com.example.lifeapp.R;
import com.example.lifeapp.databases.WeatherDB;
import com.example.lifeapp.pojo.CategoryItem;
import com.example.lifeapp.pojo.Weather;

import org.json.JSONException;
import org.json.JSONObject;

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
                .inflate(R.layout.fragment_weather_recyclerview, parent, false);

        //Returning our view
        return new WeatherAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        //Creating a new weather item using the our weatherArrayList
        Weather weatherItem = weatherArrayList.get(position);

        //Retrieving the properties data using the position and assigning them to our object's properties
        holder.cityName.setText(weatherItem.getCityName());

        //Our API Key
        String apiKey = "400cfeeacd415a94e7966090bffd4bd1";

        //Creating our API url
        String url =
                "https://api.openweathermap.org/data/2.5/weather?" +
                        "q=" + weatherItem.getCityName().toLowerCase() +
                        "&units=metric" +
                        "&appid="+apiKey;

//        //Make a new API request after 10 mins
        if (System.currentTimeMillis() - weatherItem.getLastUpdated() > 600000){
            //Make a new request
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONObject mainObject = response.getJSONObject("main");
                                weatherItem.setTemp(mainObject.getDouble("temp"));
                                weatherItem.setLastUpdated(System.currentTimeMillis());
                                WeatherDB db = new WeatherDB(context);
                                db.updateWeather(weatherItem);
                                Log.d("UPDATE", weatherItem.getCityName() + " TEMP UPDATED");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Display the error message in the logcat
                            Log.d("VOLLEY_ERROR", error.getLocalizedMessage());
                        }
                    });
            //Making sure we only create 1 object instance
            WeatherSingleton.getInstance(context).getRequestQueue().add(request);
        }
        //Setting the weatherItem's tempreature
        holder.temp.setText(weatherItem.getTemp()+"\u2103");
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
    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

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

            /*
                Locating our XML elements
             */
            this.cityName = itemView.findViewById(R.id.cityName);
            this.temp = itemView.findViewById(R.id.tempTextView);
            //Adding action when the user long clicks on an item(Delete record)
            itemView.setOnLongClickListener(this);

        }

        /**
         * @author Omar Yousef
         * @version 1.0
         * @date April 6th 2021
         *
         * OnLongClick():
         * When the user clicks on an item for long we ask them if they would like to delete the record
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
                            weatherArrayList.get(getLayoutPosition()).getCityName() + "?")
                    //Setting icon
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    //setting the positive button
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //Creating a new db connection
                            WeatherDB db = new WeatherDB(context);

                            //Delete record from the weather database
                            db.deleteWeather(weatherArrayList.get(getLayoutPosition()).getId());

                            //Delete the record from the weather ArrayList
                            weatherArrayList.remove(getLayoutPosition());

                            //Notify the RecyclerView the item was removed
                            notifyItemRemoved(getAdapterPosition());

                            //Closing connection with the weather db
                            db.close();
                        }
                    })
                    //setting the negative button
                    .setNegativeButton("No", null)
                    //Showing the dialogue box that we built
                    .show();
            return false;
        }
    }
}
