package com.example.lifeapp.pojo;

/**
 * @author Omar Yousef
 * @version 1.0
 * @date April 10th
 *
 * ContactItem class:
 * This class will create contact objects, each contact requires a image, title and a URL link
 */
public class ContactItem {
    /*
        Properties
     */
    private Integer image;
    private String title;
    private String url;


    /**
     * @author Omar Yousef
     * @date April 10th 2021
     * @version 1.0
     *
     * Class constructor:
     * A custom constructor for the credits item
     * @param image
     * @param title
     */
    public ContactItem(Integer image, String title) {
        this.image = image;
        this.title = title;
    }

    /**
     * @author Omar Yousef
     * @date April 10th 2021
     * @version 1.0
     *
     * Image getter
     * @return image
     */
    public Integer getImage() {
        return image;
    }

    /**
     * @author Omar Yousef
     * @date April 10th 2021
     * @version 1.0
     * Image setter
     * @param image
     */
    public void setImage(Integer image) {
        this.image = image;
    }

    /**
     * @author Omar Yousef
     * @date April 10th 2021
     * @version 1.0
     *
     * Title getter
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @author Omar Yousef
     * @date April 10th 2021
     * @version 1.0
     *
     * Title setter
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @author Omar Yousef
     * @date April 10th 2021
     * @version 1.0
     *
     * URL getter
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @author Omar Yousef
     * @date April 10th 2021
     * @version 1.0
     *
     * URL setter
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
