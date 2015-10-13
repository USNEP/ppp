package fgmts;

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
        if (sectionNumber==2)
        {
            TxnFgmt tfg=new TxnFgmt();
            return tfg;
        }else
        {
            FgmtStarter fragment = new FgmtStarter();
            Bundle args = new Bundle();
            args.putInt(Constants.ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;

        }
    }

    public FgmtStarter() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        int i=  getArguments().getInt(Constants.ARG_SECTION_NUMBER);
        TextView tv=(TextView)rootView.findViewById(R.id.section_label);

        return rootView;
    }
}