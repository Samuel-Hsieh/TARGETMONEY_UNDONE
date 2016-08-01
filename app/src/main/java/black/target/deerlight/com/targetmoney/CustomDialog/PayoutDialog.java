package black.target.deerlight.com.targetmoney.CustomDialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import black.target.deerlight.com.targetmoney.Adapters.RdMoneyListAdapter;
import black.target.deerlight.com.targetmoney.Constructs_class.RdMoneyListItems;
import black.target.deerlight.com.targetmoney.Constructs_class.SaveCurrentMoneyData;
import black.target.deerlight.com.targetmoney.Constructs_class.SavePayoutData;
import black.target.deerlight.com.targetmoney.R;
import black.target.deerlight.com.targetmoney.Services.requestCurrentMoneyServer;

/**
 * Created by samuel_hsieh on 15/8/26.
 */
public class PayoutDialog extends Dialog implements android.view.View.OnClickListener{
    Context mContext;
    PayoutDialog dialog;
    Button payout_ok_btn,payout_cancel_btn;
    EditText Note_EditText;
    TextView surplusMoney_textView;
    ListView payout_listview;
    List<RdMoneyListItems> rdMoneyListItemses;
    String Itemlist[] = {"早餐","午餐","晚餐","宵夜","嘴饞",
            "生活用品","帳單","汽柴油錢","女友開銷","男友開銷","禮物","交通費","孝敬費","應酬","醫療費"};
    String selectedItem;
    String Date;
    String RdMoney;
    String CurrentMoney_str;
    long CurrentMoney_long,RdMoney_long;
    long check;
    int i;
    View v;

    public PayoutDialog(Context context ,String Date ,String RdMoney ,int theme, View v) {
        super(context, theme);
        this.mContext=context;
        this.Date = Date;
        this.RdMoney = RdMoney;
        this.v = v;
    }
    public void InitPayoutDialog(){
        dialog = new PayoutDialog(mContext ,Date ,RdMoney ,R.style.RdMoney_Dialog ,v);
        dialog.setContentView(R.layout.dialog_payoutitems);
        Init();
        btn_setOnClick();
        initListView();
        dialog.show();
    }
    private void initListView(){
        /*** 初始建立支出項目群放到listView */
        rdMoneyListItemses = new ArrayList<>();
        for(i=0;i<Itemlist.length;i++){
            rdMoneyListItemses.add(new RdMoneyListItems(Itemlist[i]));
        }
        RdMoneyListAdapter rdMoneyListAdapter = new RdMoneyListAdapter(mContext,
                R.layout.recordmoney_list_items, rdMoneyListItemses);
        payout_listview.setAdapter(rdMoneyListAdapter);
        /*** 點選後將值放入selectedItem */
        payout_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = Itemlist[position];
            }
        });
    }
    public void btn_setOnClick() {
        payout_ok_btn.setOnClickListener(this);
        payout_cancel_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.payout_ok_btn:
                String note = Note_EditText.getText().toString().trim();
                if(selectedItem == null){
                    Toast.makeText(mContext,mContext.getString(R.string.PlzSelectItem),Toast.LENGTH_SHORT).show();
                }else{
                    check = new SavePayoutData(mContext,Date,selectedItem,note,RdMoney).SaveToDB();
                    if (check > 0){
                        calculate();
                        dialog.dismiss();
                        Toast.makeText(mContext, mContext.getString(R.string.SaveSuccess), Toast.LENGTH_SHORT).show();
                        /** 更改RecordMoney.class的剩餘金額textView */
                        surplusMoney_textView.setText(Long.toString(CurrentMoney_long)+"元");
                    }
                }
                break;
            case R.id.payout_cancel_btn:
                dialog.dismiss();
                break;
        }
    }

    private void calculate(){
        RdMoney_long = Long.valueOf(RdMoney);
        CurrentMoney_long = getMoney() - RdMoney_long;
        saveMoney(CurrentMoney_long);
    }

    private long getMoney(){
        requestCurrentMoneyServer rCMS = new requestCurrentMoneyServer(mContext);
        CurrentMoney_long = rCMS.get_CurrentMoney();
        return CurrentMoney_long;
    }
    private void saveMoney( long currentMoney_long){
        CurrentMoney_str = Long.toString(currentMoney_long);
        SaveCurrentMoneyData saveCurrentMoneyData = new SaveCurrentMoneyData(mContext
                ,CurrentMoney_str);
        saveCurrentMoneyData.SaveToDB();
    }

    public void Init(){
        payout_ok_btn = (Button) dialog.findViewById(R.id.payout_ok_btn);
        payout_cancel_btn = (Button) dialog.findViewById(R.id.payout_cancel_btn);
        payout_listview = (ListView) dialog.findViewById(R.id.payout_listview);
        Note_EditText = (EditText) dialog.findViewById(R.id.Note_EditText);
        surplusMoney_textView = (TextView) v.findViewById(R.id.surplusMoney_textView);
    }
}
