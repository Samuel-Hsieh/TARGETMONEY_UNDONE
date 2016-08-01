package black.target.deerlight.com.targetmoney.Constructs_class;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import black.target.deerlight.com.targetmoney.DataBase.DataBase;

/**
 * Created by samuel_hsieh on 2015/10/13.
 */
public class SaveTargetData {
    String targetName;
    String targetDetail;
    String targetMoney;
    public String targetColor;
    public Context context;
    long check;

    public SaveTargetData(Context context ,String targetName, String targetDetail,
                          String targetMoney ,String targetColor) {
        this.context = context;
        this.targetName = targetName;
        this.targetDetail = targetDetail;
        this.targetMoney = targetMoney;
        this.targetColor = targetColor;
    }
    public long SaveToDB(){
        DataBase DB = new DataBase(context);
        SQLiteDatabase sqLiteDatabase = DB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long targetMoney_int = Long.valueOf(targetMoney);
        contentValues.put("_targetName", targetName);
        contentValues.put("_targetDetail", targetDetail);
        contentValues.put("_targetMoney", targetMoney_int);
        contentValues.put("_targetColor", targetColor);
        check = sqLiteDatabase.insert("target", null, contentValues);
        DB.close();
        return check;
    }
}
