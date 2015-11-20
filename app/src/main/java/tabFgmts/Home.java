package tabFgmts;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import databbase.DbKeys;
import databbase.History;
import databbase.Status;
import global.Constants;
import global.Global;
import main.R;
import main.kyp.AllTxnsActivity;

public class Home extends Fragment implements  View.OnClickListener,SwipeRefreshLayout.OnRefreshListener {
    TextView bank;
    TextView cash;
    TextView loan;
    TextView status;

     SwipeRefreshLayout swipeView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_page, container, false);
         bank=(TextView)rootView.findViewById(R.id.value_bank);
         cash=(TextView)rootView.findViewById(R.id.value_cash);
         loan=(TextView)rootView.findViewById(R.id.value_loan);
         status=(TextView)rootView.findViewById(R.id.valueStatus);
        Button c2b=(Button)rootView.findViewById(R.id.c2b);
        Button b2c=(Button)rootView.findViewById(R.id.b2c);
        Button all_txns=(Button)rootView.findViewById(R.id.all_txns);
        c2b.setOnClickListener(this);
        b2c.setOnClickListener(this);
        all_txns.setOnClickListener(this);
         swipeView = (SwipeRefreshLayout)rootView.findViewById(R.id.swipe_container);
        swipeView.setOnRefreshListener(this);
        swipeView.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3);

        populateValues();
        return rootView;
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.c2b:
                addBankCashTxns(DbKeys.CASHTOBANK);
                break;
            case R.id.b2c:
                addBankCashTxns(DbKeys.BANKTOCASH);
                break;
            case R.id.all_txns:
                Intent in = new Intent(getContext(), AllTxnsActivity.class);
                startActivity(in);
                break;
            default:
        }

    }
    public void addBankCashTxns( final String operation ){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(operation.equals(DbKeys.CASHTOBANK)? Constants.C2BHEAD:Constants.B2CHEAD);
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_NUMBER );
        builder.setView(input);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                double amt = 0;
                try {
                    amt= Double.parseDouble(input.getText().toString());
                    History ht=new History();
                    ht.type=operation;
                    ht.amount=amt;
                    ht.date= Global.global.sdf.format(new Date());
                    ht.dcpn=operation.equals(DbKeys.CASHTOBANK)?Constants.C2B_DCPN:Constants.B2C_DCPN;
                    History.addTxns(ht);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Enter numerical value.",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

                dialog.cancel();
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
    public void populateValues(){
        Status sts= Status.getStatus();
        bank.setText(String.valueOf(sts.getBank()));
        cash.setText(String.valueOf(sts.getCash()));
        loan.setText(String.valueOf(sts.getLoan()));
        status.setText(String.valueOf(sts.getInvestment()));
    }

    @Override
    public void onRefresh() {
        Runnable swipeShow=new Runnable() {
            @Override
            public void run() {
                swipeView.setRefreshing(true);
            }
        };
        swipeView.post(swipeShow);
        populateValues();
        swipeView.post(new Runnable() {
            @Override
            public void run() {
                swipeView.setRefreshing(false);
            }
        });
    }
}
