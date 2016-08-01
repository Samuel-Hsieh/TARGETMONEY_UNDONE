package black.target.deerlight.com.targetmoney.Constructs_class;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import black.target.deerlight.com.targetmoney.DataBase.DataBase;

/**
 * Created by samuel_hsieh on 2015/10/19.
 */
public class SavePayoutData {
    Context context;
    String DateFormat;
    String selectedItem;
    String note;
    String payoutMoney;
    long check;

    public SavePayoutData(Context context, String dateFormat, String selectedItem, String note, String payoutMoney) {
        this.context = context;
        this.DateFormat = dateFormat;
        this.selectedItem = selectedItem;
        this.note = note;
        this.payoutMoney = payoutMoney;
    }
    public long SaveToDB() {
        DataBase DB = new DataBase(context);
        SQLiteDatabase sqLiteDatabase = DB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long payoutMoney_int = Long.valueOf(payoutMoney);
        contentValues.put("_date", DateFormat);
        contentValues.put("_item", selectedItem);
        contentValues.put("_note", note);
        contentValues.put("_payout_money", payoutMoney_int);
        check = sqLiteDatabase.insert("payout", null, contentValues);
        DB.close();
        return check;
    }
}
