package com.example.lifeapp.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.lifeapp.pojo.Weather;

import java.util.ArrayList;

/**
 * @author Omar Yousef
 * @version 1.0
 * @date April 1st 2021
 *
 * WeatherDB class:
 * This class will have all the CRUD operations for the Weather table
 */
public class WeatherDB extends SQLiteOpenHelper {
    //DB version
    public static final int DATABASE_VERSION = 1;

    //DB name
    public static final String DATABASE_NAME = "lifeapp";

    //Table name
    public static final String TABLE_WEATHER = "weather";

    /*
        Column names
     */
    public static final String COLUMN_ID = "id";

    /*
        Weather Table
     */
    //COLUMNS
    public static final String COLUMN_CITY = "city"; //city name
    public static final String COLUMN_TEMP = "temperature"; //city temperature
    public static final String COLUMN_LAST_UPDATED = "last_updated"; //last updated


    //CREATE TABLE
    public static final String CREATE_WEATHER_TABLE = "CREATE TABLE " +
            TABLE_WEATHER + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_CITY + " TEXT, " + COLUMN_TEMP + " TEXT, " + COLUMN_LAST_UPDATED + " INTEGER)";



    /**
     * @author Omar Yousef
     * @version 1.0
     * @date April 1st 2021
     *
     * DB constructor
     */
    public WeatherDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_WEATHER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /*
        WEATHER CRUD
     */

    /**
     * @author Omar Yousef
     * @version 1.0
     * @date April 1st 2021
     *
     * Adding a weather object to our Weather table
     * @param weather
     */
    public void addWeather(Weather weather){
        //Opening a new db writable connection because we are going to write to the database
        SQLiteDatabase db = this.getWritableDatabase();

        //Creating a content values(Key value)
        ContentValues values = new ContentValues();

        //connecting the keys with the values(for ex: city column with city name property)
        values.put(COLUMN_CITY, weather.getCityName());
        values.put(COLUMN_TEMP, weather.getTemp());
        values.put(COLUMN_LAST_UPDATED, weather.getLastUpdated());

        //Inserting into db
        db.insert(TABLE_WEATHER, null, values);

        //Closing db connection
        db.close();
    }

    /**
     * @author Omar Yousef
     * @version 1.0
     * @date April 1st 2021
     *
     * Getting a single weather object from the table using its ID
     * @param id
     * @return
     */
    public Weather getWeather(int id){
        //Creating a new db readable connection to read data from the table
        SQLiteDatabase db = this.getReadableDatabase();

        //Creating a new weather object
        Weather weather = null;

        //Creating a new Cursor instance to locate our weather object in the weather table
        Cursor cursor = db.query(TABLE_WEATHER,
                new String[]{COLUMN_ID, COLUMN_CITY, COLUMN_TEMP, COLUMN_LAST_UPDATED}, COLUMN_ID + "= ?",
                new String[]{String.valueOf(id)}, null, null, null);

        if(cursor.moveToFirst()) {
            weather = new Weather(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getLong(3)
            );
        }

        //Closing db connection
        db.close();

        //Returning weather
        return weather;
    }


    /**
     * @author Omar Yousef
     * @version 1.0
     * @date April 1st 2021
     *
     * get All weathers in an arrayList
     * @return
     */
    public ArrayList<Weather> getAllWeathers(){
        //Creating a new readable connection with the database to read records from the weather table
        SQLiteDatabase db = this.getReadableDatabase();

        //retrieving all weather records from the weather table
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                TABLE_WEATHER, null);

        //Creating a new weather arrayList to sotre the records in it
        ArrayList<Weather> weatherArrayList = new ArrayList<>();

        //Storing all table records in a weather arrayLisr
        while(cursor.moveToNext()){
            weatherArrayList.add(new Weather(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getLong(3)
            ));
        }

//      closing database connection
        db.close();

        //returning our weather arrayList
        return weatherArrayList;
    }


    /**
     * @author Omar Yousef
     * @version 1.0
     * @date April 1st 2021
     *
     * Updating a weather record
     * @param weather
     * @return
     */
    public int updateWeather(Weather weather){
        //Creating a new writable database connection
        SQLiteDatabase db = getWritableDatabase();

        //Creating a new key and value object
        ContentValues values = new ContentValues();

        /*
            Matching keys with their values(DB & POJO)
         */
        values.put(COLUMN_CITY, weather.getCityName());
        values.put(COLUMN_TEMP, weather.getTemp());
        values.put(COLUMN_LAST_UPDATED, weather.getLastUpdated());

        //Updating to the database
        return db.update(TABLE_WEATHER, values, COLUMN_ID + "=?",
                new String[]{String.valueOf(weather.getId())});
    }


    /**
     * @author Omar Yousef
     * @version 1.0
     * @date April 1st 2021
     *
     * deleteWeather()
     * Deleting a weather from the weather table
     * @param weather
     */
    public void deleteWeather(int weather){
        //Creating a new db writable connection because we are going to delete from the table
        SQLiteDatabase db = this.getWritableDatabase();

        //Deleting a record using the ID
        db.delete(TABLE_WEATHER, COLUMN_ID +  "=?",
                new String[]{String.valueOf(weather)});

        //Closing DB connection
        db.close();
    }
}
