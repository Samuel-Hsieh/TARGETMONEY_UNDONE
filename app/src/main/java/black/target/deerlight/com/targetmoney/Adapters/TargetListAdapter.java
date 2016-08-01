package black.target.deerlight.com.targetmoney.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import black.target.deerlight.com.targetmoney.Constructs_class.TargetListItems;
import black.target.deerlight.com.targetmoney.R;

/**
 * Created by samuel_hsieh on 15/10/5.
 */
public class TargetListAdapter extends ArrayAdapter<TargetListItems>{
    Context context;
    int resource;
    List<TargetListItems> targetListItems;

    public TargetListAdapter(Context context, int resource, List<TargetListItems> targetListItems) {
        super(context, resource, targetListItems);
        this.context = context;
        this.resource = resource;
        this.targetListItems = targetListItems;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context, resource , null);
        TextView targetCount = (TextView) v.findViewById(R.id.targetCount);
        TextView targetTitle = (TextView) v.findViewById(R.id.targetTitle);
        TextView targetDetail = (TextView) v.findViewById(R.id.targetDetail);
        TextView currentMoney = (TextView) v.findViewById(R.id.currentMoney);
        TextView targetMoney = (TextView) v.findViewById(R.id.targetMoney);
        LinearLayout target_list_layout = (LinearLayout) v.findViewById(R.id.target_list_layout);

        TargetListItems ListItems = targetListItems.get(position);
        targetCount.setText(Integer.toString(ListItems.getTargetCount()+1)); //讓ListView從1開始呈現
        targetTitle.setText(ListItems.getTargetTitle());
        targetDetail.setText(ListItems.getTargetDetail());
        currentMoney.setText(Integer.toString(ListItems.getCurrentMoney())+"/");
        targetMoney.setText(Long.toString(ListItems.getTargetMoney()));
        target_list_layout.setBackgroundColor(Color.parseColor(ListItems.getTargetColor()));
        return v;
    }
}
