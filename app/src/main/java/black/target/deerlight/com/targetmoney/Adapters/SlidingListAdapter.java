package black.target.deerlight.com.targetmoney.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import black.target.deerlight.com.targetmoney.Constructs_class.SlidingItems;
import black.target.deerlight.com.targetmoney.R;

/**
 * Created by samuel_hsieh on 15/9/22.
 */
public class SlidingListAdapter extends ArrayAdapter<SlidingItems> {

    Context context;
    int resLayout;
    List<SlidingItems> listNavItem;

    public SlidingListAdapter(Context context, int resLayout, List<SlidingItems> listNavItem) {
        super(context, resLayout, listNavItem);
        this.context = context;
        this.resLayout = resLayout;
        this.listNavItem = listNavItem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context, resLayout , null);
        TextView tvTitle = (TextView) v.findViewById(R.id.title);
        ImageView navIcon = (ImageView) v.findViewById(R.id.nac_icon);
        LinearLayout list_layout = (LinearLayout) v.findViewById(R.id.list_layout);

        SlidingItems navItem = listNavItem.get(position);
        tvTitle.setText(navItem.getTitle());
        navIcon.setImageResource(navItem.getResIcon());
        list_layout.setBackgroundResource(navItem.getColor());

        return v;
    }
}
