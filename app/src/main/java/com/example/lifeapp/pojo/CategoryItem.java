package com.example.lifeapp.pojo;

/**
 * @author Omar Yousef
 * @version 1.0
 * @date March 19th 2021
 *
 * CategoryItem class:
 * This class will allow us to create a CategoryItem object
 */
public class CategoryItem {
    //Image
    private Integer image;
    //Title
    private String title;

    /**
     * @author Omar Yousef
     * @version 1.0
     * @date March 19th 2021
     * CategoryItem Constructor
     */
    public CategoryItem(Integer image, String title) {
        this.image = image;
        this.title = title;
    }

    /*
        GETTERS AND SETTERS
     */

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
