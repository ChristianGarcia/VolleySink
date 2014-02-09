package es.chrisgarcia.android.discogs.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by christian on 01/02/2014.
 */
public class Result {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return title;
    }
}
