package black.target.deerlight.com.targetmoney.Services;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import black.target.deerlight.com.targetmoney.DataBase.DataBase;

/**
 * Created by samuel_hsieh on 2015/11/2.
 */
public class requestCurrentMoneyServer {
    public Context context;
    String[] Money = {"_money"};

    public requestCurrentMoneyServer(Context context) {
        this.context = context;
    }

    public long get_CurrentMoney(){
        long CurrenyMoney_long;
        DataBase DB = new DataBase(context);
        SQLiteDatabase sqLiteDatabase = DB.getReadableDatabase();
        Cursor CurrentMoney_cursor = sqLiteDatabase.query("money", Money, null, null, null, null, null);
        try{
            CurrentMoney_cursor.moveToFirst();
            CurrenyMoney_long = Long.valueOf(CurrentMoney_cursor.getString(0));
        }finally {
            CurrentMoney_cursor.close();
            DB.close();
        }
        return CurrenyMoney_long;
    }
}
