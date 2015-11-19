package databbase;

import java.util.ArrayList;
import java.util.Arrays;

import global.Constants;

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
    public static String getCashInQry(String cashOrBank){
       return "SELECT SUM("+DbKeys.AMT+") FROM "+DbKeys.TXNS+" WHERE ("+qryBuild(Constants.MONEY_IN_TYPES)+") AND "+
               DbKeys.CB+" ='"+cashOrBank+"';";
           }
    public static String getCashOutQry(String cashOrBank){
        ArrayList<String> outList = new ArrayList(Arrays.asList(Constants.EXPENSES_TYPES));
        outList.addAll(Arrays.asList(Constants.MONEY_OUT_TYPES));
        return "SELECT SUM("+DbKeys.AMT+") FROM "+DbKeys.TXNS+" WHERE ("+
                qryBuild(outList.toArray(new String[outList.size()]))+") AND "+
                DbKeys.CB+" ='"+cashOrBank+"';";
    }
    public  static  String getLoanInQry(){
         return "SELECT SUM("+DbKeys.AMT+") FROM "+DbKeys.TXNS+" WHERE "+DbKeys.TYPE+"='"+Constants.MONEY_IN_TYPES[1]+"';";
    }
    public static  String getLoanOutQry(){
        return "SELECT SUM("+DbKeys.AMT+") FROM "+DbKeys.TXNS+" WHERE "+DbKeys.TYPE+"='"+
                Constants.MONEY_OUT_TYPES[0]+"' OR "+DbKeys.TYPE+"='"+Constants.MONEY_OUT_TYPES[1]+"';";

    }
    public static  String getInvstQry(){
        return "SELECT SUM("+DbKeys.AMT+") FROM "+DbKeys.TXNS+" WHERE "+DbKeys.TYPE+"='"+
                Constants.MONEY_OUT_TYPES[2]+"';";

    }
    public static String qryBuild( String[] items){
        int n=items.length;
        String qry="";
        for (int i=0;i<n-1;i++){
         qry=qry+DbKeys.TYPE+" = '"+items[i]+"' OR ";
        }
        qry=qry+DbKeys.TYPE+" = '"+items[n-1]+"'";
        return qry;
    }
    public static String getCashToBank(){
        return "SELECT SUM("+DbKeys.AMT+") FROM "+DbKeys.TXNS+" WHERE "+DbKeys.TYPE+"='"+
                DbKeys.CASHTOBANK+"';";
    }
    public static String getBankToCash(){
        return "SELECT SUM("+DbKeys.AMT+") FROM "+DbKeys.TXNS+" WHERE "+DbKeys.TYPE+"='"+
                DbKeys.BANKTOCASH+"';";
    }

   // public static final  String TXNSQRY="SELECT  * FROM " + DbKeys.TXNS+" ORDER BY "+DbKeys.DATE+" DESC LIMIT["+Constants.NUMBER_OF_Txns+"];";
    public static final  String TXNSQRY="SELECT  * FROM " + DbKeys.TXNS+" ORDER BY "+DbKeys.ID+" DESC LIMIT '"+Constants.NUMBER_OF_Txns+"';";
}
