package es.chrisgarcia.android.discogs.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import es.chrisgarcia.android.discogs.R;
import es.chrisgarcia.android.discogs.network.manager.RequestManager;
import es.chrisgarcia.android.discogs.network.model.Artist;

public class ArtistActivity extends Activity implements Response.Listener<Artist>, Response.ErrorListener {

    public static final String EXTRA_ID        = "extra_id";
    public static final String FRAGMENT_ARTIST = "fragment_artist";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        if (savedInstanceState == null) {
            ArtistFragment artistFragment = new ArtistFragment();
            getFragmentManager().beginTransaction()
                                .add(R.id.container, artistFragment, FRAGMENT_ARTIST)
                                .commit();
        }

        int artistId = getIntent().getIntExtra(EXTRA_ID, -1);
        if (artistId > 0) {
            RequestManager.getInstance()
                          .fetchArtist(artistId, this, this);
        }
        // TODO decide what to do if no artistId found

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.artist, menu);
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
    public void onResponse(Artist artist) {
        ArtistFragment fragment = (ArtistFragment) getFragmentManager().findFragmentByTag(FRAGMENT_ARTIST);
        fragment.updateArtistData(artist);
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        Toast.makeText(this, "Unable to load artist", Toast.LENGTH_SHORT)
             .show();
    }
}
