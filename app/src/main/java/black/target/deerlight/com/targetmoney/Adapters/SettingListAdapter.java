package black.target.deerlight.com.targetmoney.Adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import black.target.deerlight.com.targetmoney.Constructs_class.SettingListItems;
import black.target.deerlight.com.targetmoney.R;

/**
 * Created by samuel_hsieh on 2015/10/28.
 */
@SuppressWarnings("ResourceType")
public class SettingListAdapter extends ArrayAdapter<SettingListItems> {
    Context context;
    List<SettingListItems> settingListItems;

    public SettingListAdapter(Context context, List<SettingListItems> settingListItems) {
        super(context,R.layout.setting_items,settingListItems);
        this.context = context;
        this.settingListItems = settingListItems;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(!settingListItems.get(position).isListTitle()) {
            v = inflater.inflate(R.layout.setting_items, parent, false);
            TextView setting_item = (TextView) v.findViewById(R.id.setting_item_textView);
            TextView setting_subtitle = (TextView) v.findViewById(R.id.setting_subtitle_textView);
            ImageView setting_icon = (ImageView) v.findViewById(R.id.setting_icon);
            Switch setting_switch = (Switch) v.findViewById(R.id.setting_switch);
            SettingListItems settingItem = settingListItems.get(position);
            setting_item.setText(settingItem.getItem());
            setting_icon.setBackgroundResource(settingItem.getIcon());
            setting_switch.setVisibility(settingItem.getisInvisible());
            setting_subtitle.setText(settingItem.getSubtitle());
        }
        else {
            v = inflater.inflate(R.layout.setting_itemtitle, parent, false);
            TextView setting_itemtitle = (TextView) v.findViewById(R.id.setting_itemtitle_textView);
            SettingListItems settingItem = settingListItems.get(position);
            setting_itemtitle.setText(settingItem.getItem());
        }
        return v;
    }
}
