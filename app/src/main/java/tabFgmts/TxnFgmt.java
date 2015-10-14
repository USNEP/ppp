package tabFgmts;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import databbase.DbKeys;
import databbase.History;
import databbase.Types;
import global.Constants;
import global.Global;
import main.R;


/**
 * Created by ashok on 9/12/15.
 */
public class TxnFgmt extends Fragment  implements  View.OnClickListener{
   private Calendar myCalendar;
   private Context thisContext;
   private EditText dat;
   private int position;
    private Spinner fldType;
    private Spinner fldSubType;
    private TextView lblType;
    private TextView lblSubType;
    private EditText fldAmount;
    private RadioButton rb;
    private RadioButton rc;
    private Button btnAddd;
    private EditText fldComment;
    private Button btnCreate;
    private String mainType;
    private List<Types> creoptions;
    private History history;
    public TxnFgmt() {
        history=new History();
    }
       @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.txn, container, false);
           thisContext=container.getContext();
           position= getArguments().getInt(Constants.ARG_SECTION_NUMBER);
           myCalendar=Calendar.getInstance();
           creoptions=new ArrayList<Types>();
           dat=(EditText)rootView.findViewById(R.id.fldDate);
           Global.global.setDate(dat);
           dat.setOnClickListener(date_listener);
           fldType =(Spinner)rootView.findViewById(R.id.spinnerList1);
           fldType.setOnItemSelectedListener(type_listener);
           fldSubType =   (Spinner)rootView.findViewById(R.id.spinnerList);
           lblType=(TextView) rootView.findViewById(R.id.lblName);
           lblSubType=(TextView) rootView.findViewById(R.id.lblspinner1);
           fldAmount = (EditText)rootView.findViewById(R.id.fldAmount);
           rb = (RadioButton)rootView. findViewById(R.id.radioBank);
           rc = (RadioButton)rootView. findViewById(R.id.radioCash);
           btnAddd = (Button) rootView.findViewById(R.id.btnAdd);
           fldComment=(EditText) rootView.findViewById(R.id.fldComment);
           btnCreate = (Button) rootView.findViewById(R.id.btnCreate);
           btnCreate.setOnClickListener(this);
           btnAddd.setOnClickListener(this);


           switch(position){
               case 2:
                   onCreateMoneyInView();
                   break;
               case 3:
                   onCreateExpenseView();
                   break;
               case 4:
                   onCreateMoneyOutView();
                   break;
               default:
           }
        return rootView;
    }
    View.OnClickListener date_listener = new View.OnClickListener(){
        public void onClick(View v) {
            Global.global.showDatePicker(thisContext,dat);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCreate:
                createType();
                break;
            case R.id.btnAdd:
                addEntry();
                break;
            default:
    }

    }

    public void onCreateMoneyInView(){
        lblType.setText(Constants.LBL_TITLE);
        lblSubType.setText(Constants.LBL_SOURCE);
        updateSpinner(new ArrayList<String>(Arrays.asList(Constants.MONEY_IN_TYPES)), fldType);
        mainType=fldType.getSelectedItem().toString();
        String head=Constants.LOAN_ITEMS.contains(mainType)?DbKeys.LOAN_TYPE:mainType;
        creoptions=Types.getSubTypeByHead(head);
        updateSpinner(Global.global.getStringListOfTypes(creoptions),fldSubType);

    }
    public void onCreateExpenseView(){
        lblType.setText(Constants.LBL_EXPENSE_HEAD);
        lblSubType.setText(Constants.LBL_EXPENSE_TYPE);
        updateSpinner(new ArrayList<String>(Arrays.asList(Constants.EXPENSES_TYPES)), fldType);
        mainType=fldType.getSelectedItem().toString();
        String head=Constants.LOAN_ITEMS.contains(mainType)?DbKeys.LOAN_TYPE:mainType;
        creoptions=Types.getSubTypeByHead(head);
        updateSpinner(Global.global.getStringListOfTypes(creoptions),fldSubType);
    }
    public void onCreateMoneyOutView(){
        lblType.setText(Constants.LBL_TO);
        lblSubType.setText(Constants.LBL_OUT_FOR);
        updateSpinner(new ArrayList<String>(Arrays.asList(Constants.MONEY_OUT_TYPES)), fldType);
        mainType=fldType.getSelectedItem().toString();
        String head=Constants.LOAN_ITEMS.contains(mainType)?DbKeys.LOAN_TYPE:mainType;
        creoptions=Types.getSubTypeByHead(head);
        updateSpinner(Global.global.getStringListOfTypes(creoptions), fldSubType);
    }
    public void updateSpinner(List<String> listValue,Spinner spinner){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(thisContext,android.R.layout.simple_spinner_item,listValue);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.invalidate();
    }
    public void createType(){
        AlertDialog.Builder builder = new AlertDialog.Builder(thisContext);
        builder.setTitle("Create New?");
        final EditText input = new EditText(thisContext);
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        builder.setView(input);
        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newType=input.getText().toString();
                String head=Constants.LOAN_ITEMS.contains(mainType)?DbKeys.LOAN_TYPE:mainType;
               if(addTypeToDB(head,newType)){
                   creoptions=Types.getSubTypeByHead(head);
                    updateSpinner(Global.global.getStringListOfTypes(creoptions),fldSubType);
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
    public void addEntry(){
        if(validateForm()){
            try {
                history.save();
                clearForm();
            }catch(Exception e){
                e.printStackTrace();
            }

        }else{
            Toast.makeText(thisContext, "Invalid Entry", Toast.LENGTH_LONG).show();
        }

    }
    public boolean addTypeToDB(String head ,String type){
        if(!type.isEmpty() || type.length()>10) {
           Types tp = new Types(head, type);
            try {
                tp.save();

            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }
    return false;
    }


    AdapterView.OnItemSelectedListener type_listener = new AdapterView.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1,
                                   int arg2, long arg3) {
            if(position==2 ){
                if(arg2==0 && arg3==0){
                    lblType.setText(Constants.LBL_TITLE);
                }else{
                    lblType.setText(Constants.LBL_FROM);
                }
            }
            if(position==4){
                if((arg2==0 && arg3==0) ||(arg2==1 && arg3==1)){
                    lblType.setText(Constants.LBL_TO);
                }else{
                    lblType.setText(Constants.LBL_TITLE);
                }
            }
            try {
                mainType=fldType.getSelectedItem().toString();
                String head=Constants.LOAN_ITEMS.contains(mainType)?DbKeys.LOAN_TYPE:mainType;
                if(Constants.LOAN_ITEMS.contains(mainType)) {
                    if(mainType.equals(Constants.LOAN_ITEMS.get(1)))
                    {
                        btnCreate.setVisibility(View.GONE);
                    }
                    else{

                        btnCreate.setVisibility(View.VISIBLE);
                    }
                    creoptions=Types.getSubTypeByHead(head);
                    updateSpinner(Global.global.getStringListOfTypes(creoptions), fldSubType);
                }else{
                    btnCreate.setVisibility(View.VISIBLE);
                    creoptions=Types.getSubTypeByHead(head);
                    updateSpinner(Global.global.getStringListOfTypes(creoptions), fldSubType);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {

        }
    };
    public boolean validateForm(){
        try {
            history.type=fldType.getSelectedItem().toString();
            history.cashOrBank=rc.isChecked() ? true : false;
            history.amount = Double.parseDouble(fldAmount.getText().toString());
            history.dcpn=fldComment.getText().toString();
            history.date = dat.getText().toString();
            history.subType=Types.getSubTypeByName(fldSubType.getSelectedItem().toString());
        }catch(Exception e)
        {
            return false;
        }

        return true;
    }
    public void clearForm(){

        fldType.setSelection(0);
        fldAmount.setText("");
        fldSubType.setSelection(0);
        fldComment.setText("");
        myCalendar=Calendar.getInstance();
        Global.global.setDate(dat);
    }

}
