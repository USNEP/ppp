package databbase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import reports.ReportData;

public class DataBaseHandler extends SQLiteOpenHelper {



    public DataBaseHandler(Context context) {
        super(context, DbKeys.DB_NAME, null, DbKeys.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Querys.CREATE_TXNS_TABLE);
        db.execSQL(Querys.CREATE_TYPE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + DbKeys.TXNS);
            db.execSQL("DROP TABLE IF EXISTS " + DbKeys.TYPES);
        }catch(Exception e){
            e.printStackTrace();
        }
        onCreate(db);
    }

    public  boolean insertTxns(ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
        db.insert(DbKeys.TXNS, null, values);
        System.out.println("Txns added >>>>>>>>>>");
        db.close(); // Closing database connection
        }catch(Exception e) {
            db.close();
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public  boolean insertType(ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.insert(DbKeys.TYPES, null, values);
            System.out.println("Type added >>>>>>>>>>");
            db.close();
        }catch(Exception e) {
            db.close();
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public Types getType(String type) {
        SQLiteDatabase db = this.getReadableDatabase();
        System.out.println("&&&&&&&&&&&&&&&&&&&&"+type);
        Cursor cursor = db.query(DbKeys.TYPES, new String[]{DbKeys.ID,DbKeys.HEAD,DbKeys.TYPE}, DbKeys.TYPE + "=?",
                new String[]{type}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return new Types(Long.parseLong(cursor.getString(0)),cursor.getString(1),cursor.getString(2));
      }

    public List<Types> getTypes(String type) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Types> contactList = new ArrayList<Types>();
        Cursor cursor = db.query(DbKeys.TYPES, new String[]{DbKeys.ID,DbKeys.TYPE},type , null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Types t=new Types();
                t.id=(Long.parseLong(cursor.getString(0)));
                t.type=(cursor.getString(1));
                contactList.add(t);
            } while (cursor.moveToNext());
        }

        return contactList;
    }

    public int updateType(ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println("****************"+values.get(DbKeys.TYPE).toString()+"***********************************");
        return db.update(DbKeys.TYPES, values, DbKeys.TYPE + " = ?",
                new String[] { values.get(DbKeys.TYPE).toString() });
    }
//
    public ReportData AmtByType(String type,String qry){
        ReportData rpData=new ReportData();
        System.out.println(qry);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(qry, null);
        if (cursor.moveToFirst()) {
            do {try {
                rpData.setAmount(Double.parseDouble(cursor.getString(0)));
            }catch(Exception e){
                e.printStackTrace();
                rpData.setAmount(0);

            }
                rpData.setType(type);
            } while (cursor.moveToNext());
        }
        System.out.println("Amount::::::::::" + rpData.getAmount());
        return rpData;
    }
    public ReportData AmtByHead(String head,String query){
        ReportData rpData=new ReportData();
        System.out.println(query);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {try {
                rpData.setAmount(Double.parseDouble(cursor.getString(0)));
            }catch(Exception e){
                e.printStackTrace();
                rpData.setAmount(0);
            }
                rpData.setType(head);
            } while (cursor.moveToNext());
        }
        System.out.println("Amount::::::::::"+rpData.getAmount());
        return rpData;
    }
    public List<History> indvHistory(String qry) {
        List<History> contactList = new ArrayList<History>();
        System.out.println(qry);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(qry, null);
        if (cursor.moveToFirst()) {
            do {
                History contact = new History();
                contact.id=Long.parseLong(cursor.getString(0));
                contact.type=(cursor.getString(1));
                contact.cashOrBank=(cursor.getString(2) == DbKeys.CB ? true : false);
                contact.amount=(Double.valueOf(cursor.getString(3)));
                contact.dcpn=(cursor.getString(4));
                contact.date=(cursor.getString(5));
                contact.subType=Long.parseLong(cursor.getString(6));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
    }
    public double getStatus(String query){
        System.out.println(query);
        double amt=0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                try {
                    amt = Double.parseDouble(cursor.getString(0));
                }catch(Exception e){
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }
        return amt;
    }

    public boolean deleteType(String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(DbKeys.TYPES, DbKeys.TYPE + " = ?",
                    new String[]{type});
        }catch(Exception e){
            db.close();
            return false;
        }
        db.close();
        return true;
    }
    public boolean deleteHistoryByType(String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
        db.delete(DbKeys.TXNS, DbKeys.SUB_TYPE + " = ?",
                new String[]{type});
        }catch(Exception e){
            db.close();
            return false;
        }
        db.close();
        return true;
    }
    public List<History> getLast30Txns(String qry) {
        List<History> contactList = new ArrayList<History>();
        System.out.println(qry);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(qry, null);
        if (cursor.moveToFirst()) {
            do {
                History contact = new History();
                contact.id=Long.parseLong(cursor.getString(0));
                contact.type=(cursor.getString(1));
                contact.cashOrBank=(cursor.getString(2) == DbKeys.CB ? true : false);
                contact.amount=(Double.valueOf(cursor.getString(3)));
                contact.dcpn=(cursor.getString(4));
                contact.date=(cursor.getString(5));
                contact.subType=Long.parseLong(cursor.getString(6));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
    }
}