package chris.volleysink;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import chris.volleysink.network.model.Artist;
import chris.volleytest.R;

/**
 * Created by christian on 01/02/2014.
 * <p/>
 * Fragment to search artists
 */
public class SearchArtistFragment extends Fragment {

    private ArrayAdapter<Artist> mListAdapter = null;
    private List<Artist>         mResults     = new ArrayList<Artist>();

    public SearchArtistFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        if (rootView != null) {

            // list view
            ListView listView = (ListView) rootView.findViewById(R.id.listView);
            final Activity activity = getActivity();
            if (activity != null) {
                mListAdapter = new ArrayAdapter<Artist>(activity, android.R.layout.simple_list_item_1, android.R.id.text1, mResults);
                listView.setAdapter(mListAdapter);
                listView.setEmptyView(rootView.findViewById(android.R.id.empty));
            }
        }
        return rootView;
    }

    public void updateResults(List<Artist> results) {
        mResults = new ArrayList<Artist>(results);
        mListAdapter.clear();
        mListAdapter.addAll(mResults);
    }
}
