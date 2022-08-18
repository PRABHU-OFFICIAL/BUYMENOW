package com.example.buymenow.Models;

import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class PopularModel implements Serializable {
    private String image;
    private String name;
    private String price;
    private int icon1;
    private String text1;
    private int icon2;
    private String text2;
    private int icon3;
    private String text3;
    private int icon4;
    private String text4;

    public PopularModel(String image, String name, String price, int icon1, String text1, int icon2, String text2, int icon3, String text3, int icon4, String text4) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.icon1 = icon1;
        this.text1 = text1;
        this.icon2 = icon2;
        this.text2 = text2;
        this.icon3 = icon3;
        this.text3 = text3;
        this.icon4 = icon4;
        this.text4 = text4;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getIcon1() {
        return icon1;
    }

    public void setIcon1(int icon1) {
        this.icon1 = icon1;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public int getIcon2() {
        return icon2;
    }

    public void setIcon2(int icon2) {
        this.icon2 = icon2;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public int getIcon3() {
        return icon3;
    }

    public void setIcon3(int icon3) {
        this.icon3 = icon3;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    public int getIcon4() {
        return icon4;
    }

    public void setIcon4(int icon4) {
        this.icon4 = icon4;
    }

    public String getText4() {
        return text4;
    }

    public void setText4(String text4) {
        this.text4 = text4;
    }
}
