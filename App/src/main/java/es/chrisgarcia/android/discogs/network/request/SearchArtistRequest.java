package es.chrisgarcia.android.discogs.network.request;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;

import es.chrisgarcia.android.discogs.network.ApiUtils;
import es.chrisgarcia.android.discogs.network.model.Results;

/**
 * Created by christian on 01/02/2014.
 * <p/>
 * Get Request to search artists
 */
public class SearchArtistRequest extends Request<Results> {

    public static final Object TAG = "search_artist";

    private final Response.Listener<Results> successListener;

    public SearchArtistRequest(String query, Response.Listener<Results> successListener, Response.ErrorListener errorListener) {
        super(Method.GET, buildUrl(query), errorListener);
        setTag(TAG);

        this.successListener = successListener;
    }

    private static String buildUrl(String query) {
        @SuppressWarnings("ConstantConditions")
        final String url = ApiUtils.builBaseUrl()
                                   .appendPath("database")
                                   .appendPath("search")
                                   .appendQueryParameter("q", query)
                                   .appendQueryParameter("type", "artist")
                                   .build()
                                   .toString();
        return url;
    }

    @Override
    protected Response<Results> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            Gson gson = new Gson();
            return Response.success(gson.fromJson(jsonString, Results.class), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(Results results) {
        successListener.onResponse(results);
    }
}
