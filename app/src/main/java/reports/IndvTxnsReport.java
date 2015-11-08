package reports;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import databbase.History;
import databbase.Querys;
import databbase.Types;
import global.Constants;
import main.R;

/**
 * Created by ashok on 11/8/15.
 */
public class IndvTxnsReport extends Fragment implements ListView.OnItemClickListener{
    private Context thiscontext;
    private String[] argArray;
    private List<Types> types;
    private TextView lblTitle;
    private TextView lblHead;
    private ListView list_view;

    public IndvTxnsReport() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.indv_report, container, false);
        thiscontext = container.getContext();
        argArray = getArguments().getStringArray(Constants.ARG_FRGMT);
        lblHead = (TextView) view.findViewById(R.id.lblHead);
        lblTitle = (TextView) view.findViewById(R.id.lblheader1);
        lblHead.setText(argArray[0]);
        lblTitle.setText(argArray[1]);
        list_view = (ListView) view.findViewById(R.id.listView1);
        String tpId=String.valueOf(Types.getSubTypeByName(argArray[1]).id);
        list_view.setAdapter(new IndvRepArrayAdaptor(getActivity(), History.getIndvHistory(Querys.getHistoryQry(tpId,argArray))));
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //  Toast.makeText(thiscontext,hist.get(position).get_description() , Toast.LENGTH_SHORT).show();
    }

}

