package chris.volleysink.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import chris.volleysink.R;
import chris.volleysink.network.model.Artist;
import chris.volleysink.network.model.Releases;

/**
 * Fragment that holds the view of the Result detail
 */
public class ArtistFragment extends Fragment {

    @InjectView(R.id.artist_name)
    TextView name;

    @InjectView(R.id.artist_profile)
    TextView profile;

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

    public void updateArtistData(Artist artist) {
        name.setText(artist.getName());
        profile.setText(artist.getProfile());
    }

    public void updateReleasesData(Releases releases) {

    }
}
