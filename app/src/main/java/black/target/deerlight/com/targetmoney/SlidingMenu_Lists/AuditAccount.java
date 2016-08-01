package black.target.deerlight.com.targetmoney.SlidingMenu_Lists;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import black.target.deerlight.com.targetmoney.Adapters.FragmentsPagerAdapter;
import black.target.deerlight.com.targetmoney.Pages_Fragments.AuditAccounts_Fragments.AuditAccountPayout_Fragment;
import black.target.deerlight.com.targetmoney.Pages_Fragments.AuditAccounts_Fragments.AuditAccountIncome_Fragment;
import black.target.deerlight.com.targetmoney.R;


/**
 * Created by samuel_hsieh on 15/9/22.
 */
public class AuditAccount extends Fragment implements ViewPager.OnPageChangeListener
        ,TabHost.OnTabChangeListener{
    private ViewPager auditaccount_viewpager;
    private TabHost auditaccount_tabHost;
    private FragmentsPagerAdapter FragmentPagerAdapter;
    View v;
    int i = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.auditaccount_fragment,container,false);
        initTabHost();
        initViewPager();
        return v;
    }
    public class FakeContent implements TabHost.TabContentFactory{

        Context context;
        public FakeContent(Context context) {
            this.context = context;
        }
        @Override
        public View createTabContent(String tag) {
            View fakeview = new View(context);
            fakeview.setMinimumHeight(0); //設定view的最小高度
            fakeview.setMinimumWidth(0); //設定view的最小寬度
            return fakeview;
        }
    }
    private void initTabHost(){
        auditaccount_tabHost = (TabHost) v.findViewById(R.id.auditaccount_tabHost);
        auditaccount_tabHost.setup();
        final String[] tabNames = {getString(R.string.Payout), getString(R.string.Income)};
        for(i = 0;i<tabNames.length;i++){
            TabHost.TabSpec tabSpec;
            tabSpec = auditaccount_tabHost.newTabSpec(tabNames[i]);
            tabSpec.setIndicator(tabNames[i]);
            tabSpec.setContent(new FakeContent(getActivity()));
            auditaccount_tabHost.addTab(tabSpec);
            /** 更改Tab title上的字體*/
            TabWidget tw = (TabWidget) auditaccount_tabHost.findViewById(android.R.id.tabs);
            View tabView = tw.getChildTabViewAt(i);
            TextView tv = (TextView) tabView.findViewById(android.R.id.title);
            tv.setTextAppearance(getActivity(), R.style.TabTextStyle);
        }
        auditaccount_tabHost.setOnTabChangedListener(this);
    }
    private void initViewPager() {
        List<Fragment> fragmentLists = new ArrayList<>();
        fragmentLists.add(new AuditAccountPayout_Fragment());
        fragmentLists.add(new AuditAccountIncome_Fragment());
        this.FragmentPagerAdapter = new FragmentsPagerAdapter(
                getChildFragmentManager(),fragmentLists);
        this.auditaccount_viewpager = (ViewPager) v.findViewById(R.id.auditaccount_viewpager);
        this.auditaccount_viewpager.setAdapter(this.FragmentPagerAdapter);
        this.auditaccount_viewpager.addOnPageChangeListener(this); //replace setOnPageChangeListener with this method
    }
    /*Tab Listener*/
    @Override
    public void onTabChanged(String tabId) {
        int selectedItem = auditaccount_tabHost.getCurrentTab();
        auditaccount_viewpager.setCurrentItem(selectedItem);
        HorizontalScrollView HscrollView = (HorizontalScrollView) v.findViewById(R.id.auditaccount_HscrollView);
        View tabView = auditaccount_tabHost.getCurrentTabView();
        int scorllPos = tabView.getLeft() - (HscrollView.getWidth() - tabView.getWidth()) / 2;
        HscrollView.smoothScrollTo(scorllPos, 0);
    }
    /*ViewPager Listener*/
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int selectedItem = auditaccount_viewpager.getCurrentItem();
        auditaccount_tabHost.setCurrentTab(selectedItem);
    }

    @Override
    public void onPageSelected(int selectedItem) {
        auditaccount_viewpager.setCurrentItem(selectedItem);
    }
}
