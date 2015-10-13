package fgmts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import main.R;


/**
 * Created by ashok on 9/12/15.
 */
public class TxnFgmt extends Fragment  {

    public TxnFgmt() {

    }
       @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.txn, container, false);
        return rootView;
    }





}
