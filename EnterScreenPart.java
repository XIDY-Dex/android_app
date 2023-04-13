package com.example.worldskills;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
public class EnterScreenPart {

    protected Drawable Image;
    protected String Text;
    protected String subText;

    public EnterScreenPart(String text, String subtext, Drawable image) {
        Text = text;
        subText = subtext;
        Image = image;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public Drawable getImage() {
        return Image;
    }

    public void setImage(Drawable image) {
        Image = image;
    }

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }
}