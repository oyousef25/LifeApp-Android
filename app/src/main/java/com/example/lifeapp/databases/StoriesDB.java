package com.example.lifeapp.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.lifeapp.pojo.Story;

import java.util.ArrayList;

/**
 * @author Omar Yousef
 * @version 1.0
 * @date March 19th 2021
 *
 * StoriesDB class:
 * This class will have all the CRUD operations for the stories table
 */
public class StoriesDB extends SQLiteOpenHelper {
    //DB version
    public static final int DATABASE_VERSION = 1;

    //DB name
    public static final String DATABASE_NAME = "storiesdb";

    //Table name
    public static final String TABLE_STORIES = "stories";

    /*
        Column names
     */
    public static final String COLUMN_ID = "id";

    /*
        Stories Table
     */
    //COLUMNS
    public static final String COLUMN_TITLE = "title"; //story title
    public static final String COLUMN_DESCRIPTION = "description"; //story description


    //CREATE TABLE
    public static final String CREATE_STORIES_TABLE =  "CREATE TABLE " +
            TABLE_STORIES + "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_TITLE + " TEXT, " + COLUMN_DESCRIPTION + " TEXT)";


    /**
     * @author Omar Yousef
     * @version 1.0
     * @date April 7th
     *
     * StoriesDB Constructor to create a new connection with the database
     * @param context
     */
    public StoriesDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Creating our stories table
        sqLiteDatabase.execSQL(CREATE_STORIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    /**
     * @author Omar Yousef
     * @version 1.0
     * @date April 7th 2021
     *
     * Adding a new story to the stories table
     * @param story
     */
    public void addStory(Story story){
        //Creating a writable connection with the database because we are going to write to it
        SQLiteDatabase db = this.getWritableDatabase();

        //Creating a new key and value object to match our key(column) with the value(Property value)
        ContentValues contentValues = new ContentValues();

        //Adding keys and values to out key and value object
        contentValues.put(COLUMN_TITLE, story.getTitle());
        contentValues.put(COLUMN_DESCRIPTION, story.getDescription());

        //Inserting our new record into the stories table
        db.insert(TABLE_STORIES, null, contentValues);

        //Closing connection with the db
        db.close();
    }

    /**
     * @author Omar Yousef
     * @version 1.0
     * @date April 7th 2021
     *
     * Retrieving a single story record from the stories table using the record's ID
     * @param id
     * @return Story
     */
    public Story getStory(int id){
        //Creating a new readable connection with the db
        SQLiteDatabase db = this.getReadableDatabase();

        //Creating a new story null object
        Story story = null;

        //Create a Cursor object to locate our Story record in the stories table
        //Using the id provided, we will be able to get the value of all columns whitin the same row
        Cursor cursor = db.query(TABLE_STORIES,
                new String[]{COLUMN_ID, COLUMN_TITLE, COLUMN_DESCRIPTION}, COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null);

        //Using the cursor we are going to get the values of the columns and add them to our story object
        if (cursor.moveToFirst()){
            story = new Story(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2)
            );
        }

        //Closing connection with the db
        db.close();

        //returning our new story object
        return story;
    }


    //All Stories
    public ArrayList<Story> getAllStories(){
        //Creating a readable connection with the database
        SQLiteDatabase db = this.getReadableDatabase();

        //Creating a new empty Story arrayList
        ArrayList<Story> stories = new ArrayList<>();

        //Using the Cursor we are going to retrieve all records from the stories table
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                TABLE_STORIES, null);

        //While there is more story records, add those records values to our story objects
        while(cursor.moveToNext()){
            //Adding all records to our stories arrayList
            stories.add(new Story(
                    //id
                    cursor.getInt(0),
                    //title
                    cursor.getString(1),
                    //description
                    cursor.getString(2)
            ));
        }

        //Closing db connection
        db.close();

        //returning our stories arrayList
        return stories;
    }

    //Update
    public int updateStory(Story story){
        //Creating a new writable connection with the db
        SQLiteDatabase db = this.getWritableDatabase();

        //Creating a new key and value object
        ContentValues contentValues = new ContentValues();

        //Matching our keys(columns) with the values
        contentValues.put(COLUMN_TITLE, story.getTitle());
        contentValues.put(COLUMN_DESCRIPTION, story.getDescription());

        //Updating the table's record
        return db.update(TABLE_STORIES, contentValues, COLUMN_ID + "=?",
                new String[]{String.valueOf(story.getId())});
    }

    /**
     * Delete a story
     * @param id
     */
    public void deleteStory(int id){
        //Creating a new writable connection with the db
        SQLiteDatabase db = this.getWritableDatabase();

        //Deleting the record using the id passed in the parameter
        db.delete(TABLE_STORIES, COLUMN_ID + "=?",
                new String[]{String.valueOf(id)});

        //Closing connection with the db
        db.close();

    }
}
