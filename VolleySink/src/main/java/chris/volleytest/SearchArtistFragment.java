package chris.volleytest;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.List;

import chris.volleytest.network.SearchArtistRequest;
import chris.volleytest.network.model.Artist;
import chris.volleytest.network.model.ArtistResults;

/**
 * Created by christian on 01/02/2014.
 * <p/>
 * Fragment to search artists
 */
public class SearchArtistFragment extends Fragment implements Response.Listener<ArtistResults>, Response.ErrorListener {

    private RequestQueue         mRequestQueue;
    private ArrayAdapter<Artist> mListAdapter;
    private EditText             mEtSearch;

    public SearchArtistFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mRequestQueue = Volley.newRequestQueue(activity);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        if (rootView != null) {
            // edit text
            mEtSearch = (EditText) rootView.findViewById(R.id.et_search);

            //button search
            final Button btnSearch = (Button) rootView.findViewById(R.id.btn_search);
            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSearch_Clicked();
                }
            });

            // list view
            ListView listView = (ListView) rootView.findViewById(R.id.listView);
            final Activity activity = getActivity();
            if (activity != null) {
                mListAdapter = new ArrayAdapter<Artist>(activity, android.R.layout.simple_list_item_1, android.R.id.text1);
                listView.setAdapter(mListAdapter);
            }
        }
        return rootView;
    }

    private void onSearch_Clicked() {
        final Editable text = mEtSearch.getText();
        final String searchQuery = text != null ? text.toString() : "";
        final SearchArtistFragment responseListener = this;
        final SearchArtistRequest searchArtistRequest = new SearchArtistRequest(searchQuery, responseListener, responseListener);
        mRequestQueue.add(searchArtistRequest);
    }


    @Override
    public void onStop() {
        super.onStop();
        mRequestQueue.cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return true;
            }
        });
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {

    }

    @Override
    public void onResponse(ArtistResults artistResults) {
        final List<Artist> results = artistResults.results;
        mListAdapter.clear();
        mListAdapter.addAll(results);
    }
}
