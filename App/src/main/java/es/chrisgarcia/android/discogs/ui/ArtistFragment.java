package es.chrisgarcia.android.discogs.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import butterknife.ButterKnife;
import butterknife.InjectView;
import es.chrisgarcia.android.discogs.R;
import es.chrisgarcia.android.discogs.network.model.Artist;
import es.chrisgarcia.android.discogs.network.model.Releases;

/**
 * Fragment that holds the view of the Result detail
 */
public class ArtistFragment extends Fragment {

    @InjectView(R.id.artist_name)
    TextView name;

    @InjectView(R.id.artist_profile)
    TextView profile;

    @InjectView(R.id.switch_artist)
    ViewSwitcher switcherArtist;

    public ArtistFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_artist, container, false);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        switcherArtist.showNext();
    }

    public void updateArtistData(Artist artist) {
        name.setText(artist.getName());
        profile.setText(artist.getProfile());
        switcherArtist.showNext();
    }

    public void updateReleasesData(Releases releases) {

    }
}
