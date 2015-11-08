package tabFgmts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import databbase.History;
import databbase.Querys;
import global.Constants;
import global.Global;
import main.R;
import main.kyp.TypeRepActivity;
import reports.ReportArrayAdopter;
import reports.ReportData;

public class Report extends Fragment implements ListView.OnItemClickListener{

    private Context thisContext;
    ListView list_view1;
    ListView list_view2;
    ListView list_view3;
    ListView list_view4;
    ListView list_view5;
    ListView list_view6;
    EditText from;
    EditText to;
    Calendar myDte= Calendar.getInstance();
    Button btnLoad;
    String fromDate="";
    String toDate="";
    SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.US);
    public Report() {
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= (View)inflater.inflate(R.layout.report_multi_list, container, false);
        thisContext=container.getContext();
        list_view1=(ListView)view.findViewById(R.id.listView1);
        list_view2=(ListView)view.findViewById(R.id.listView2);
        list_view3=(ListView)view.findViewById(R.id.listView3);
        list_view4=(ListView)view.findViewById(R.id.listView4);
        list_view5=(ListView)view.findViewById(R.id.listView5);
        list_view6=(ListView)view.findViewById(R.id.listView6);
        list_view3.setVisibility(View.GONE);
        list_view5.setVisibility(View.GONE);
        list_view2.setOnItemClickListener(this);
        list_view4.setOnItemClickListener(this);
        list_view1.setOnItemClickListener(this);
        list_view3.setOnItemClickListener(this);
        list_view5.setOnItemClickListener(this);
        list_view6.setOnItemClickListener(this);


        from=(EditText)view.findViewById(R.id.editDate1);
        to=(EditText)view.findViewById(R.id.editDate2);
        myDte.set(Calendar.DATE, myDte.getActualMinimum(Calendar.DATE));
        from.setText(sdf.format(myDte.getTime()));
        myDte= Calendar.getInstance();
        myDte.set(Calendar.DATE, myDte.getActualMaximum(Calendar.DATE));
        to.setText(sdf.format(myDte.getTime()));
        myDte= Calendar.getInstance();
        from.setOnClickListener(date_listener);
        to.setOnClickListener(date_listener);
        refreshView();
        btnLoad=(Button)view.findViewById(R.id.btnShow);
        btnLoad.setOnClickListener(loadListener);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.listView2:
                if(list_view3.getVisibility()==View.VISIBLE){
                    list_view3.setVisibility(View.GONE);}
                else{
                    list_view3.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.listView4:
                if(list_view5.getVisibility()==View.VISIBLE){
                    list_view5.setVisibility(View.GONE);}
                else{
                    list_view5.setVisibility(View.VISIBLE);
                }
                break;
            default:
                fromDate=from.getText().toString();
                toDate=to.getText().toString();
                TextView tv=(TextView)view.findViewById(R.id.st_row);
                Intent in = new Intent(getContext(), TypeRepActivity.class);
                in.putExtra(Constants.ARG_ARRAY_REPORT, new String[]{tv.getText().toString(),fromDate,toDate});
                startActivity(in);
        }
    }
    View.OnClickListener loadListener = new View.OnClickListener(){
        public void onClick(View v) {
            refreshView();
        }
    };
    public void refreshView(){

        list_view1.setAdapter(new ReportArrayAdopter(getActivity(), getReportArray(Constants.REPORT_LIST_INCOME)));
        List<ReportData> rp= getReportArray(Arrays.asList(Constants.EXPENSES_TYPES));
        list_view2.setAdapter(new ReportArrayAdopter(getActivity(),getTotal(rp,Constants.REPORT_LIST_EXPENSE)));
        list_view3.setAdapter(new ReportArrayAdopter(getActivity(), rp));
        rp= getReportArray(Constants.LOAN_ITEMS);
        list_view4.setAdapter(new ReportArrayAdopter(getActivity(),getLoanTotal(rp,Constants.REPORT_LIST_LOANS)));
        list_view5.setAdapter(new ReportArrayAdopter(getActivity(), rp));
        list_view6.setAdapter(new ReportArrayAdopter(getActivity(), getReportArray(Constants.REPORT_LIST_INVESTMENT)));
    }
    View.OnClickListener date_listener = new View.OnClickListener(){
        public void onClick(View v) {
            Global.global.showDatePicker(thisContext,(EditText)v);
        }
    };
    public List<ReportData> getReportArray(List<String> lst){
         fromDate=from.getText().toString();
         toDate=to.getText().toString();
        List<ReportData> rp=new ArrayList<ReportData>();
       for(String st:lst){
           rp.add(History.getAmountByHead(st,Querys.getAmtByHeadQry(st, fromDate, toDate)));
       }
        return rp;
    }
    public List<ReportData> getTotal(List<ReportData> rep,List<String> lst){
        double value=0;
         for(ReportData r:rep){
            value=value+r.getAmount();
         }
         rep=new ArrayList<ReportData>();
        rep.add(new ReportData(lst.get(0),value));
        return  rep;
    }

    public List<ReportData> getLoanTotal(List<ReportData> rep,List<String> lst){
        double value=rep.get(0).getAmount()-rep.get(1).getAmount()-rep.get(2).getAmount();
        rep=new ArrayList<ReportData>();
        rep.add(new ReportData(lst.get(0),value));
        return  rep;

    }

}

