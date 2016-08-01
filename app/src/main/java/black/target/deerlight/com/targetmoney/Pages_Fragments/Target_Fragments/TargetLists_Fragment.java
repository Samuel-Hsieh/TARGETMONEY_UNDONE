package black.target.deerlight.com.targetmoney.Pages_Fragments.Target_Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import black.target.deerlight.com.targetmoney.Constructs_class.TargetShowList;
import black.target.deerlight.com.targetmoney.CustomDialog.TargetUpdateOrRemove_dialog;
import black.target.deerlight.com.targetmoney.R;

public class TargetLists_Fragment extends Fragment {

    private View v ;
    ListView TargetItems_list;
    private boolean isViewShown = false;
    TargetUpdateOrRemove_dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.targetlists_fragment, container, false);
        TargetItems_list = (ListView) v.findViewById(R.id.TargetItems_List);
        if (!isViewShown) {
            showList();
        }
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*** 按下list做一些事情 */
        TargetItems_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog = new TargetUpdateOrRemove_dialog(getActivity(),view ,v);
                dialog.TargetUpdateDialog();
            }
        });
    }

    /*** 回到此fragment時觸發,判斷getView有拿到值才觸發 */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getView() != null) {
            isViewShown = true;
            showList();
        } else {
            isViewShown = false;
        }
    }

    public void showList(){
        TargetShowList targetShowList = new TargetShowList(getActivity(),v);
        targetShowList.showList();
    }
}
