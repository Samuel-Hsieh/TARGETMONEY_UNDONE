package black.target.deerlight.com.targetmoney.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by samuel_hsieh on 15/9/21.
 */
/** 所有Fragment都經過此class選配＾＿＾*/

public class FragmentsPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentlists;

    public FragmentsPagerAdapter(FragmentManager fm, List<Fragment> fragmentlists) {
        super(fm);
        this.fragmentlists = fragmentlists;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentlists.get(position);
    }

    @Override
    public int getCount() {
        return fragmentlists.size();
    }
}
