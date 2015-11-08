package databbase;

public class Querys {

    public static final  String CREATE_TXNS_TABLE = "CREATE TABLE " + DbKeys.TXNS+ "("
            + DbKeys.ID+ " INTEGER PRIMARY KEY," + DbKeys.TYPE + " TEXT,"
            + DbKeys.CB + " TEXT,"+ DbKeys.AMT + " TEXT," +DbKeys.DCPN + " TEXT,"+DbKeys.DATE + " TEXT,"
            +DbKeys.SUB_TYPE + " TEXT "+ ")";
    public static final String CREATE_TYPE_TABLE = "CREATE TABLE " + DbKeys.TYPES + "("
            + DbKeys.ID + " INTEGER PRIMARY KEY," + DbKeys.HEAD + " TEXT,"
            + DbKeys.TYPE  + "  TEXT NOT NULL UNIQUE)";
    public static String getAmtByHeadQry(String head,String fromDate,String toDate){
        return "SELECT SUM("+DbKeys.AMT+") FROM "+DbKeys.TXNS+" WHERE "+DbKeys.TYPE+" = '"+head+
               "' AND "+DbKeys.DATE+" >= '"+fromDate+"' AND "+DbKeys.DATE+" <='"+toDate+"';";
    }
    public static String getAmtByTypeQry(String type,String[] argArray){
        return "SELECT SUM("+DbKeys.AMT+") FROM "+DbKeys.TXNS+" WHERE "+DbKeys.SUB_TYPE+" = '"+type+
                "' AND "+DbKeys.TYPE+" = '"+argArray[0]+
                "' AND "+DbKeys.DATE+" >= '"+argArray[1]+"' AND "+DbKeys.DATE+" <='"+argArray[2]+"';";
    }
    public static String getHistoryQry(String typeId,String[] argArray){
        return  "SELECT  * FROM " + DbKeys.TXNS+" WHERE "+DbKeys.SUB_TYPE+" = '"+typeId+"' AND "+DbKeys.TYPE+" = '"+
        argArray[0]+"' AND "+DbKeys.DATE+" >= '"+argArray[2]+"' AND "+DbKeys.DATE+" <='"+argArray[3]+"';";
    }
}
