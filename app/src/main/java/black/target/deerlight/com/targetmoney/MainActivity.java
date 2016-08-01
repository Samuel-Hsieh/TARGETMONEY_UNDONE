package black.target.deerlight.com.targetmoney;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import black.target.deerlight.com.targetmoney.Adapters.SlidingListAdapter;
import black.target.deerlight.com.targetmoney.Constructs_class.SlidingItems;
import black.target.deerlight.com.targetmoney.SlidingMenu_Lists.AuditAccount;
import black.target.deerlight.com.targetmoney.SlidingMenu_Lists.RecordMoney;
import black.target.deerlight.com.targetmoney.SlidingMenu_Lists.Settings;
import black.target.deerlight.com.targetmoney.SlidingMenu_Lists.Targets;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    RelativeLayout drawerPane;
    ListView lvNav;
    TextView title;
    List<SlidingItems> listNavItems;
    ArrayList<android.support.v4.app.Fragment> listFragments;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*** custom 寫action bar 主要修改成置中*/
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*** 宣告元件*/
        Init();
        /** 新增滑動抽屜的項目列表 */
        listNavItems = new ArrayList<>();
        listNavItems.add(new SlidingItems("記帳", R.drawable.sliding_item_rdmoney_bg));
        listNavItems.add(new SlidingItems("查帳", R.drawable.sliding_item_auditauccont_bg));
        listNavItems.add(new SlidingItems("目標", R.drawable.sliding_item_target_bg));
        listNavItems.add(new SlidingItems("設定", R.drawable.sliding_item_setting_bg));
        SlidingListAdapter navListAdater = new SlidingListAdapter(getApplicationContext(), R.layout.item_nv_list ,listNavItems);
        lvNav.setAdapter(navListAdater);
        listFragments = new ArrayList<>();
        listFragments.add(new RecordMoney());
        listFragments.add(new AuditAccount());
        listFragments.add(new Targets());
        listFragments.add(new Settings());
        // Load first fragment as default:
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_content,listFragments.get(0)).commit();
        title.setText(listNavItems.get(0).getTitle());
        lvNav.setItemChecked(0, true); //預設listView選中的項目
        drawerLayout.closeDrawer(drawerPane); //預設關閉左側選單
        //set listener fot navigation items
        lvNav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //replace the fragment with the selection correspondingly
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_content, listFragments.get(position))
                        .commit();
                /*** 設定ActionBar顏色*/
                switch (position){
                    case 0:
                        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.colorPrimary));
                        break;
                    case 1:
                        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.AuditAccountActionBarColor));
                        break;
                    case 2:
                        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.targetActionBarColor));
                        break;
                    case 3:
                        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.settingsActionBarColor));
                        break;
                }
                title.setText(listNavItems.get(position).getTitle());
                lvNav.setItemChecked(position, true);
                drawerLayout.closeDrawer(drawerPane);
            }
        });

        //create listener for drawer layout
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout
                ,R.string.drawer_opened,R.string.drawer_closed) {
            @Override
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu(); //更新menu
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                invalidateOptionsMenu();
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    private void Init(){
        title = (TextView) findViewById(R.id.action_bar_title);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerPane = (RelativeLayout) findViewById(R.id.drawer_pane);
        lvNav = (ListView) findViewById(R.id.nav_list);
    }
}
