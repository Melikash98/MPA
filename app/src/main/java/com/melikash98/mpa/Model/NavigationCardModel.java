package com.melikash98.mpa.Model;

public class NavigationCardModel {
    private String id;
    private String name;
    private String img;
    private String imgColor;

    public NavigationCardModel(String id, String name, String img, String imgColor) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.imgColor = imgColor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImgColor() {
        return imgColor;
    }

    public void setImgColor(String imgColor) {
        this.imgColor = imgColor;
    }
}
