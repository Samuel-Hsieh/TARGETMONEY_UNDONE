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
import black.target.deerlight.com.targetmoney.Constructs_class.SaveIncomeData;
import black.target.deerlight.com.targetmoney.R;
import black.target.deerlight.com.targetmoney.Services.requestCurrentMoneyServer;

/**
 * Created by samuel_hsieh on 15/8/26.
 */
public class IncomeDialog extends Dialog implements View.OnClickListener{
    Context mContext;
    IncomeDialog dialog;
    Button income_ok_btn,income_cancel_btn;
    EditText Note_EditText;
    TextView surplusMoney_textView;
    ListView income_listview;
    List<RdMoneyListItems> rdMoneyListItemses;
    String Itemlist[] = {"薪水","獎金","禮金","零用金","補助款"};
    String selectedItem;
    String Date;
    String RdMoney;
    String CurrentMoney_str;
    long RdMoney_long,CurrentMoney_long;
    long check;
    int i;
    View v;

    public IncomeDialog(Context context, String Date, String RdMoney, int theme ,View v) {
        super(context, theme);
        this.mContext=context;
        this.Date = Date;
        this.RdMoney = RdMoney;
        this.v = v;
    }
    public void InitIncomeDialog(){
        dialog = new IncomeDialog(mContext ,Date ,RdMoney ,R.style.RdMoney_Dialog ,v);
        dialog.setContentView(R.layout.dialog_incomeitems);
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
        income_listview.setAdapter(rdMoneyListAdapter);
        /*** 點選後將值放入selectedItem */
        income_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = Itemlist[position];
            }
        });
    }
    public void btn_setOnClick(){
        income_ok_btn.setOnClickListener(this);
        income_cancel_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.income_ok_btn:
                String note = Note_EditText.getText().toString().trim();
                if(selectedItem == null){
                    Toast.makeText(mContext,mContext.getString(R.string.PlzSelectItem),Toast.LENGTH_SHORT).show();
                }else{
                    check = new SaveIncomeData(mContext,Date,selectedItem,note,RdMoney).SaveToDB();
                    if (check > 0){
                        calculate();
                        dialog.dismiss();
                        Toast.makeText(mContext, mContext.getString(R.string.SaveSuccess), Toast.LENGTH_SHORT).show();
                        /** 更改RecordMoney.class的剩餘金額textView */
                        surplusMoney_textView.setText(Long.toString(CurrentMoney_long)+"元");
                    }
                }
                break;
            case R.id.income_cancel_btn:
                dialog.dismiss();
                break;
        }
    }

    private void calculate(){
        RdMoney_long = Long.valueOf(RdMoney);
        CurrentMoney_long = getMoney() + RdMoney_long;
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
        income_ok_btn = (Button) dialog.findViewById(R.id.income_ok_btn);
        income_cancel_btn = (Button) dialog.findViewById(R.id.income_cancel_btn);
        income_listview = (ListView) dialog.findViewById(R.id.income_listview);
        Note_EditText = (EditText) dialog.findViewById(R.id.Note_EditText);
        surplusMoney_textView = (TextView) v.findViewById(R.id.surplusMoney_textView);
    }
}
