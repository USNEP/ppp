package allTxns;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import databbase.History;
import main.R;

/**
 * Created by ashok on 11/16/15.
 */
public class AllTxns extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.all_txns, container, false);
       ListView list_view=(ListView)rootView.findViewById(R.id.listView);
        list_view.setAdapter(new AllTxnArrayAdaptor(getActivity(),History.getTxns()));
        return rootView;
    }

}
