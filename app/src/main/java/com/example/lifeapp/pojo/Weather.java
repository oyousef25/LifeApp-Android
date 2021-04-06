package com.example.lifeapp.pojo;

/**
 * @author Omar Yousef
 * @version 1.0
 * @date April 1st 2021
 *
 * Weather object class to allow the user to create a new weather object
 */
public class Weather {
    /*
        Properties
     */
    //weather id
    private int id;

    //City name
    private String cityName;

    //Temperature
    private double temp;

    //Last time we read the API
    private long lastUpdated;



    /*
        Empty Constructor
     */
    public Weather() {
    }

    /*
        Constructor
     */
    public Weather(String cityName) {
        this.cityName = cityName;
        this.temp = 0;
        //Set the last time the temp was updated to the current time - 15 mins
        this.lastUpdated = System.currentTimeMillis() - 900000;
    }

    /**
     * @author Omar Yousef
     * @version 1.0
     * @date April 1st 2021
     *
     * A constructor to use when reading from DB
     * @param id
     * @param cityName
     * @param temp
     * @param lastUpdated
     */
    public Weather(int id, String cityName, double temp, long lastUpdated) {
        this.id = id;
        this.cityName = cityName;
        this.temp = temp;
        this.lastUpdated = lastUpdated;
    }


    /*
            GETTERS AND SETTERS
         */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
