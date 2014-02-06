package chris.volleysink.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import chris.volleysink.network.SearchArtistRequest;
import chris.volleysink.network.model.Results;

/**
 * Singleton that holds the {@link chris.volleysink.volley.RequestProxy}
 *
 * @author Christian
 */
public class RequestManager {

    private static RequestManager instance;

    public static final Object SEARCH_ARTIST = "search_artist";

    private RequestQueue mRequestQueue;

    private RequestManager(Context context) {
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized RequestManager getInstance(Context context) {
        if (instance == null) {
            instance = new RequestManager(context);
        }
        return instance;
    }

    public static synchronized RequestManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException(RequestManager.class.getSimpleName() + " is not initialized. Call getInstance(Context) first");
        }
        return instance;
    }

    public void cancelAll(Object tag) {
        mRequestQueue.cancelAll(tag);
    }

    public void cancelAll() {
        mRequestQueue.cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return true;
            }
        });
    }

    public void searchArtist(String searchQuery, Response.Listener<Results> successListener, Response.ErrorListener errorListener) {
        final SearchArtistRequest searchArtistRequest = new SearchArtistRequest(searchQuery, successListener, errorListener);
        searchArtistRequest.setTag(RequestManager.SEARCH_ARTIST);
        mRequestQueue.add(searchArtistRequest);
    }

}
