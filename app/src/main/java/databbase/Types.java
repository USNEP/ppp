package databbase;


import android.content.ContentValues;

import java.util.List;

import global.Global;

public class Types {
    public Types(){}
    public long id;
    public String head;
    public String type;
    public Types( long id,String head, String type){
        super();
        this.head = head;
        this.type = type;
        this.id=id;
    }
    public Types( String head, String type){
        super();
        this.head = head;
        this.type = type;
    }
    public static boolean addType(Types type) {
        ContentValues values = new ContentValues();
        values.put(DbKeys.HEAD, type.head);
        values.put(DbKeys.TYPE, type.type);
       return Global.global.getDb().insertType(values);
    }
    public static List<Types> getSubTypeByHead(String head) {
        return Global.global.getDb().getTypes(DbKeys.HEAD + "='" + head + "'");
    }
    public static Types getSubTypeByName(String type) {
         return Global.global.getDb().getType(type);
    }
    public static boolean updateType(Types type){
        ContentValues values = new ContentValues();
        values.put(DbKeys.HEAD, type.head);
        values.put(DbKeys.TYPE, type.type);
        return Global.global.getDb().updateType(values)==0?false:true;

    }
    public static boolean deleteType(String type){
        return Global.global.getDb().deleteType(type);
    }


}
