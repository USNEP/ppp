package tabFgmts;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import global.Constants;
import main.R;

public  class FgmtStarter extends Fragment {



    public static Fragment newInstance(int sectionNumber) {
        if (sectionNumber==2 || sectionNumber==3 || sectionNumber==4)
        {
            TxnFgmt tfg=new TxnFgmt();
            Bundle args = new Bundle();
            args.putInt(Constants.ARG_SECTION_NUMBER, sectionNumber);
            tfg.setArguments(args);
            return tfg;
        }else if(sectionNumber==1){
            FgmtStarter fragment = new FgmtStarter();
            return fragment;

        }
        else
        {
            Report tfg=new Report();
             return tfg;
        }
    }

    public FgmtStarter() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context thiscontext = container.getContext();
        View rootView = inflater.inflate(R.layout.home_page, container, false);
        TextView bank=(TextView)rootView.findViewById(R.id.value_bank);
        TextView cash=(TextView)rootView.findViewById(R.id.value_cash);
        TextView loan=(TextView)rootView.findViewById(R.id.value_loan);
        TextView status=(TextView)rootView.findViewById(R.id.valueStatus);
                return rootView;
    }
}