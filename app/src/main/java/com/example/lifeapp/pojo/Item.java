package com.example.lifeapp.pojo;

/**
 * @author Omar Yousef
 * @version 1.0
 * @date April 10th
 *
 * ContactItem class:
 * This class will create recipes, exercises and locations objects, each item requires a image, title and a description link
 */
public class Item {
    /*
        Properties
     */
    private Integer image;
    private String title;
    private String description;

    /**
     * @author Omar Yousef
     * @date April 10th 2021
     * @version 1.0
     *
     * @param image
     * @param title
     * @param description
     */
    public Item(Integer image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }


    /**
     * image getter
     * @return image
     */
    public Integer getImage() {
        return image;
    }

    /**
     * image setter
     * @param image
     */
    public void setImage(Integer image) {
        this.image = image;
    }

    /**
     * title getter
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * title setter
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * description getter
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * description setter
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
