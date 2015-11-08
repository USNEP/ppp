package databbase;


import android.content.ContentValues;

import java.util.List;

import global.Global;
import reports.ReportData;

 public class History  {

    public History(){
        super();
    }
     public long id;
    public String type;
    public boolean cashOrBank;
    public double amount;
    public String dcpn;
    public String date;
    public long subType;
     public History(long id,String type, boolean cashOrBank, double amount,String dcpn, String date,long subType){
         super();
         this.id=id;
         this.type = type;
         this.cashOrBank = cashOrBank;
         this.dcpn = dcpn;
         this.subType = subType;
         this.date = date;
         this.amount = amount;
     }
    public History(String type, boolean cashOrBank, double amount,String dcpn, String date,long subType){
        super();
        this.type = type;
        this.cashOrBank = cashOrBank;
        this.dcpn = dcpn;
        this.subType = subType;
        this.date = date;
        this.amount = amount;
    }
    public static ReportData getAmountByHead(String head,String qry) {
        return Global.global.getDb().AmtByHead(head,qry);
    }
     public static boolean addTxns(History hst){
         ContentValues values = new ContentValues();
         values.put(DbKeys.TYPE, hst.type);
         values.put(DbKeys.CB, hst.cashOrBank?DbKeys.CB:"");
         values.put(DbKeys.DCPN, hst.dcpn);
         values.put(DbKeys.SUB_TYPE, hst.subType);
         values.put(DbKeys.DATE, hst.date);
         values.put(DbKeys.AMT, hst.amount);
         return Global.global.getDb().insertTxns(values);
     }
     public static ReportData getAmountByType(String type,String qry) {
         return Global.global.getDb().AmtByType(type, qry);
     }
     public static List<History> getIndvHistory(String qry) {
         return Global.global.getDb().indvHistory(qry);
     }
}