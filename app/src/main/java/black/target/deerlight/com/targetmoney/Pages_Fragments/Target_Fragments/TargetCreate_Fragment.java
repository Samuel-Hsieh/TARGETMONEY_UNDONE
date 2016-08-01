package black.target.deerlight.com.targetmoney.Pages_Fragments.Target_Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import black.target.deerlight.com.targetmoney.Constructs_class.SaveTargetData;
import black.target.deerlight.com.targetmoney.R;


public class TargetCreate_Fragment extends Fragment {
    EditText targetName_Edit,targetDetail_Edit,targetMoney_Edit;
    Button TargetOk_btn;
    Spinner ColorSpinner;
    View v;
    String targetName;
    String targetDetail;
    String targetMoney;
    String targetColor;
    SaveTargetData saveTargetData;
    long check;
    private String[] Spinnerlist;
    private String[] Colorlist;
    private ArrayAdapter<String> listAdapter;
    boolean SpinnerFlag = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.targetcreate_fragment, container, false);
        /*** 宣告元件*/
        init();
        return v;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Spinnerlist = getResources().getStringArray(R.array.Spinnerlist);
        Colorlist = getResources().getStringArray(R.array.TargetColorlist);
        /*** Spinner選擇清單*/
        listAdapter = new ArrayAdapter<>(getActivity(), R.layout.custom_spinner, R.id.mySpinner, Spinnerlist);
        ColorSpinner.setAdapter(listAdapter);
        ColorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                targetColor = Colorlist[position];
                if(SpinnerFlag){    //使得剛CreateView時不會觸發Toast
                    Toast.makeText(getActivity(), "顏色為："+ Spinnerlist[position], Toast.LENGTH_SHORT).show();
                }else{
                    SpinnerFlag = true;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                Toast.makeText(getActivity(), "您沒有選擇任何項目", Toast.LENGTH_SHORT).show();
            }
        });
        /*** 確定按鈕*/
        TargetOk_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                targetName = targetName_Edit.getText().toString().trim();
                targetDetail = targetDetail_Edit.getText().toString().trim();
                targetMoney = targetMoney_Edit.getText().toString().trim();
                saveTargetData = new SaveTargetData(getActivity(), targetName, targetDetail, targetMoney, targetColor);
                if (targetName.isEmpty()) {
                    Toast.makeText(getActivity(), getString(R.string.isTitleEmpty), Toast.LENGTH_SHORT).show();
                } else {
                    if (targetDetail.isEmpty()) {
                        Toast.makeText(getActivity(), getString(R.string.isDetailEmpty), Toast.LENGTH_SHORT).show();
                    } else {
                        if (targetMoney.isEmpty() || Integer.valueOf(targetMoney) == 0) {
                            Toast.makeText(getActivity(), getString(R.string.isMoneyEmpty), Toast.LENGTH_SHORT).show();
                        } else {
                            check = saveTargetData.SaveToDB();
                            targetName_Edit.setText("");
                            targetDetail_Edit.setText("");
                            targetMoney_Edit.setText("");
                            if (check > 0) {
                                Toast.makeText(getActivity(), getString(R.string.SaveSuccess), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });
    }
    private void init(){
        targetName_Edit = (EditText) v.findViewById(R.id.targetName_Edit);
        targetDetail_Edit = (EditText) v.findViewById(R.id.targetDetail_Edit);
        targetMoney_Edit = (EditText) v.findViewById(R.id.targetMoney_Edit);
        TargetOk_btn = (Button) v.findViewById(R.id.TargetOk_btn);
        ColorSpinner = (Spinner) v.findViewById(R.id.ColorSpinner);
    }
}
