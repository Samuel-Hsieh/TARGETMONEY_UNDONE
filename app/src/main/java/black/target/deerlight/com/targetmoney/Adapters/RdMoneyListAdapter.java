package black.target.deerlight.com.targetmoney.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import black.target.deerlight.com.targetmoney.Constructs_class.RdMoneyListItems;
import black.target.deerlight.com.targetmoney.R;

/**
 * Created by samuel_hsieh on 15/10/5.
 */
public class RdMoneyListAdapter extends ArrayAdapter<RdMoneyListItems>{
    Context context;
    int resource;
    List<RdMoneyListItems> RdMoneyListItems;

    public RdMoneyListAdapter(Context context, int resource, List<RdMoneyListItems> RdMoneyListItems) {
        super(context, resource, RdMoneyListItems);
        this.context = context;
        this.resource = resource;
        this.RdMoneyListItems = RdMoneyListItems;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context, resource , null);
        TextView RdMoney_item = (TextView) v.findViewById(R.id.RdMoney_item);

        RdMoneyListItems ListItems = RdMoneyListItems.get(position);
        RdMoney_item.setText(ListItems.getRdMoneyItem());
        return v;
    }
}
