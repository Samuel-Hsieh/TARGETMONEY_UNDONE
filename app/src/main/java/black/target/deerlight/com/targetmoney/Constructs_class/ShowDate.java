package black.target.deerlight.com.targetmoney.Constructs_class;

import java.util.Calendar;
/**
 * Created by samuel_hsieh on 2015/10/21.
 */
public class ShowDate {
    int CurrentYear,CurrentMonth,CurrentDay;
    private StringBuilder Date_str;

    public ShowDate(Calendar calender) {
        CurrentYear = calender.get(Calendar.YEAR);  //年
        CurrentMonth = calender.get(Calendar.MONTH);  //月
        CurrentDay = calender.get(Calendar.DAY_OF_MONTH);  //日
    }

    public StringBuilder getDate(){
        /*** 顯示現在日期時間 */
        Date_str = new StringBuilder().append(CurrentYear).append("年").
                append(CurrentMonth+1).append("月").append(CurrentDay).append("日");
        return Date_str;
    }
    public String getFormatDate(){
        String CurrentMonth_str = Integer.toString(CurrentMonth+1);
        String CurrentDay_str = Integer.toString(CurrentDay);
        if(CurrentMonth+1<10){
            CurrentMonth_str = "0"+CurrentMonth_str;
        }
        if(CurrentDay<10){
            CurrentDay_str = "0"+CurrentDay_str;
        }
        Date_str = new StringBuilder().append(CurrentYear).append("-").
                    append(CurrentMonth_str).append("-").append(CurrentDay_str);
        String Date = Date_str.toString();
        return Date;
    }

    public int getCurrentYear() {
        return CurrentYear;
    }

    public int getCurrentMonth() {
        return CurrentMonth;
    }

    public int getCurrentDay() {
        return CurrentDay;
    }
}
