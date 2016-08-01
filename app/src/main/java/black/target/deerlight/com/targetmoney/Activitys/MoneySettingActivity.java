package black.target.deerlight.com.targetmoney.Activitys;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import black.target.deerlight.com.targetmoney.Constructs_class.SaveCurrentMoneyData;
import black.target.deerlight.com.targetmoney.R;
import black.target.deerlight.com.targetmoney.Services.requestCurrentMoneyServer;

public class MoneySettingActivity extends AppCompatActivity {

    private TextView title;
    private TextView CurrentMoney_textView;
    private EditText Setting_CurrentMoney;
    private Button Setting_CurrentMoney_btn;
    long check;
    String showMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.money_setting_activity);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.settingsActionBarColor));
        Init();
        title.setText(getString(R.string.MoneySetting));
        CurrentMoney_textView.setText(getMoney());
        Setting_CurrentMoney_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CurrentMoney = Setting_CurrentMoney.getText().toString().trim();
                SaveCurrentMoneyData saveCurrentMoneyData = new SaveCurrentMoneyData(getApplicationContext()
                        ,CurrentMoney);
                if(CurrentMoney.isEmpty()){
                    Toast.makeText(getApplicationContext(), getString(R.string.isMoneyEmpty), Toast.LENGTH_SHORT).show();
                }
                else {
                    check = saveCurrentMoneyData.SaveToDB();
                    Setting_CurrentMoney.setText("");
                    if (check > 0) {
                        CurrentMoney_textView.setText(getMoney());
                        Toast.makeText(getApplicationContext(), getString(R.string.SaveSuccess), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void Init(){
        title = (TextView) findViewById(R.id.action_bar_title);
        Setting_CurrentMoney_btn = (Button) findViewById(R.id.Setting_CurrentMoney_btn);
        CurrentMoney_textView = (TextView) findViewById(R.id.CurrentMoney_textView);
        Setting_CurrentMoney = (EditText) findViewById(R.id.Setting_CurrentMoney_editText);
    }
    private String getMoney(){
        requestCurrentMoneyServer rCMS = new requestCurrentMoneyServer(getApplicationContext());
        showMoney = Long.toString(rCMS.get_CurrentMoney());
        showMoney = showMoney+"元";
        return showMoney;
    }
}
