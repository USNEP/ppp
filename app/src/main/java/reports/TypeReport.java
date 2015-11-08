package reports;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import databbase.DbKeys;
import databbase.History;
import databbase.Querys;
import databbase.Types;
import global.Constants;
import main.R;
import main.kyp.IndvReportActivity;

/**
 * Created by ashok on 11/8/15.
 */
public class TypeReport extends Fragment implements ListView.OnItemClickListener{

    private Context thiscontext;
    private String[] argArray;
    private List<Types> types;
    private TextView lblTitle;

    public TypeReport() {
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= (View)inflater.inflate(R.layout.sub_type_rep, container, false);
        thiscontext=container.getContext();
        argArray= getArguments().getStringArray(Constants.ARG_FRGMT);
        System.out.println("-----"+argArray[0]+"......"+argArray[1]+",,,,,,,"+argArray[2]);
        lblTitle=(TextView) view.findViewById(R.id.lblheader1);
        lblTitle.setText(argArray[0]);
        ListView list_view1=(ListView)view.findViewById(R.id.listView1);
        String head=Constants.LOAN_ITEMS.contains(argArray[0])? DbKeys.LOAN_TYPE:argArray[0];
        list_view1.setAdapter(new SubTypeRepArrayAdaptor(getActivity(), getReportArray(Types.getSubTypeByHead(head))));
        list_view1.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView tv=(TextView)view.findViewById(R.id.st_row);
        Intent in = new Intent(getContext(), IndvReportActivity.class);
        in.putExtra(Constants.ARG_ARRAY_REPORT, new String[]{argArray[0],tv.getText().toString(),argArray[1],argArray[2]});
        startActivity(in);
    }
    public List<ReportData> getReportArray(List<Types> array){
        List<ReportData> rp=new ArrayList<ReportData>();
        for(Types t:array){
            String tpId=String.valueOf(Types.getSubTypeByName(t.type).id);
            rp.add(History.getAmountByType(t.type,Querys.getAmtByTypeQry(tpId,argArray)));
        }
        return rp;
    }
}
