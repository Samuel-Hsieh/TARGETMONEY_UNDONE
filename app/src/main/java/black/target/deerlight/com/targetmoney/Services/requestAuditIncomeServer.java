package black.target.deerlight.com.targetmoney.Services;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import black.target.deerlight.com.targetmoney.Constructs_class.AuditListItems;
import black.target.deerlight.com.targetmoney.DataBase.DataBase;

/**
 * Created by samuel_hsieh on 2015/10/15.
 */
public class requestAuditIncomeServer {
    Context context;
    String[] audioIncomeItem;
    List<AuditListItems> auditListItems;
    int itemCount = 1,ArrayCount = 1;
    double incomeTotal = 0 ,incomePercent = 100;
    StringBuilder Date_str;
    double[] ArrayPersent;

    public requestAuditIncomeServer(Context context, String[] audioIncomeItem, List<AuditListItems> auditListItems) {
        this.context = context;
        this.audioIncomeItem = audioIncomeItem;
        this.auditListItems = auditListItems;
    }

    //取得目標資料
    public List<AuditListItems> get_AuditIncome(String selectQuery){
        Log.i("rTS_income!", "SQL :" + selectQuery);
        /** 從DB提取資料*/
        DataBase DB = new DataBase(context);
        SQLiteDatabase sqLiteDatabase = DB.getReadableDatabase();
        Cursor AuditIncome_cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        /***get Total*/
        if(AuditIncome_cursor.getCount()>0){
            AuditIncome_cursor.moveToFirst();
            incomeTotal = Double.valueOf(AuditIncome_cursor.getString(3));
            while (AuditIncome_cursor.moveToNext()){
                ArrayCount++;
                incomeTotal = incomeTotal + Double.valueOf(AuditIncome_cursor.getString(3));
            }
        }
        /*** 抓出陣列長度並且建立等長的陣列*/
        ArrayPersent = new double[ArrayCount];
        /*** 取出資料*/
        if(AuditIncome_cursor.getCount()>0){
            AuditIncome_cursor.moveToFirst();
            incomePercent = (Double.valueOf(AuditIncome_cursor.getString(3))/incomeTotal)*100;
            incomePercent = (double)(Math.round(incomePercent *100))/100;
            auditListItems.add(new AuditListItems(
                    itemCount
                    , AuditIncome_cursor.getString(1)
                    , incomePercent
                    , Long.valueOf(AuditIncome_cursor.getString(3))));
            ArrayPersent[itemCount-1] = incomePercent; //將%數放在陣列
            while (AuditIncome_cursor.moveToNext()){
                itemCount++;
                incomePercent = (Double.valueOf(AuditIncome_cursor.getString(3))/incomeTotal)*100;
                incomePercent = (double)(Math.round(incomePercent *100))/100;
                auditListItems.add(new AuditListItems(
                        itemCount
                        , AuditIncome_cursor.getString(1)
                        , incomePercent
                        , Long.valueOf(AuditIncome_cursor.getString(3))));
                ArrayPersent[itemCount-1] = incomePercent; //將%數放在陣列
            }
        }
        AuditIncome_cursor.close();
        DB.close();
        return auditListItems; //回傳DB的資料
    }
    public StringBuilder getDate(){
        return Date_str;
    }
    public int PayTotal(){
        int Total = (int)incomeTotal;
        return Total;
    }
    public void setDate_str(StringBuilder date_str) {
        Date_str = date_str;
    }
    public double[] getArrayPersent() {
        return ArrayPersent;
    }
}
