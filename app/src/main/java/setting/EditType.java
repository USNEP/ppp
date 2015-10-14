package setting;

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

import databbase.DbKeys;
import databbase.Types;
import global.Constants;
import global.Global;
import main.R;

/**
 * Created by ashok on 9/28/15.
 */
public class EditType extends Fragment implements ListView.OnItemClickListener{
    private Context thiscontext;
    private TextView lblHead;
    List<Types> types;

    public EditType() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= (View)inflater.inflate(R.layout.setting_editor, container, false);
        thiscontext=container.getContext();
        lblHead=(TextView) view.findViewById(R.id.lblHead);
        String head =getArguments().getString(Constants.ARG_FRGMT);
        lblHead.setText(head);
        head=Constants.LOAN_ITEMS.contains(head)? DbKeys.LOAN_TYPE:head;
        types=Types.getSubTypeByHead(head);
        ListView list_view1=(ListView)view.findViewById(R.id.listView1);
        list_view1.setAdapter(new SettingListAdaptor(getActivity(), Global.global.getStringListOfTypes(types)));
        list_view1.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}