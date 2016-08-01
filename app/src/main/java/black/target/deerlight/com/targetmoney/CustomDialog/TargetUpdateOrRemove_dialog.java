package black.target.deerlight.com.targetmoney.CustomDialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import black.target.deerlight.com.targetmoney.Constructs_class.DeleteTargetData;
import black.target.deerlight.com.targetmoney.Constructs_class.TargetShowList;
import black.target.deerlight.com.targetmoney.R;

/**
 * Created by samuel_hsieh on 15/10/5.
 */
public class TargetUpdateOrRemove_dialog extends AlertDialog implements DialogInterface{
    String targetTitle,targetDetail,targetMoney;
    Context context;
    View Item_view,TargetCreate_view;
    AlertDialog dialog;
    TargetUpdate_dialog targetUpdate_dialog;
    Builder builder = new Builder(getContext());
    TextView targetTitle_textView;
    TextView targetDetail_textView;
    TextView targetMoney_textView;

    public TargetUpdateOrRemove_dialog(Context context, View Item_view, View TargetCreate_view) {
        super(context);
        this.context = context;
        this.Item_view = Item_view;
        this.TargetCreate_view = TargetCreate_view;
    }
    /**
     * 刪除、取消、編輯，目標列表的dialog
     */
    public void TargetUpdateDialog(){
        Init();
        targetTitle = targetTitle_textView.getText().toString().trim();
        targetDetail = targetDetail_textView.getText().toString().trim();
        targetMoney = targetMoney_textView.getText().toString().trim();
        dialog = new TargetUpdateOrRemove_dialog(context,Item_view,TargetCreate_view);
        builder.setMessage(getContext().getString(R.string.Choice_UpdateDetail));
        builder.setTitle(getContext().getString(R.string.Choice_UpateTitle));
        builder.setPositiveButton(R.string.Update, new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                targetUpdate_dialog = new TargetUpdate_dialog(context, R.style.TargetUpdate_Dialog, TargetCreate_view);
                targetUpdate_dialog.setTargetTitle(targetTitle);
                targetUpdate_dialog.setTargetDetail(targetDetail);
                targetUpdate_dialog.setTargetMoney(targetMoney);
                targetUpdate_dialog.TargetUpdateDialog();
            }
        });
        builder.setNeutralButton(R.string.Remove, new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                DeleteTargetData deleteTargetData = new DeleteTargetData(context, targetTitle);
                deleteTargetData.DeleteData();
                showlist();
            }
        });
        builder.setNegativeButton(R.string.Cancel, new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
    }
    private void showlist(){
        TargetShowList targetShowList = new TargetShowList(context,TargetCreate_view);
        targetShowList.showList();
    }
    private void Init(){
        targetTitle_textView = (TextView) Item_view.findViewById(R.id.targetTitle);
        targetDetail_textView = (TextView) Item_view.findViewById(R.id.targetDetail);
        targetMoney_textView = (TextView) Item_view.findViewById(R.id.targetMoney);
    }
}
