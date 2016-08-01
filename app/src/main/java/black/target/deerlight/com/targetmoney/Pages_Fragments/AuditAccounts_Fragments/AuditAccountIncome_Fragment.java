package black.target.deerlight.com.targetmoney.Pages_Fragments.AuditAccounts_Fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import black.target.deerlight.com.targetmoney.Adapters.AuditListAdapter;
import black.target.deerlight.com.targetmoney.Constructs_class.AuditListItems;
import black.target.deerlight.com.targetmoney.Constructs_class.DrawIncomePercent;
import black.target.deerlight.com.targetmoney.Constructs_class.ShowDate;
import black.target.deerlight.com.targetmoney.R;
import black.target.deerlight.com.targetmoney.Services.requestAuditIncomeServer;

public class AuditAccountIncome_Fragment extends Fragment {

    View v;
    List<AuditListItems> auditListItems;
    int CurrentYear,CurrentMonth,CurrentDay;
    StringBuilder Date_str,DateFormat_str;
    final String[] auditIncomeItem = {"_date","_item","_note","_income_money"};
    ShowDate showDate;
    requestAuditIncomeServer rAIS;
    int width,height;
    public String  selectQuery;
    private TextView IncomeTotal;
    private ListView IncomeAuditAccount_listView;
    private FrameLayout IncomePieChart_FrameLayout;
    private String StartDate;
    private String EndDate;
    String StartDateFormat,EndDateFormat;
    private RelativeLayout IncomeStartDate_RelativeLayout;
    private RelativeLayout IncomeEndDate_RelativeLayout;
    private TextView IncomeAuditStartDate_textView;
    private TextView IncomeAuditEndDate_textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.auditaccount_income_fragment, container, false);
        Init();
        /**取得現在日期*/
        showDate = new ShowDate(Calendar.getInstance());
        CurrentYear = showDate.getCurrentYear();
        CurrentMonth = showDate.getCurrentMonth();
        CurrentDay = showDate.getCurrentDay();
        Date_str = showDate.getDate();
        StartDateFormat = showDate.getFormatDate(); //日期格式（YYYY-MM-DD）
        EndDateFormat = showDate.getFormatDate(); //日期格式（YYYY-MM-DD）
        /**轉成String後丟給開始日期和結束日期*/
        StartDate = Date_str.toString();
        EndDate = Date_str.toString();
        IncomeAuditStartDate_textView.setText(StartDate);
        IncomeAuditEndDate_textView.setText(EndDate);
        /***預先拿取IncomePieChart_FrameLayout的寬和高*/
        IncomePieChart_FrameLayout.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver
                .OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                //作用好像可以擋掉一直迴圈
                IncomePieChart_FrameLayout.getViewTreeObserver().removeOnPreDrawListener(this);
                width = IncomePieChart_FrameLayout.getWidth();
                height = IncomePieChart_FrameLayout.getHeight();
                Log.i("AuditIncome", "getWidth :" + width + " getHeight :" + height);
                showListAndPie(StartDateFormat, EndDateFormat);
                return true;
            }
        });
        return v;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*** 按下list做一些事情 */
        IncomeAuditAccount_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });
        /** StartDate_DatePickerDialog */
        IncomeStartDate_RelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        CurrentYear = year;
                        CurrentMonth = monthOfYear;
                        CurrentDay = dayOfMonth;
                        //給使用者看的UI
                        Date_str = new StringBuilder().append(CurrentYear).append("年").
                                append(CurrentMonth + 1).append("月").append(CurrentDay).append("日");
                        /**日期格式為YYYY-MM-DD，為資料庫的時間紀錄格式**/
                        String CurrentMonth_str = Integer.toString(CurrentMonth+1);
                        String CurrentDay_str = Integer.toString(CurrentDay);
                        if(CurrentMonth+1<10){
                            CurrentMonth_str = "0"+CurrentMonth_str;
                        }
                        if(CurrentDay<10){
                            CurrentDay_str = "0"+CurrentDay_str;
                        }
                        DateFormat_str = new StringBuilder().append(CurrentYear).append("-").
                                append(CurrentMonth_str).append("-").append(CurrentDay_str);
                        StartDateFormat = DateFormat_str.toString();
                        IncomeAuditStartDate_textView.setText(Date_str);
                        StartDate = Date_str.toString();
                        showListAndPie(StartDateFormat, EndDateFormat);
                    }
                }, CurrentYear, CurrentMonth, CurrentDay).show();
            }
        });
        /** EndDate_DatePickerDialog */
        IncomeEndDate_RelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        CurrentYear = year;
                        CurrentMonth = monthOfYear;
                        CurrentDay = dayOfMonth;
                        //給使用者看的UI
                        Date_str = new StringBuilder().append(CurrentYear).append("年").
                                append(CurrentMonth + 1).append("月").append(CurrentDay).append("日");
                        /**日期格式為YYYY-MM-DD，為資料庫的時間紀錄格式**/
                        String CurrentMonth_str = Integer.toString(CurrentMonth+1);
                        String CurrentDay_str = Integer.toString(CurrentDay);
                        if(CurrentMonth+1<10){
                            CurrentMonth_str = "0"+CurrentMonth_str;
                        }
                        if(CurrentDay<10){
                            CurrentDay_str = "0"+CurrentDay_str;
                        }
                        DateFormat_str = new StringBuilder().append(CurrentYear).append("-").
                                append(CurrentMonth_str).append("-").append(CurrentDay_str);
                        EndDateFormat = DateFormat_str.toString();
                        IncomeAuditEndDate_textView.setText(Date_str);
                        EndDate = Date_str.toString();
                        showListAndPie(StartDateFormat, EndDateFormat);
                    }
                }, CurrentYear, CurrentMonth, CurrentDay).show();
            }
        });
    }

    /***顯示listView和圓餅圖*/
    public void showListAndPie(String startDate ,String endDate){
        selectQuery = "SELECT _date , _item , _note , SUM(_income_money) FROM 'income' WHERE _date BETWEEN '"+startDate+"' AND '"+endDate+
                "' GROUP BY _item ORDER BY SUM(_income_money) DESC";
        auditListItems = new ArrayList<>();
        /*** 從server取得資料並回傳 */
        rAIS = new requestAuditIncomeServer(getActivity() ,auditIncomeItem, auditListItems);
        auditListItems = rAIS.get_AuditIncome(selectQuery);
        /*** 資料放進ListView呈現 */
        AuditListAdapter auditListAdapter = new AuditListAdapter(getActivity(),
                R.layout.auditaccount_items ,auditListItems);
        IncomeAuditAccount_listView.setAdapter(auditListAdapter);
        IncomeTotal.setText(Integer.toString(rAIS.PayTotal()) + "元");
        /**顯示圓餅圖**/
        DrawIncomePercent view = new DrawIncomePercent(v.getContext());
        view.setArrayPersent(rAIS.getArrayPersent());
        view.invalidate();
        view.setWidth(height);   //將圓餅圖的寬度設為View的高度 (高度較短，才能確保圖都在View內)
        view.setHeight(height);  //將圓餅圖的高度設為View的高度
        LayoutParams lp = new LayoutParams(width, height);  //View的長高取決FrameLayout的寬高
        lp.setMarginStart(width/2-height/2);  //往右推移(位置為置中)
        IncomePieChart_FrameLayout.addView(view,lp);
        /****/
    }

    private void Init() {
        IncomeAuditAccount_listView = (ListView) v.findViewById(R.id.IncomeAuditAccount_listView);
        IncomeTotal = (TextView) v.findViewById(R.id.IncomeTotal_textView);
        IncomeAuditStartDate_textView = (TextView) v.findViewById(R.id.IncomeAuditStartDate_textView);
        IncomeAuditEndDate_textView = (TextView) v.findViewById(R.id.IncomeAuditEndDate_textView);
        IncomeStartDate_RelativeLayout = (RelativeLayout) v.findViewById(R.id.IncomeStartDate_RelativeLayout);
        IncomeEndDate_RelativeLayout = (RelativeLayout) v.findViewById(R.id.IncomeEndDate_RelativeLayout);
        IncomePieChart_FrameLayout = (FrameLayout) v.findViewById(R.id.IncomePieChart_FrameLayout);
    }
}
