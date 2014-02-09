package es.chrisgarcia.android.discogs.network;

import android.net.Uri;

/**
 * Created by christian on 01/02/2014.
 */
public class ApiUtils {

    public static Uri.Builder builBaseUrl() {
        return new Uri.Builder().scheme("http")
                                .authority("api.discogs.com");
    }

}
