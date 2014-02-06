package chris.volleysink.ui;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import chris.volleysink.R;
import chris.volleysink.network.SearchArtistRequest;
import chris.volleysink.network.model.ArtistResults;
import chris.volleysink.network.model.Result;
import chris.volleysink.util.IntentUtils;

public class SearchArtistActivity extends Activity implements Response.Listener<ArtistResults>, Response.ErrorListener, SearchArtistFragment.OnResultItemClickListener {

    public static final String       TAG_FRAGMENT_SEARCH = "fragment_search";
    private             RequestQueue mRequestQueue       = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            SearchArtistFragment searchArtistFragment = SearchArtistFragment.newInstance(this);
            getFragmentManager().beginTransaction()
                                .add(R.id.container, searchArtistFragment, TAG_FRAGMENT_SEARCH)
                                .commit();
        }

        mRequestQueue = Volley.newRequestQueue(this);

        handleIntent(getIntent());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // Handle intent for Search
        handleIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        @SuppressWarnings("ConstantConditions")
        SearchView searchView = (SearchView) menu.findItem(R.id.search)
                                                 .getActionView();
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStop() {
        mRequestQueue.cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return true;
            }
        });
        super.onStop();
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        Toast.makeText(this, "Error retrieving results" + volleyError, Toast.LENGTH_LONG)
             .show();
    }

    @Override
    public void onResponse(ArtistResults artistResults) {
        final List<Result> results = artistResults.results != null ? artistResults.results : new ArrayList<Result>();
        updateResults(results);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Object item = adapterView.getItemAtPosition(position);
        if (item instanceof Result) {
            Result result = (Result) item;
            IntentUtils.openArtist(this, result.getId());
        }
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String searchQuery = intent.getStringExtra(SearchManager.QUERY);
            final SearchArtistRequest searchArtistRequest = new SearchArtistRequest(searchQuery, this, this);
            mRequestQueue.add(searchArtistRequest);

        }
    }

    private void updateResults(List<Result> results) {
        final SearchArtistFragment searchFragment = (SearchArtistFragment) getFragmentManager().findFragmentByTag(TAG_FRAGMENT_SEARCH);
        if (searchFragment != null) {
            searchFragment.updateResults(results);
        }
    }

}
