package com.stevechuls.test.fragment.imageview;

import android.graphics.drawable.Drawable;

/**
 * Created by entermate_ksc on 2018. 4. 10..
 */

public class ImageViewItem {
    private String imageUrl;
    private Drawable drawable;
    private String text;

    public Drawable getDrawable()
    {
        return this.drawable;
    }

    public void setDrawable(Drawable drawable)
    {
        this.drawable = drawable;
    }

    public String getText()
    {
        return this.text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
