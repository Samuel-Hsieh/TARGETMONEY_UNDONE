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
public class requestAuditPayoutServer {
    Context context;
    String[] audioPayoutItem;
    List<AuditListItems> auditListItems;
    int itemCount = 1,ArrayCount = 1;
    double payoutTotal = 0 ,payoutPercent = 100;
    double[] ArrayPersent;

    public requestAuditPayoutServer(Context context, String[] audioPayoutItem, List<AuditListItems> auditListItems) {
        this.context = context;
        this.audioPayoutItem = audioPayoutItem;
        this.auditListItems = auditListItems;
    }

    //取得目標資料
    public List<AuditListItems> get_AuditPayout(String selectQuery){
        Log.i("rTS_payout!","SQL :"+selectQuery);
        Log.i("context!","context :"+context);
        /** 從DB提取資料*/
        DataBase DB = new DataBase(context);
        SQLiteDatabase sqLiteDatabase = DB.getReadableDatabase();
        Cursor AuditPayout_cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        /***get Total*/
        if(AuditPayout_cursor.getCount()>0){
            AuditPayout_cursor.moveToFirst();
            payoutTotal = Double.valueOf(AuditPayout_cursor.getString(3));
            while (AuditPayout_cursor.moveToNext()){
                ArrayCount++;
                payoutTotal = payoutTotal + Double.valueOf(AuditPayout_cursor.getString(3));
            }
        }
        /*** 抓出陣列長度並且建立等長的陣列*/
        ArrayPersent = new double[ArrayCount];
        /*** 取出資料*/
        if(AuditPayout_cursor.getCount()>0){
            AuditPayout_cursor.moveToFirst();
            payoutPercent = (Double.valueOf(AuditPayout_cursor.getString(3))/payoutTotal)*100;
            payoutPercent = (double)(Math.round(payoutPercent *100))/100;
            auditListItems.add(new AuditListItems(
                    itemCount
                    , AuditPayout_cursor.getString(1)
                    , payoutPercent
                    , Long.valueOf(AuditPayout_cursor.getString(3))));
            ArrayPersent[itemCount-1] = payoutPercent; //將%數放在陣列
            while (AuditPayout_cursor.moveToNext()){
                itemCount++;
                payoutPercent = (Double.valueOf(AuditPayout_cursor.getString(3))/payoutTotal)*100;
                payoutPercent = (double)(Math.round(payoutPercent *100))/100;
                auditListItems.add(new AuditListItems(
                        itemCount
                        , AuditPayout_cursor.getString(1)
                        , payoutPercent
                        , Long.valueOf(AuditPayout_cursor.getString(3))));
                ArrayPersent[itemCount-1] = payoutPercent; //將%數放在陣列
            }
        }
        AuditPayout_cursor.close();
        DB.close();
        return auditListItems; //回傳DB的資料
    }
    public int PayTotal(){
        int Total = (int)payoutTotal;
        return Total;
    }
    public double[] getArrayPersent() {
        return ArrayPersent;
    }
}
