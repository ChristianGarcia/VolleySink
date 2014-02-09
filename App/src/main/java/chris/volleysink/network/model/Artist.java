package chris.volleysink.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Christian on 2/6/14.
 */
public class Artist {

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    @SerializedName("profile")
    private String profile;

    public String getProfile() {
        return profile;
    }
}
