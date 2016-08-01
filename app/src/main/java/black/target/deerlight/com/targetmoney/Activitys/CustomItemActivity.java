package black.target.deerlight.com.targetmoney.Activitys;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import black.target.deerlight.com.targetmoney.R;

public class CustomItemActivity extends AppCompatActivity {
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customitem_activity);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.settingsActionBarColor));
        Init();
        title.setText(getString(R.string.CustomItem));
    }
    private void Init(){
        title = (TextView) findViewById(R.id.action_bar_title);
    }

}
