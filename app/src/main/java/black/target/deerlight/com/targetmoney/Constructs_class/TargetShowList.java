package black.target.deerlight.com.targetmoney.Constructs_class;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import black.target.deerlight.com.targetmoney.Adapters.TargetListAdapter;
import black.target.deerlight.com.targetmoney.R;
import black.target.deerlight.com.targetmoney.Services.requestTargetServer;

/**
 * Created by samuel_hsieh on 2015/11/7.
 */
public class TargetShowList {
    String[] targetItem = {"_targetName","_targetDetail","_targetMoney","_targetColor"};
    ListView TargetItems_list;
    List<TargetListItems> targetListItemses;
    View view;
    Context context;

    public TargetShowList(Context context ,View view) {
        this.context = context;
        this.view = view;
    }
    public void showList(){
        targetListItemses = new ArrayList<>();
        /*** 從server取得資料並回傳 */
        requestTargetServer rTS = new requestTargetServer(context,targetItem,targetListItemses);
        targetListItemses = rTS.get_target();
        /*** 資料放進ListView呈現 */
        TargetListAdapter targetAdapter = new TargetListAdapter(context,
                R.layout.target_items ,targetListItemses);
        TargetItems_list = (ListView) view.findViewById(R.id.TargetItems_List);
        TargetItems_list.setAdapter(targetAdapter);
    }
}
