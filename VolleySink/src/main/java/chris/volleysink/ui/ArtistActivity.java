package chris.volleysink.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import chris.volleysink.R;

public class ArtistActivity extends Activity {

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

}
