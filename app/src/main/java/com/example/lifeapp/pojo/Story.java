package com.example.lifeapp.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Omar Yousef
 * @version 1.0
 * @date March 19th 2021
 *
 * Story class:
 * This class will have all what we need to create and update a new Story object
 */
public class Story implements Parcelable {
    //Properties
    private int id;
    private String title;
    private String description;

    /**
     * @author Omar Yousef
     * @version 1.0
     * @date April 7th 2021
     * Story Empty Constructor
     */
    public Story() {
    }

    /**
     * @author Omar Yousef
     * @version 1.0
     * @date April 7th 2021
     * Story Constructor
     */
    public Story(String title, String description) {
        this.title = title;
        this.description = description;
    }

    /**
     * @author Omar Yousef
     * @version 1.0
     * @date April 7th 2021
     * Story Constructor with the id proprty to use it when trying to retrieve a single record from the db
     */
    public Story(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    /*
        GETTERS AND SETTERS
     */

    /**
     * @author Omar Yousef
     * @version 1.0
     * @date April 7th 2021
     * getting the title value for the current object
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @author Omar Yousef
     * @version 1.0
     * @date April 7th 2021
     *
     * setting the title to a new value
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @author Omar Yousef
     * @version 1.0
     * @date April 7th 2021
     *
     * getting the object's description value
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @author Omar Yousef
     * @version 1.0
     * @date April 7th 2021
     *
     * Setting the description to a new value
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getting our story id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @author Omar Yousef
     * @version 1.0
     * @date April 7th 2021
     *
     * Setting our story id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(description);
    }
}
