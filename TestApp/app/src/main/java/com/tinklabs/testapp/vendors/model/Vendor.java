package com.tinklabs.testapp.vendors.model;

/**
 * This is a class represents a vendor.
 */
public class Vendor {

    public static final int STYLE_DETAIL = 1;
    public static final int STYLE_IMAGE_ONLY = 2;

    String title;
    String desc;
    String imageUrl;
    int style;

    public Vendor(){
    }

    public Vendor(String title, String desc, String imageUrl, int style){
        this.title = title;
        this.desc = desc;
        this.imageUrl = imageUrl;
        this.style = style;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }
}
