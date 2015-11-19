package setting;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import databbase.History;
import databbase.Types;
import main.R;

public class SettingListAdaptor extends ArrayAdapter<String> {
    private Context thisContext;
    private ViewHolder viewHolder;
    TextView tv;
    String type;
    List<String> items;
    String   m_Text="";

    public SettingListAdaptor(Context context, List<String> items) {
        super(context, R.layout.setting_list, items);
        this.items=items;

    }

    @Override
    public View getView( final int position, View convertView, ViewGroup parent) {
        thisContext=getContext();
       final String item = this.getItem(position);
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.setting_list, parent, false);
            viewHolder = new ViewHolder();
            tv=(TextView)convertView.findViewById(R.id.item_st);
            viewHolder.SerNo = (TextView) convertView.findViewById(R.id.sn);
            viewHolder.ivIcon = (TextView) convertView.findViewById(R.id.item_st);
            viewHolder.btnEdit = (ImageButton) convertView.findViewById(R.id.btnEdit);

            viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(thisContext);
                    builder.setTitle("Update?");
                    final RelativeLayout layout=(RelativeLayout) v.getParent();
                    final EditText input = new EditText(thisContext);
                    final TextView text=(TextView)layout.findViewById(R.id.item_st);

                    input.setInputType(InputType.TYPE_CLASS_TEXT );
                    builder.setView(input);
                    input.setText(text.getText().toString());
                    input.setSelection(0,text.getText().toString().length());

                    builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            m_Text = input.getText().toString();
                            if(!m_Text.isEmpty()) {
                                if (updateType(text.getText().toString(), m_Text)){
                                    text.setText(m_Text);

                                }
                            }

                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();


                }

            });
            viewHolder.btnDelete = (ImageButton) convertView.findViewById(R.id.btnDelete);
            viewHolder.btnDelete.setOnClickListener(new View.OnClickListener()
            { @Override
              public void onClick(View v)
                {   final RelativeLayout layout=(RelativeLayout) v.getParent();
                    final TextView tv=(TextView)layout.findViewById(R.id.item_st);
                    final ListView listView=(ListView) layout.getParent();
                    new AlertDialog.Builder(thisContext)
                            .setTitle("Delete Type?")
                            .setMessage("Are you sure to delete "+tv.getText().toString()+ " type?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    deleteType(tv.getText().toString());
                                    remove(item);
                                }

                            })
                            .setNegativeButton("No", null).show();

                }

            } );
            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        String s = getItem(position);
        viewHolder.SerNo.setText(String.valueOf(position+1)+".");
        viewHolder.ivIcon.setText(s);
        type=s;

        return convertView;
    }
    public boolean  deleteType(String value){
        try {
            Types.deleteType(value);
            Types type = Types.getSubTypeByName(value);
            History.deleteTxnsByType(String.valueOf(type.id));
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean updateType(String  firstValue,String updatValue){
        try{
        Types type=Types.getSubTypeByName(firstValue);
        type.type=updatValue;
        Types.updateType(type);
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static class ViewHolder {
        TextView ivIcon;
        TextView SerNo;
        ImageButton btnEdit;
        ImageButton btnDelete;
    }
}

