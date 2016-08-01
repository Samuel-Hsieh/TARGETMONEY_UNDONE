package black.target.deerlight.com.targetmoney.Constructs_class;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import black.target.deerlight.com.targetmoney.DataBase.DataBase;

/**
 * Created by samuel_hsieh on 2015/11/7.
 */
public class UpdateTargetData {
    Context context;
    String targetName;
    String targetDetail;
    String targetMoney;
    String targetColor;
    String Old_targetTitle;
    long check;

    public UpdateTargetData(Context context, String targetName, String targetDetail, String targetMoney, String targetColor ,String Old_targetTitle) {
        this.context = context;
        this.targetName = targetName;
        this.targetDetail = targetDetail;
        this.targetMoney = targetMoney;
        this.targetColor = targetColor;
        this.Old_targetTitle = Old_targetTitle;
    }

    public long UpdateData(){
        DataBase DB = new DataBase(context);
        SQLiteDatabase sqLiteDatabase = DB.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("_targetName", targetName);
        value.put("_targetDetail", targetDetail);
        value.put("_targetMoney", targetMoney);
        value.put("_targetColor", targetColor);
        check = sqLiteDatabase.update("target", value, "_targetName='"
                +Old_targetTitle+ "'", null);
        DB.close();
        return check;
    }
}
