package black.target.deerlight.com.targetmoney.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by samuel_hsieh on 2015/10/13.
 */
public class DataBase extends SQLiteOpenHelper{
    private final static int _DBVersion = 1; //版本
    private final static String _DBName = "Target_Money.db";  //資料庫名稱
    private final static String _Target = "target"; //資料表名稱(目標)
    private final static String _Payout = "payout"; //資料表名稱(支出)
    private final static String _Income = "income"; //資料表名稱(收入)
    private final static String _Money = "money"; //資料表名稱(目前金額)
    private final static String _Password = "password"; //資料表名稱(密碼)

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBase(Context context) {
        super(context, _DBName, null, _DBVersion);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
         final String targetSQL = "CREATE TABLE IF NOT EXISTS "+ _Target + "( " +
                 "_targetName VARCHAR NOT NULL, " +
                 "_targetDetail VARCHAR NOT NULL, " +
                 "_targetMoney VARCHAR NOT NULL, " +
                 "_targetColor VARCHAR NOT NULL " +
                 ");";
        db.execSQL(targetSQL);
         final String RdMoneyPayoutSQL = "CREATE TABLE IF NOT EXISTS " + _Payout + "( " +
                 "_date DATE NULL, " +
                 "_item VARCHAR NULL, " +
                 "_note VARCHAR NULL, " +
                 "_payout_money VARCHAR NULL " +
                 ");";
        db.execSQL(RdMoneyPayoutSQL);
        final String RdMoneyIncomeSQL = "CREATE TABLE IF NOT EXISTS " + _Income + "( " +
                "_date DATE NULL, " +
                "_item VARCHAR NULL, " +
                "_note VARCHAR NULL, " +
                "_income_money VARCHAR NULL " +
                ");";
        db.execSQL(RdMoneyIncomeSQL);
        final String CurrentMoneySQL = "CREATE TABLE IF NOT EXISTS " + _Money + "( " +
                "_money VARCHAR NULL " +
                ");";
        db.execSQL(CurrentMoneySQL);
        //初始新增money=0元
        ContentValues money_value = new ContentValues();
        money_value.put("_money", 0);
        db.insert(_Money, null, money_value);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String target = "DROP TABLE " + _Target;
        db.execSQL(target);
        final String RdMoneyPayout = "DROP TABLE " +_Payout;
        db.execSQL(RdMoneyPayout);
        final String RdMoneyIncome = "DROP TABLE " +_Income;
        db.execSQL(RdMoneyIncome);
        final String CurrentMoney = "DROP TABLE " +_Money;
        db.execSQL(CurrentMoney);
    }
}
