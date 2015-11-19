package setting;

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

import java.util.Arrays;

import global.Constants;
import main.R;
import main.kyp.EditTypeActivity;

/**
 * Created by ashok on 9/14/15.
 */
public class Setting extends Fragment implements ListView.OnItemClickListener,View.OnClickListener{

    private Context thiscontext;
    Bundle bundle;
    ListView list_view1;
    ListView list_view2;
    ListView list_view3;
    TextView view1;
    TextView view2;
    TextView view3;

    public Setting() {
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= (View)inflater.inflate(R.layout.setting_mlt_list, container, false);
        thiscontext=container.getContext();
        list_view1=(ListView)view.findViewById(R.id.listView1);
        list_view2=(ListView)view.findViewById(R.id.listView2);
        list_view3=(ListView)view.findViewById(R.id.listView3);
        view1=(TextView)view.findViewById(R.id.lblheader1);
        view2=(TextView)view.findViewById(R.id.lblheader2);
        view3=(TextView)view.findViewById(R.id.lblheader3);
        list_view1.setAdapter(new SettingArrayAdapter(getActivity(),Arrays.asList(Constants.MONEY_IN_TYPES)));
        list_view2.setAdapter(new SettingArrayAdapter(getActivity(), Arrays.asList(Constants.EXPENSES_TYPES)));
        list_view3.setAdapter(new SettingArrayAdapter(getActivity(),Arrays.asList(Constants.MONEY_OUT_TYPES) ));
        list_view1.setOnItemClickListener(this);
        list_view2.setOnItemClickListener(this);
        list_view3.setOnItemClickListener(this);
        view1.setOnClickListener(this);
        view2.setOnClickListener(this);
        view3.setOnClickListener(this);
        list_view2.setVisibility(View.GONE);
        list_view3.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent in = new Intent(thiscontext, EditTypeActivity.class);

        if(parent.getId()==list_view1.getId())
        {         in.putExtra(Constants.ARG_SECTION_NUMBER, Constants.MONEY_IN_TYPES[position]);

        }
        else if(parent.getId()==list_view2.getId()){
            in.putExtra(Constants.ARG_SECTION_NUMBER,Constants.EXPENSES_TYPES[position] );

        }
        else{
            in.putExtra(Constants.ARG_SECTION_NUMBER,Constants.MONEY_OUT_TYPES[position] );

        }
        startActivity(in);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lblheader1:
                if (list_view1.getVisibility() == View.VISIBLE) {
                    list_view1.setVisibility(View.GONE);
                } else {
                    list_view1.setVisibility(View.VISIBLE);
                    list_view2.setVisibility(View.GONE);
                    list_view3.setVisibility(View.GONE);

                }
                break;
            case R.id.lblheader2:
                if (list_view2.getVisibility() == View.VISIBLE) {
                    list_view2.setVisibility(View.GONE);
                } else {
                    list_view2.setVisibility(View.VISIBLE);
                    list_view3.setVisibility(View.GONE);
                    list_view1.setVisibility(View.GONE);

                }
                break;
            case R.id.lblheader3:
                if (list_view3.getVisibility() == View.VISIBLE) {
                    list_view3.setVisibility(View.GONE);
                } else {
                    list_view3.setVisibility(View.VISIBLE);
                    list_view1.setVisibility(View.GONE);
                    list_view2.setVisibility(View.GONE);

                }
                break;
            default:
        }
    }


//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        setListAdapter(new MobileArrayAdapter(getActivity(), Arrays.asList(getResources().getStringArray(R.array.loan_items))));
//
//}


}