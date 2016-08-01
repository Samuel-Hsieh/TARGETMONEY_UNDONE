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
import black.target.deerlight.com.targetmoney.Constructs_class.DrawPayoutPercent;
import black.target.deerlight.com.targetmoney.Constructs_class.ShowDate;
import black.target.deerlight.com.targetmoney.R;
import black.target.deerlight.com.targetmoney.Services.requestAuditPayoutServer;

public class AuditAccountPayout_Fragment extends Fragment{

    View v;
    List<AuditListItems> auditListItems;
    int CurrentYear,CurrentMonth,CurrentDay;
    StringBuilder Date_str,DateFormat_str;
    final String[] auditPayoutItem = {"_date","_item","_note","_payout_money"};
    String StartDate,EndDate;
    String StartDateFormat,EndDateFormat;
    ShowDate showDate;
    requestAuditPayoutServer rAPS;
    int width,height;
    public String  selectQuery;
    private TextView PayoutTotal;
    private ListView PayoutAuditAccount_listView;
    private FrameLayout PayoutPieChart_FrameLayout;
    private TextView PayoutAuditStartDate_textView;
    private TextView PayoutAuditEndDate_textView;
    private RelativeLayout PayoutStartDate_RelativeLayout;
    private RelativeLayout PayoutEndDate_RelativeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.auditaccount_payout_fragment, container, false);
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
        PayoutAuditStartDate_textView.setText(StartDate);
        PayoutAuditEndDate_textView.setText(EndDate);
        /***預先拿取PayoutPieChart_FrameLayout的寬和高*/
        PayoutPieChart_FrameLayout.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver
                .OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                //作用好像可以擋掉一直迴圈
                PayoutPieChart_FrameLayout.getViewTreeObserver().removeOnPreDrawListener(this);
                width = PayoutPieChart_FrameLayout.getWidth();
                height = PayoutPieChart_FrameLayout.getHeight();
                Log.i("AuditPayout", "getWidth :" + width + " getHeight :" + height);
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
        PayoutAuditAccount_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });
        /** StartDate_DatePickerDialog */
        PayoutStartDate_RelativeLayout.setOnClickListener(new View.OnClickListener() {
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
                        PayoutAuditStartDate_textView.setText(Date_str);
                        StartDate = Date_str.toString();
                        showListAndPie(StartDateFormat, EndDateFormat);
                    }
                }, CurrentYear, CurrentMonth, CurrentDay).show();
            }
        });
        /** EndDate_DatePickerDialog */
        PayoutEndDate_RelativeLayout.setOnClickListener(new View.OnClickListener() {
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
                        PayoutAuditEndDate_textView.setText(Date_str);
                        EndDate = Date_str.toString();
                        showListAndPie(StartDateFormat, EndDateFormat);
                    }
                }, CurrentYear, CurrentMonth, CurrentDay).show();
            }
        });
    }

    /***顯示listView和圓餅圖*/
    public void showListAndPie(String startDate ,String endDate) {
        selectQuery = "SELECT _date , _item , _note , SUM(_payout_money) FROM 'payout' WHERE _date BETWEEN '" + startDate + "' AND '" + endDate +
                    "' GROUP BY _item ORDER BY SUM(_payout_money) DESC";
        auditListItems = new ArrayList<>();
        /*** 從server取得資料並回傳 */
        rAPS = new requestAuditPayoutServer(getActivity(), auditPayoutItem, auditListItems);
        Log.i("getActivity!", "getActivity :" + getActivity());
        auditListItems = rAPS.get_AuditPayout(selectQuery);
        /*** 資料放進ListView呈現 */
        AuditListAdapter auditListAdapter = new AuditListAdapter(getActivity(),
                R.layout.auditaccount_items, auditListItems);
        PayoutAuditAccount_listView.setAdapter(auditListAdapter);
        PayoutTotal.setText(Integer.toString(rAPS.PayTotal()) + "元");
        /**顯示圓餅圖**/
        DrawPayoutPercent view = new DrawPayoutPercent(v.getContext());
        view.setArrayPersent(rAPS.getArrayPersent());
        view.invalidate();
        view.setWidth(height);   //將圓餅圖的寬度設為View的高度 (高度較短，才能確保圖都在View內)
        view.setHeight(height);  //將圓餅圖的高度設為View的高度
        LayoutParams lp = new LayoutParams(width, height);  //View的長高取決FrameLayout的寬高
        lp.setMarginStart(width / 2 - height / 2);  //往右推移(位置為置中)
        PayoutPieChart_FrameLayout.addView(view, lp);
        /****/
    }

    private void Init() {
        PayoutAuditAccount_listView = (ListView) v.findViewById(R.id.PayoutAuditAccount_listView);
        PayoutTotal = (TextView) v.findViewById(R.id.PayoutTotal_textView);
        PayoutAuditStartDate_textView = (TextView) v.findViewById(R.id.PayoutAuditStartDate_textView);
        PayoutAuditEndDate_textView = (TextView) v.findViewById(R.id.PayoutAuditEndDate_textView);
        PayoutStartDate_RelativeLayout = (RelativeLayout) v.findViewById(R.id.PayoutStartDate_RelativeLayout);
        PayoutEndDate_RelativeLayout = (RelativeLayout) v.findViewById(R.id.PayoutEndDate_RelativeLayout);
        PayoutPieChart_FrameLayout = (FrameLayout) v.findViewById(R.id.PayoutPieChart_FrameLayout);
    }

}
