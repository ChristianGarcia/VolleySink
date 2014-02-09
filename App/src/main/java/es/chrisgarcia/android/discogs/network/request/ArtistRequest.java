package es.chrisgarcia.android.discogs.network.request;

import android.net.Uri;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;

import es.chrisgarcia.android.discogs.network.ApiUtils;
import es.chrisgarcia.android.discogs.network.model.Artist;

/**
 * Created by Christian on 2/6/14.
 */
public class ArtistRequest extends Request<Artist> {
    private static final Object TAG = "artist";
    private final Response.Listener<Artist> mListener;

    public ArtistRequest(int artistId, Response.Listener<Artist> listener, Response.ErrorListener errorListener) {
        super(Method.GET, buildUrl(artistId).toString(), errorListener);
        setTag(TAG);
        mListener = listener;
    }

    static Uri.Builder buildUrl(int artistId) {
        return ApiUtils.builBaseUrl()
                       .appendPath("artists")
                       .appendPath(String.valueOf(artistId));
    }

    @Override
    protected Response<Artist> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            Artist artist = new Gson().fromJson(jsonString, Artist.class);
            return Response.success(artist, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(Artist artist) {
        mListener.onResponse(artist);
    }
}
