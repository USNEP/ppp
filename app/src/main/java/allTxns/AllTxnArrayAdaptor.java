package allTxns;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import databbase.History;
import main.R;

public class AllTxnArrayAdaptor extends ArrayAdapter<History> {
    private Context thisContext;
    private ViewHolder viewHolder;
    public AllTxnArrayAdaptor(Context context, List<History> items) {
        super(context, R.layout.all_txns_row, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        thisContext=getContext();
        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.all_txns_row, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.amt = (TextView) convertView.findViewById(R.id.amt);
            viewHolder.dcpn = (TextView) convertView.findViewById(R.id.dcpn);


            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        History s = getItem(position);
        viewHolder.date.setText(s.date);
        viewHolder.amt.setText(String.valueOf(s.amount));
        viewHolder.dcpn.setText(s.dcpn);

        return convertView;
    }

    private static class ViewHolder {
        TextView serNo;
        TextView date;
        TextView amt;
        TextView dcpn;
    }
}
