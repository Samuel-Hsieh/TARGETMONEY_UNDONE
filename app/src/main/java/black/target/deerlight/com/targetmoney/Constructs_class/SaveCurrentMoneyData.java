package black.target.deerlight.com.targetmoney.Constructs_class;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import black.target.deerlight.com.targetmoney.DataBase.DataBase;

/**
 * Created by samuel_hsieh on 2015/10/13.
 */
public class SaveCurrentMoneyData {

    String currentMoney;
    public Context context;
    long check;

    public SaveCurrentMoneyData(Context context ,String currentMoney) {
        this.currentMoney = currentMoney;
        this.context = context;
    }

    public long SaveToDB(){
        DataBase DB = new DataBase(context);
        SQLiteDatabase sqLiteDatabase = DB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long currentMoney_int = Long.valueOf(currentMoney);
        contentValues.put("_money", currentMoney_int);
        check = sqLiteDatabase.update("money", contentValues, null, null);
        DB.close();
        return check;
    }
}
