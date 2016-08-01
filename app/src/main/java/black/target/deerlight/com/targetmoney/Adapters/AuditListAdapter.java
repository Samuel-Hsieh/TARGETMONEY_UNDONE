package black.target.deerlight.com.targetmoney.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import black.target.deerlight.com.targetmoney.Constructs_class.AuditListItems;
import black.target.deerlight.com.targetmoney.R;

/**
 * Created by samuel_hsieh on 15/10/5.
 */
public class AuditListAdapter extends ArrayAdapter<AuditListItems>{
    Context context;
    int resource;
    List<AuditListItems> auditListItems;

    public AuditListAdapter(Context context, int resource, List<AuditListItems> auditListItems) {
        super(context, resource, auditListItems);
        this.context = context;
        this.resource = resource;
        this.auditListItems = auditListItems;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context, resource , null);
        TextView Audit_count = (TextView) v.findViewById(R.id.Audit_count);
        TextView Audit_item = (TextView) v.findViewById(R.id.Audit_item);
        TextView Audit_percent = (TextView) v.findViewById(R.id.Audit_percent);
        TextView Audit_money = (TextView) v.findViewById(R.id.Audit_money);

        AuditListItems ListItems = auditListItems.get(position);
        Audit_count.setText(Integer.toString(ListItems.getAuditCount())+".");
        Audit_item.setText(ListItems.getAuditItem());
        Audit_percent.setText(Double.toString(ListItems.getAuditPercent())+"%");
        Audit_money.setText(Long.toString(ListItems.getAuditMoney())+"元");
        return v;
    }
}
