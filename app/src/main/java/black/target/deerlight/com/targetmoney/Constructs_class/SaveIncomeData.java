package black.target.deerlight.com.targetmoney.Constructs_class;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import black.target.deerlight.com.targetmoney.DataBase.DataBase;

/**
 * Created by samuel_hsieh on 2015/10/19.
 */
public class SaveIncomeData {
    Context context;
    String DateFormat;
    String selectedItem;
    String note;
    String incomeMoney;
    long check;

    public SaveIncomeData(Context context, String dateFormat, String selectedItem, String note, String incomeMoney) {
        this.context = context;
        this.DateFormat = dateFormat;
        this.selectedItem = selectedItem;
        this.note = note;
        this.incomeMoney = incomeMoney;
    }
    public long SaveToDB() {
        DataBase DB = new DataBase(context);
        SQLiteDatabase sqLiteDatabase = DB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long incomeMoney_int = Long.valueOf(incomeMoney);
        contentValues.put("_date", DateFormat);
        contentValues.put("_item", selectedItem);
        contentValues.put("_note", note);
        contentValues.put("_income_money", incomeMoney_int);
        check = sqLiteDatabase.insert("income", null, contentValues);
        DB.close();
        return check;
    }
}
