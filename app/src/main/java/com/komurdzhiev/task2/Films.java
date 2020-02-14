package com.komurdzhiev.task2;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Films {
    @SerializedName("count")
    @Expose
    private Integer count;

    @SerializedName("next")
    @Expose
    private Object next;

    @SerializedName("previous")
    @Expose
    private Object previous;

    @SerializedName("results")
    @Expose
    private List<Results> results = null;

    public Integer getCount() {
        return count;
    }

    public Object getNext() {
        return next;
    }

    public Object getPrevious() {
        return previous;
    }

    public List<Results> getResults() {
        return results;
    }
}

class Results {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("poster")
    @Expose
    private Poster poster;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Poster getPoster() {
        return poster;
    }
}

class Poster {
    @SerializedName("image")
    @Expose
    private String image;

    private Bitmap bitmap;

    public String getImage() {
        return image;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}



