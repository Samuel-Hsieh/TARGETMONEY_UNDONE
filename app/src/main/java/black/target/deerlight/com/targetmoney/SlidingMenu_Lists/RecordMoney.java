package black.target.deerlight.com.targetmoney.SlidingMenu_Lists;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import black.target.deerlight.com.targetmoney.Constructs_class.ShowDate;
import black.target.deerlight.com.targetmoney.CustomDialog.IncomeDialog;
import black.target.deerlight.com.targetmoney.CustomDialog.PayoutDialog;
import black.target.deerlight.com.targetmoney.R;
import black.target.deerlight.com.targetmoney.Services.requestCurrentMoneyServer;

/**
 * Created by samuel_hsieh on 15/9/22.
 */
public class RecordMoney extends Fragment{

    View v;
    Button payout_btn,income_btn;
    TextView changeDate_textView,showDate_textView,surplusMoney_textView;
    EditText RdMoney_editText;
    IncomeDialog incomeDialog ;
    PayoutDialog payoutDialog;
    StringBuilder Date_str,DateFormat_str;
    int CurrentYear,CurrentMonth,CurrentDay;
    String DateFormat,showMoney;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.recordmoney_fragment, container, false);
        Init();
        /*** 顯示現在日期時間 */
        final ShowDate showDate = new ShowDate(Calendar.getInstance());
        CurrentYear = showDate.getCurrentYear();
        CurrentMonth = showDate.getCurrentMonth();
        CurrentDay = showDate.getCurrentDay();
        Date_str = showDate.getDate();
        DateFormat = showDate.getFormatDate(); //日期格式（YYYY-MM-DD）
        showDate_textView.setText(Date_str);

        return v;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /** 支出按鈕 */
        payout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Date = DateFormat.toString();
                String RdMoney = RdMoney_editText.getText().toString().trim();
                if(RdMoney.isEmpty() || Integer.valueOf(RdMoney) == 0){
                    Toast.makeText(getActivity(),getString(R.string.isMoneyEmpty),Toast.LENGTH_SHORT).show();
                }
                else {
                    /*** 帶值給PayoutDialog（一併加入了日期和金額） */
                    payoutDialog = new PayoutDialog(getActivity() ,Date ,RdMoney ,R.style.RdMoney_Dialog,v);
                    payoutDialog.InitPayoutDialog();
                    RdMoney_editText.setText("");
                }
            }
        });
        /** 收入按鈕 */
        income_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Date = DateFormat.toString();
                String RdMoney = RdMoney_editText.getText().toString().trim();
                if(RdMoney.isEmpty() || Integer.valueOf(RdMoney) == 0){
                    Toast.makeText(getActivity(), getString(R.string.isMoneyEmpty), Toast.LENGTH_SHORT).show();
                } else {
                    /*** 帶值給IncomeDialog（一併加入了日期和金額） */
                    incomeDialog = new IncomeDialog(getActivity(), Date, RdMoney, R.style.RdMoney_Dialog , v);
                    incomeDialog.InitIncomeDialog();
                    RdMoney_editText.setText("");
                }
            }
        });
        /** DatePickerDialog */
        changeDate_textView.setOnClickListener(new View.OnClickListener() {
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
                        showDate_textView.setText(Date_str);
                        /**日期格式為YYYY-MM-DD，為資料庫的時間紀錄格式**/
                        String CurrentMonth_str = Integer.toString(CurrentMonth + 1);
                        String CurrentDay_str = Integer.toString(CurrentDay);
                        if (CurrentMonth + 1 < 10) {
                            CurrentMonth_str = "0" + CurrentMonth_str;
                        }
                        if (CurrentDay < 10) {
                            CurrentDay_str = "0" + CurrentDay_str;
                        }
                        DateFormat_str = new StringBuilder().append(CurrentYear).append("-").
                                append(CurrentMonth_str).append("-").append(CurrentDay_str);
                        DateFormat = DateFormat_str.toString();
                    }
                }, CurrentYear, CurrentMonth, CurrentDay).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getMoney();
        surplusMoney_textView.setText(showMoney);
    }

    private String getMoney(){
        requestCurrentMoneyServer rCMS = new requestCurrentMoneyServer(getActivity());
        showMoney = Long.toString(rCMS.get_CurrentMoney());
        showMoney = showMoney+"元";
        return showMoney;
    }

    private void Init(){
        payout_btn = (Button) v.findViewById(R.id.payout_btn);
        income_btn = (Button) v.findViewById(R.id.income_btn);
        changeDate_textView = (TextView) v.findViewById(R.id.changeDate_textView);
        showDate_textView = (TextView) v.findViewById(R.id.showDate_textView);
        surplusMoney_textView = (TextView) v.findViewById(R.id.surplusMoney_textView);
        RdMoney_editText = (EditText) v.findViewById(R.id.RdMoney_editText);
    }
}