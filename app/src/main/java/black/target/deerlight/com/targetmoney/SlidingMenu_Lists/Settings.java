package black.target.deerlight.com.targetmoney.SlidingMenu_Lists;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import black.target.deerlight.com.targetmoney.Activitys.CustomItemActivity;
import black.target.deerlight.com.targetmoney.Activitys.MoneySettingActivity;
import black.target.deerlight.com.targetmoney.Adapters.SettingListAdapter;
import black.target.deerlight.com.targetmoney.Constructs_class.SettingListItems;
import black.target.deerlight.com.targetmoney.R;
import black.target.deerlight.com.targetmoney.Services.requestCurrentMoneyServer;


/**
 * Created by samuel_hsieh on 15/9/22.
 */
public class Settings extends Fragment {

    View v;
    private ListView Setting_List;
    List<SettingListItems> settingListItems;
    String showMoney;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.setting_fragment, container, false);
        Setting_List = (ListView) v.findViewById(R.id.Setting_List);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*** 按下list做一些事情 */
        Setting_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 1: /** 跳轉金額設定頁面 */
                        intent.setClass(getActivity(), MoneySettingActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        break;
                    case 4:
                        break;
                    case 5: /** 跳轉自定項目頁面 */
                        intent.setClass(getActivity(), CustomItemActivity.class);
                        startActivity(intent);
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        showSettingList();
    }

    private String getMoney(){
        requestCurrentMoneyServer rCMS = new requestCurrentMoneyServer(getActivity());
        showMoney = Long.toString(rCMS.get_CurrentMoney());
        showMoney = showMoney+"元";
        return showMoney;
    }

    public void showSettingList() {
        /** 新增滑動抽屜的項目列表 */
        settingListItems = new ArrayList<>();
        settingListItems.add(new SettingListItems(getString(R.string.MoneySettingTitle)));
        settingListItems.add(new SettingListItems(getString(R.string.MoneySetting),R.drawable.settingmoney_icon,View.GONE,getMoney()));
        settingListItems.add(new SettingListItems(getString(R.string.BudgetSetting),R.drawable.budget_icon,View.GONE,"1000元"));
        settingListItems.add(new SettingListItems(getString(R.string.CustomItemTitle)));
        settingListItems.add(new SettingListItems(getString(R.string.PasswordSetting),R.drawable.password_icon,View.VISIBLE,""));
        settingListItems.add(new SettingListItems(getString(R.string.CustomItem),R.drawable.coustomitem_icon,View.GONE,""));
        settingListItems.add(new SettingListItems(getString(R.string.AboutUsTitle)));
        settingListItems.add(new SettingListItems(getString(R.string.AboutUs),R.drawable.team_icon,View.GONE,getString(R.string.Deerlight)));
        settingListItems.add(new SettingListItems(getString(R.string.GiveMeStar),R.drawable.getstar_icon,View.GONE,getString(R.string.Thanks)));
        settingListItems.add(new SettingListItems(getString(R.string.Share),R.drawable.share_icon,View.GONE,getString(R.string.ShareAPP)));
        settingListItems.add(new SettingListItems(getString(R.string.SupportUs),R.drawable.supportus_icon,View.GONE,getString(R.string.ClickAdmob)));
        SettingListAdapter settingListAdapter = new SettingListAdapter(getActivity(),settingListItems);
        Setting_List.setAdapter(settingListAdapter);
    }
}

