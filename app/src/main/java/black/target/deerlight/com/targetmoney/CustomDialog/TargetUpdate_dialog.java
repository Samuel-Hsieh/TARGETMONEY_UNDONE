package black.target.deerlight.com.targetmoney.CustomDialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import black.target.deerlight.com.targetmoney.Constructs_class.TargetShowList;
import black.target.deerlight.com.targetmoney.Constructs_class.UpdateTargetData;
import black.target.deerlight.com.targetmoney.R;

/**
 * Created by samuel_hsieh on 15/10/5.
 */
public class TargetUpdate_dialog extends Dialog implements View.OnClickListener{
    Context context;
    TargetUpdate_dialog targetUpdate_dialog;
    String targetTitle,targetDetail,targetMoney,targetColor;
    String Old_targetTitle;
    Button TargetOk_btn;
    Spinner ColorSpinner;
    EditText targetName_Edit,targetDetail_Edit,targetMoney_Edit;
    private String[] Colorlist;
    private String[] Spinnerlist;

    private ArrayAdapter<String> listAdapter;
    boolean SpinnerFlag = false;
    UpdateTargetData updateTargetData;
    long check;
    View TargetCreate_view;

    public TargetUpdate_dialog(Context context, int theme, View TargetCreate_view) {
        super(context, theme);
        this.context = context;
        this.TargetCreate_view = TargetCreate_view;
    }
    public void TargetUpdateDialog() {
        targetUpdate_dialog = new TargetUpdate_dialog(context,R.style.TargetUpdate_Dialog,TargetCreate_view);
        targetUpdate_dialog.setContentView(R.layout.dialog_targetupdate);
        Declare();
        Old_targetTitle = targetTitle;
        targetName_Edit.setText(targetTitle);
        targetDetail_Edit.setText(targetDetail);
        targetMoney_Edit.setText(targetMoney);
        Spinnerlist = context.getResources().getStringArray(R.array.Spinnerlist);
        Colorlist = context.getResources().getStringArray(R.array.TargetColorlist);
        /*** Spinner選擇清單*/
        listAdapter = new ArrayAdapter<>(context, R.layout.custom_spinner, R.id.mySpinner, Spinnerlist);
        ColorSpinner.setAdapter(listAdapter);
        ColorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                targetColor = Colorlist[position];
                if (SpinnerFlag) {    //使得剛CreateView時不會觸發Toast
                    Toast.makeText(context, "顏色為：" + Spinnerlist[position], Toast.LENGTH_SHORT).show();
                } else {
                    SpinnerFlag = true;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                Toast.makeText(context, "您沒有選擇任何項目", Toast.LENGTH_SHORT).show();
            }
        });
        btn_setOnClick();
        targetUpdate_dialog.show();
    }
    public void btn_setOnClick() {
        TargetOk_btn.setOnClickListener(this);
    }

    void Declare(){
        TargetOk_btn = (Button) targetUpdate_dialog.findViewById(R.id.TargetOk_btn);
        targetName_Edit = (EditText) targetUpdate_dialog.findViewById(R.id.targetName_Edit);
        targetDetail_Edit = (EditText) targetUpdate_dialog.findViewById(R.id.targetDetail_Edit);
        targetMoney_Edit = (EditText) targetUpdate_dialog.findViewById(R.id.targetMoney_Edit);
        ColorSpinner = (Spinner) targetUpdate_dialog.findViewById(R.id.ColorSpinner);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.TargetOk_btn:
                targetTitle = targetName_Edit.getText().toString().trim();
                targetDetail = targetDetail_Edit.getText().toString().trim();
                targetMoney = targetMoney_Edit.getText().toString().trim();
                updateTargetData = new UpdateTargetData(context, targetTitle, targetDetail, targetMoney, targetColor ,Old_targetTitle);
                if (targetTitle.isEmpty()) {
                    Toast.makeText(context, context.getString(R.string.isTitleEmpty), Toast.LENGTH_SHORT).show();
                } else {
                    if (targetDetail.isEmpty()) {
                        Toast.makeText(context, context.getString(R.string.isDetailEmpty), Toast.LENGTH_SHORT).show();
                    } else {
                        if (targetMoney.isEmpty() || Integer.valueOf(targetMoney) == 0) {
                            Toast.makeText(context, context.getString(R.string.isMoneyEmpty), Toast.LENGTH_SHORT).show();
                        } else {
                            check = updateTargetData.UpdateData();
                            if (check > 0) {
                                targetUpdate_dialog.dismiss();
                                showlist();
                                Toast.makeText(context, context.getString(R.string.SaveSuccess), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
                break;
        }

    }

    public void setTargetTitle(String targetTitle) {
        this.targetTitle = targetTitle;
    }

    public void setTargetDetail(String targetDetail) {
        this.targetDetail = targetDetail;
    }

    public void setTargetMoney(String targetMoney) {
        this.targetMoney = targetMoney;
    }
    private void showlist(){
        TargetShowList targetShowList = new TargetShowList(context,TargetCreate_view);
        targetShowList.showList();
    }
}
