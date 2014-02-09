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

import es.chrisgarcia.android.discogs.network.model.Releases;

/**
 * Created by Christian on 2/6/14.
 */
public class ReleasesRequest extends Request<Releases> {
    private static final Object TAG = "releases";
    private final Response.Listener<Releases> mListener;

    public ReleasesRequest(int artistId, Response.Listener<Releases> listener, Response.ErrorListener errorListener) {
        super(Method.GET, buildUrl(artistId).toString(), errorListener);
        setTag(TAG);
        mListener = listener;
    }

    static Uri.Builder buildUrl(int artistId) {
        return ArtistRequest.buildUrl(artistId)
                            .appendPath("releases");
    }

    @Override
    protected Response<Releases> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            Releases artist = new Gson().fromJson(jsonString, Releases.class);
            return Response.success(artist, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(Releases releases) {
        mListener.onResponse(releases);
    }
}
