package chris.volleysink;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import chris.volleysink.network.model.Result;

/**
 * Created by christian on 01/02/2014.
 * <p/>
 * Fragment to search artists
 */
public class SearchArtistFragment extends Fragment {

    private ArrayAdapter<Result> mListAdapter = null;
    private List<Result>         mResults     = new ArrayList<Result>();
    private OnResultItemClickListener mOnResultItemClickListener;

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
                mListAdapter = new ArrayAdapter<Result>(activity, android.R.layout.simple_list_item_1, android.R.id.text1, mResults);
                listView.setAdapter(mListAdapter);
                View emptyView = rootView.findViewById(android.R.id.empty);
                listView.setEmptyView(emptyView);
                if (mOnResultItemClickListener != null) {
                    listView.setOnItemClickListener(mOnResultItemClickListener);
                }
            }
        }
        return rootView;
    }

    public void updateResults(List<Result> results) {
        mResults = new ArrayList<Result>(results);
        mListAdapter.clear();
        mListAdapter.addAll(mResults);
    }

    public void setOnResultItemClickListener(OnResultItemClickListener mOnResultItemClickListener) {
        this.mOnResultItemClickListener = mOnResultItemClickListener;
    }

    public static interface OnResultItemClickListener extends AdapterView.OnItemClickListener {

    }

}
