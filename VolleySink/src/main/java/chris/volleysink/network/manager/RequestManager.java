package chris.volleysink.network.manager;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import chris.volleysink.network.request.ArtistRequest;
import chris.volleysink.network.request.SearchArtistRequest;
import chris.volleysink.network.model.Artist;
import chris.volleysink.network.model.Results;

/**
 * Singleton that makes and cancels requests}
 *
 * @author Christian
 */
public class RequestManager {

    private static RequestManager instance;

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
        final SearchArtistRequest request = new SearchArtistRequest(searchQuery, successListener, errorListener);
        mRequestQueue.add(request);
    }

    public void fetchArtist(int artistId, Response.Listener<Artist> listener, Response.ErrorListener errorListener) {
        final ArtistRequest request = new ArtistRequest(artistId, listener, errorListener);
        mRequestQueue.add(request);

    }

}
