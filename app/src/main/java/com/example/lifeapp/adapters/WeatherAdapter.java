package com.example.lifeapp.adapters;

import android.content.Context;
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

        //Make a new API request after 10 mins
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
