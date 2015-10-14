package global;

import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final int NUMBER_OF_TABS =5;
    public static final  String[] TAB_NAMES ={"Home","Money In","Expenses","Money Out","Report"};
    public static final String ARG_SECTION_NUMBER = "section_number";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final  String[] MONEY_IN_TYPES={"Income","Loan Taken"};
    public static final  String[] EXPENSES_TYPES={"Day to Day Expenses","Periodic Expenses","Educational Expenses","Other Expenses"};
    public static final  String[] MONEY_OUT_TYPES={"Loan Paid","Loan Given","Investment"};
    public static final String LBL_SOURCE = "Source :";
    public static final String LBL_TITLE = "Title :";
    public static final List<String> LOAN_ITEMS=Arrays.asList(MONEY_IN_TYPES[1], MONEY_OUT_TYPES[0], MONEY_OUT_TYPES[1]);
    public static final String LBL_EXPENSE_TYPE = "Expense Type :";
    public static final String LBL_EXPENSE_HEAD = "Expense Head :";
    public static final String LBL_TO = "To :";
    public static final String LBL_OUT_FOR = "Out For :";
    public static final String LBL_FROM = "From :";
    public static final String ARG_FRGMT = "drawer";
    public static final List<String> REPORT_LIST_INCOME=Arrays.asList(MONEY_IN_TYPES[0]);
    public static final List<String> REPORT_LIST_EXPENSE=Arrays.asList(TAB_NAMES[2]);
    public static final List<String> REPORT_LIST_LOANS=Arrays.asList("Loans");
    public static final List<String> REPORT_LIST_INVESTMENT=Arrays.asList(MONEY_OUT_TYPES[2]);





}
