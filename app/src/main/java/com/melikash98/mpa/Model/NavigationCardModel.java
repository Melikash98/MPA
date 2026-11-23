package com.melikash98.mpa.Model;

public class NavigationCardModel {
    private String id;
    private int name;
    private int img;
    private int imgColor;
    private int imgBgColor;

    public NavigationCardModel(String id, int name, int img, int imgColor, int imgBgColor) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.imgColor = imgColor;
        this.imgBgColor = imgBgColor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getImgColor() {
        return imgColor;
    }

    public void setImgColor(int imgColor) {
        this.imgColor = imgColor;
    }

    public int getImgBgColor() {
        return imgBgColor;
    }

    public void setImgBgColor(int imgBgColor) {
        this.imgBgColor = imgBgColor;
    }
}