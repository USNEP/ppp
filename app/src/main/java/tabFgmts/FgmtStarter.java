package tabFgmts;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import global.Constants;

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
            Home fragment = new Home();
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

}