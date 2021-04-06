package com.example.lifeapp.API;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * @author Omar Yousef
 * @version 1.0
 * @date 6th April
 *
 * WeatherSingleton:
 * Making sure we only create 1 instance of the object
 */
public class WeatherSingleton {
    //Creating an instance
    public static WeatherSingleton instance;

    //request
    private RequestQueue requestQueue;

    //Current context
    private static Context context;

    /**
     * @author Omar Yousef
     * @version 1.0
     * @date 6th April
     *
     * WeatherSingleton constructor
     */
    private WeatherSingleton(Context context){
        this.context = context;
    }

    /**
     * @author Omar Yousef
     * @version 1.0
     * @date 6th April
     *
     * Creating a new instance
     * @param context
     * @return
     */
    public static WeatherSingleton getInstance(Context context) {
        //Checking if there is no instance
        if(instance == null){
            //Creating a new instance
            instance = new WeatherSingleton(context);
        }
        //Returning our instance
        return instance;
    }

    /**
     * @author Omar Yousef
     * @version 1.0
     * @date 6th April
     *
     * API Request
     * @return
     */
    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }
}
