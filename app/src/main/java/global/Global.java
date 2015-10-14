package global;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import databbase.Types;

/**
 * Created by ashok on 9/28/15.
 */
public class Global {


    public static Global global;
    Calendar myCalendar;
    EditText edtText;
    SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.US);

    public EditText getEdtText() {
        return edtText;
    }

    public void setEdtText(EditText edtText) {
        this.edtText = edtText;
    }

    private Global(){ }
    public static Global getInstance( ) {
        if (global == null) {
            global = new Global();

        }
        return global;
    }
    public  void showDatePicker( Context thisContext,EditText txt){
        setEdtText(txt);
        myCalendar=Calendar.getInstance();
        new DatePickerDialog(thisContext, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();

    }
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {
        getEdtText().setText(sdf.format(myCalendar.getTime()));
    }
    public void setDate(EditText txt){
        myCalendar=Calendar.getInstance();
        txt.setText(sdf.format(myCalendar.getTime()));
    }
    public List<String> getStringListOfTypes(List<Types> types){
        List<String> s=new ArrayList<String>();
        for(Types t:types)
        {
            s.add(t.type);
        }
        return s;
    }
}
