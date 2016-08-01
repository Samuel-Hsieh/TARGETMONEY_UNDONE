package black.target.deerlight.com.targetmoney.Services;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import black.target.deerlight.com.targetmoney.Constructs_class.TargetListItems;
import black.target.deerlight.com.targetmoney.DataBase.DataBase;

/**
 * Created by samuel_hsieh on 2015/10/15.
 */
public class requestTargetServer {
    Context context;
    String[] targetItem;
    List<TargetListItems> targetListItems;
    int i=0;

    public requestTargetServer(Context context, String[] targetItem, List<TargetListItems> targetListItems) {
        this.context = context;
        this.targetItem = targetItem;
        this.targetListItems = targetListItems;
    }
    //取得目標資料
    public List<TargetListItems> get_target(){
        DataBase DB = new DataBase(context);
        SQLiteDatabase sqLiteDatabase = DB.getReadableDatabase();
        Cursor target_cursor = sqLiteDatabase.query("target",targetItem,null, null, null, null, null);
        if(target_cursor.getCount()>0){
            target_cursor.moveToFirst();
            targetListItems.add(new TargetListItems(i, target_cursor.getString(0),
                    target_cursor.getString(1), 20000, Long.valueOf(target_cursor.getString(2)),
                    target_cursor.getString(3)));
            while (target_cursor.moveToNext()){
                i++;
                targetListItems.add(new TargetListItems(i, target_cursor.getString(0),
                        target_cursor.getString(1), 20000, Long.valueOf(target_cursor.getString(2)),
                        target_cursor.getString(3)));
            }
        }
        target_cursor.close();
        DB.close();
        return targetListItems; //回傳DB的資料
    }
}
