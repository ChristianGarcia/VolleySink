package es.chrisgarcia.android.discogs.util;

import android.content.Intent;

import es.chrisgarcia.android.discogs.ui.ArtistActivity;
import es.chrisgarcia.android.discogs.ui.SearchArtistActivity;

/**
 * Class to start Intents
 * Created by Christian on 2/6/14.
 */
public class IntentUtils {
    public static void openArtist(SearchArtistActivity packageContext, int resultId) {
        Intent openArtistIntent = new Intent(packageContext, ArtistActivity.class);
        openArtistIntent.putExtra(ArtistActivity.EXTRA_ID, resultId);
        packageContext.startActivity(openArtistIntent);
    }
}
