package black.target.deerlight.com.targetmoney.Constructs_class;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import black.target.deerlight.com.targetmoney.DataBase.DataBase;

/**
 * Created by samuel_hsieh on 2015/11/6.
 */
public class DeleteTargetData {
    Context context;
    String targetName;
    private String deleteQuery;

    public DeleteTargetData(Context context, String targetName) {
        this.targetName = targetName;
        this.context = context;
    }
    public void DeleteData(){
        DataBase DB = new DataBase(context);
        SQLiteDatabase sqLiteDatabase = DB.getWritableDatabase();
        deleteQuery = "DELETE FROM target WHERE _targetName = '"+targetName+"'";
        sqLiteDatabase.execSQL(deleteQuery);
    }
}
